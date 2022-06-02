package r97.lucy.line.webhook

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class WebhookEvent(
    val type: String = "",
    val timestamp: Long = 0L,
    val source: Object = Object(),
    val webhookEventId: String = "",
    @JsonProperty("deliveryContext.isRedelivery")
    val isRedelivery: Boolean = false,
    val mode: String = "",

    // === メッセージイベント ==
    // Todo:イベントの種別ごとにクラスがほしい
    val replyToken: String = "",
    // Todo:Textメッセージのみ対応
    val message: TextMessage = TextMessage()


    ) {
    fun isMessageEvent(): Boolean {
        return this.type == "message"
    }
}
