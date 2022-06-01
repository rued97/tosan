package r97.lucy.line.api

import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.slf4j.LoggerFactory

class MessagePushTextClient(
    override val channelAccessToken: String,
    val to: String
): MessagingAPIClient() {

    override val messagingAPI = MessagingAPI.MESSAGE_PUSH

    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)
    }

    private val textList = mutableListOf<String>()

    fun add(text: String): MessagePushTextClient {
        this.textList.add(text)
        return this
    }

    override fun body(): RequestBody {
        val body = """
            {
                "to":"${this.to}",
                "messages":[
                    ${this.textList.map { """{"type":"text","text":"${it}"}""" }.joinToString(",")}
                ]
            }
        """
        logger.debug("BODY : [$body]")
        return body.toRequestBody()
    }
}