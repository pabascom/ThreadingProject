package phil.homework.threadingproject.network

import com.google.firebase.database.core.Repo
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import phil.homework.week3test_gitcrawler.model.repo.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

class RemoteDataSource {

    val baseUrl = "https://api.github.com"

    fun createClient(): Retrofit {

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun createService(): RemoteService{
        return createClient().create(RemoteService::class.java)
    }

    fun getRepoList(username: String, callback: (List<Repository>) -> Unit){
        createService().getRepoList(username).enqueue(object: Callback<List<Repository>>{
            override fun onFailure(call: Call<List<Repository>>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call<List<Repository>>, response: Response<List<Repository>>) {
                callback.invoke(response.body()!!)
            }
        })
    }

    fun getRepoListObs(username: String){

        val retrofit = Retrofit.Builder().baseUrl(baseUrl)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(RemoteService::class.java)
            .getRepoListObservable(username)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .flatMap { list -> Observable.create<Repository> { emitter -> list.forEach { emitter.onNext(it) } } }
            .map { it.name = it.name }

    }


}

interface RemoteService {

    @GET("users/{username}/repos")
    fun getRepoList(@Path("username") username: String): Call<List<Repository>>

    @GET("users/{username}/repos")
    fun getRepoListObservable(@Path("username") username: String): Observable<List<Repository>>

}