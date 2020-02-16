package id.jason.kotlinexpert.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.jason.kotlinexpert.R
import id.jason.kotlinexpert.model.League
import id.jason.kotlinexpert.view.LeagueDetailActivity
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_league_list.*

class LeagueViewHolderAdapter(
    private val listLeague: ArrayList<League>,
    var context: Context
) :
    RecyclerView.Adapter<LeagueViewHolderAdapter.LeagueViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeagueViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_league_list, parent, false)
        return LeagueViewHolder(view)
    }

    override fun getItemCount(): Int = listLeague.size


    override fun onBindViewHolder(holder: LeagueViewHolder, position: Int) {
        holder.setView(listLeague[position])
    }

    inner class LeagueViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        LayoutContainer {

        override val containerView: View?
            get() = itemView

        fun setView(league: League) {
            Glide.with(context)
                .load(league.leagueBadge)
                .into(iv_league_badge)
            tv_league_name.text = league.leagueName

            itemView.setOnClickListener {
                val intentToDetail = Intent(context, LeagueDetailActivity::class.java)
                intentToDetail.putExtra(LeagueDetailActivity.EXTRA_LEAGUE, league)
                itemView.context.startActivity(intentToDetail)
            }
        }
    }
}