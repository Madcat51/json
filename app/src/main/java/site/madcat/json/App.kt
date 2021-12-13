package site.madcat.json

import android.app.Application
import android.content.Context
import site.madcat.json.data.MovieRepoUseCase
import site.madcat.json.domain.MovieRepo

class App: Application() {
    val movieRepo:MovieRepo by lazy { MovieRepoUseCase() }
}

val Context.app
get()=applicationContext as App