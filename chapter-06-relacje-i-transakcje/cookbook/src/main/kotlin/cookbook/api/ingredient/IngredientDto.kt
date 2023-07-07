package cookbook.api.ingredient

data class IngredientDto(
    val id: Int,
    val recipeId: Int,
    val name: String,
)
