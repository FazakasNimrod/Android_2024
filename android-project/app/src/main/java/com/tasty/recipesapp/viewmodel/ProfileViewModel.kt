package com.tasty.recipesapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tasty.recipesapp.domain.model.RecipeEntity
import com.tasty.recipesapp.repository.RecipeRepository2
import kotlinx.coroutines.launch

class ProfileViewModel(private val repository: RecipeRepository2) : ViewModel() {
    val recipes = mutableListOf<RecipeEntity>() // Use RecipeEntity directly

    fun getAllRecipes() {
        viewModelScope.launch {
            recipes.clear()
            recipes.addAll(repository.getAllRecipeEntities()) // Use the new function
        }
    }

    fun insertRecipe(recipe: RecipeEntity) {
        viewModelScope.launch {
            repository.insertRecipe(recipe)
        }
    }

    fun deleteRecipe(recipe: RecipeEntity) {
        viewModelScope.launch {
            repository.deleteRecipe(recipe)
        }
    }
}
