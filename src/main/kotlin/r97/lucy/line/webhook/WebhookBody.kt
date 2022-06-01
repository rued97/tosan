package r97.lucy.line.webhook

import com.fasterxml.jackson.databind.ObjectMapper

data class WebhookBody(
    val destination: String,
    val events: String
) {
    companion object {
        fun from(requestBody: String): WebhookBody {
            return ObjectMapper().readValue(requestBody, WebhookBody::class.java)
        }
    }
}