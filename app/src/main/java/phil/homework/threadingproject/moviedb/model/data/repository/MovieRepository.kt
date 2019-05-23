package phil.homework.threadingproject.moviedb.model.data.repository

import phil.homework.threadingproject.moviedb.manager.CacheManager
import phil.homework.threadingproject.moviedb.model.data.DataSource
import phil.homework.threadingproject.moviedb.model.data.local.LocalDataSource
import phil.homework.threadingproject.moviedb.model.data.remote.RemoteDataSource
import phil.homework.threadingproject.moviedb.model.entities.movie.Movie
import javax.inject.Inject

class MovieRepository @Inject constructor(
    val remoteDataSource: RemoteDataSource, val localDataSource: LocalDataSource
) : MovieRepositoryContract {

    lateinit var dataSource: DataSource

    fun currentDataSource(): DataSource = if (CacheManager.isCacheDirty()) remoteDataSource else localDataSource

    override fun getMovieList(callback: (List<Movie>) -> Unit) {
        dataSource.getMovieList {
            if(CacheManager.isCacheDirty()) localDataSource.saveMovieList(it) {}
            callback.invoke(it)
        }
    }

    override fun saveMovieList(movieList: List<Movie>, callback: (Boolean) -> Unit) {
        localDataSource.saveMovieList(movieList) { callback.invoke(it) }
    }
}