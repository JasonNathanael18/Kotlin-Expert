package id.jason.kotlinexpert.adapter

import android.content.Context
import androidx.annotation.Nullable
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import id.jason.kotlinexpert.R
import id.jason.kotlinexpert.view.EventFragment

class EventsPagerAdapter(private val mContext: Context, fm: FragmentManager, private val leagueId: String) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    @StringRes
    private val TAB_TITLES = intArrayOf(R.string.tab_event_last_match, R.string.tab_event_next_match)

    override fun getItem(position: Int): Fragment {
        return EventFragment.newInstance(position+1, leagueId)
    }
    @Nullable
    override fun getPageTitle(position: Int): CharSequence? {
        return mContext.resources.getString(TAB_TITLES[position])
    }
    override fun getCount(): Int {
        return 2
    }
}