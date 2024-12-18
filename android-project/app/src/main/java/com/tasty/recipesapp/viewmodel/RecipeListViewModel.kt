package com.tasty.recipesapp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.tasty.recipesapp.api.RecipeApiClient
import com.tasty.recipesapp.data.dto.RecipeDTO
import com.tasty.recipesapp.repository.RecipeRepository
import com.tasty.recipesapp.domain.model.RecipeModel
import kotlinx.coroutines.launch


class RecipeListViewModel(application: Application) : AndroidViewModel(application) {

    private val recipeRepository = RecipeRepository(application.applicationContext, RecipeApiClient())
    private val _recipeList = MutableLiveData<List<RecipeModel>>()
    val recipeList: LiveData<List<RecipeModel>> = _recipeList

    private val _selectedRecipe = MutableLiveData<RecipeModel?>()
    val selectedRecipe: LiveData<RecipeModel?> get() = _selectedRecipe

    fun fetchRecipeData() {
        viewModelScope.launch {
            _recipeList.value = recipeRepository.getAllRecipes()
        }
    }

    fun getRecipeById(id: Int?) {
        if (id == null) return

        viewModelScope.launch{
            try {
                val recipeDTO = recipeRepository.getRecipeDetails(id.toString())
                val recipe = recipeDTO.toModel()
                _selectedRecipe.value = recipe
            } catch (e: Exception) {
                _selectedRecipe.value = null
                Log.e("RecipeListViewModel","Error fetching recipe by ID: ${e.message}",e)
            }
        }
    }

}

private fun RecipeDTO.toModel(): RecipeModel {
    return RecipeModel(
        id = this.recipeID,
        name = this.name ?: "",
        description = this.description ?: "",
        thumbnailUrl = this.thumbnailUrl ?: "",
        keywords = this.keywords ?: "",
        isPublic = this.isPublic ?: false,
        userEmail = this.userEmail ?: "",
        originalVideoUrl = this.originalVideoUrl ?: "",
        country = this.country ?: "",
        numServings = this.numServings ?: 0
    )
}