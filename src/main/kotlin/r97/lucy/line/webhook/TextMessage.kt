package r97.lucy.line.webhook

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class TextMessage(
    val id: String = "",
    val type: String = "",
    val text: String = "",
) {

    fun isTextMessage(): Boolean {
        return this.type == "text"
    }
}
