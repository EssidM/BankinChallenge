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

    lateinit var adapter: com.bankin.challengeandroid.adapter.CategoriesAdapter

    var id: Long? = null

    val disposables = CompositeDisposable()

    // on category selected callback

    private val onCategorySelectedCallback: (parent: Long) -> Unit = {
        val intent = Intent(context, SubCategoryActivity::class.java)
        intent.putExtra(Arguments.PARENT_CATEGORY_ID, it)
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
        val disposable = id?.let {
            fetchSubCategories(it)
        } ?: kotlin.run {
            fetchMainCategories()
        }

        disposables.add(disposable)
    }

    override fun parseArguments(args: Bundle?) {
        super.parseArguments(args)

        id = args?.getLong(Arguments.PARENT_CATEGORY_ID)
    }

    fun bindCategoriesList(items: List<CategoryViewModel>, openableCategories: Boolean) {
        if (!::adapter.isInitialized) {
            adapter = com.bankin.challengeandroid.adapter.CategoriesAdapter(
                items,
                openableCategories,
                onCategorySelectedCallback
            )
            categoriesRecycler.adapter = adapter
        } else {
            adapter.setItems(items)
        }
    }

    fun fetchMainCategories(): Disposable {
        return categoriesMainViewModel.getMainCategories()
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext {
                bindCategoriesList(it, true)
            }.doOnError {
                Toast.makeText(context, "fetch error : ${it.message}", Toast.LENGTH_SHORT).show()
            }.subscribe()
    }

    fun fetchSubCategories(id: Long): Disposable {
        return categoriesMainViewModel.getSubcategories(id)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext {
                bindCategoriesList(it, false)
            }.doOnError {
                Toast.makeText(context, "fetch error : ${it.message}", Toast.LENGTH_SHORT).show()
            }.subscribe()
    }

    override fun onDestroyView() {
        super.onDestroyView()

        disposables.dispose()
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