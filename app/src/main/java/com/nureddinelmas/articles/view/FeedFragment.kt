package com.nureddinelmas.articles.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager

import com.nureddinelmas.articles.R
import com.nureddinelmas.articles.adapter.ArticleAdapter
import com.nureddinelmas.articles.databinding.FragmentFeedBinding
import com.nureddinelmas.articles.viewmodels.FeedViewModel

import kotlinx.serialization.*
import kotlinx.serialization.json.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*

class FeedFragment : Fragment(), MenuProvider {
	
	private lateinit var viewModel: FeedViewModel
	private val articleAdapter = ArticleAdapter(arrayListOf())
	private var _binding: FragmentFeedBinding? = null
	private val binding get() = _binding!!
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		
	}
	
	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		_binding = FragmentFeedBinding.inflate(inflater, container, false)
		activity?.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)
		return binding.root
	}
	
	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		
		
		viewModel = ViewModelProvider(requireActivity())[FeedViewModel::class.java]
		viewModel.refreshData()
		binding.articlesList.layoutManager = LinearLayoutManager(context)
		binding.articlesList.adapter = articleAdapter
		
		binding.swipeRefreshLayout.setOnRefreshListener {
			binding.articlesList.visibility = View.GONE
			binding.articleError.visibility = View.GONE
			binding.articleLoading.visibility = View.VISIBLE
			viewModel.refreshData()
			binding.swipeRefreshLayout.isRefreshing = false
		}
		
		observeLiveData()
	}
	
	private fun observeLiveData() {
		viewModel.articles.observe(viewLifecycleOwner, Observer {articles ->
		
		articles?.let {
			binding.articlesList.visibility = View.VISIBLE
			articleAdapter.updateCountryList(articles)
		}
		
		})
		
		viewModel.articleError.observe(viewLifecycleOwner, Observer {error ->
		error?.let {
			if(it){
				binding.articleError.visibility = View.VISIBLE
				
			} else {
				binding.articleError.visibility = View.GONE
			}
		}
		
		})
		
		viewModel.articleLoading.observe(viewLifecycleOwner, Observer {loading->
			loading?.let {
				if (it){
					binding.articleLoading.visibility = View.VISIBLE
					
					binding.articlesList.visibility = View.GONE
					binding.articleLoading.visibility = View.GONE
				}else {
					binding.articleLoading.visibility = View.GONE
					
				}
			}
		})
	}
	
	override fun onDestroy() {
		super.onDestroy()
		_binding = null
	}
	
	override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
		menuInflater.inflate(R.menu.list_fragment_menu, menu)
	}
	
	override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
		if(menuItem.itemId == R.id.menu_saving_article){
		val action = FeedFragmentDirections.actionFeedFragmentToFeedDatabaseFragment()
			view?.let { Navigation.findNavController(it).navigate(action) }
		}
		return true
	}
}