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
import android.test.com.frappfavourites.classes.*
import android.test.com.frappfavourites.databases.enitities.InternMission
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_favourites.*

class FavouritesFragment : Fragment(), HomeFavouritesRecyclerAdapter.HomeAdapterListener {

    override fun onClicked(internMission: InternMission) {
        val internMissionDetails = Intent(context, InternMissionDetailsActivity::class.java)
        internMissionDetails.putExtra(INTERNSHIP_VALUE, internMission)
        startActivity(internMissionDetails)
    }

    override fun onFavouriteClicked(internMission: InternMission) {
        imViewModel.setFavourite(!internMission.isFavourite, internMission.imId)
        context?.showMessage("Removed to favourites")
    }

    private lateinit var imViewModel: InternMissionViewModel
    private lateinit var adapter: HomeFavouritesRecyclerAdapter
    private val favouriteList = mutableListOf<InternMission>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        imViewModel = ViewModelProviders.of(this).get(InternMissionViewModel::class.java)
        imViewModel.getAllFavourites()?.observe(this, object : Observer<List<InternMission>> {
            override fun onChanged(favList: List<InternMission>?) {
                progressBar.visibility = View.GONE
                refreshAdapter(favList)
            }
        })
        return inflater.inflate(R.layout.fragment_favourites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initRecyclerView()
    }

    private fun initRecyclerView() {
        adapter = HomeFavouritesRecyclerAdapter(favouriteList, this, false)
        favouriteRecyclerView.setHasFixedSize(true)
        favouriteRecyclerView.layoutManager = LinearLayoutManager(context)
        favouriteRecyclerView.adapter = adapter
    }

    private fun refreshAdapter(favList: List<InternMission>?) {

        if (favList != null && favList.isNotEmpty()) {

            val mFavouriteList = mutableListOf<InternMission>()
            val iList = mutableListOf<InternMission>()
            val mList = mutableListOf<InternMission>()

            favList.forEach {
                if (it.imType == INTERNSHIP) iList.add(it) else mList.add(it)
            }
            val internshipFakeModel = InternMission()
            internshipFakeModel.prop = INTERNSHIP_VALUE
            val missionFakeModel = InternMission()
            missionFakeModel.prop = MISSION_VALUE

            if (iList.isNotEmpty()) {
                mFavouriteList.add(internshipFakeModel)
                mFavouriteList.addAll(iList)
            }

            if (mList.isNotEmpty()) {
                mFavouriteList.add(missionFakeModel)
                mFavouriteList.addAll(mList)
            }
            adapter.setInternMission(mFavouriteList)
            hideEmptyLayout()
        } else {
            showEmptyLayout()
        }
    }

    private fun showEmptyLayout() {
        favouriteRecyclerView.visibility = View.GONE
        emptyImage.loadImage(R.drawable.ic_favorite_border)
        emptyImage.visibility = View.VISIBLE
        emptyText.visibility = View.VISIBLE
    }

    private fun hideEmptyLayout() {
        favouriteRecyclerView.visibility = View.VISIBLE
        emptyImage.visibility = View.GONE
        emptyText.visibility = View.GONE
    }
}
