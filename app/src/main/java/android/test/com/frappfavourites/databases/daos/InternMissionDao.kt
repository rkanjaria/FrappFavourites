package android.test.com.frappfavourites.databases.daos

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.test.com.frappfavourites.classes.INTERN_MISSION_TABLE
import android.test.com.frappfavourites.classes.IS_FAVOURITE
import android.test.com.frappfavourites.classes.VIEWS
import android.test.com.frappfavourites.databases.enitities.InternMission

@Dao
interface InternMissionDao {

    @Insert
    fun insertAllInternMission(internMission: List<InternMission>?)

    @Query("SELECT * FROM ${INTERN_MISSION_TABLE} WHERE ${IS_FAVOURITE} = 0 ORDER BY $VIEWS DESC")
    fun getAllInternMission(): LiveData<List<InternMission>>

    @Query("SELECT * FROM ${INTERN_MISSION_TABLE} WHERE ${IS_FAVOURITE} = 1 ORDER BY $VIEWS DESC")
    fun getAllFavourites(): LiveData<List<InternMission>>

}
