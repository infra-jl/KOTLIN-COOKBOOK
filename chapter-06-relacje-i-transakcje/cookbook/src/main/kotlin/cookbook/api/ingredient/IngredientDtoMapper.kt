package cookbook.api.ingredient

import cookbook.core.ingredient.Ingredient

fun mapToIngredientDto(ingredient: Ingredient) =
    IngredientDto(
        id = ingredient.id!!,
        recipeId = ingredient.recipeId,
        name = ingredient.name,
    )
