package com.tasty.recipesapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tasty.recipesapp.repository.RecipeRepository
import com.tasty.recipesapp.domain.model.RecipeModel


class RecipeListViewModel(application: Application) : AndroidViewModel(application) {

    private val recipeRepository = RecipeRepository(application.applicationContext)
    private val _recipeList = MutableLiveData<List<RecipeModel>>()
    val recipeList: LiveData<List<RecipeModel>> = _recipeList

    fun fetchRecipeData() {
        _recipeList.value = recipeRepository.getAllRecipes()
    }
}

