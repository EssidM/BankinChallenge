package com.bankin.challengeandroid.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bankin.callengeandroid.R
import com.bankin.challengeandroid.viewmodel.CategoryViewModel


class CategoriesAdapter(
    private var items: List<CategoryViewModel>,
    private val onCategorySelectedCallback: (Long, String) -> Unit
) :
    RecyclerView.Adapter<CategoriesAdapter.CategoryItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): CategoryItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        return CategoryItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(
        viewHolder: CategoryItemViewHolder,
        position: Int
    ) {
        viewHolder.bind(items[position], onCategorySelectedCallback)
    }

    fun setItems(items: List<CategoryViewModel>) {
        this.items = items
        notifyDataSetChanged()

    }

    class CategoryItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var title: TextView = itemView.findViewById(R.id.categoryItemTitle)

        fun bind(
            category: CategoryViewModel,
            onCategorySelectedCallback: (Long, String) -> Unit
        ) {
            title.text = category.title

            itemView.setOnClickListener {
                // call on category selected callback with parent id
                category.id?.let { onCategorySelectedCallback(it, category.title) }
            }
        }
    }
}