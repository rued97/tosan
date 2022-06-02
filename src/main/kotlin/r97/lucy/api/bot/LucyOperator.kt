package r97.lucy.api.bot

import org.springframework.stereotype.Service
import r97.lucy.api.config.LineConfig
import r97.lucy.line.api.MessageReplyClient
import r97.lucy.line.webhook.TextMessage
import r97.lucy.line.webhook.WebhookBody
import r97.lucy.line.webhook.WebhookEvent

@Service
class LucyOperator(
    val lineConfig: LineConfig
) {

    fun receive(body: WebhookBody) {
        body.events
            .filter { it.isMessageEvent() }
            .forEach { this.execute(it)}
    }

    private fun execute(event: WebhookEvent) {
        // TODO: クラス化
        if(!event.message.isTextMessage()) {
            return;
        }
        if(!this.isToLucyMessage(event.message)) {
            return;
        }
        MessageReplyClient(this.lineConfig.channelAccessToken, event.replyToken)
            .add("""
                呼びましたか？
                まだな～んにも実装されてませんよ。
                ハロー世界！
            """.trimIndent().replace("\r\n", "\n"))
            .execute()
    }

    private fun isToLucyMessage(message: TextMessage): Boolean {
        return message.text.startsWith("ルーシー")
    }
}
