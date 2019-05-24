package phil.homework.threadingproject.moviedb.di.component

import dagger.Subcomponent
import phil.homework.threadingproject.moviedb.di.module.MovieListModule
import phil.homework.threadingproject.moviedb.ui.movielist.MovieListActivity
import phil.homework.threadingproject.moviedb.ui.movielist.MovieListPresenter

@Subcomponent(modules = arrayOf(MovieListModule::class))
interface MovieListComponent {

    fun injectIn(movieListActivity: MovieListActivity)

}