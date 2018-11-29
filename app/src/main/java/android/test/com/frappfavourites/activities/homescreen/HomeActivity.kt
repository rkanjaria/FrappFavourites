package android.test.com.frappfavourites.activities.homescreen

import android.os.Bundle
import android.test.com.frappfavourites.R
import android.test.com.frappfavourites.base.BaseActivity

class HomeActivity : BaseActivity<HomeActivityContract.HomeView, HomeActivityPresenter>(), HomeActivityContract.HomeView {
    override fun setupViewPager() {
        //homeViewPager.adapter = HomeViewPagerAdapter()
    }

    override var mPresenter = HomeActivityPresenter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        mPresenter.requestViewPagar()
    }
}
