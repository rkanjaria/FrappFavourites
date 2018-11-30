package android.test.com.frappfavourites.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

class HomeViewPagerAdapter(fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager) {
    val fragmentsList = mutableListOf<Fragment>()
    val titleList = mutableListOf<String>()

    override fun getItem(position: Int): Fragment = fragmentsList[position]

    override fun getCount(): Int = fragmentsList.size

    override fun getPageTitle(position: Int): CharSequence? = titleList[position]

    fun addFragment(fragment: Fragment, title: String) {
        fragmentsList.add(fragment)
        titleList.add(title)
    }
}