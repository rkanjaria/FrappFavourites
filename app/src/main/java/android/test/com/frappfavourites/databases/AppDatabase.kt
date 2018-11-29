package android.test.com.frappfavourites.databases

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import android.test.com.frappfavourites.classes.DATABASE_NAME
import android.test.com.frappfavourites.databases.daos.InternMissionDao
import android.test.com.frappfavourites.databases.enitities.InternMission

@Database(entities = [InternMission::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun internMissionDao(): InternMissionDao

    companion object {
        private var instance: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase? {
            if (instance == null) {
                synchronized(AppDatabase::class) {
                    instance = Room.databaseBuilder(context.applicationContext,
                            AppDatabase::class.java, DATABASE_NAME)
                            .fallbackToDestructiveMigration()
                            .build()
                }
            }
            return instance
        }
    }
}