package id.jason.kotlinexpert.view

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import id.jason.kotlinexpert.R
import id.jason.kotlinexpert.helper.Constants
import id.jason.kotlinexpert.model.Events
import id.jason.kotlinexpert.model.Team
import id.jason.kotlinexpert.view_model.EventDetailViewModel
import id.jason.kotlinexpert.view_model.FavouriteViewModel
import kotlinx.android.synthetic.main.activity_event_detail.*

class EventDetailActivity : AppCompatActivity() {

    private lateinit var eventId: String
    private lateinit var homeTeamId: String
    private lateinit var awayTeamId: String
    private lateinit var viewModel: EventDetailViewModel
    private lateinit var viewModelFavourite: FavouriteViewModel
    private var eventDetail: List<Events> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_detail)

        eventId = intent.getStringExtra(Constants.IntentBundle.EVENT_ID) ?: ""
        homeTeamId = intent.getStringExtra(Constants.IntentBundle.HOME_TEAM_ID) ?: ""
        awayTeamId = intent.getStringExtra(Constants.IntentBundle.AWAY_TEAM_ID) ?: ""

        supportActionBar?.title = resources.getString(R.string.actionbar_event_detail_title)
        supportActionBar?.elevation = 0f
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        viewModel = ViewModelProvider(this).get(EventDetailViewModel::class.java)
        viewModelFavourite =  ViewModelProvider(this).get(FavouriteViewModel::class.java)
        val error = resources.getString(R.string.error)
        viewModel.setDataEventDetail(eventId, this, error)
        showLoading(true)

        viewModel.getDataEventDetail().observe(this, Observer { t ->
            t?.events?.let { setData(it) }
        })

        viewModel.setDataHomeTeam(homeTeamId,this,error)

        viewModel.getDataHomeTeam().observe(this, Observer {
                t ->
            t?.events?.let { setHomeBadge(it) }
        })

        viewModel.setDataAwayTeam(awayTeamId,this,error)

        viewModel.getDataAwayTeam().observe(this, Observer {
                t ->
            t?.events?.let { setAwayBadge(it) }
        })

        setClickListener()
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            pb_event_detail.visibility = View.VISIBLE
        } else {
            pb_event_detail.visibility = View.GONE
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    @SuppressLint("SetTextI18n")
    private fun setData(events: List<Events>) {
        eventDetail = events
        tv_item_event_name.text = events[0].strEvent
        tv_item_event_date.text = events[0].strDate
        tv_item_home_team.text = events[0].strHomeTeam
        tv_item_away_team.text = events[0].strAwayTeam
        val homeScore = events[0].intHomeScore ?: "0"
        val awayScore = events[0].intAwayScore ?: "0"
        tv_item_score.text = "$homeScore : $awayScore"
        if (events[0].intHomeScore.isNullOrEmpty()) tv_status.visibility = View.INVISIBLE

        tv_home_scorers.text = events[0].strHomeGoalDetails?.replace(";", "\n") ?: ""
        tv_away_scorers.text = events[0].strAwayGoalDetails?.replace(";", "\n") ?: ""

        tv_home_red_card.text = events[0].strHomeRedCards?.replace(";", "\n") ?: ""
        tv_away_red_card.text = events[0].strAwayRedCards?.replace(";", "\n") ?: ""

        tv_home_yellow_card.text = events[0].strHomeYellowCards?.replace(";", "\n") ?: ""
        tv_away_yellow_card.text = events[0].strAwayYellowCards?.replace(";", "\n") ?: ""

        val homeGK = events[0].strHomeLineupGoalkeeper?.replace("; ", "\n") ?: ""
        val homeDEF = events[0].strHomeLineupDefense?.replace("; ", "\n") ?: ""
        val homeMID = events[0].strHomeLineupMidfield?.replace("; ", "\n") ?: ""
        val homeFW = events[0].strHomeLineupForward?.replace("; ", "\n") ?: ""
        tv_home_lineup.text = homeGK + homeDEF + homeMID + homeFW

        val awayGK = events[0].strAwayLineupGoalkeeper?.replace("; ", "\n") ?: ""
        val awayDEF = events[0].strAwayLineupDefense?.replace("; ", "\n") ?: ""
        val awayMID = events[0].strAwayLineupMidfield?.replace("; ", "\n") ?: ""
        val awayFW = events[0].strAwayLineupForward?.replace("; ", "\n") ?: ""
        tv_away_lineup.text = awayGK + awayDEF + awayMID + awayFW

        tv_home_substitutes.text = events[0].strHomeLineupSubstitutes?.replace("; ", "\n") ?: ""
        tv_away_substitutes.text = events[0].strAwayLineupSubstitutes?.replace("; ", "\n") ?: ""

        showLoading(false)
    }

    private fun setHomeBadge(home: List<Team>){
        Glide.with(this)
            .load(home[0].strTeamBadge)
            .into(home_badge)
    }

    private fun setAwayBadge(away: List<Team>){
        Glide.with(this)
            .load(away[0].strTeamBadge)
            .into(away_badge)
    }

    private fun setClickListener(){
        btn_favourite.setOnClickListener {
            Toast.makeText(this, getString(R.string.add_to_favorite), Toast.LENGTH_LONG).show()
            viewModelFavourite.insert(eventDetail[0])
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_favourite, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.go_to_favourite) {
            val mIntent = Intent(this,FavouriteListActivity::class.java)
            startActivity(mIntent)
        }
        return super.onOptionsItemSelected(item)
    }
}
