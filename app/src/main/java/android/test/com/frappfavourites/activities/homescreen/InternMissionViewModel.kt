package android.test.com.frappfavourites.activities.homescreen

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.test.com.frappfavourites.databases.enitities.InternMission
import android.test.com.frappfavourites.helper.RetrofitHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class InternMissionViewModel(application: Application) : AndroidViewModel(application) {

    private var internMissionRepository: InternMissionRepository

    init {
        internMissionRepository = InternMissionRepository(application)
    }

    fun getInternshipAndMissionsFromDb() = internMissionRepository.getAllInternMission()

    fun insertAllInternMission(internMissionList: List<InternMission>) {
        internMissionRepository.insertAllInternMission(internMissionList)
    }

    fun getInternshipAndMissionsFromApi() {

        val combinedList = mutableListOf<InternMission>()

        val internshipsObservable = RetrofitHelper.create().getInternships()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())

        val missionsObservable = RetrofitHelper.create().getMissions()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    insertAllInternMission(it)
                }

        /*Observable.zip(internshipsObservable, missionsObservable,
                BiFunction<List<InternMission>, List<InternMission>, List<InternMission>> { iList, mList ->
                    iList.forEach { it.imType = INTERNSHIP }
                    mList.forEach { it.imType = MISSION }
                    combinedList.addAll(iList)
                    combinedList.addAll(mList)
                    return@BiFunction combinedList
                }
        ).doOnNext { insertAllInternMission(combinedList)

            Toast.makeText(getApplication(), "no next", Toast.LENGTH_SHORT).show()
        }*/
    }
}