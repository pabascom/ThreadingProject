package phil.homework.threadingproject.moviedb.model.data

import phil.homework.threadingproject.moviedb.model.entities.movie.Movie
import phil.homework.threadingproject.moviedb.model.entities.moviesearchresult.Result

interface DataSource {

    fun getMovieList(callback: (List<Result>) -> Unit)

    fun saveMovieList(movieList: List<Result>, callback: (Boolean) -> Unit)

}