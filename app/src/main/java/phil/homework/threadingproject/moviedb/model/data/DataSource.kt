package phil.homework.threadingproject.moviedb.model.data

import phil.homework.threadingproject.moviedb.model.entities.movie.Movie

interface DataSource {

    fun getMovieList(callback: (List<Movie>) -> Unit)

    fun saveMovieList(movieList: List<Movie>, callback: (Boolean) -> Unit)

}