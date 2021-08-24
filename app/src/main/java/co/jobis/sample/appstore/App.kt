package co.jobis.sample.appstore

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {

    companion object {
        private lateinit var instance: App

//        private val database: AppStoreDatabase by lazy {
//            AppStoreDatabase.buildDatabase(instance)
//        }
    }

    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(this);
        instance = this
    }
}