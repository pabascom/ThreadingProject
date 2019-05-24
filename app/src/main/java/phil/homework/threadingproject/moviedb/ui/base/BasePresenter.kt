package phil.homework.threadingproject.moviedb.ui.base

interface BasePresenter<V: BaseView> {

    fun attachView(view: V)
    fun detachView()
}