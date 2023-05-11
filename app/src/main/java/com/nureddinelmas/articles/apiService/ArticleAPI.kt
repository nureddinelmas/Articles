package com.nureddinelmas.articles.apiService

import com.nureddinelmas.articles.model.Article
import com.nureddinelmas.articles.util.Constants.PART_URL
import io.reactivex.Single
import retrofit2.http.GET

interface ArticleAPI {

	@GET(PART_URL)
	fun getArticles(): Single<List<Article>>
}