package phil.homework.threadingproject.moviedb.model.data.local

import phil.homework.threadingproject.moviedb.model.data.DataSource
import phil.homework.threadingproject.moviedb.model.entities.movie.Movie
import phil.homework.threadingproject.moviedb.model.entities.moviesearchresult.Result

class LocalDataSource: DataSource {
    override fun getMovieList(callback: (List<Result>) -> Unit) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun saveMovieList(movieList: List<Result>, callback: (Boolean) -> Unit) {

    }
}