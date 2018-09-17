package celernetworks.akashdeep.com.videointro.mainui.roomdatabase.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import celernetworks.akashdeep.com.videointro.mainui.roomdatabase.entity.MiniClipEntity

/**
 * DAO interface for the room database
 */
@Dao
interface MiniClipsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(clips:List<MiniClipEntity>)

    @Query("Select * from clips")
    fun loadAll():LiveData<List<MiniClipEntity>>
}