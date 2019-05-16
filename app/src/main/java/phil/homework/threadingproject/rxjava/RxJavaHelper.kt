package phil.homework.threadingproject.rxjava

import android.annotation.SuppressLint
import com.example.RandomUserResponse
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit

class RxJavaHelper(val period: Long) {

    lateinit var disposable: Disposable


    private fun createIntervalObservable(period: Long): Observable<Long> {

        return Observable.interval(period, TimeUnit.SECONDS)

    }

    val observer = object: Observer<Long> {
        override fun onComplete() {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onSubscribe(d: Disposable) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onNext(t: Long) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onError(e: Throwable) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

    }

    @SuppressLint("CheckResult")
    fun listenToObservable(callback: (Long) -> Unit){
        disposable = createIntervalObservable(period)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .filter{it%3!=0L}
            //.subscribe(observer)
            .subscribe {callback.invoke(it)}
    }

    fun stopListening(){
        disposable.dispose()
    }

    fun getUserListObservable(callback: (RandomUserResponse) -> Unit){

    }

}