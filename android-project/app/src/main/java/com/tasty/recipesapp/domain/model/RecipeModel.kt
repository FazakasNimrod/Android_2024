package com.tasty.recipesapp.domain.model

data class RecipeModel(
    val id: Int = 0,
    val name: String = "",
    val description: String = "",
    val thumbnailUrl: String = "",
    val keywords: String = "",
    val isPublic: Boolean = false,
    val userEmail: String = "",
    val originalVideoUrl: String = "",
    val country: String = "",
    val numServings: Int = 1
)
