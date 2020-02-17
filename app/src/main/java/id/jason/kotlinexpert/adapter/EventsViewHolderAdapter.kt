package id.jason.kotlinexpert.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.jason.kotlinexpert.R
import id.jason.kotlinexpert.helper.Constants
import id.jason.kotlinexpert.model.Events
import id.jason.kotlinexpert.view.EventDetailActivity
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_event_list.*

class EventsViewHolderAdapter : RecyclerView.Adapter<EventsViewHolderAdapter.EventsViewHolder>() {
    private val mData = ArrayList<Events>()
    fun setData(items: ArrayList<Events>) {
        mData.clear()
        mData.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_event_list, parent, false)
        return EventsViewHolder(view)
    }

    override fun getItemCount(): Int = mData.size


    override fun onBindViewHolder(holder: EventsViewHolder, position: Int) {
        holder.setView(mData[position])
    }

    inner class EventsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        LayoutContainer {
        override val containerView: View?
            get() = itemView

        @SuppressLint("SetTextI18n")
        fun setView(events: Events) {
            tv_item_event_name.text = events.strEvent
            tv_item_event_date.text = events.strDate
            tv_item_home_team.text = events.strHomeTeam
            tv_item_away_team.text = events.strAwayTeam
            val homeScore = events.intHomeScore ?: "0"
            val awayScore = events.intAwayScore ?: "0"
            tv_item_score.text = "$homeScore : $awayScore"
            if (events.intHomeScore.isNullOrEmpty()) tv_status.visibility = View.INVISIBLE

            itemView.setOnClickListener {
                val intentToEventDetail = Intent(itemView.context, EventDetailActivity::class.java)
                intentToEventDetail.putExtra(Constants.IntentBundle.EVENT_ID, events.idEvent)
                intentToEventDetail.putExtra(Constants.IntentBundle.HOME_TEAM_ID, events.idHomeTeam)
                intentToEventDetail.putExtra(Constants.IntentBundle.AWAY_TEAM_ID, events.idAwayTeam)
                itemView.context.startActivity(intentToEventDetail)
            }
        }
    }
}