package com.nureddinelmas.articles.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nureddinelmas.articles.model.Article


@Database(entities = [Article::class], version = 1, exportSchema = false)
abstract class ArticleDatabase : RoomDatabase() {
	
	abstract fun articleDao(): ArticleDao
	

	
	companion object {
		@Volatile
		var instance: ArticleDatabase? = null
		
		private val lock = Any()
		
		operator fun invoke(context: Context) = instance ?: synchronized(lock) {
			instance ?: makeDatabase(context).also {
				instance = it
			}
		}
		
		private fun makeDatabase(context: Context) = Room.databaseBuilder(
			context.applicationContext,
			ArticleDatabase::class.java,
			"articledatabase"
		).build()
		
		
	}
}