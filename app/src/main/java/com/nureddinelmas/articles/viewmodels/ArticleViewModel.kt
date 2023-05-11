package com.nureddinelmas.articles.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.nureddinelmas.articles.model.Article
import com.nureddinelmas.articles.repository.ArticleRepository
import com.nureddinelmas.database.ArticleDao
import com.nureddinelmas.database.ArticleDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ArticleViewModel (application: Application) : AndroidViewModel(application){

	// private val articleDao = ArticleDatabase.getDatabase(application).articleDao()

	private val articleDao = ArticleDatabase(getApplication()).articleDao()
	private val repository : ArticleRepository = ArticleRepository(articleDao)
	
//	val getAllData : LiveData<List<Article>> = repository.getAllData
	
	fun insertData(article: Article){
		viewModelScope.launch (Dispatchers.IO){
			repository.insertData(article)
		}
	}
	
	fun deleteAllData(){
		viewModelScope.launch (Dispatchers.IO){
			repository.deleteAllData()
		}
	}

}