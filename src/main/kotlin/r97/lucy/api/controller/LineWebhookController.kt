package r97.lucy.api.controller

import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import r97.lucy.api.config.LineConfig
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
    fun receive(@RequestBody requestBody: String?): String {
        val header = WebhookHeader.from(this.request)
        logger.debug("署名 : ${header.xLineSignature}")
        logger.debug("本体 : ${requestBody}")
        return ""
    }

}
