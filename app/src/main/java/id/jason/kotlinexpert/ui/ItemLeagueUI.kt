package id.jason.kotlinexpert.ui

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import org.jetbrains.anko.*

class ItemLeagueUI : AnkoComponent<ViewGroup> {
    companion object {
        const val leagueName = 1
        const val leaguePhoto = 2
    }

    override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui) {
        verticalLayout {

            lparams(matchParent, wrapContent)
            {
                margin = dip(4)
            }
            gravity = Gravity.CENTER
            padding = dip(8)
            background = ColorDrawable(Color.parseColor("#32407b"))

            imageView {
                id = leaguePhoto
                layoutParams = LinearLayout.LayoutParams(dip(100), dip(100))
                padding = dip(20)
            }

            textView {
                id = leagueName
                layoutParams = LinearLayout.LayoutParams(matchParent, wrapContent)
                textSize = 14f
                gravity = Gravity.CENTER
                textColor = Color.WHITE
            }
        }
    }
}