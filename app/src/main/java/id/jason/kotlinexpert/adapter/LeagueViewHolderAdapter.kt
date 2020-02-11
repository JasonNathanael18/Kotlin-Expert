package id.jason.kotlinexpert.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.jason.kotlinexpert.model.League
import id.jason.kotlinexpert.ui.ItemLeagueUI
import id.jason.kotlinexpert.view.LeagueDetailActivity
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class LeagueViewHolderAdapter(
    private val listLeague: ArrayList<League>,
    var context: Context
) :
    RecyclerView.Adapter<LeagueViewHolderAdapter.LeagueViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeagueViewHolder {
        return LeagueViewHolder(
            ItemLeagueUI().createView(
                AnkoContext.create(
                    parent.context,
                    parent
                )
            )
        )
    }

    override fun getItemCount(): Int = listLeague.size


    override fun onBindViewHolder(holder: LeagueViewHolder, position: Int) {
        holder.setView(listLeague[position])
    }

    inner class LeagueViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvLeagueName: TextView = itemView.findViewById(ItemLeagueUI.leagueName)
        private val ivLeagueBadge: ImageView = itemView.findViewById(ItemLeagueUI.leaguePhoto)

        fun setView(league: League) {
            Glide.with(itemView.context)
                .load(league.leagueBadge)
                .into(ivLeagueBadge)
            tvLeagueName.text = league.leagueName

            itemView.setOnClickListener {
                val leagueSend = League(
                    league.leagueName,
                    league.leagueId,
                    league.leagueDescription,
                    league.leagueBadge
                )
                context.toast("Showing ${league.leagueName} detail")
                itemView.context.startActivity<LeagueDetailActivity>(LeagueDetailActivity.EXTRA_LEAGUE to leagueSend)
            }
        }
    }
}