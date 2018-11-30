package android.test.com.frappfavourites.fragments


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.test.com.frappfavourites.R
import android.test.com.frappfavourites.activities.homescreen.InternMissionViewModel
import android.test.com.frappfavourites.adapters.HomeRecyclerAdapter
import android.test.com.frappfavourites.databases.enitities.InternMission
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(), Observer<List<InternMission>> {

    override fun onChanged(internMissionList: List<InternMission>?) {
        if (internMissionList != null && internMissionList.isNotEmpty()) {
            this.internMissionList.addAll(internMissionList)
            adapter.setInternMission(internMissionList)
        }else{
            //Toast.makeText(context, "done", Toast.LENGTH_SHORT).show()
        }
    }

    private lateinit var imViewModel: InternMissionViewModel
    private val internMissionList = mutableListOf<InternMission>()
    private lateinit var adapter: HomeRecyclerAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        imViewModel = ViewModelProviders.of(this).get(InternMissionViewModel::class.java)
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initRecyclerView()
    }

    fun initRecyclerView() {
        imViewModel.getInternshipAndMissionsFromApi()
        imViewModel.getInternshipAndMissionsFromDb()?.observe(this, this)
        adapter = HomeRecyclerAdapter(internMissionList)
        homeRecyclerView.setHasFixedSize(true)
        homeRecyclerView.layoutManager = LinearLayoutManager(context)
        homeRecyclerView.adapter = adapter
    }
}
