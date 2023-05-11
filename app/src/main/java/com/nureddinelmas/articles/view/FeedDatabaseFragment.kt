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
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.nureddinelmas.articles.R
import com.nureddinelmas.articles.adapter.ArticleDatabaseAdapter
import com.nureddinelmas.articles.databinding.FragmentFeedDatabaseBinding
import com.nureddinelmas.articles.model.Article
import com.nureddinelmas.articles.util.SwipeToDelete
import com.nureddinelmas.articles.viewmodels.ArticleViewModel
import com.nureddinelmas.articles.viewmodels.FeedDatabaseViewModel
import com.nureddinelmas.articles.viewmodels.FeedViewModel


class FeedDatabaseFragment : Fragment() , MenuProvider{
	private  var _binding : FragmentFeedDatabaseBinding? =null
	private val binding get() = _binding!!
	private lateinit var viewModel : FeedDatabaseViewModel
	private lateinit var articleViewModel : ArticleViewModel
	private val articleAdapter = ArticleDatabaseAdapter()

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
		articleViewModel = ViewModelProvider(requireActivity())[ArticleViewModel::class.java]
		
		binding.articlesListDatabase.layoutManager = LinearLayoutManager(context)
		binding.articlesListDatabase.adapter = articleAdapter
		
		viewModel.getAllData.observe(viewLifecycleOwner, Observer {data ->
			articleAdapter.updateCountryList(data)
			
			binding.articleError.visibility = View.GONE
			binding.articlesListDatabase.visibility = View.VISIBLE
			binding.articleLoading.visibility = View.GONE
		})
		
		
		swipeToDelete(binding.articlesListDatabase)
	}
	
	private fun swipeToDelete(recyclerView: RecyclerView) {
		val swipeToDeleteCallback = object : SwipeToDelete(){
			override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
				val itemToDelete = articleAdapter.articleList[viewHolder.adapterPosition]
				viewModel.deleteOneData(itemToDelete)
				restoreDeletedData(viewHolder.itemView, itemToDelete, viewHolder.adapterPosition)
			}
			
		}
		
		val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallback)
		itemTouchHelper.attachToRecyclerView(recyclerView)
	}
	
	private fun restoreDeletedData(view: View, deletedItem: Article, position: Int) {
		var deleted: String = R.string.deleted.toString()
		
		var ekmessage = "  ->  ' ${deletedItem.title} '"
		
		val snackbar = Snackbar.make(view, (R.string.deleted), Snackbar.LENGTH_LONG)
		
		snackbar.setAction(R.string.undo) {
			articleViewModel.insertData(deletedItem)
			articleAdapter.notifyDataSetChanged()
			articleAdapter.notifyItemChanged(position)
			
		}
		snackbar.show()
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