package r97.lucy.api.db.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class ExtensiveKnowledge(
    var summary: String,
    var detail: String,
    @Id
    @GeneratedValue
    var id: Long? = null) {
}
