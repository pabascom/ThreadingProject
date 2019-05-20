package phil.homework.threadingproject.ui.base

interface BasePresenter<V: BaseView> {

    fun addView(view: V)
    fun removeView()

}