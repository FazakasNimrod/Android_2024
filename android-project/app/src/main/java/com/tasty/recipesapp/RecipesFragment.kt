package com.tasty.recipesapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.tasty.recipesapp.viewmodel.RecipeListViewModel

class RecipesFragment : Fragment() {

    private lateinit var recipeViewModel: RecipeListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment without binding (UI not needed)
        val rootView = inflater.inflate(R.layout.fragment_recipes, container, false)

        // Initialize ViewModel using ViewModelProvider
        recipeViewModel = ViewModelProvider(this).get(RecipeListViewModel::class.java)
        recipeViewModel.fetchRecipeData()

        // Observe the recipe list and log the recipe details
        recipeViewModel.recipeList.observe(viewLifecycleOwner, Observer { recipes ->
            // Loop through the list of RecipeModels and print properties
            for (recipe in recipes) {
                //Log.d("RecipeData", "Recipe ID: ${recipe.id}")
                Log.d("RecipeData", "Recipe Name: ${recipe.name}")
                Log.d("RecipeData", "Recipe Description: ${recipe.description}")
                //Log.d("RecipeData", "Recipe Thumbnail: ${recipe.thumbnailUrl}")
                //Log.d("RecipeData", "Recipe Keywords: ${recipe.keywords}")
                //Log.d("RecipeData", "Recipe Servings: ${recipe.numServings}")
            }
        })

        // Return the root view
        return rootView
    }
}
