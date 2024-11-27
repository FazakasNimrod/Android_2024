package com.tasty.recipesapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.tasty.recipesapp.adapters.RecipeAdapter
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
        // Inflate the layout for this fragment
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

        // Observe the recipe list and display 3 random recipes
        recipeViewModel.recipeList.observe(viewLifecycleOwner) { recipes ->

            recipeAdapter = RecipeAdapter(recipes) { recipe -> navigateToRecipeDetail(recipe) }
            recyclerView.adapter = recipeAdapter
        }

        // Handle FloatingActionButton to navigate to NewRecipeFragment
        val fab = rootView.findViewById<FloatingActionButton>(R.id.fab_add_recipe)
        fab.setOnClickListener {
            navigateToFragment(NewRecipeFragment())
        }

        return rootView
    }

    // Navigation function to navigate to RecipeDetailFragment with selected recipe data
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
