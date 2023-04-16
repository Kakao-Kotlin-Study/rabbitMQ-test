import com.rabbitmq.client.Channel
import com.rabbitmq.client.ConnectionFactory

private const val QUEUE_NAME = "helloQueue"

fun main() {
    val factory: ConnectionFactory = ConnectionFactory()
    factory.setHost("localhost")

    factory.newConnection().use {
        val channel: Channel = it.createChannel()

        channel.queueDeclare(QUEUE_NAME,
            false,
            false,
            false,
            null
        )

        val message = "Hello World!"
        channel.basicPublish("",
            QUEUE_NAME,
            null,
            message.toByteArray())

        println(" [x] Sent '$message'");
    }
}
