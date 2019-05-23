package phil.homework.threadingproject.moviedb.ui.di.module

import dagger.Module
import dagger.Provides
import phil.homework.threadingproject.moviedb.model.data.repository.MovieRepository
import phil.homework.threadingproject.moviedb.ui.movielist.MovieListPresenter

@Module
class MovieListModule {

    @Provides
    fun providesMovieListPresenter(movieRepository: MovieRepository) = MovieListPresenter(movieRepository)
}