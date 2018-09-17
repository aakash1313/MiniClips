package celernetworks.akashdeep.com.videointro.mainui.roomdatabase.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Clips table for storing the api data
 */
@Entity(tableName = "clips")
data class MiniClipEntity(
        @PrimaryKey()
        val id:Int,
        val name:String?=null,
        val imageUrl:String,
        val videoUrl:String
        //please add last update after the setup is complete
)