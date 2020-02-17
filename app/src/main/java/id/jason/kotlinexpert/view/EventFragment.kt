package id.jason.kotlinexpert.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import id.jason.kotlinexpert.R
import id.jason.kotlinexpert.adapter.EventsViewHolderAdapter
import id.jason.kotlinexpert.model.Events
import id.jason.kotlinexpert.view_model.EventViewModel
import kotlinx.android.synthetic.main.event_fragment.*

class EventFragment : Fragment() {

    private lateinit var viewModel: EventViewModel
    private lateinit var adapter: EventsViewHolderAdapter
    private var index: Int = 0
    private var leagueId: String = ""

    companion object {
        private const val ARG_SECTION_NUMBER = "section_number"
        private const val LEAGUE_ID = "league_id"

        fun newInstance(index: Int, leagueId: String): EventFragment {
            val fragment = EventFragment()
            val bundle = Bundle()
            bundle.putInt(ARG_SECTION_NUMBER, index)
            bundle.putString(LEAGUE_ID, leagueId)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.event_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        index = arguments?.getInt(ARG_SECTION_NUMBER, 0) as Int
        leagueId = arguments?.getString(LEAGUE_ID, "") as String
        initRecyclerView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(EventViewModel::class.java)
        val error = resources.getString(R.string.error)
        if (arguments != null) {
            when (index) {
                1 -> {
                    viewModel.setDataPreviousEvent(leagueId, context, error)
                    showLoading(true)

                    viewModel.getDataPreviousEvent().observe(viewLifecycleOwner, Observer { t ->
                        if (t?.events.isNullOrEmpty()) {
                            showLoading(false)
                            showEmpty(true)
                        } else {
                            t?.events?.let {
                                showEmpty(false)
                                showData(it)
                            }
                        }
                    })
                }

                2 -> {
                    viewModel.setDataNextEvent(leagueId, context, error)
                    showLoading(true)

                    viewModel.getDataNextEvent().observe(viewLifecycleOwner, Observer { t ->
                        if (t?.events.isNullOrEmpty()) {
                            showLoading(false)
                            showEmpty(true)
                        } else {
                            t?.events?.let {
                                showEmpty(false)
                                showData(it)
                            }
                        }
                    })
                }
            }
        }
    }

    private fun initRecyclerView() {
        adapter = EventsViewHolderAdapter()
        adapter.notifyDataSetChanged()
        rv_event.layoutManager = LinearLayoutManager(context)
        rv_event.adapter = adapter
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            pb_event.visibility = View.VISIBLE
            rv_event.visibility = View.GONE
        } else {
            pb_event.visibility = View.GONE
            rv_event.visibility = View.VISIBLE
        }
    }

    private fun showData(data: List<Events>) {
        adapter.setData(data as ArrayList<Events>)
        showLoading(false)
    }

    private fun showEmpty(state: Boolean) {
        if (state) {
            empty_message.visibility = View.VISIBLE
            rv_event.visibility = View.GONE
        } else empty_message.visibility = View.GONE
    }
}
