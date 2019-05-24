package phil.homework.threadingproject.moviedb.ui.movielist

import phil.homework.threadingproject.moviedb.model.entities.moviesearchresult.Result
import phil.homework.threadingproject.moviedb.ui.base.BasePresenter
import phil.homework.threadingproject.moviedb.ui.base.BaseView

interface MovieListContract {

    interface View : BaseView {

        fun onMovieList(movieList: List<Result>)

    }

    interface Presenter : BasePresenter<View> {

        fun getMovieList()

    }

}