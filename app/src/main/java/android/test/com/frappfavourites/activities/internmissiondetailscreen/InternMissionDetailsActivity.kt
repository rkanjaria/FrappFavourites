package android.test.com.frappfavourites.activities.internmissiondetailscreen

import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.test.com.frappfavourites.R
import android.test.com.frappfavourites.classes.INTERNSHIP
import android.test.com.frappfavourites.classes.INTERNSHIP_VALUE
import android.test.com.frappfavourites.classes.loadCircularImage
import android.test.com.frappfavourites.databases.enitities.InternMission
import android.text.Html
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_intern_mission_details.*

class InternMissionDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intern_mission_details)
        setSupportActionBar(toolbar as Toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        initUI(intent.getParcelableExtra(INTERNSHIP_VALUE))
    }

    private fun initUI(internMission: InternMission?) {
        supportActionBar?.title = if (internMission?.imType == INTERNSHIP) "Internship" else "Mission"
        logo.loadCircularImage(internMission?.logo)
        mainTitle.text = internMission?.title
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            mainDescription.text = Html.fromHtml(internMission?.description, Html.FROM_HTML_MODE_LEGACY)
        } else {
            @Suppress("DEPRECATION")
            mainDescription.text = Html.fromHtml(internMission?.description)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> super.onBackPressed()
        }
        return true
    }
}
