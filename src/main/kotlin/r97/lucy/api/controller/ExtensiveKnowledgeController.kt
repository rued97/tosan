package r97.lucy.api.controller

import org.springframework.web.bind.annotation.*
import r97.lucy.api.db.entity.ExtensiveKnowledge
import r97.lucy.api.db.service.ExtensiveKnowledgeService

@RestController
@RequestMapping("/extensiveKnowledge")
class ExtensiveKnowledgeController(
    val extensiveKnowledgeService: ExtensiveKnowledgeService
) {

    @GetMapping
    fun all(): List<ExtensiveKnowledge> {
        return this.extensiveKnowledgeService.findAll()
    }

    @PostMapping
    fun post(@RequestBody body: ExtensiveKnowledgeRequestBody) {
        if (body.summary.isEmpty()) {
            throw Exception("summary は必須です。")
        }
        this.extensiveKnowledgeService.create(body.summary, body.detail)
    }

}

data class ExtensiveKnowledgeRequestBody(
    var summary: String = "",
    var detail: String = "",
) {}