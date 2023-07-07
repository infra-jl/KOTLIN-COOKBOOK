package cookbook.api.ingredient

import cookbook.core.ingredient.IngredientService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/ingredients/{id}")
@RestController
class IngredientRestController(
    private val ingredientService: IngredientService,
) {

    @GetMapping
    fun getIngredientById(@PathVariable id: Int): ResponseEntity<IngredientDto> =
        ingredientService.getIngredientById(id)
            ?.let { mapToIngredientDto(it) }
            ?.let { ResponseEntity.ok(it) }
            ?: ResponseEntity.notFound().build()

    @PutMapping
    fun putIngredientById(
        @PathVariable id: Int,
        @RequestBody ingredientUpdateDto: IngredientUpdateDto
    ): ResponseEntity<IngredientDto> =
        ingredientService.updateIngredientById(id, ingredientUpdateDto.name)
            ?.let { mapToIngredientDto(it) }
            ?.let { ResponseEntity.ok(it) }
            ?: ResponseEntity.notFound().build()

    @DeleteMapping
    fun deleteIngredientById(@PathVariable id: Int): ResponseEntity<Unit> {
        val removed = ingredientService.removeIngredientById(id)
        return if (removed != null)
            ResponseEntity.noContent().build()
        else
            ResponseEntity.notFound().build()
    }

}
