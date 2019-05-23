package phil.homework.threadingproject.moviedb.ui.movielist

import phil.homework.threadingproject.moviedb.ui.base.BasePresenter
import phil.homework.threadingproject.moviedb.ui.base.BaseView

interface MovieListContract {

    interface View : BaseView {

    }

    interface Presenter : BasePresenter<View> {

    }

}