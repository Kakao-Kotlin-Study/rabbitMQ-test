import com.rabbitmq.client.ConnectionFactory

private const val QUEUE_NAME = "helloQueue"

fun main() {
    val factory: ConnectionFactory = ConnectionFactory()
    factory.setHost("localhost")    // 로컬 머신의 RabbitMQ 노드에 연결

    // Connection과 Channel은 java.lang.AutoCloseable을 구현.
    // 따라서 Try-with-resources를 사용할 수 있음
    factory.newConnection().use {connection ->    // Connection 생성
        connection.createChannel().use {channel ->  // Channel 생성
            // Queue 선언
            channel.queueDeclare(QUEUE_NAME,    // queue
                false,  // durable
                false,  // exclusive
                false,  // autoDelete
                null    // arguments
            )

            // 메시지 Publish
            val message = "안녕 우기 제리!"
            channel.basicPublish("",    // exchange
                QUEUE_NAME, // routingKey
                null,   // props
                message.toByteArray()   // body
            )

            println(" [x] Sent '$message'");
        }
    }
}