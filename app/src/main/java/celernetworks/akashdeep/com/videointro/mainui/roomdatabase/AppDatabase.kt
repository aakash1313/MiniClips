package celernetworks.akashdeep.com.videointro.mainui.roomdatabase


import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import celernetworks.akashdeep.com.videointro.mainui.roomdatabase.dao.MiniClipsDao
import celernetworks.akashdeep.com.videointro.mainui.roomdatabase.entity.MiniClipEntity

/**
 * This is the application database
 */
@Database(entities = [(MiniClipEntity::class)], version = 1)
abstract class AppDatabase : RoomDatabase(){
    abstract fun clipsDao():MiniClipsDao

    companion object : SingletonHolder<AppDatabase, Context>({
        Room.databaseBuilder(it.applicationContext,
                AppDatabase::class.java,
                "mini-clips.db")
                .build()
    })
}
