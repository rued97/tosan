package r97.lucy.api.db.service

import org.springframework.stereotype.Service
import r97.lucy.api.db.entity.ExtensiveKnowledge
import r97.lucy.api.db.repository.ExtensiveKnowledgeRepository

@Service
class ExtensiveKnowledgeService(
    private val extensiveKnowledgeRepository: ExtensiveKnowledgeRepository
) {

    fun findAll(): List<ExtensiveKnowledge> {
        return this.extensiveKnowledgeRepository.findAll()
    }

    fun create(summary: String, detail: String = ""): ExtensiveKnowledge {
        if(summary.isEmpty()) {
            throw Exception("summary は必須です。")
        }
        val e = ExtensiveKnowledge(summary, detail)
        this.extensiveKnowledgeRepository.save(e)
        return e
    }
}