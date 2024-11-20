package com.tasty.recipesapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment

class NewRecipeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_new_recipe, container, false)

        // Initialize input fields
        val titleField = rootView.findViewById<EditText>(R.id.editTextTitle)
        val descriptionField = rootView.findViewById<EditText>(R.id.editTextDescription)
        val durationField = rootView.findViewById<EditText>(R.id.editTextDuration)
        val saveButton = rootView.findViewById<Button>(R.id.buttonSave)

        // Handle Save button click
        saveButton.setOnClickListener {
            val title = titleField.text.toString()
            val description = descriptionField.text.toString()
            val duration = durationField.text.toString()

            if (title.isNotBlank() && description.isNotBlank() && duration.isNotBlank()) {
                Toast.makeText(requireContext(), "Recipe saved!", Toast.LENGTH_SHORT).show()
                // Logic to save the recipe (e.g., add to array or Room DB)

                // TODO
            } else {
                Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }

        return rootView
    }
}
