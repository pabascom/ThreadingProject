package phil.homework.threadingproject.manager

import phil.homework.threadingproject.network.NativeNetworkHelper

object NetworkManager {
    enum class PROVIDER {NATIVE, OKHTTP, RETROFIT}

    val nativeNetworkHelper = NativeNetworkHelper()

    val nativeUrl = "http://www.mocky.io/v2/5cdad937300000680068ca90"
    val okHttpUrl = "http://www.mocky.io/v2/5cdad937300000680068ca90"

    fun makeRequest(provider: NetworkManager.PROVIDER){
        when(provider){
            NetworkManager.PROVIDER.NATIVE -> nativeNetworkHelper.makeRequest(nativeUrl)
            else -> println("Invalid network provider")
        }
    }



}