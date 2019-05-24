package phil.homework.threadingproject

import android.app.Application
import android.content.Context
import phil.homework.threadingproject.di.DaggerFirebaseComponent
import phil.homework.threadingproject.di.FirebaseComponent
import phil.homework.threadingproject.di.FirebaseModule
import phil.homework.threadingproject.manager.CloudManager
import phil.homework.threadingproject.manager.LoginManager
import phil.homework.threadingproject.moviedb.di.component.ApplicationComponent
import phil.homework.threadingproject.moviedb.di.component.DaggerApplicationComponent
import phil.homework.threadingproject.moviedb.di.component.MovieListComponent
import phil.homework.threadingproject.moviedb.di.module.AppModule
import phil.homework.threadingproject.moviedb.ui.movielist.MovieListActivity

class ThreadingApp: Application() {

    val firebaseComponent by lazy {
        DaggerFirebaseComponent.builder()
            .firebaseModule(FirebaseModule(applicationContext))
            .build()
    }

    companion object {
        fun get(context: Context): ThreadingApp = context.applicationContext as ThreadingApp
    }

    lateinit var appComponent: ApplicationComponent
    var movieListComponent: MovieListComponent? = null

    override fun onCreate() {
        super.onCreate()
        CloudManager.context = applicationContext

        appComponent = DaggerApplicationComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

    fun createMovieListComponent(movieListActivity: MovieListActivity) {
        movieListComponent = appComponent.movieListComponent()
        movieListComponent?.injectIn(movieListActivity)
    }

    fun clearMovieListComponent(){
        movieListComponent = null
    }
}

