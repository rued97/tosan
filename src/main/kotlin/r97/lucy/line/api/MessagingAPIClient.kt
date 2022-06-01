package r97.lucy.line.api

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import org.slf4j.LoggerFactory
import org.springframework.http.HttpMethod

abstract class MessagingAPIClient {

    abstract val channelAccessToken: String
    abstract val messagingAPI: MessagingAPI

    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)
    }

    open fun url(): String {
        return this.messagingAPI.url
    }

    abstract fun body(): RequestBody

    fun execute(): String {
        val response = OkHttpClient()
            .newCall(this.request()).execute()

        val request = response.request
        logger.debug("REQUEST : URL = [${request.url}] METHOD = [${request.method}]")

        val message = response.body?.string().orEmpty()
        logger.debug("RESPONSE : CODE = [${response.code}] MESSAGE = [${message}]")

        return message
    }

    private fun requestBuilder(): Request.Builder {
        return Request.Builder()
            .url(this.url())
            .header("Content-Type", "application/json")
            .header("Authorization", "Bearer ${channelAccessToken}")
    }

    private fun request(): Request {
        return when(this.messagingAPI.method) {
            HttpMethod.GET -> this.requestBuilder().get().build()
            HttpMethod.POST -> this.requestBuilder().post(this.body()).build()
            else -> throw Exception("Enumに想定外の値が設定されています。 METHOD = [${this.messagingAPI.method}]")
        }
    }

}
