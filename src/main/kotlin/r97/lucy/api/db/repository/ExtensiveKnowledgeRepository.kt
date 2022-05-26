package r97.lucy.api.db.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import r97.lucy.api.db.entity.ExtensiveKnowledge

@Repository
interface ExtensiveKnowledgeRepository: JpaRepository<ExtensiveKnowledge, Long> {
}
