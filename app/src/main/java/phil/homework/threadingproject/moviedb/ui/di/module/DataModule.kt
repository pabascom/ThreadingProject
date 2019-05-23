package phil.homework.threadingproject.moviedb.ui.di.module

import dagger.Module
import dagger.Provides
import phil.homework.threadingproject.moviedb.model.data.local.LocalDataSource
import phil.homework.threadingproject.moviedb.model.data.remote.RemoteDataSource
import phil.homework.threadingproject.moviedb.model.data.remote.RemoteService
import phil.homework.threadingproject.moviedb.model.data.repository.MovieRepository

@Module
class DataModule {

    @Provides
    fun providesMovieRepository(remoteDataSource: RemoteDataSource, localDataSource: LocalDataSource) =
            MovieRepository(remoteDataSource, localDataSource)

    @Provides
    fun providesRemoteDataSource(remoteService: RemoteService) = RemoteDataSource(remoteService)

    @Provides
    fun providesLocalDataSource() = LocalDataSource()

}