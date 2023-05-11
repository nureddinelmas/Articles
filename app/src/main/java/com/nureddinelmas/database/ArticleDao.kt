package com.nureddinelmas.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.nureddinelmas.articles.model.Article


@Dao
interface ArticleDao {
	
	@Query("SELECT * FROM articles")
	fun getAllData() : LiveData<List<Article>>
	
/*	@Query("SELECT * FROM articles WHERE id=:articleId")
	suspend fun getSelectedArticle(articleId: Int) : LiveData<List<Article>>*/
	
	@Insert(onConflict = OnConflictStrategy.IGNORE)
	suspend fun insertData(article: Article)
	
	@Delete
	suspend fun deleteSelectedArticle(article: Article)
	
	@Query("DELETE FROM articles")
	suspend fun deleteAllData()
	
}