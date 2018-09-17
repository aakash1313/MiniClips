package celernetworks.akashdeep.com.videointro.mainui.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import celernetworks.akashdeep.com.AppExecutors
import celernetworks.akashdeep.com.MiniClipsApplication
import celernetworks.akashdeep.com.retrofitapi.MiniClipsApiInterface
import celernetworks.akashdeep.com.videointro.mainui.roomdatabase.AppDatabase
import celernetworks.akashdeep.com.videointro.mainui.roomdatabase.entity.MiniClipEntity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * This is the repository class where we fetch the data from the network and update the database
 * accordingly
 */
class MiniClipsRepository {

    private val database: AppDatabase = MiniClipsApplication.appDatabase
    private val appExecutors: AppExecutors = MiniClipsApplication.mAppExecutors
    private val miniClipsApiInterface by lazy {
        MiniClipsApiInterface.create()
    }

    fun getClips(): LiveData<List<MiniClipEntity>> {
        var data: LiveData<List<MiniClipEntity>> = database.clipsDao().loadAll()
        if (data.value != null) {
            return data
        }
        data = getDataFromNetwork()
        return data
    }

    private fun getDataFromNetwork(): LiveData<List<MiniClipEntity>> {
        val data: MutableLiveData<List<MiniClipEntity>> = MutableLiveData()
        miniClipsApiInterface.getPictures().enqueue(object : Callback<List<MiniClipEntity>> {
            override fun onResponse(call: Call<List<MiniClipEntity>>, response: Response<List<MiniClipEntity>>) {
                if (response.isSuccessful)
                    data.value = response.body()
                saveDataToPersistance(response.body())
                //update Cache
                //save data to persistence
            }

            override fun onFailure(call: Call<List<MiniClipEntity>>, t: Throwable?) {

            }
        })
        return data
    }

    private fun saveDataToPersistance(data: List<MiniClipEntity>?) {
        if (data != null) {
            appExecutors.diskIO().execute {
                database.runInTransaction {
                    database.clipsDao().insertAll(data)
                }
            }
        }
    }
}