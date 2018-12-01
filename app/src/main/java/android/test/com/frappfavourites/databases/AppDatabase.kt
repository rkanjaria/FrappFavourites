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
        @Volatile
        private var instance: AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) =
                Room.databaseBuilder(context.applicationContext,
                        AppDatabase::class.java, DATABASE_NAME)
                        .fallbackToDestructiveMigration()
                        .build()
    }
}