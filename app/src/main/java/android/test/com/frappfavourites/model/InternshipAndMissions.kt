package android.test.com.frappfavourites.model

import android.test.com.frappfavourites.databases.enitities.InternMission

data class InternshipAndMissions(
        val internship: List<InternMission>,
        val missions: List<InternMission>
)