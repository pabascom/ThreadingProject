package phil.homework.threadingproject.moviedb.model.data.remote

import io.reactivex.Observable
import io.reactivex.Single
import phil.homework.threadingproject.moviedb.model.entities.movie.Movie
import phil.homework.threadingproject.moviedb.model.entities.moviesearchresult.MovieSearchResult
import retrofit2.http.GET
import retrofit2.http.Query

interface RemoteService {

    @GET(MOVIE_SEARCH_PATH)
    fun movieListStream(
        @Query(MOVIE_QUERY_STRING) query: String,
        @Query(MOVIE_QUERY_LANGUAGE) language: String,
        @Query(MOVIE_QUERY_PAGE) page: Int
    ): Observable<MovieSearchResult>
}