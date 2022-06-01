package r97.lucy.line.api

import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.slf4j.LoggerFactory

class MessageReplyClient(
    override val channelAccessToken: String,
    val replyToken: String
): MessagingAPIClient() {

    override val messagingAPI = MessagingAPI.MESSAGE_REPLY

    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)
    }

    private val textList = mutableListOf<String>()

    fun add(text: String): MessageReplyClient {
        this.textList.add(text)
        return this
    }

    override fun body(): RequestBody {
        val body = """
            {
                "replyToken":"${this.replyToken}",
                "messages":[
                    ${this.textList.map { """{"type":"text","text":"${it}"}""" }.joinToString(",")}
                ]
            }
        """
        logger.debug("BODY : [$body]")
        return body.toRequestBody()
    }
}