package id.jason.kotlinexpert.view

import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import id.jason.kotlinexpert.R
import id.jason.kotlinexpert.adapter.EventsViewHolderAdapter
import id.jason.kotlinexpert.adapter.SearchEventViewHolderAdapter
import id.jason.kotlinexpert.helper.Constants
import id.jason.kotlinexpert.model.Event
import id.jason.kotlinexpert.view_model.EventSearchViewModel
import kotlinx.android.synthetic.main.activity_event_search.*

class EventSearchActivity : AppCompatActivity() {

    private lateinit var eventSearch: String
    private lateinit var viewModel: EventSearchViewModel
    private lateinit var adapter: SearchEventViewHolderAdapter
    private lateinit var error: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_search)
        supportActionBar?.elevation = 0f
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        eventSearch = intent.getStringExtra(Constants.IntentBundle.EVENT_SEARCH) ?: ""
        initRecyclerView()

        viewModel = ViewModelProviders.of(this).get(EventSearchViewModel::class.java)
        error =resources.getString(R.string.error)
        viewModel.setDataSearchEvent(eventSearch,this,error)
        showLoading(true)

        viewModel.getDataSearchEvent().observe(this, Observer {
                t ->
            if (t?.events.isNullOrEmpty()) {
                showLoading(false)
                showEmpty(true)
            } else {
                t?.events?.let {
                    showData(it)
                }
            }
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun initRecyclerView(){
        adapter = SearchEventViewHolderAdapter()
        adapter.notifyDataSetChanged()
        rv_search_event.layoutManager = LinearLayoutManager(this)
        rv_search_event.adapter = adapter
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            pb_search_event.visibility = View.VISIBLE
            rv_search_event.visibility = View.GONE
        } else {
            pb_search_event.visibility = View.GONE
            rv_search_event.visibility = View.VISIBLE
        }
    }

    private fun showData(data: List<Event>) {
        val dataFiltered = data.filter {
            it.strSport == "Soccer"
        }
        if (dataFiltered.isNullOrEmpty()) {
            showEmpty(true)
            showLoading(false)
            rv_search_event.visibility = View.GONE
        }
        else{
            showEmpty(false)
            adapter.setData(dataFiltered as ArrayList<Event>)
            showLoading(false)
        }
    }

    private fun showEmpty(state: Boolean) {
        if(state){
            empty_message.visibility = View.VISIBLE
            rv_search_event.visibility = View.GONE
        }
        else empty_message.visibility = View.GONE
    }

    private fun goToSearch(query: String?) {
        if (query != null) {
            viewModel.setDataSearchEvent(query,this,error)
            showLoading(true)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)

        val searchItem = menu.findItem(R.id.app_bar_search)
        val searchView = searchItem?.actionView as SearchView
        searchView.setQuery(eventSearch,false)
        searchView.isIconifiedByDefault = true
        searchView.isIconified = false
        searchView.isFocusable = true
        searchView.clearFocus()

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                showLoading(true)
                showEmpty(false)
                goToSearch(query)
                searchView.clearFocus()
                return true
            }
        })
        return true
    }
}
