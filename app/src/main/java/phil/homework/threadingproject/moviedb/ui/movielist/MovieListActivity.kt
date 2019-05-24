package phil.homework.threadingproject.moviedb.ui.movielist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import phil.homework.threadingproject.R
import phil.homework.threadingproject.ThreadingApp
import phil.homework.threadingproject.moviedb.model.entities.movie.Movie
import phil.homework.threadingproject.moviedb.model.entities.moviesearchresult.Result
import javax.inject.Inject

class MovieListActivity : AppCompatActivity(), MovieListContract.View {

    @Inject
    lateinit var presenter: MovieListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)

        ThreadingApp.get(this).createMovieListComponent(this)
        presenter.attachView(this)

        presenter.getMovieList()
    }

    override fun onMovieList(movieList: List<Result>) {
        movieList.forEach{ Log.d(MovieListActivity::class.java.simpleName, "onMovieList: ${it.title}")}
    }

    override fun showError(error: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
