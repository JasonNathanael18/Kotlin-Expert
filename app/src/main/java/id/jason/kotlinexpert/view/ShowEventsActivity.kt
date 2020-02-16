package id.jason.kotlinexpert.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import id.jason.kotlinexpert.R
import id.jason.kotlinexpert.adapter.EventsPagerAdapter
import id.jason.kotlinexpert.helper.Constants
import kotlinx.android.synthetic.main.activity_show_events.*

class ShowEventsActivity : AppCompatActivity() {

    private lateinit var leagueId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_events)

        leagueId = intent.getStringExtra(Constants.IntentBundle.LEAGUE_ID) ?: ""

        val eventsPagerAdapter = EventsPagerAdapter(this, supportFragmentManager, leagueId)
        vp_event.adapter = eventsPagerAdapter
        tab_event.setupWithViewPager(vp_event)
        supportActionBar?.elevation = 0f
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)

        val searchItem = menu.findItem(R.id.app_bar_search)
        val searchView = searchItem?.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                goToSearch(query)
                return true
            }
        })
        return true
    }

    private fun goToSearch(query: String?) {
        val intent = Intent(this, EventSearchActivity::class.java)
        intent.putExtra(Constants.IntentBundle.EVENT_SEARCH,query)
        startActivity(intent)
    }
}
