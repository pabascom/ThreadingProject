package phil.homework.threadingproject.network

import android.content.Context
import android.util.Log
import com.example.RandomUserResponse
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap
import java.util.*
import kotlin.collections.HashMap

class RetrofitHelper(val context: Context) {

    private val baseUrl = "https://randomuser.me/"
    private val path = "api"

    private val queryMap = HashMap<String, String>()

    private val cacheSize: Long = 10 * 1024 * 1024
    private val cache = Cache(context.cacheDir, cacheSize)

    private val okHttpClient = OkHttpClient.Builder()
        .cache(cache)
        .addInterceptor{chain ->
            var request = chain.request()
            request = request.newBuilder()
                .header("Cache-Control", "public, max-age="+5)
                .build()
            chain.proceed(request)
        }
        .build()

    private fun createClient(): Retrofit {

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun createClientWithRxJava() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun createService(): RetrofitService {
        return createClient().create(RetrofitService::class.java)
    }

    fun createServiceWithRxJava(): RetrofitService {
        return createClientWithRxJava().create(RetrofitService::class.java)
    }

    fun getUserList(resultCount: String, callback: (RandomUserResponse) -> Unit){

        queryMap.apply {
            put("results", resultCount)
            put("gender", "female")
            put("nat", "FR")
        }

        createService().getUserList(path, queryMap).enqueue(object: Callback<RandomUserResponse>{
            override fun onResponse(call: Call<RandomUserResponse>, response: Response<RandomUserResponse>) {
                callback.invoke(response.body()!!)
            }

            override fun onFailure(call: Call<RandomUserResponse>, t: Throwable) {
                Log.d(RetrofitHelper::class.java.simpleName, "onFailure: ")
            }
        })
    }

    fun getUserListObservable(results: String, callback: (RandomUserResponse) -> Unit){
        createServiceWithRxJava()
            .getUserListWithRxJava(results)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{callback.invoke(it)}
    }

    interface RetrofitService{
        @GET("{atPath}")
        fun getUserList(@Path("atPath") path: String, @QueryMap result: HashMap<String, String>): Call<RandomUserResponse>

        @GET("api")
        fun getUserListWithRxJava(@Query("results") results: String): Observable<RandomUserResponse>
    }
}