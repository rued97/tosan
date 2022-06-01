package r97.lucy.api.controller

import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import r97.lucy.api.config.LineConfig
import r97.lucy.line.api.MessageReplyClient
import r97.lucy.line.secret.SignatureCreator
import r97.lucy.line.webhook.WebhookBody
import r97.lucy.line.webhook.WebhookHeader
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("/line/webhook")
class LineWebhookController(
    val request: HttpServletRequest,
    val lineConfig: LineConfig
) {

    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)
    }

    @PostMapping
    fun receive(@RequestBody requestBody: String = ""): String {
        val header = WebhookHeader.from(this.request)
        try {
            this.verifyAndThrowIfInValid(header, requestBody)
        } catch (e: Exception) {
            logger.warn("署名の検証に失敗しました。", e)
            logger.warn("署名 : ${header.xLineSignature}")
            logger.warn("本体 : ${requestBody}")
            // 署名検証の結果を通知しない。
            return ""
        }
        logger.debug("署名 : ${header.xLineSignature}")
        logger.debug("本体 : ${requestBody}")
        logger.debug("署名の検証に成功しました。")

        try {
            val body = WebhookBody.from(requestBody)
            MessageReplyClient(this.lineConfig.channelAccessToken, body.events[0].replyToken)
                .add("お返事のテスト。こんにちわ世界！")
                .execute();
        } catch (e: Exception) {
            logger.warn("処理に失敗しました。", e)
            // 処理成功の結果を通知しない。
            return ""
        }

        return ""
    }

    private fun verifyAndThrowIfInValid(header: WebhookHeader, body: String) {
        if(header.xLineSignature.isEmpty() || body.isEmpty()) {
            throw Exception("署名または本文が不正です。")
        }
        val signature = SignatureCreator(this.lineConfig.channelSecret, body).create()
        if(header.xLineSignature != signature) {
            throw Exception("署名または本文が不正です。")
        }
    }
}
