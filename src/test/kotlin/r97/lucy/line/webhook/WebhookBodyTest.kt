package r97.lucy.line.webhook

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class WebhookBodyTest {

    @Test
    fun test_from_空文字列() {
        val result = WebhookBody.from("")
        assertEquals("", result.destination)
        assertTrue(result.events.isEmpty())
    }

    @Test
    fun test_from_空オブジェクト() {
        val result = WebhookBody.from("{}")
        assertEquals("", result.destination)
        assertTrue(result.events.isEmpty())
    }

    @Test
    fun test_from_正常値() {
        val result = WebhookBody.from("""{"destination":"hoge","events":[{"type":"fuga","timestamp":15,"source":{"hoge":"hoge"},"deliveryContext.isRedelivery":true}]}""")
        assertEquals("hoge", result.destination)
        assertEquals("fuga", result.events[0].type)
        assertEquals(15L, result.events[0].timestamp)
        assertEquals(true, result.events[0].isRedelivery)
    }

    @Test
    fun test_from_destinationなし() {
        val result = WebhookBody.from("""{"events":[{"type":"fuga"}]}""")
        assertEquals("", result.destination)
        assertEquals("fuga", result.events[0].type)
    }

    @Test
    fun test_from_eventsなし() {
        val result = WebhookBody.from("""{"destination":"hoge"}""")
        assertEquals("hoge", result.destination)
        assertTrue(result.events.isEmpty())
    }

    @Test
    fun test_from_JSON文字列に余計なプロパティがあっても無視する() {
        val result = WebhookBody.from("""{"destination":"hoge","events":[{"type":"fuga"}],"peko":"pa"}""")
        assertEquals("hoge", result.destination)
        assertEquals("fuga", result.events[0].type)
    }

    @Test
    fun test_from_eventsがリスト形式でも単純に文字列として設定する() {
        val result = WebhookBody.from("""{"destination":"hoge","events":[{"type":"fuga1","peko":"pa"},{"type":"fuga2","peko":"pa"}]}""")
        assertEquals("hoge", result.destination)
        assertEquals("fuga1", result.events[0].type)
        assertEquals("fuga2", result.events[1].type)
    }

}