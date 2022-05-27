package r97.lucy.api.db.service

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import r97.lucy.api.db.entity.ExtensiveKnowledge
import r97.lucy.api.db.repository.ExtensiveKnowledgeRepository

@DataJpaTest
internal class ExtensiveKnowledgeServiceTest(
    @Autowired val extensiveKnowledgeRepository: ExtensiveKnowledgeRepository
) {

    val extensiveKnowledgeService = ExtensiveKnowledgeService(this.extensiveKnowledgeRepository)

    @Test
    fun testFindAll_正常値_0件() {
        val result = this.extensiveKnowledgeService.findAll()
        assertTrue(result.isEmpty())
    }

    @Test
    fun testFindAll_正常値_1件() {
        this.extensiveKnowledgeRepository.save(ExtensiveKnowledge("Hoge1", "Fuga1"))
        val result = this.extensiveKnowledgeService.findAll()
        assertEquals(1, result.size)
        assertEquals("Hoge1", result[0].summary)
        assertEquals("Fuga1", result[0].detail)
    }

    @Test
    fun testFindAll_正常値_複数件() {
        this.extensiveKnowledgeRepository.save(ExtensiveKnowledge("Hoge1", "Fuga1"))
        this.extensiveKnowledgeRepository.save(ExtensiveKnowledge("Hoge2", "Fuga2"))
        this.extensiveKnowledgeRepository.save(ExtensiveKnowledge("Hoge3", "Fuga3"))
        val result = this.extensiveKnowledgeService.findAll()
        assertEquals(3, result.size)
        assertEquals("Hoge1", result[0].summary)
        assertEquals("Fuga1", result[0].detail)
        assertEquals("Hoge2", result[1].summary)
        assertEquals("Fuga2", result[1].detail)
        assertEquals("Hoge3", result[2].summary)
        assertEquals("Fuga3", result[2].detail)
    }

    @Test
    fun testCreate_正常値() {
        this.extensiveKnowledgeService.create("Hoge", "Fuga")
        val result = this.extensiveKnowledgeRepository.findAll()
        assertEquals(1, result.size)
        assertEquals("Hoge", result[0].summary)
        assertEquals("Fuga", result[0].detail)
    }

    @Test
    fun testCreate_正常値_詳細未入力は空文字で登録() {
        this.extensiveKnowledgeService.create("Hoge")
        val result = this.extensiveKnowledgeRepository.findAll()
        assertEquals(1, result.size)
        assertEquals("Hoge", result[0].summary)
        assertEquals("", result[0].detail)
    }

    @Test
    fun testCreate_異常値_概要空文字はExeption発生() {
        try {
            this.extensiveKnowledgeService.create("")
            fail("想定した例外が発生しませんでした。")
        } catch (e: Exception) {
            assertEquals("summary は必須です。", e.message)
        }
    }

}
