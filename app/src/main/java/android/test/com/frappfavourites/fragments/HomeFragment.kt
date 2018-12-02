package android.test.com.frappfavourites.fragments


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.test.com.frappfavourites.R
import android.test.com.frappfavourites.activities.homescreen.InternMissionViewModel
import android.test.com.frappfavourites.activities.internmissiondetailscreen.InternMissionDetailsActivity
import android.test.com.frappfavourites.adapters.HomeFavouritesRecyclerAdapter
import android.test.com.frappfavourites.classes.INTERNSHIP_VALUE
import android.test.com.frappfavourites.classes.showMessage
import android.test.com.frappfavourites.databases.enitities.InternMission
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(), HomeFavouritesRecyclerAdapter.HomeAdapterListener {

    override fun onClicked(internMission: InternMission) {
        val internMissionDetails = Intent(context, InternMissionDetailsActivity::class.java)
        internMissionDetails.putExtra(INTERNSHIP_VALUE, internMission)
        startActivity(internMissionDetails)
    }

    override fun onFavouriteClicked(internMission: InternMission) {
        imViewModel.setFavourite(!internMission.isFavourite, internMission.imId)
        context?.showMessage("Added to favourites")
    }

    private lateinit var imViewModel: InternMissionViewModel
    private lateinit var adapter: HomeFavouritesRecyclerAdapter
    private val internMissionList = mutableListOf<InternMission>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        imViewModel = ViewModelProviders.of(this).get(InternMissionViewModel::class.java)
        if (imViewModel.isTableEmpty() <= 0) {
            imViewModel.getInternshipAndMissionsFromApi()
        }

        imViewModel.getInternshipAndMissionsFromDb()?.observe(this, object : Observer<List<InternMission>> {
            override fun onChanged(internMissionList: List<InternMission>?) {
                progressBar.visibility = View.GONE
                adapter.setInternMission(internMissionList)
            }
        })
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initRecyclerView()
    }

    fun initRecyclerView() {
        adapter = HomeFavouritesRecyclerAdapter(internMissionList, this, true)
        homeRecyclerView.setHasFixedSize(true)
        homeRecyclerView.layoutManager = LinearLayoutManager(context)
        homeRecyclerView.adapter = adapter
    }
}
