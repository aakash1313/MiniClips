package celernetworks.akashdeep.com.utils

import android.content.Context
import android.net.ConnectivityManager
import android.os.Environment
import celernetworks.akashdeep.com.MiniClipsApplication
import android.os.Environment.MEDIA_MOUNTED



/**
 * This is the util class for the app. All common utils to be added to this class
 */
fun isNetworkAvailable(): Boolean {
    val context = MiniClipsApplication.appInstance
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetworkInfo = connectivityManager.activeNetworkInfo
    return activeNetworkInfo != null && activeNetworkInfo.isConnected
}
fun isSDCardPresent(): Boolean {
    return Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED
}

val DOWNLOAD_VIDEO_DIRECTORY = "MiniClipVideo"