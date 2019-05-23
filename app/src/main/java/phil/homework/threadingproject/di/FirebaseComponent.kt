package phil.homework.threadingproject.di

import dagger.Component
import phil.homework.threadingproject.ui.activity.firebase.FirebaseActivity

@Component(modules = arrayOf(FirebaseModule::class))
interface FirebaseComponent {

    fun inject(activity: FirebaseActivity)

}