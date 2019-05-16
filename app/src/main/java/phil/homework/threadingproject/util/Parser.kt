package phil.homework.threadingproject.util

import android.util.Log
import com.example.RandomUserResponse
import com.google.gson.Gson
import org.json.JSONObject

object Parser {

    fun gsonParse(response: String?): RandomUserResponse {
        return Gson().fromJson(response, RandomUserResponse::class.java)
    }

    fun nativeParse(response: String?): String {
        val rootObject = JSONObject(response)
        val results = rootObject.getJSONArray("results")
        val result_0 = results.get(0) as JSONObject
        val gender = result_0.getString("gender")

        Log.d(Parser::class.java.simpleName, "nativeParse: ${gender}")
        return gender
    }

}