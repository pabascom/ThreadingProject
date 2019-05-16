package phil.homework.threadingproject.network

import android.util.Log
import com.example.RandomUserResponse
import com.google.gson.Gson
import okhttp3.*
import phil.homework.threadingproject.util.Parser
import java.io.IOException

class OkHttpHelper {

    val baseUrl = "https://randomuser.me/api/"

    val client = OkHttpClient()

    fun buildRequest(): Request{

        val httpUrl = HttpUrl.Builder()
            .scheme("https")
            .host("randomuser.me")
            .addPathSegment("api")
            .addQueryParameter("results", "10")
            .build()

        val request = Request.Builder()
            .url(httpUrl)
            .build()

        return request
    }

    fun makeSynchronousRequest(){
        client.newCall(buildRequest()).execute()
    }

    fun makeAsynchronousRequest(callback: (RandomUserResponse) -> Unit){
        client.newCall(buildRequest()).enqueue(object: Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.d(OkHttpHelper::class.java.simpleName, "onFailure: $e")
            }

            override fun onResponse(call: Call, response: Response) {
                val responseString = response.body()?.string()
                val randomUserResponse = Parser.gsonParse(responseString)
                Parser.nativeParse(responseString)
                callback.invoke(randomUserResponse)
            }
        })
    }
}