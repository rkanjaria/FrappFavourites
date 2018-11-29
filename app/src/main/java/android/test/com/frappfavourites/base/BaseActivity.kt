package android.test.com.frappfavourites.base

import android.content.Context
import android.os.Bundle
import android.support.v4.content.res.ResourcesCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.test.com.frappfavourites.R
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast

abstract class BaseActivity<in V : BaseView, T : BasePresenter<V>> : AppCompatActivity(), BaseView {

    protected abstract var mPresenter: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPresenter.attachView(this as V)
    }

    override fun getContext(): Context = this

    override fun initToolbar(toolbar: Toolbar, showBackButton: Boolean, title: String) {
        setSupportActionBar(toolbar)
        supportActionBar?.title = title
        supportActionBar?.setDisplayHomeAsUpEnabled(showBackButton)
    }

    override fun showMessage(message: String) {
        toast(message)
    }

    override fun finishActivity() {
        super.finish()
    }

    fun toast(message: String?) {
        val toast = Toast.makeText(this, message, Toast.LENGTH_SHORT)
        val toastLayout = toast.getView() as LinearLayout
        val toastTV = toastLayout.getChildAt(0) as TextView
        toastTV.typeface = ResourcesCompat.getFont(this, R.font.noto_sans_regular)
        toastTV.textSize = 14f
        toast.show()
    }
}