package phil.homework.threadingproject.moviedb.ui.di.component

import dagger.Component
import phil.homework.threadingproject.moviedb.model.data.remote.RemoteDataSource
import phil.homework.threadingproject.moviedb.model.data.repository.MovieRepository
import phil.homework.threadingproject.moviedb.ui.di.module.DataModule
import phil.homework.threadingproject.moviedb.ui.di.module.NetworkModule

@Component(modules = arrayOf(NetworkModule::class, DataModule::class))
interface NetworkComponent {

    fun injectIn(remoteDataSource: RemoteDataSource)

    fun injectIn(repository: MovieRepository)
}