package r97.lucy.line.api

import org.springframework.http.HttpMethod

enum class MessagingAPI(
    val url: String,
    val method: HttpMethod
) {
    MESSAGE_REPLY("https://api.line.me/v2/bot/message/reply", HttpMethod.POST),
    MESSAGE_PUSH("https://api.line.me/v2/bot/message/push", HttpMethod.POST)
}
