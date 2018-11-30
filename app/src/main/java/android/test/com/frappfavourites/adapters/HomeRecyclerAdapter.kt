package android.test.com.frappfavourites.adapters

import android.support.v7.widget.RecyclerView
import android.test.com.frappfavourites.R
import android.test.com.frappfavourites.classes.inflate
import android.test.com.frappfavourites.databases.enitities.InternMission
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.intern_mission_recycler_layout.view.*

class HomeRecyclerAdapter(var internMissionList: List<InternMission>) : RecyclerView.Adapter<HomeRecyclerAdapter.HomeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): HomeViewHolder {
        return HomeViewHolder(parent.inflate(R.layout.intern_mission_recycler_layout, false))
    }

    override fun getItemCount() = internMissionList.size

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bindView(internMissionList[position])
    }

    fun setInternMission(listInternMission: List<InternMission>) {
        this.internMissionList = listInternMission
        notifyDataSetChanged()
    }

    class HomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mView = itemView

        fun bindView(internMission: InternMission) {
            Glide.with(mView).load(internMission.logo).into(mView.logo)
            mView.title.text = internMission.title
            mView.description.text = internMission.description
            mView.views.text = internMission.views.toString()
        }
    }
}
