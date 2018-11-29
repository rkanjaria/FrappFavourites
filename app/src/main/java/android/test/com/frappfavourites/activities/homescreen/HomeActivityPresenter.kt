package android.test.com.frappfavourites.activities.homescreen

import android.test.com.frappfavourites.base.BasePresenterImpl

class HomeActivityPresenter : BasePresenterImpl<HomeActivityContract.HomeView>(), HomeActivityContract.HomePresenter {
    fun requestViewPagar() {
        mView?.setupViewPager()
    }

}