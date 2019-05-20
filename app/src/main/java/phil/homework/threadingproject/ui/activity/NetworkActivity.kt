package phil.homework.threadingproject.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_network.*
import phil.homework.threadingproject.R
import phil.homework.threadingproject.manager.NetworkManager
import phil.homework.threadingproject.network.OkHttpHelper
import phil.homework.threadingproject.network.RetrofitHelper
import phil.homework.threadingproject.rxjava.RxJavaHelper
import phil.homework.threadingproject.util.toast

class NetworkActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_network)

        btnHttpUrlConnection.setOnClickListener { NetworkManager.makeRequest(NetworkManager.PROVIDER.NATIVE) }
        btnOkHttpAsync.setOnClickListener { OkHttpHelper().makeAsynchronousRequest {response ->
            val name = response?.results?.get(0)?.name
            runOnUiThread{toast("${name?.title} ${name?.first} ${name?.last}")}
        } }

        val retrofitHelper = RetrofitHelper(applicationContext)
        btnRetrofitAsync.setOnClickListener {
            /*retrofitHelper.getUserList("10"){
                it.results?.forEach { toast("${it.name?.title} ${it.name?.first} ${it.name?.last}") }
            }*/

            retrofitHelper.getUserListObservable("10") {
                it.results?.forEach{
                    toast("${it.name?.title} ${it.name?.first} ${it.name?.last}")
                }
            }
        }

        val rxJavaHelper = RxJavaHelper(1)
        btnStartListening.setOnClickListener {
            rxJavaHelper.listenToObservable { toast(it.toString()) }
        }
        btnStopListening.setOnClickListener {
            rxJavaHelper.stopListening()
        }


    }
}
