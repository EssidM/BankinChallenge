package com.bankin.challengeandroid.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bankin.callengeandroid.R
import com.bankin.challengeandroid.Arguments
import com.bankin.challengeandroid.adapter.CategoriesAdapter
import com.bankin.challengeandroid.viewmodel.CategoriesMainViewModel
import com.bankin.challengeandroid.viewmodel.CategoryViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.fragment_categories_list.*
import javax.inject.Inject


class CategoriesListFragment : BaseFragment() {

    @Inject
    lateinit var categoriesMainViewModel: CategoriesMainViewModel

    private lateinit var adapter: CategoriesAdapter

    private var id: Long? = null

    private val disposables = CompositeDisposable()


    // on category selected callback
    private val onCategorySelectedCallback: (Long, String) -> Unit = { id, name ->
        val intent = Intent(context, SubCategoryActivity::class.java)
        intent.putExtra(Arguments.PARENT_CATEGORY_ID, id)
        intent.putExtra(Arguments.PARENT_CATEGORY_NAME, name)
        startActivity(intent)
    }

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

        // fetch subcategories if id of parent is defined
        id?.let {
            categoriesMainViewModel.getSubcategories(it)
        } ?: kotlin.run {
            // fetch main categories
            categoriesMainViewModel.getMainCategories()
        }

        val disposable = categoriesMainViewModel.categoriesSubject
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext {
                    if (it.isEmpty()) {
                        emptyCategoriesPlaceHolder.visibility = View.VISIBLE
                    } else {
                        emptyCategoriesPlaceHolder.visibility = View.GONE
                        bindCategoriesList(it)
                    }
                }
                .subscribe()

        disposables.addAll(disposable)
    }

    override fun parseArguments(args: Bundle?) {
        super.parseArguments(args)

        id = args?.getLong(Arguments.PARENT_CATEGORY_ID)
    }

    private fun bindCategoriesList(items: List<CategoryViewModel>) {
        if (!::adapter.isInitialized) {
            adapter = CategoriesAdapter(
                    items,
                    onCategorySelectedCallback
            )
            categoriesRecycler.adapter = adapter
        } else {
            adapter.setItems(items)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        disposables.dispose()
    }

    private fun openSubcategories(id: Long) {
        val intent = Intent(context, SubCategoryActivity::class.java)
        intent.putExtra(Arguments.PARENT_CATEGORY_ID, id)
        context?.startActivity(intent)
    }

    companion object {
        fun newInstance(): CategoriesListFragment {
            return CategoriesListFragment()
        }

        fun newInstance(parent: Long): CategoriesListFragment {
            val args = Bundle()
            args.putLong(Arguments.PARENT_CATEGORY_ID, parent)

            val fragment = CategoriesListFragment()
            fragment.arguments = args

            return fragment
        }
    }
}