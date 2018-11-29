package android.test.com.frappfavourites.base

open class BasePresenterImpl<V : BaseView> : BasePresenter<V> {
    protected var mView: V? = null

    override fun attachView(view: V) {
        mView = view
    }

    override fun detachView() {
        mView = null
    }
}