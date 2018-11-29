package android.test.com.frappfavourites.activities.homescreen

import android.app.Application
import android.arch.lifecycle.LiveData
import android.os.AsyncTask
import android.test.com.frappfavourites.databases.AppDatabase
import android.test.com.frappfavourites.databases.daos.InternMissionDao
import android.test.com.frappfavourites.databases.enitities.InternMission

class InternMissionRepository(application: Application) {

    private var internMissionDao: InternMissionDao? = null
    private var internMissionList: LiveData<List<InternMission>>? = null

    init {
        val appDb = AppDatabase.getInstance(application)
        internMissionDao = appDb?.internMissionDao()
        internMissionList = internMissionDao?.getAllInternMission()
    }

    fun getAllInternMission(): LiveData<List<InternMission>>? {
        return internMissionList
    }

    fun insertAllInternMission(internMissionList: List<InternMission>) {
        InsertAsyncTask(internMissionDao).execute(internMissionList)
    }

    //doing all db operations in background to avoid runtime crashes and ANR
    companion object {
        class InsertAsyncTask(val internMissionDao: InternMissionDao?) : AsyncTask<List<InternMission>, Void, Void>() {
            override fun doInBackground(vararg params: List<InternMission>?): Void? {
                internMissionDao?.insertAllInternMission(params[0])
                return null
            }
        }
    }
}