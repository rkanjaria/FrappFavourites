package android.test.com.frappfavourites.activities.homescreen

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.test.com.frappfavourites.databases.enitities.InternMission

class InternMissionViewModel(application: Application) : AndroidViewModel(application) {

    private var internMissionRepository: InternMissionRepository
    private var internMissionList: LiveData<List<InternMission>>? = null

    init {
        internMissionRepository = InternMissionRepository(application)
        internMissionList = internMissionRepository.getAllInternMission()
    }

    fun getAllInternMission() = internMissionList

    fun insertAllInternMission(internMissionList: List<InternMission>) {
        internMissionRepository.insertAllInternMission(internMissionList)
    }
}