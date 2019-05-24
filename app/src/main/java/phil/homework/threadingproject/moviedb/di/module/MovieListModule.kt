package phil.homework.threadingproject.moviedb.di.module

import dagger.Module
import dagger.Provides
import phil.homework.threadingproject.moviedb.model.data.repository.MovieRepository
import phil.homework.threadingproject.moviedb.ui.movielist.MovieListActivity
import phil.homework.threadingproject.moviedb.ui.movielist.MovieListContract
import phil.homework.threadingproject.moviedb.ui.movielist.MovieListPresenter

@Module
class MovieListModule {

    @Provides
    fun providesMovieListPresenter(
        view: MovieListContract.View,
        movieRepository: MovieRepository
    ): MovieListContract.Presenter = MovieListPresenter(movieRepository)

    @Provides
    fun providesMovieListView(
        movieListActivity: MovieListActivity
    ): MovieListContract.View = movieListActivity
}