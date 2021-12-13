package site.madcat.json.data

import site.madcat.json.MovieEntity
import site.madcat.json.Results
import site.madcat.json.domain.MovieRepo

class MovieRepoUseCase : MovieRepo {
    override fun getMovieSync(path: String): List<Results> {
        return listOf(Results(1, "ru", "234", ",1,2,", 23L, "12345", "1919", "32145r"))
    }

    override fun getMovieAsync(path: String, callback: (List<MovieEntity>) -> Unit) {
       // callback.invoke(getMovieSync(path))
    }
}