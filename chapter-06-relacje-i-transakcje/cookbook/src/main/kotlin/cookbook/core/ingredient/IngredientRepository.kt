package cookbook.core.ingredient

import org.springframework.data.repository.ListCrudRepository
import org.springframework.data.repository.ListPagingAndSortingRepository
import org.springframework.data.repository.query.ListQueryByExampleExecutor
import org.springframework.stereotype.Repository

@Repository
interface IngredientRepository :
    ListCrudRepository<Ingredient, Int>,
    ListPagingAndSortingRepository<Ingredient, Int>,
    ListQueryByExampleExecutor<Ingredient> {

    fun findByRecipeIdOrderById(recipeId: Int): List<Ingredient>

}
