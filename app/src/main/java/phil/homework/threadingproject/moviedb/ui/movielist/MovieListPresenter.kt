package phil.homework.threadingproject.moviedb.ui.movielist

import phil.homework.threadingproject.moviedb.model.data.repository.MovieRepository
import phil.homework.threadingproject.moviedb.ui.base.BaseView
import javax.inject.Inject

class MovieListPresenter @Inject constructor(val movieRepository: MovieRepository): MovieListContract.Presenter {

    var view: MovieListContract.View? = null

    override fun getMovieList() {
        movieRepository.getMovieList { view?.onMovieList(it) }
    }

    override fun attachView(view: MovieListContract.View) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
    }
}