package phil.homework.threadingproject.moviedb.di.module

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import phil.homework.threadingproject.moviedb.model.data.remote.MOVIE_BASE_URL
import phil.homework.threadingproject.moviedb.model.data.remote.MOVIE_INTERCEPTOR
import phil.homework.threadingproject.moviedb.model.data.remote.RemoteService
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {

    @Provides
    fun providesNetworkClient(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(client)
            .baseUrl(MOVIE_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Provides
    fun providesBaseClient(): OkHttpClient{
        return OkHttpClient.Builder()
            .addInterceptor(MOVIE_INTERCEPTOR)
            .build()
    }

    @Provides
    fun providesNetworkService(networkClient: Retrofit): RemoteService{
        return networkClient.create(RemoteService::class.java)
    }
}