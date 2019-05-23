package phil.homework.threadingproject.moviedb.model.data.local

import phil.homework.threadingproject.moviedb.model.data.DataSource
import phil.homework.threadingproject.moviedb.model.entities.movie.Movie

class LocalDataSource: DataSource {
    override fun getMovieList(callback: (List<Movie>) -> Unit) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun saveMovieList(movieList: List<Movie>, callback: (Boolean) -> Unit) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}