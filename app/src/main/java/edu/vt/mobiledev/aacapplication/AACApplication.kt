package edu.vt.mobiledev.aacapplication

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import edu.vt.mobiledev.aacapplication.local.DataInitializer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltAndroidApp
class AACApplication : Application() {
    @Inject
    lateinit var dataInitializer: DataInitializer

    override fun onCreate() {
        super.onCreate()
        CoroutineScope(Dispatchers.IO).launch {
            dataInitializer.initIfFirstRun()
        }
    }
}