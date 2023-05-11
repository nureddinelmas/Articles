package com.nureddinelmas.articles.repository

import androidx.lifecycle.LiveData
import com.nureddinelmas.articles.model.Article
import com.nureddinelmas.articles.database.ArticleDao

class ArticleRepository (private val articleDao: ArticleDao){
	 val getAllData : LiveData<List<Article>> = articleDao.getAllData()
	
	
	suspend fun insertData(article: Article){
		articleDao.insertData(article)
	}
	
	suspend fun deleteAllData(){
		articleDao.deleteAllData()
	}
	
	suspend fun deleteSelectedData(article: Article){
		articleDao.deleteSelectedArticle(article)
	}

	suspend fun getSelectedData(articleTitle: String){
		articleDao.getSelectedArticle(articleTitle)
	}
	

}