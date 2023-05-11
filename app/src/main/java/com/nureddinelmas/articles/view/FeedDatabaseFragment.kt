package com.nureddinelmas.articles.view

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.nureddinelmas.articles.R
import com.nureddinelmas.articles.adapter.ArticleDatabaseAdapter
import com.nureddinelmas.articles.databinding.FragmentFeedDatabaseBinding
import com.nureddinelmas.articles.viewmodels.FeedDatabaseViewModel


class FeedDatabaseFragment : Fragment() , MenuProvider{
	private  var _binding : FragmentFeedDatabaseBinding? =null
	private val binding get() = _binding!!
	private lateinit var viewModel : FeedDatabaseViewModel
	private val articleAdapter = ArticleDatabaseAdapter(arrayListOf())

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		
	}
	
	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		_binding = FragmentFeedDatabaseBinding.inflate(inflater, container, false)
		activity?.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)
		return binding.root
	}
	
	
	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		viewModel = ViewModelProvider(requireActivity())[FeedDatabaseViewModel::class.java]
		binding.articlesListDatabase.layoutManager = LinearLayoutManager(context)
		binding.articlesListDatabase.adapter = articleAdapter
		
		viewModel.getAllData.observe(viewLifecycleOwner, Observer {data ->
			articleAdapter.updateCountryList(data)
			Log.d("!!!", "data.toString()")
			Log.d("!!!", data.toString())
			Log.d("!!!", "olmadi")
			binding.articleError.visibility = View.GONE
			binding.articlesListDatabase.visibility = View.VISIBLE
			binding.articleLoading.visibility = View.GONE
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
			val action = FeedDatabaseFragmentDirections.actionFeedDatabaseFragmentToFeedFragment()
			view?.let { Navigation.findNavController(it).navigate(action) }
		}
		return true
	}
	
	
}