package celernetworks.akashdeep.com.videointro.mainui.downloadvideoservice

import android.Manifest
import android.app.IntentService
import android.content.Intent
import android.os.Environment
import android.support.v4.content.ContextCompat
import android.util.Log
import celernetworks.akashdeep.com.utils.DOWNLOAD_VIDEO_DIRECTORY
import celernetworks.akashdeep.com.utils.isSDCardPresent
import okhttp3.ResponseBody
import java.io.*
import java.net.HttpURLConnection
import java.net.URL


class DownloadVideo : IntentService(DownloadVideo::class.simpleName) {

    val APP_LOGS = "MINICLIPLOGS"
    override fun onHandleIntent(intent: Intent) {
        val url = intent.getStringExtra("url")
        download_save_local(url)
    }

    private fun download_save_local(url: String) {
        val endInd = url.lastIndexOf("/")
        val subStr = url.substring(0, endInd)
        val uniqueIdInd = subStr.lastIndexOf("/")
        val downloadFileName = subStr.substring(uniqueIdInd + 1) + ".mp4"
        try{
            val ul = URL(url)
            val c:HttpURLConnection = ul.openConnection() as HttpURLConnection
            c.requestMethod = "GET"
            c.connect()
            if(c.responseCode != HttpURLConnection.HTTP_OK){
                Log.d(APP_LOGS,"Server returned"+ c.responseMessage +"wirth code" + c.responseCode)
                return;
            }
            if(!saveTOSDCard(c,downloadFileName)){
                Log.d(APP_LOGS,"Problem downloading file")
                return
            }
            Log.d(APP_LOGS,"File was successfully saved")

        }
        catch (ex:Exception){
            Log.d(APP_LOGS,"Exception occured "+ ex.message)

        }

    }

    private fun saveTOSDCard(c:HttpURLConnection, downloadFileName:String): Boolean {
        var apkStorage: File
        //check if SD Card is present
        if (isSDCardPresent()) {
            apkStorage = File(
                    Environment.getExternalStorageDirectory().toString() + "/"
                            + DOWNLOAD_VIDEO_DIRECTORY)
        } else {
            return false
        }
        //Check if app directory exists
        if (!apkStorage.exists()) {
            apkStorage.mkdir();
            Log.e(APP_LOGS, "Directory Created.");
        }
        //Output file name
        val outputFile = File(apkStorage, downloadFileName)
        //Create New File if not present
        if (!outputFile.exists()) {
            outputFile.createNewFile();
            Log.e(APP_LOGS, "File Created");
        }
        var inputStream: InputStream? = null
        var outputStream: OutputStream? = null
        try {
            inputStream = c.inputStream
            outputStream = FileOutputStream(outputFile)
            val fileReader = ByteArray(4096)
            var fileSizeDownloaded: Long = 0
            while (true) {
                val read = inputStream.read(fileReader)

                if (read == -1) {
                    break
                }

                outputStream.write(fileReader, 0, read)
                fileSizeDownloaded += read;
            }
            outputStream.flush()
            return true
        } catch (ex: IOException) {
            return false
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }

            if (outputStream != null) {
                outputStream.close();
            }
        }
    }
}