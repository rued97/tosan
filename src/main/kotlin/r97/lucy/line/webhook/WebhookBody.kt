package r97.lucy.line.webhook

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

@JsonIgnoreProperties(ignoreUnknown=true)
data class WebhookBody(
    val destination: String = "",
    val events: List<WebhookEvent> = emptyList()
) {
    companion object {
        fun from(requestBody: String): WebhookBody {
            if(requestBody.isEmpty()) {
                return WebhookBody()
            }
            return jacksonObjectMapper().readValue(requestBody, WebhookBody::class.java)
        }
    }
}
