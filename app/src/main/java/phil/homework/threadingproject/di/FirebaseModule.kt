package phil.homework.threadingproject.di

import android.content.Context
import dagger.Module
import dagger.Provides
import phil.homework.threadingproject.manager.LoginManager
import phil.homework.threadingproject.ui.activity.firebase.FirebasePresenter

@Module
class FirebaseModule(val context: Context) {

    @Provides
    fun provideFirebasePresenter(loginManager: LoginManager): FirebasePresenter = FirebasePresenter(loginManager)

    @Provides
    fun providesLoginManager(): LoginManager = LoginManager(context)

}