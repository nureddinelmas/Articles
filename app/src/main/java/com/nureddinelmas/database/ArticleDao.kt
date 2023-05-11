package com.nureddinelmas.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.nureddinelmas.articles.model.Article


@Dao
interface ArticleDao {
	
	@Query("SELECT * FROM articles")
	fun getAllData() : LiveData<List<Article>>
	
	
	@Insert(onConflict = OnConflictStrategy.IGNORE)
	suspend fun insertData(article: Article)
	
	@Query("DELETE FROM articles")
	suspend fun deleteAllData()
	
}