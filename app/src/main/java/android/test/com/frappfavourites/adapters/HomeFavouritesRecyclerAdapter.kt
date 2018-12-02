package android.test.com.frappfavourites.adapters

import android.os.Build
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.test.com.frappfavourites.R
import android.test.com.frappfavourites.classes.inflate
import android.test.com.frappfavourites.classes.loadCircularImage
import android.test.com.frappfavourites.classes.loadImage
import android.test.com.frappfavourites.databases.enitities.InternMission
import android.text.Html
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.intern_mission_recycler_layout.view.*
import kotlinx.android.synthetic.main.section_recycler_layout.view.*

class HomeFavouritesRecyclerAdapter(var internMissionList: List<InternMission>, val mListener: HomeAdapterListener?, val isHome: Boolean) : RecyclerView.Adapter<HomeFavouritesRecyclerAdapter.HomeViewHolder>() {

    val INTERN_MISSION_VIEW = 1
    val SECTION_VIEW = 2

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        when (viewType) {
            SECTION_VIEW -> return SectionViewHolder(parent.inflate(R.layout.section_recycler_layout, false))
            else -> return HomeViewHolder(parent.inflate(R.layout.intern_mission_recycler_layout, false))
        }
    }

    override fun getItemCount() = internMissionList.size

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        if (holder is SectionViewHolder) {
            holder.bindSecView(internMissionList[position])
        } else {
            holder.bindView(internMissionList[position])
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (internMissionList[position].prop != null) {
            return SECTION_VIEW
        } else {
            return INTERN_MISSION_VIEW
        }
    }

    fun setInternMission(listInternMission: List<InternMission>?) {
        if (listInternMission != null) {
            this.internMissionList = listInternMission
            notifyDataSetChanged()
        }
    }

    inner class SectionViewHolder(itemView: View) : HomeViewHolder(itemView) {
        val secView = itemView
        fun bindSecView(internMission: InternMission) {
            secView.sectionName.text = internMission.prop
        }
    }

    open inner class HomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mView = itemView
        fun bindView(internMission: InternMission) {
            mView.logo.loadCircularImage(internMission.logo)
            mView.title.text = internMission.title
            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                mView.description.text = Html.fromHtml(internMission.description, Html.FROM_HTML_MODE_LEGACY)
            } else {
                @Suppress("DEPRECATION")
                mView.description.text = Html.fromHtml(internMission.description)
            }

            mView.favouriteImage.setColorFilter(R.color.colorAccent)
            if (internMission.isFavourite) {
                mView.favouriteImage.setImageDrawable(ContextCompat.getDrawable(mView.context, R.drawable.ic_favorite_blue))
            } else {
                mView.favouriteImage.setImageDrawable(ContextCompat.getDrawable(mView.context, R.drawable.ic_favorite_border))
            }

            mView.views.text = "${internMission.views} Views"
            mView.favouriteImage.setOnClickListener {
                mListener?.onFavouriteClicked(internMission)
            }

            mView.setOnClickListener {
                mListener?.onClicked(internMission)
            }
        }
    }

    interface HomeAdapterListener {
        fun onFavouriteClicked(internMission: InternMission)
        fun onClicked(internMission: InternMission)
    }
}
