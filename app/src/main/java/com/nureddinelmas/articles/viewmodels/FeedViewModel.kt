package com.nureddinelmas.articles.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.nureddinelmas.articles.model.Article
import com.nureddinelmas.articles.apiService.ArticleAPIService
import com.nureddinelmas.database.ArticleDatabase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class FeedViewModel (application: Application) : BaseViewModel(application){
	private val articlesApiService = ArticleAPIService()
	private val disposable = CompositeDisposable()
	
	var articles = MutableLiveData<List<Article>>()
	val articleError = MutableLiveData<Boolean>()
	val articleLoading = MutableLiveData<Boolean>()
	
	fun refreshData(){
		getDataFromAPI()


	}
	private fun getDataFromAPI(){
		articleLoading.value = true
		disposable.add(
			articlesApiService.getData()
				.subscribeOn(Schedulers.newThread())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeWith(object : DisposableSingleObserver<List<Article>>(){
					
					override fun onSuccess(t: List<Article>) {
						
						articles.value = t
						articleError.value = false
						articleLoading.value = false
					}
					
					override fun onError(e: Throwable) {
						articleLoading.value = false
						articleError.value = true
						e.printStackTrace()
						
					}
					
				})
		)
	}
	

	private fun showArticles(articleList: List<Article>){
		articles.value = articleList
		articleError.value= false
		articleLoading.value = false
	}
	override fun onCleared() {
		super.onCleared()
		disposable.clear()
	}
}
