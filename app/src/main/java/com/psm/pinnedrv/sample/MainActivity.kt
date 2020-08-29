package com.psm.pinnedrv.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.psm.pinnedrv.PinnedTabMediator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dataSet = getMockData()

        val adapter = RecycleViewAdapter()
        recycleView.pinnedAdapter = adapter
        adapter.items.addAll(dataSet)
        adapter.notifyDataSetChanged()

        PinnedTabMediator(
            tabLayout,
            recycleView,
            { tab, position ->
                tab.text = (dataSet[position] as Category).categoryName
            }
        ).attach()

    }
}