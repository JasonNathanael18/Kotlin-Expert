package id.jason.kotlinexpert.ui

import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import org.jetbrains.anko.*

class ItemLeagueUI: AnkoComponent<ViewGroup> {
    companion object {
        val leagueName = 1
        val leagueDesc = 2
        val leaguePhoto = 3
    }
    override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui){
        verticalLayout {

            lparams(wrapContent, wrapContent)
            padding = dip(16)

            imageView().lparams(width = matchParent){
                id = leaguePhoto
                padding = dip(20)
                margin = dip(15)
            }

            textView{
                id = leagueName
                layoutParams = LinearLayout.LayoutParams(matchParent, wrapContent)
                textSize = 14f
            }
        }
    }
}