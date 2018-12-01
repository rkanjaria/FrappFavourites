package android.test.com.frappfavourites.activities.homescreen

import android.graphics.PorterDuff
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.test.com.frappfavourites.R
import android.test.com.frappfavourites.adapters.HomeViewPagerAdapter
import android.test.com.frappfavourites.fragments.FavouritesFragment
import android.test.com.frappfavourites.fragments.HomeFragment
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)
        setUpViewPager()
    }

    private fun setUpViewPager() {
        val homeViewPagerAdapter = HomeViewPagerAdapter(supportFragmentManager)
        homeViewPagerAdapter.addFragment(HomeFragment(), "")
        homeViewPagerAdapter.addFragment(FavouritesFragment(), "")
        homeViewPager.adapter = homeViewPagerAdapter
        tabLayout.setupWithViewPager(homeViewPager)

        tabLayout.getTabAt(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_home)
        tabLayout.getTabAt(1)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite)
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
                if (tab?.position == 0) onTabSelected(tab)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                tab?.icon?.setColorFilter(ContextCompat.getColor(baseContext, R.color.colorWhite), PorterDuff.Mode.SRC_IN)
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.icon?.setColorFilter(ContextCompat.getColor(baseContext, R.color.colorAccent), PorterDuff.Mode.SRC_IN)
                when (tab?.position) {
                    0 -> supportActionBar?.title = "Home"
                    1 -> supportActionBar?.title = "Favourites"
                    else -> supportActionBar?.title = "Internships & Missions"
                }
            }
        })
        tabLayout.getTabAt(0)?.select() // selecting first tab by default
    }
}
