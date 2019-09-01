package com.bankin.challengeandroid.view

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bankin.callengeandroid.R
import com.bankin.challengeandroid.viewmodel.CategoriesMainViewModel
import com.bankin.challengeandroid.viewmodel.CategoryViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_categories_list.*
import javax.inject.Inject


class CatgeoriesListFragment : Fragment() {

    @Inject
    lateinit var categoriesMainViewModel: CategoriesMainViewModel

    lateinit var adapter: com.bankin.challengeandroid.adapter.CategoriesAdapter

    val disposables = CompositeDisposable()

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        (activity?.application as com.bankin.challengeandroid.BankinChallengeApp).getCategoriesComponent().inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_categories_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        categoriesRecycler.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        val disposable = categoriesMainViewModel.getMainCategories()
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext {
                bindCategoriesList(it)
            }.doOnError {
                Toast.makeText(context, "fetch error : ${it.message}", Toast.LENGTH_SHORT).show()
            }.subscribe()

        disposables.add(disposable)
    }

    fun bindCategoriesList(items: List<CategoryViewModel>) {
        if (!::adapter.isInitialized) {
            adapter = com.bankin.challengeandroid.adapter.CategoriesAdapter(items)
            categoriesRecycler.adapter = adapter
        } else {
            adapter.setItems(items)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        disposables.dispose()
    }
}