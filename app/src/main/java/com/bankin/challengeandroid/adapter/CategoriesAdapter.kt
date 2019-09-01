package com.bankin.challengeandroid.adapter

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bankin.callengeandroid.R
import com.bankin.challengeandroid.Arguments
import com.bankin.challengeandroid.view.SubCategoryActivity
import com.bankin.challengeandroid.viewmodel.CategoryViewModel


/**
 * @author messid on 2019-09-01.
 */
class CategoriesAdapter(private var items: List<CategoryViewModel>, val openableCategories: Boolean) :
    RecyclerView.Adapter<CategoriesAdapter.CategoryItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): CategoryItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        return CategoryItemViewHolder(view, openableCategories)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(
        viewHolder: CategoryItemViewHolder,
        position: Int
    ) {
        viewHolder.bind(items[position])
    }

    fun setItems(items: List<CategoryViewModel>) {
        this.items = items
        notifyDataSetChanged()
    }

    class CategoryItemViewHolder(itemView: View, val openableCategories: Boolean) : RecyclerView.ViewHolder(itemView) {

        var title: TextView = itemView.findViewById(R.id.categoryItemTitle)

        fun bind(category: CategoryViewModel) {
            title.text = category.title

            itemView.setOnClickListener {

                if (openableCategories) {
                    openSubcategories(category.id)
                }
            }
        }

        private fun openSubcategories(parent: Long?) {
            parent?.let {
                val intent = Intent(itemView.context, SubCategoryActivity::class.java)
                intent.putExtra(Arguments.PARENT_CATEGORY_ID, it)
                itemView.context.startActivity(intent)
            }
        }
    }

}