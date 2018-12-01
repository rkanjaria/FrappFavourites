package android.test.com.frappfavourites.fragments


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.test.com.frappfavourites.R
import android.test.com.frappfavourites.activities.homescreen.InternMissionViewModel
import android.test.com.frappfavourites.adapters.HomeFavouritesRecyclerAdapter
import android.test.com.frappfavourites.classes.loadImage
import android.test.com.frappfavourites.classes.showMessage
import android.test.com.frappfavourites.databases.enitities.InternMission
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(), HomeFavouritesRecyclerAdapter.HomeAdapterListener {

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

                if (internMissionList != null && internMissionList.isNotEmpty()) {
                    adapter.setInternMission(internMissionList)
                    hideEmptyLayout()
                } else {
                    showEmptyLayout()
                }
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


    private fun showEmptyLayout() {
        homeRecyclerView.visibility = View.GONE
        emptyImage.loadImage(R.drawable.ic_home)
        emptyImage.visibility = View.VISIBLE
        emptyText.visibility = View.VISIBLE
    }

    private fun hideEmptyLayout() {
        homeRecyclerView.visibility = View.VISIBLE
        emptyImage.visibility = View.GONE
        emptyText.visibility = View.GONE
    }
}
