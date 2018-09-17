package celernetworks.akashdeep.com

import android.app.Application
import celernetworks.akashdeep.com.videointro.mainui.roomdatabase.AppDatabase

/**
 * Android Application Class. Used for accessing singletons
 */
class MiniClipsApplication : Application() {

    companion object {
        lateinit var appInstance: MiniClipsApplication
        lateinit var appDatabase: AppDatabase
        lateinit var mAppExecutors: AppExecutors
    }

    override fun onCreate() {
        super.onCreate()
        MiniClipsApplication.appInstance = this
        MiniClipsApplication.mAppExecutors = AppExecutors()
        MiniClipsApplication.appDatabase = AppDatabase.getInstance(this)
    }
}