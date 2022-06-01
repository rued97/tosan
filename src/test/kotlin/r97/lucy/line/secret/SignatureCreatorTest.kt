package r97.lucy.line.secret

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class SignatureCreatorTest {

    @Test
    fun test_create() {
        val channelSecret = "hogefugahogefuga"
        val httpRequestBody = "{\"destination\":\"Ucf5c0ad1a0a18c33e13379a8a74b88fa\",\"events\":[]}"
        val result = SignatureCreator(channelSecret, httpRequestBody).create()
        assertEquals("ayudCC9hQwO6ia9jjFGxOQ/EJs3lyeZGKF+nZMMjQEQ=", result)
    }

}