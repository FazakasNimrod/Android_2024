package com.tasty.recipesapp.api

import com.tasty.recipesapp.data.dto.RecipeDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface RecipeService {

    @GET("api/recipes")
    suspend fun getRecipes(): List<RecipeDTO>

    @GET("api/recipes/{Id}")
    suspend fun getRecipeDetails(
        @Path("Id") id: String,
    ): RecipeDTO
}