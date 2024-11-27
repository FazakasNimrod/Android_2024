package com.tasty.recipesapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.tasty.recipesapp.domain.model.RecipeModel
import com.tasty.recipesapp.viewmodel.ProfileViewModel

class NewRecipeFragment : Fragment() {
    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_new_recipe, container, false)

        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        // Initialize input fields
        val titleField = rootView.findViewById<EditText>(R.id.editTextTitle)
        val descriptionField = rootView.findViewById<EditText>(R.id.editTextDescription)
        val userEmailField = rootView.findViewById<EditText>(R.id.userEmailText)
        val saveButton = rootView.findViewById<Button>(R.id.buttonSave)

        // Handle Save button click
        saveButton.setOnClickListener {
            val title = titleField.text.toString()
            val description = descriptionField.text.toString()
            val userEmail = userEmailField.text.toString()

            if (title.isNotBlank() && description.isNotBlank() && userEmail.isNotBlank()) {
                val recipe = RecipeModel(
                    name = title,
                    description = description,
                    userEmail = userEmail
                )
                viewModel.insertRecipe(recipe)
                Toast.makeText(requireContext(), "Recipe added successfully", Toast.LENGTH_SHORT).show()
                navigateToFragment(ProfileFragment())
            } else {
                Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }

        return rootView
    }
    private fun navigateToFragment(fragment: Fragment) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment, fragment)
            .addToBackStack(null)
            .commit()
    }
}
