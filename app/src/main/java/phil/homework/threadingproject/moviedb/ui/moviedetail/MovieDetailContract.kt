package phil.homework.threadingproject.moviedb.ui.moviedetail

import phil.homework.threadingproject.moviedb.ui.base.BasePresenter
import phil.homework.threadingproject.moviedb.ui.base.BaseView

interface MovieDetailContract {

    interface View : BaseView {

    }

    interface Presenter : BasePresenter<View> {

    }
}