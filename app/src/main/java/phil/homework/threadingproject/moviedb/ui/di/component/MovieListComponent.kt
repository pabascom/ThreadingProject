package phil.homework.threadingproject.moviedb.ui.di.component

import dagger.Subcomponent
import phil.homework.threadingproject.moviedb.ui.di.module.MovieListModule
import phil.homework.threadingproject.moviedb.ui.movielist.MovieListPresenter

@Subcomponent(modules = arrayOf(MovieListModule::class))
interface MovieListComponent {

    fun injectIn(movieListPresenter: MovieListPresenter)

}