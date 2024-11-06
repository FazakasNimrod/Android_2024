package com.tasty.recipesapp.data.dto

import com.tasty.recipesapp.domain.model.RecipeModel

data class RecipeDTO(
    val recipeId: Long,
    val name: String,
    val description: String,
    val thumbnailUrl: String,
    val keywords: String,
    val isPublic: Boolean,
    val userEmail: String,
    val originalVideoUrl: String,
    val country: String,
    val numServings: Long,
    //val components: List<Any?>,
    //val instructions: List<Any?>,
)


fun RecipeDTO.toModel(): RecipeModel {
    return RecipeModel(
        name = this.name
    )
}