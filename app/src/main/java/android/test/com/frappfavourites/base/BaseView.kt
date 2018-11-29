package android.test.com.frappfavourites.base

import android.content.Context
import android.support.v7.widget.Toolbar


interface BaseView {
    fun getContext(): Context
    fun initToolbar(toolbar: Toolbar, showBackButton: Boolean = false, title: String)
    fun showMessage(message: String)
    fun finishActivity()
}