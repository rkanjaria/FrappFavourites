package android.test.com.frappfavourites.activities.homescreen

import android.test.com.frappfavourites.base.BasePresenter
import android.test.com.frappfavourites.base.BaseView

class HomeActivityContract {

    interface HomeView : BaseView {
        fun setupViewPager()
    }

    interface HomePresenter : BasePresenter<HomeView> {}
}