package cookbook.core.recipe

import org.springframework.stereotype.Service
import java.util.concurrent.atomic.AtomicInteger

@Service
class RecipeService {

    companion object {
        val idSequence = AtomicInteger()
        val recipesMap = mutableMapOf<Int, Recipe>()
    }

    fun getRecipes(): List<Recipe> =
        recipesMap
            .entries
            .sortedBy { it.key }
            .map { it.value }

    fun addRecipe(recipeName: String): Recipe {
        val id = idSequence.incrementAndGet()
        val recipe = Recipe(id = id, name = recipeName)
        recipesMap[id] = recipe
        return recipe
    }

    fun getRecipeById(id: Int): Recipe? =
        recipesMap[id]

    fun updateRecipeById(id: Int, recipeName: String): Recipe? {
        val oldRecipe = recipesMap[id] ?: return null
        val newRecipe = oldRecipe.copy(name = recipeName)
        recipesMap[id] = newRecipe
        return newRecipe
    }

    fun removeRecipeById(id: Int): Unit? {
        val removedRecipe = recipesMap.remove(id)
        return if (removedRecipe != null) Unit else null
    }

}
