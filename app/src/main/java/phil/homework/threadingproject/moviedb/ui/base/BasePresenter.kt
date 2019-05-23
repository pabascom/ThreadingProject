package phil.homework.threadingproject.moviedb.ui.base

interface BasePresenter<V: BaseView> {

    fun attachView(view: BaseView)
    fun detachView()
}