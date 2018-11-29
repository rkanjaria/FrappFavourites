package android.test.com.frappfavourites.databases

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.test.com.frappfavourites.databases.daos.InternMissionDao
import android.test.com.frappfavourites.databases.enitities.InternMission

@Database(entities = [InternMission::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun internMissionDao(): InternMissionDao
}