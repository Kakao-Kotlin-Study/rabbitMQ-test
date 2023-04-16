import com.rabbitmq.client.*
import kotlin.text.Charsets.UTF_8

private const val QUEUE_NAME = "helloQueue"

fun main() {
    val factory: ConnectionFactory = ConnectionFactory()
    factory.host = "localhost"

    factory.newConnection().use{
        val connection: Connection = factory.newConnection()
        val channel: Channel = connection.createChannel()

        channel.queueDeclare(QUEUE_NAME,
            false,
            false,
            false,
            null)
        println(" [*] Waiting for messages. To exit press CTRL+C");

        val deliverCallback: DeliverCallback = DeliverCallback{ consumeTag: String, delivery: Delivery ->
            val message = String(delivery.body, UTF_8)
            println(" [x] Received '" + message + "'");
        }

        channel.basicConsume(QUEUE_NAME, true, deliverCallback, {consumerTag ->

        })
    }
}