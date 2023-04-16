import com.rabbitmq.client.*
import kotlin.text.Charsets.UTF_8

private const val QUEUE_NAME = "helloQueue"

fun main() {
    val factory: ConnectionFactory = ConnectionFactory()
    factory.host = "localhost"

    val connection: Connection = factory.newConnection()    // Connection 생성
    val channel: Channel = connection.createChannel()   // Channel 생성

    // connection, channel을 try-with-resources를 사용하면
    // Consumer가 메시지를 수신할 때까지 대기하지 않고 종료됨.
    channel.queueDeclare(QUEUE_NAME,    // Consume할 Queue 선언
        false,  // durable
        false,  // exclusive
        false,  // actoDelete
        null    // arguments
    )

    println(" [*] Waiting for messages. To exit press CTRL+C");

    val deliverCallback: DeliverCallback
        = DeliverCallback{ consumeTag: String, delivery: Delivery ->
            val message = String(delivery.body, UTF_8)
            println(" [x] Received '" + message + "'");
        }

    // 비동기적으로 메시지를 수신 대기
    // 메시지가 전달되면 deliverCallback이 실행됨
    channel.basicConsume(QUEUE_NAME,
        true,   // autoAct
        deliverCallback,    // deliverCallback
        {consumerTag -> }   // cancelCallback
    )
}