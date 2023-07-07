package cookbook.api.recipe

data class RecipeCreateDto(
    val name: String,
    val ingredientNames: List<String>? = null,
)
