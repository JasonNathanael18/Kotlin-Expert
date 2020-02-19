package id.jason.kotlinexpert.adapter

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import id.jason.kotlinexpert.R
import id.jason.kotlinexpert.view.FavouriteFragment

class FavouriteTabPagingAdapter(
    private val mContext: Context,
    fm: FragmentManager
) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    @StringRes
    private val TAB_TITLES =
        intArrayOf(R.string.tab_event_last_match, R.string.tab_event_next_match)

    override fun getItem(position: Int): Fragment {
        return FavouriteFragment.newInstance(position + 1)
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mContext.resources.getString(TAB_TITLES[position])
    }
}