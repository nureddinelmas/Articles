package com.nureddinelmas.articles.view

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.nureddinelmas.articles.R
import com.nureddinelmas.articles.adapter.ArticleClickListener
import com.nureddinelmas.articles.databinding.FragmentArticleBinding
import com.nureddinelmas.articles.databinding.FragmentFeedBinding
import com.nureddinelmas.articles.model.Article
import com.nureddinelmas.articles.util.SwipeToDelete
import com.nureddinelmas.articles.viewmodels.ArticleViewModel
import kotlinx.coroutines.NonDisposableHandle.parent

class ArticleFragment : Fragment() {
	private lateinit var article: Article
	lateinit var articleViewModel: ArticleViewModel
	private var isFromDatabase: Boolean = false
	private lateinit var dataBinding: FragmentArticleBinding
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		
	}
	
	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		
		dataBinding =
			DataBindingUtil.inflate(layoutInflater, R.layout.fragment_article, container, false)
		
		return dataBinding.root
	}
	
	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		
		
		arguments?.let { art ->
			isFromDatabase = ArticleFragmentArgs.fromBundle(art).isFromDatabase
			article = ArticleFragmentArgs.fromBundle(art).article
			
			
		}
		
		if (isFromDatabase) {
			dataBinding.saveArticleButton.visibility = View.GONE
		}
		articleViewModel = ViewModelProvider(requireActivity())[ArticleViewModel::class.java]
		dataBinding.selectedArticle = article
		
		
		
		dataBinding.saveArticleButton.setOnClickListener {
			insertDataDb(article)
			val action = ArticleFragmentDirections.actionArticleFragmentToFeedDatabaseFragment()
			Navigation.findNavController(view).navigate(
				action
			)
		}
		
	}
	

	
	
	private fun insertDataDb(article: Article) {
		// articleViewModel.deleteAllData()
		articleViewModel.insertData(article)
		
	}
	
	
}