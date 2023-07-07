package cookbook.core.recipe

import cookbook.core.ingredient.Ingredient
import cookbook.core.ingredient.IngredientRepository
import org.springframework.data.domain.Sort
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.support.TransactionTemplate

@Service
class RecipeService(
    private val transactionTemplate: TransactionTemplate,
    private val recipeRepository: RecipeRepository,
    private val ingredientRepository: IngredientRepository,
) {

    fun getRecipes(): List<Recipe> =
        recipeRepository.findAll(Sort.by("id"))

    fun addRecipe(
        recipeName: String,
        ingredientNames: List<String> = listOf()
    ): Recipe =
        transactionTemplate.execute { _ ->
            val recipe = Recipe(name = recipeName)
            val savedRecipe = recipeRepository.save(recipe)
            if (ingredientNames.isNotEmpty()) {
                val recipeId = savedRecipe.id!!
                val ingredients = ingredientNames
                    .map {
                        Ingredient(
                            recipeId = recipeId,
                            name = it
                        )
                    }
                ingredientRepository.saveAll(ingredients)
            }
            return@execute savedRecipe
        }!!

    fun getRecipeById(id: Int): Recipe? =
        recipeRepository.findByIdOrNull(id)

    fun updateRecipeById(id: Int, recipeName: String): Recipe? =
        transactionTemplate.execute { _ ->
            recipeRepository.findByIdOrNull(id)
                ?.copy(name = recipeName)
                ?.let { recipeRepository.save(it) }
        }

    fun removeRecipeById(id: Int): Unit? =
        transactionTemplate.execute { _ ->
            val ingredients = ingredientRepository.findByRecipeIdOrderById(recipeId = id)
            if (ingredients.isNotEmpty()) {
                ingredientRepository.deleteAll(ingredients)
            }
            return@execute recipeRepository.findByIdOrNull(id)
                ?.let { recipeRepository.delete(it) }
        }

}
