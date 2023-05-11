package com.nureddinelmas.articles.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.nureddinelmas.articles.R
import com.nureddinelmas.articles.databinding.ItemArticleBinding
import com.nureddinelmas.articles.model.Article
import com.nureddinelmas.articles.view.FeedDatabaseFragmentDirections

class ArticleDatabaseAdapter (private val articleList: ArrayList<Article>) :
	RecyclerView.Adapter<ArticleDatabaseAdapter.ArticleViewHolder>() {
	
	
	class ArticleViewHolder(var view: ItemArticleBinding) : RecyclerView.ViewHolder(view.root) {
	
	}
	
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
		val inflater = LayoutInflater.from(parent.context)
		val view = DataBindingUtil.inflate<ItemArticleBinding>(
			inflater,
			R.layout.item_article,
			parent,
			false
		)
		return ArticleViewHolder(view)
	}
	
	override fun getItemCount(): Int = articleList.size
	
	override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
		holder.view.article = articleList[position]
		
		
		
		
		holder.view.linearLayoutItemArticle.setOnClickListener {
			val action = FeedDatabaseFragmentDirections.actionFeedDatabaseFragmentToArticleFragment(articleList[position], true)
				Navigation.findNavController(holder.itemView).navigate(action)
		}
		
	}
	
	
	fun updateCountryList(newArticleList: List<Article>) {
		articleList.clear()
		articleList.addAll(newArticleList)
		notifyDataSetChanged()
	}
	
	
	
	
	
	
}