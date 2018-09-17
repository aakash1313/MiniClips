package celernetworks.akashdeep.com.videointro.mainui.viewmodels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import celernetworks.akashdeep.com.videointro.mainui.repository.MiniClipsRepository
import celernetworks.akashdeep.com.videointro.mainui.roomdatabase.entity.MiniClipEntity

/**
 * View model part of the clips screen. This will fetch the data from the repository which can be
 * either from the cache/database if not available in both,fetches from the data from the network
 * and returns, simultaneously adding the data to the database and updating the cache on the worker
 * thread.
 *
 * Improvement to except in V2: images and videos would be saved in local directory and if found there
 * would be directly populated from there, thereby adding more efficiency in the app
 *
 */
class MiniClipsViewModel : ViewModel() {

    //Observable clips data
    private val clips: LiveData<List<MiniClipEntity>>
    private var clipsRepo: MiniClipsRepository = MiniClipsRepository()


    fun getPics(): LiveData<List<MiniClipEntity>> {
        return clips
    }

    init {
        clips = clipsRepo.getClips()
    }
}