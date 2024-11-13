package com.tasty.recipesapp.repository

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tasty.recipesapp.data.dto.RecipeDTO
import com.tasty.recipesapp.domain.model.RecipeModel
import java.io.IOException
import java.io.InputStreamReader

class RecipeRepository(private val context: Context) {

    fun getAllRecipes(): List<RecipeModel> {
        val recipeDtos = readAllRecipesFromJson(context)
        return recipeDtos.map { it.toModel() }
    }

    private fun readAllRecipesFromJson(context: Context): List<RecipeDTO> {
        val gson = Gson()
        val assetManager = context.assets
        val recipeList = mutableListOf<RecipeDTO>()
        try {
            val inputStream = assetManager.open("more_recipes.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            val jsonString = String(buffer, Charsets.UTF_8)
            val type = object : TypeToken<List<RecipeDTO>>() {}.type
            recipeList.addAll(gson.fromJson(jsonString, type))
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return recipeList
    }

    private fun RecipeDTO.toModel(): RecipeModel {
        return RecipeModel(
            //id = this.recipeID,
            name = this.name,
            description = this.description,
            //thumbnailUrl = this.thumbnailUrl,
            //keywords = this.keywords,
            //isPublic = this.isPublic,
            //userEmail = this.userEmail,
            //originalVideoUrl = this.originalVideoUrl,
            //country = this.country,
            //numServings = this.numServings
        )
    }
}
