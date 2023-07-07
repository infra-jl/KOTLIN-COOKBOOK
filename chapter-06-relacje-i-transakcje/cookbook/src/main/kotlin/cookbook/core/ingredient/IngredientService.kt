package cookbook.core.ingredient

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.support.TransactionTemplate

@Service
class IngredientService(
    private val transactionTemplate: TransactionTemplate,
    private val ingredientRepository: IngredientRepository,
) {

    fun getIngredientById(id: Int): Ingredient? =
        ingredientRepository.findByIdOrNull(id)

    fun updateIngredientById(id: Int, ingredientName: String): Ingredient? =
        transactionTemplate.execute { _ ->
            ingredientRepository.findByIdOrNull(id)
                ?.copy(name = ingredientName)
                ?.let { ingredientRepository.save(it) }
        }

    fun removeIngredientById(id: Int): Unit? =
        transactionTemplate.execute { _ ->
            ingredientRepository.findByIdOrNull(id)
                ?.let { ingredientRepository.delete(it) }
        }

    fun getIngredientsByRecipeId(recipeId: Int): List<Ingredient> =
        ingredientRepository.findByRecipeIdOrderById(recipeId)

}
