package com.nureddinelmas.articles.apiService
import com.nureddinelmas.articles.model.Article
import com.nureddinelmas.articles.util.Constants.BASE_URL
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ArticleAPIService {
	
	private val api = Retrofit.Builder()
		.baseUrl(BASE_URL)
		.addConverterFactory(GsonConverterFactory.create())
		.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
		.build()
		.create(ArticleAPI::class.java)
	
	fun getData() : Single<List<Article>>{
		return api.getArticles()
	}

}
