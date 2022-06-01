package r97.lucy.api.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "lucy.line")
class LineConfig(
    val channelAccessToken: String,
    val channelSecret: String
) {

}