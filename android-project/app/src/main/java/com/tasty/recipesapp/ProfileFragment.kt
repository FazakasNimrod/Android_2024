package com.tasty.recipesapp

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.gson.Gson
import com.tasty.recipesapp.adapters.RecipeAdapter
import com.tasty.recipesapp.adapters.RecyclerViewOnItemLongClickListener
import com.tasty.recipesapp.domain.model.RecipeEntity
import com.tasty.recipesapp.domain.model.RecipeModel
import com.tasty.recipesapp.viewmodel.ProfileViewModel
import com.tasty.recipesapp.viewmodel.RecipeListViewModel

class ProfileFragment : Fragment() {

    private lateinit var recipeViewModel: ProfileViewModel
    private lateinit var recipeAdapter: RecipeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val rootView = inflater.inflate(R.layout.fragment_profile, container, false)

        // Initialize RecyclerView
        val recyclerView = rootView.findViewById<RecyclerView>(R.id.profileRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Initialize ViewModel
        recipeViewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        recipeViewModel.getAllRecipes()

        // Initialize Adapter with empty list initially
        recipeAdapter = RecipeAdapter(emptyList()) { recipe -> navigateToRecipeDetail(recipe) }
        recyclerView.adapter = recipeAdapter

        // Observe the recipe list
        recipeViewModel.recipeList.observe(viewLifecycleOwner) { recipes ->
            recipeAdapter = RecipeAdapter(recipes) { recipe -> navigateToRecipeDetail(recipe) }
            recyclerView.adapter = recipeAdapter

            // Add long-click listener for deletion
            recyclerView.addOnItemTouchListener(
                RecyclerViewOnItemLongClickListener(
                    context = requireContext(),
                    onItemLongClick = { position ->
                        val recipe = recipes[position]
                        confirmAndDeleteRecipe(recipe)
                    }
                )
            )
        }

        // Handle FloatingActionButton to navigate to NewRecipeFragment
        val fab = rootView.findViewById<FloatingActionButton>(R.id.fab_add_recipe)
        fab.setOnClickListener {
            navigateToFragment(NewRecipeFragment())
        }

        return rootView
    }

    private fun confirmAndDeleteRecipe(recipe: RecipeModel) {
        AlertDialog.Builder(requireContext())
            .setTitle("Delete Recipe")
            .setMessage("Are you sure you want to delete this recipe?")
            .setPositiveButton("Yes") { _, _ ->
                val recipeEntity = RecipeEntity(
                    internalId = recipe.internalId.toLong(),
                    json = Gson().toJson(recipe)
                )
                recipeViewModel.deleteRecipe(recipeEntity)

                // Observe deletion result
                recipeViewModel.getAllRecipes()
                Toast.makeText(requireContext(), "Recipe deleted successfully", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("No", null)
            .show()
    }


    private fun navigateToRecipeDetail(recipe: RecipeModel) {
        navigateToFragment(RecipeDetailFragment())
    }

    private fun navigateToFragment(fragment: Fragment) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment, fragment)
            .addToBackStack(null)
            .commit()
    }
}

