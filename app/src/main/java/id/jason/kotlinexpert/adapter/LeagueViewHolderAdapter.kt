package id.jason.kotlinexpert.adapter

import android.content.Intent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.jason.kotlinexpert.model.League
import id.jason.kotlinexpert.ui.ItemLeagueUI
import org.jetbrains.anko.AnkoContext

class LeagueViewHolderAdapter(private val listLeague: ArrayList<League>) :
    RecyclerView.Adapter<LeagueViewHolderAdapter.LeagueViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeagueViewHolder {
        return LeagueViewHolder(ItemLeagueUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun getItemCount(): Int = listLeague.size


    override fun onBindViewHolder(holder: LeagueViewHolder, position: Int) {
        holder.setView(listLeague[position])
    }

    inner class LeagueViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvLeagueName: TextView = itemView.findViewById(ItemLeagueUI.leagueName)
        var ivLeagueBadge: ImageView = itemView.findViewById(ItemLeagueUI.leaguePhoto)

        fun setView(league: League) {
            Glide.with(itemView.context)
                .load(league.showPhoto)
                .into(ivLeagueBadge)
            tvLeagueName.text = league.leagueName

//            itemView.setOnClickListener {
//                val movieSend = Shows(
//                    shows.showTitle,
//                    shows.showDate,
//                    shows.showDesc,
//                    shows.showRating,
//                    shows.showPhoto
//                )
//                val intentToDetail = Intent(itemView.context, ShowDetailActivity::class.java)
//                intentToDetail.putExtra(ShowDetailActivity.EXTRA_SHOWS, movieSend)
//                itemView.context.startActivity(intentToDetail)
//            }
        }
    }
}