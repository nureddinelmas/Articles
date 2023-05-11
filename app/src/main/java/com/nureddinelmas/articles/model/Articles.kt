package com.nureddinelmas.articles.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.nureddinelmas.articles.util.Constants.TABLE_NAME
import kotlinx.parcelize.Parcelize


@Entity(tableName = TABLE_NAME)
@Parcelize
data class Article(
/*	@ColumnInfo(name = "source")
	@SerializedName("source")
	val source: Source?,*/
	
	@PrimaryKey(autoGenerate = true)
	var uuid : Int ,
	
	@ColumnInfo(name = "author")
	@SerializedName("author")
	val author: String?,
	
	@ColumnInfo(name = "title")
	@SerializedName("title")
	val title: String?,
	
	@ColumnInfo(name = "description")
	@SerializedName("description")
	val description: String?,
	
	@ColumnInfo(name = "url")
	@SerializedName("url")
	val url: String?,
	
	@ColumnInfo(name = "urlToImage")
	@SerializedName("urlToImage")
	val urlToImage: String?,
	
	@ColumnInfo(name = "publishedAt")
	@SerializedName("publishedAt")
	val publishedAt: String?,
	
	@ColumnInfo(name = "content")
	@SerializedName("content")
	val content: String?

) : Parcelable

/*@Parcelize
data class Source(
	
	@ColumnInfo(name = "id")
	@SerializedName("id")
	val content: String?,
	
	@ColumnInfo(name = "name")
	@SerializedName("name")
	val name: String?

): Parcelable*/

