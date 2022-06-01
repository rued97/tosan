package r97.lucy.line.webhook

import javax.servlet.http.HttpServletRequest

data class WebhookHeader(
    val xLineSignature: String
) {
    companion object {
        fun from(requestHeader: HttpServletRequest): WebhookHeader {
            val xLineSignature = requestHeader.getHeader("x-line-signature").orEmpty()
            return WebhookHeader(xLineSignature)
        }
    }
}