package android.test.com.frappfavourites.activities.homescreen

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.test.com.frappfavourites.R
import android.test.com.frappfavourites.activities.adapters.HomeViewPagerAdapter
import android.test.com.frappfavourites.databases.enitities.InternMission
import android.test.com.frappfavourites.fragments.FavouritesFragment
import android.test.com.frappfavourites.fragments.HomeFragment
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    private lateinit var internMissionViewModel: InternMissionViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        initObserver()
        setUpViewPager()
    }

    private fun setUpViewPager() {
        val homeViewPagerAdapter = HomeViewPagerAdapter(supportFragmentManager)
        homeViewPagerAdapter.addFragment(HomeFragment(), "Home")
        homeViewPagerAdapter.addFragment(FavouritesFragment(), "Favourites")
        homeViewPager.adapter = homeViewPagerAdapter
        tabLayout.setupWithViewPager(homeViewPager)
    }

    fun initObserver() {
        internMissionViewModel = ViewModelProviders.of(this).get(InternMissionViewModel::class.java)
        internMissionViewModel.getAllInternMission()?.observe(this, object : Observer<List<InternMission>> {
            override fun onChanged(t: List<InternMission>?) {
                // update recycelrview
            }
        })

    }
}
