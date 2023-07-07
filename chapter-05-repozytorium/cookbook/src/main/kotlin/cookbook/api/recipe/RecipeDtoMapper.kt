package cookbook.api.recipe

import cookbook.core.recipe.Recipe

fun mapToRecipeDto(recipe: Recipe) =
    RecipeDto(
        id = recipe.id!!,
        name = recipe.name,
    )
