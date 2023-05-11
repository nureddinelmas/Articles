package com.nureddinelmas.articles.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.nureddinelmas.articles.model.Article


@Dao
interface ArticleDao {
	
	@Query("SELECT * FROM articles")
	fun getAllData() : LiveData<List<Article>>
	
	@Query("SELECT * FROM articles WHERE title= :articleTitle")
	suspend fun getSelectedArticle(articleTitle: String) : Article
	
	@Insert(onConflict = OnConflictStrategy.IGNORE)
	suspend fun insertData(article: Article)
	
	@Delete
	suspend fun deleteSelectedArticle(article: Article)
	
	@Query("DELETE FROM articles")
	suspend fun deleteAllData()
	
}