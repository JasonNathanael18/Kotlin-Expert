package id.jason.kotlinexpert.view

import android.content.res.TypedArray
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import id.jason.kotlinexpert.R
import id.jason.kotlinexpert.adapter.LeagueViewHolderAdapter
import id.jason.kotlinexpert.model.League
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var leagueNameData: Array<String>
    private lateinit var leagueIdData: Array<String>
    private lateinit var leagueDescData: Array<String>
    private lateinit var leagueBadgeData: TypedArray
    private var leagues = arrayListOf<League>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.title = resources.getString(R.string.actionbar_home_title)

        prepareData()
        addData()
        initRecyclerView()
    }

    private fun prepareData() {
        leagueNameData = resources.getStringArray(R.array.league_name_data)
        leagueIdData = resources.getStringArray(R.array.league_id_data)
        leagueDescData = resources.getStringArray(R.array.league_desc_data)
        leagueBadgeData = resources.obtainTypedArray(R.array.league_badge_data)
    }

    private fun addData() {
        for (position in leagueNameData.indices) {
            val league = League(
                leagueNameData[position],
                leagueIdData[position],
                leagueDescData[position],
                leagueBadgeData.getResourceId(position, -1)
            )
            leagues.add(league)
        }
    }

    private fun initRecyclerView(){
        rv_home.layoutManager = GridLayoutManager(this,2)
        val listHeroAdapter = LeagueViewHolderAdapter(leagues, this)
        rv_home.adapter = listHeroAdapter
    }

}
