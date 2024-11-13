package com.tasty.recipesapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide // or Coil for image loading
import com.tasty.recipesapp.R
import com.tasty.recipesapp.domain.model.RecipeModel

class RecipeAdapter(
    private val recipes: List<RecipeModel>,
    private val onItemClick: (RecipeModel) -> Unit
) : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    inner class RecipeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val recipeImage = view.findViewById<ImageView>(R.id.recipeImageView)
        private val recipeName = view.findViewById<TextView>(R.id.recipeNameTextView)
        private val recipeDescription = view.findViewById<TextView>(R.id.recipeDescriptionTextView)
        private val recipeKeywords = view.findViewById<TextView>(R.id.recipeKeywordsTextView)

        fun bind(recipe: RecipeModel) {
            recipeName.text = recipe.name
            recipeDescription.text = recipe.description
            recipeKeywords.text = "Keywords: ${recipe.keywords.joinToString(", ")}"

            // Load image using Glide (or Coil, if preferred)
            Glide.with(recipeImage.context)
                .load(recipe.thumbnailUrl)
                .placeholder(R.drawable.placeholder) // a default placeholder
                .into(recipeImage)

            itemView.setOnClickListener { onItemClick(recipe) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recipe, parent, false)
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bind(recipes[position])
    }

    override fun getItemCount(): Int = recipes.size
}

private fun String.joinToString(s: String): String {
    val elements = this.split(",")
    return elements.joinToString(separator = s)
}

