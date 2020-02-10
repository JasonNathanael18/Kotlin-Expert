package id.jason.kotlinexpert.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import id.jason.kotlinexpert.adapter.LeagueViewHolderAdapter
import org.jetbrains.anko.matchParent
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.verticalLayout

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        verticalLayout {
            recyclerView {
                lparams(width = matchParent, height =  matchParent)
                layoutManager = LinearLayoutManager(context)
                adapter = LeagueViewHolderAdapter()
            }
        }
    }
}
