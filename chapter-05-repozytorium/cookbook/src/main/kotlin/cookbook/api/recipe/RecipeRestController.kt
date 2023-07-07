package cookbook.api.recipe

import cookbook.core.recipe.RecipeService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/recipes")
@RestController
class RecipeRestController(
    private val recipeService: RecipeService,
) {

    @GetMapping
    fun getRecipes(): List<RecipeDto> =
        recipeService.getRecipes()
            .map { mapToRecipeDto(it) }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun postRecipe(@RequestBody recipeCreateDto: RecipeCreateDto): RecipeDto {
        val recipeName = recipeCreateDto.name
        val recipe = recipeService.addRecipe(recipeName)
        return mapToRecipeDto(recipe)
    }

    @GetMapping("/{id}")
    fun getRecipeById(@PathVariable id: Int): ResponseEntity<RecipeDto> =
        recipeService.getRecipeById(id)
            ?.let { mapToRecipeDto(it) }
            ?.let { ResponseEntity.ok(it) }
            ?: ResponseEntity.notFound().build()

    @PutMapping("/{id}")
    fun putRecipeById(
        @PathVariable id: Int,
        @RequestBody recipeUpdateDto: RecipeUpdateDto
    ): ResponseEntity<RecipeDto> =
        recipeService.updateRecipeById(id, recipeUpdateDto.name)
            ?.let { mapToRecipeDto(it) }
            ?.let { ResponseEntity.ok(it) }
            ?: ResponseEntity.notFound().build()

    @DeleteMapping("/{id}")
    fun deleteRecipeById(@PathVariable id: Int): ResponseEntity<Unit> {
        val removed = recipeService.removeRecipeById(id)
        return if (removed != null)
            ResponseEntity.noContent().build()
        else
            ResponseEntity.notFound().build()
    }

}
