package android.test.com.frappfavourites.activities.homescreen

import android.app.Application
import android.arch.lifecycle.LiveData
import android.os.AsyncTask
import android.test.com.frappfavourites.classes.INTERNSHIP
import android.test.com.frappfavourites.classes.MISSION
import android.test.com.frappfavourites.databases.AppDatabase
import android.test.com.frappfavourites.databases.daos.InternMissionDao
import android.test.com.frappfavourites.databases.enitities.InternMission
import android.test.com.frappfavourites.helper.RetrofitHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class InternMissionRepository(application: Application) {

    val appDb = AppDatabase.invoke(application)
    private val internMissionDao: InternMissionDao = appDb.internMissionDao()

    fun getAllInternMission(): LiveData<List<InternMission>>? {
        return internMissionDao.getAllInternMission()
    }

    fun getAllFavourites(): LiveData<List<InternMission>>? {
        return internMissionDao.getAllFavourites()
    }

    fun insertAllInternMission(internMissionList: List<InternMission>) {
        InsertAsyncTask(internMissionDao).execute(internMissionList)
    }

    fun isTableEmpty(): Int {
        return GetIsTableEmptyAsyncTask(internMissionDao).execute().get()
    }

    fun setFavourite(isFavourite: Boolean, id: Int) {
        SetFavouriteAsyncTask(internMissionDao, isFavourite, id).execute()
    }

    fun getInternshipAndMissionsFromApi() {
        RetrofitHelper.create().getInternships()
                .flatMap { intershipResult ->
                    intershipResult.forEach { it.imType = INTERNSHIP }
                    insertAllInternMission(intershipResult)
                    return@flatMap RetrofitHelper.create().getMissions()
                }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ missionResult ->
                    missionResult.forEach { it.imType = MISSION }
                    insertAllInternMission(missionResult)
                }, { e -> e.printStackTrace() })
    }

    //doing all db operations in background to avoid runtime crashes and ANR
    companion object {

        class InsertAsyncTask(val internMissionDao: InternMissionDao) : AsyncTask<List<InternMission>, Void, Void>() {
            override fun doInBackground(vararg params: List<InternMission>?): Void? {
                internMissionDao.insertAllInternMission(params[0])
                return null
            }
        }

        class GetIsTableEmptyAsyncTask(val internMissionDao: InternMissionDao) : AsyncTask<Void, Void, Int>() {
            override fun doInBackground(vararg params: Void?): Int {
                return internMissionDao.isTableEmpty()
            }
        }

        class SetFavouriteAsyncTask(val internMissionDao: InternMissionDao, val isFavourite: Boolean, val id: Int) : AsyncTask<Void, Void, Void>() {
            override fun doInBackground(vararg params: Void?): Void? {
                internMissionDao.setFavourite(isFavourite, id)
                return null
            }
        }
    }
}

