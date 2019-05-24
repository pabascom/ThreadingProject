package phil.homework.threadingproject.moviedb.di.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class AppModule(val application: Application) {

    @Provides
    fun provideContext(): Context = application

}