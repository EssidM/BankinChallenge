package com.bankin.challengeandroid.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bankin.callengeandroid.R
import com.bankin.challengeandroid.viewmodel.CategoryViewModel


/**
 * @author messid on 2019-09-01.
 */
class CategoriesAdapter(private var items: List<CategoryViewModel> = ArrayList()) :
    RecyclerView.Adapter<com.bankin.challengeandroid.adapter.CategoriesAdapter.CategoryItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): com.bankin.challengeandroid.adapter.CategoriesAdapter.CategoryItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        return com.bankin.challengeandroid.adapter.CategoriesAdapter.CategoryItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(viewHolder: com.bankin.challengeandroid.adapter.CategoriesAdapter.CategoryItemViewHolder, position: Int) {
        viewHolder.bind(items[position])
    }

    fun setItems(items: List<CategoryViewModel>) {
        this.items = items
        notifyDataSetChanged()
    }


    class CategoryItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var title: TextView = itemView.findViewById(R.id.categoryItemTitle)


        fun bind(category: CategoryViewModel) {
            title.text = category.title
        }

    }

}