package id.jason.kotlinexpert.view

import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import id.jason.kotlinexpert.R
import id.jason.kotlinexpert.model.League
import org.jetbrains.anko.*

class LeagueDetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_LEAGUE = "extra_league"
        const val txtLeagueName = 4
        const val txtLeagueDesc = 5
        const val ivLeaguePhoto = 6
    }

    private lateinit var leagueDetail: League

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar!!.title = resources.getString(R.string.actionbar_detail_title)
        leagueDetail = intent.getParcelableExtra(EXTRA_LEAGUE) as League
        generateView()
        setData()
    }

    private fun generateView() {
        scrollView {
            lparams(matchParent, matchParent)
            verticalLayout {
                lparams(matchParent, matchParent){
                    leftMargin = dip(5)
                    rightMargin = dip(5)
                }
                gravity = Gravity.CENTER

                imageView {
                    id = ivLeaguePhoto
                    layoutParams = LinearLayout.LayoutParams(dip(200), dip(200))
                    padding = dip(20)
                }

                textView {
                    id = txtLeagueName
                    layoutParams = LinearLayout.LayoutParams(matchParent, wrapContent)
                    textSize = 22f
                    gravity = Gravity.CENTER
                    typeface = Typeface.DEFAULT_BOLD
                }

                verticalLayout {
                    lparams(matchParent, dip(2)){
                        topMargin = dip(5)
                        bottomMargin = dip(5)
                        background = ColorDrawable(Color.parseColor("#32407b"))
                    }
                }

                textView {
                    id = txtLeagueDesc
                    layoutParams = LinearLayout.LayoutParams(matchParent, wrapContent)
                    textSize = 14f
                    gravity = Gravity.LEFT
                }.lparams{
                    bottomMargin = dip(10)
                }
            }
        }
    }

    private fun setData() {
        val tvLeagueName: TextView = findViewById(txtLeagueName)
        val tvLeagueDesc: TextView = findViewById(txtLeagueDesc)
        val ivLeagueBadge: ImageView = findViewById(ivLeaguePhoto)

        tvLeagueName.text = leagueDetail.leagueName
        tvLeagueDesc.text = leagueDetail.leagueDescription
        Glide.with(this).load(leagueDetail.leagueBadge).into(ivLeagueBadge)
    }
}
