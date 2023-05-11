package com.nureddinelmas.articles.adapter

import androidx.recyclerview.widget.DiffUtil
import com.nureddinelmas.articles.model.Article

class ArticleDiffUtil (private val oldList: List<Article>, private val newList: List<Article>) : DiffUtil.Callback() {
	
	
	override fun getOldListSize(): Int = oldList.size
	
	override fun getNewListSize(): Int = newList.size
	
	override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
		return oldList[oldItemPosition] === newList[newItemPosition]
	}
	
	override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
		return  oldList[oldItemPosition].title == newList[newItemPosition].title
				&& oldList[oldItemPosition].description == newList[newItemPosition].description
				
	}
	
}