package com.nureddinelmas.articles.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nureddinelmas.articles.model.Article
import com.nureddinelmas.articles.repository.ArticleRepository
import com.nureddinelmas.database.ArticleDatabase
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FeedDatabaseViewModel(application: Application) : AndroidViewModel(application) {
	private val articleDao = ArticleDatabase(getApplication()).articleDao()
	private val repository : ArticleRepository = ArticleRepository(articleDao)
	val getAllData : LiveData<List<Article>> = repository.getAllData
	val articleError = MutableLiveData<Boolean>()
	val articleLoading = MutableLiveData<Boolean>()
	
	
	fun deleteOneData(article: Article){
		viewModelScope.launch(Dispatchers.IO) {
			repository.deleteSelectedData(article)
		}
	}
	
}