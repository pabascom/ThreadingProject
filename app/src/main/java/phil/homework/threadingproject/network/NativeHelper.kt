package phil.homework.threadingproject.network

import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.URL
import java.util.*

class NativeNetworkHelper {

    lateinit var httpURLConnection: HttpURLConnection

    fun makeRequest(url: String): String{

        val url = URL(url)
        httpURLConnection = url.openConnection() as HttpURLConnection
        val inputStream = httpURLConnection.inputStream

        val scanner = Scanner(inputStream)

        val result = StringBuilder()
        while(scanner.hasNext()){
            result.append(scanner.nextLine())
        }

        return result.toString()
    }

}