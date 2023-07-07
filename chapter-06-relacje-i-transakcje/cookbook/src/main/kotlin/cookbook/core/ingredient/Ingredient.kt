package cookbook.core.ingredient

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Immutable
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.annotation.Version
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.OffsetDateTime

@Immutable
@Table("ingredient")
data class Ingredient(
    @Id
    @Column("id")
    val id: Int? = null,

    @Version
    @Column("version")
    val version: Int? = null,

    @CreatedDate
    @Column("created_at")
    val createdAt: OffsetDateTime? = null,

    @LastModifiedDate
    @Column("last_modified_at")
    val lastModifiedAt: OffsetDateTime? = null,

    @Column("recipe_id")
    val recipeId: Int,

    @Column("name")
    val name: String,
)
