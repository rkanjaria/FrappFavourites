package android.test.com.frappfavourites.base

interface BasePresenter<in V : BaseView> {

    fun attachView(view: V)
    fun detachView()
}