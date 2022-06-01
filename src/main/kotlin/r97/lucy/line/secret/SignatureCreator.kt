package r97.lucy.line.secret

import java.util.*
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

class SignatureCreator(
    val channelSecret: String,
    val httpRequestBody: String
) {

    /**
     * @see https://developers.line.biz/ja/reference/messaging-api/#signature-validation
     */
    fun create(): String {
        val key = SecretKeySpec(this.channelSecret.toByteArray(), "HmacSHA256")
        val mac: Mac = Mac.getInstance("HmacSHA256")
        mac.init(key)
        val source = this.httpRequestBody.toByteArray(charset("UTF-8"))
        return Base64.getEncoder()?.encodeToString(mac.doFinal(source)).orEmpty()
    }

}