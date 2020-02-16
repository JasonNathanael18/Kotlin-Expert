package id.jason.kotlinexpert.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import id.jason.kotlinexpert.R
import id.jason.kotlinexpert.helper.Constants
import id.jason.kotlinexpert.model.League
import kotlinx.android.synthetic.main.activity_league_detail.*

class LeagueDetailActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_LEAGUE = "extra_league"
    }

    private lateinit var leagueDetail: League

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_league_detail)

        supportActionBar!!.title = resources.getString(R.string.actionbar_detail_title)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        leagueDetail = intent.getParcelableExtra(EXTRA_LEAGUE) as League

        setData(leagueDetail)
        setClickListener()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun setData(detail: League) {
        tv_league_name_detail.text = detail.leagueName
        tv_league_desc_detail.text = detail.leagueDescription
        Glide.with(this).load(detail.leagueBadge).into(iv_league_badge_detail)
    }

    private fun setClickListener(){
        btn_league_event.setOnClickListener {
            val intent = Intent(this, ShowEventsActivity::class.java)
            intent.putExtra(Constants.IntentBundle.LEAGUE_ID,leagueDetail.leagueId)
            startActivity(intent)
        }
    }
}
