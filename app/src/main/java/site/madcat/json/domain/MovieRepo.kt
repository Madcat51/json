package site.madcat.json.domain

import androidx.annotation.WorkerThread
import site.madcat.json.MovieEntity
import site.madcat.json.Results

interface MovieRepo {
    @WorkerThread             //фоновый поток
    fun getMovieSync(path:String): List<Results>
    fun getMovieAsync(path:String, callback:(List<MovieEntity>)->Unit)

}