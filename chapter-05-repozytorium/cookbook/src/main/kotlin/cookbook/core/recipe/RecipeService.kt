package cookbook.core.recipe

import org.springframework.data.domain.Sort
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class RecipeService(
    private val recipeRepository: RecipeRepository,
) {

    fun getRecipes(): List<Recipe> =
        recipeRepository.findAll(Sort.by("id"))

    fun addRecipe(recipeName: String): Recipe {
        val recipe = Recipe(name = recipeName)
        return recipeRepository.save(recipe)
    }

    fun getRecipeById(id: Int): Recipe? =
        recipeRepository.findByIdOrNull(id)

    fun updateRecipeById(id: Int, recipeName: String): Recipe? =
        recipeRepository.findByIdOrNull(id)
            ?.copy(name = recipeName)
            ?.let { recipeRepository.save(it) }

    fun removeRecipeById(id: Int): Unit? =
        recipeRepository.findByIdOrNull(id)
            ?.let { recipeRepository.delete(it) }

}
