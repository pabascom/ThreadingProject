package phil.homework.threadingproject.moviedb.model.data.remote

import android.annotation.SuppressLint
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import phil.homework.threadingproject.moviedb.model.data.DataSource
import phil.homework.threadingproject.moviedb.model.entities.movie.Movie
import retrofit2.Retrofit
import javax.inject.Inject

class RemoteDataSource @Inject constructor(val service: RemoteService): DataSource {

    @SuppressLint("CheckResult")
    override fun getMovieList(callback: (List<Movie>) -> Unit) {
        service.movieListStream("avatar", "en_US", 1)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(callback)
    }

    override fun saveMovieList(movieList: List<Movie>, callback: (Boolean) -> Unit) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}