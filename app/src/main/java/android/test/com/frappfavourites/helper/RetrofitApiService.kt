package android.test.com.frappfavourites.helper

import io.reactivex.Observable
import android.test.com.frappfavourites.databases.enitities.InternMission
import retrofit2.http.GET

interface RetrofitApiService {

    @GET("internships.json")
    fun getInternships(): Observable<List<InternMission>>

    @GET("missions.json")
    fun getMissions(): Observable<List<InternMission>>
}