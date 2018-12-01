package android.test.com.frappfavourites.activities.homescreen

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.test.com.frappfavourites.classes.INTERNSHIP
import android.test.com.frappfavourites.classes.MISSION
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
        internMissionRepository.getInternshipAndMissionsFromApi()
    }

    fun getAllFavourites(): LiveData<List<InternMission>>? {
        return internMissionRepository.getAllFavourites()
    }

    fun setFavourite(isFavourite: Boolean, id: Int) {
        internMissionRepository.setFavourite(isFavourite, id)
    }

    fun isTableEmpty() = internMissionRepository.isTableEmpty()
}