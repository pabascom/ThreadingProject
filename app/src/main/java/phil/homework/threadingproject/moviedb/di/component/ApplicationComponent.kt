package phil.homework.threadingproject.moviedb.di.component

import dagger.Component
import phil.homework.threadingproject.moviedb.di.module.AppModule
import phil.homework.threadingproject.moviedb.di.module.DataModule
import phil.homework.threadingproject.moviedb.di.module.NetworkModule

@Component(modules = [AppModule::class, NetworkModule::class, DataModule::class])
interface ApplicationComponent {

    fun movieListComponent(): MovieListComponent
}