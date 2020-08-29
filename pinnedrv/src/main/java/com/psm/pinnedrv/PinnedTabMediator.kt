package com.psm.pinnedrv

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import java.lang.ref.WeakReference

class PinnedTabMediator(
    private val tabLayout: TabLayout,
    private val recyclerView: PinnedRecycleView,
    private val tabConfigurationStrategy: TabLayoutMediator.TabConfigurationStrategy,
    private val autoRefresh: Boolean = false
) {
    private var attached = false
    private var adapter: PinnedRvAdapter<*>? = null
    private var onHeaderChange: PinnedRecycleView.OnHeaderChange? = null
    private var adapterDataObserver: AdapterDataObserver? = null

    fun attach() {
        if (attached) {
            throw IllegalStateException("${this.javaClass.simpleName} is already attached")
        }

        attached = true

        adapter = (recyclerView.adapter as PinnedRvAdapter?)

        checkNotNull(adapter) {
            throw IllegalStateException("${this.javaClass.simpleName} attached before RecycleView has an adapter")
        }
        onHeaderChange = PinnedTabOnHeaderChangeCallback(tabLayout)
        recyclerView.setHeaderChange(onHeaderChange ?: return)


        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {

            private var cachePo = -1


            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (cachePo != tab?.position) {
                    val items = adapter!!.items
                    val header = adapter!!.header
                    val po = items.indexOf(header[tab?.position ?: return])
                    val layoutManager = (recyclerView.layoutManager as LinearLayoutManager)
                    layoutManager.scrollToPositionWithOffset(po, 0)
                    cachePo = tab.position
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) = Unit

            override fun onTabReselected(tab: TabLayout.Tab?) = Unit
        })

        if (autoRefresh) {
            adapterDataObserver = AdapterDataObserver()
            adapter?.registerAdapterDataObserver(adapterDataObserver!!)
        }

        populateTabsRecycleViewAdapter()
    }

    fun detach() {
        if (autoRefresh && adapter != null && adapterDataObserver != null) {
            adapter?.unregisterAdapterDataObserver(adapterDataObserver!!)
            adapterDataObserver = null
        }
        tabLayout.clearOnTabSelectedListeners()
        recyclerView.clearHeaderChangeListener()
        onHeaderChange = null
        adapter = null
        attached = false
    }

    fun populateTabsRecycleViewAdapter() {
        tabLayout.removeAllTabs()

        if (adapter != null) {
            val adapterCount = adapter?.items
            adapterCount?.forEachIndexed { idx, data ->
                when (data) {
                    is Header -> {
                        val tab = tabLayout.newTab()
                        tabConfigurationStrategy.onConfigureTab(tab, idx)
                        tabLayout.addTab(tab)
                    }
                }
            }
        }
    }


    private class PinnedTabOnHeaderChangeCallback(tabLayout: TabLayout) :
        PinnedRecycleView.OnHeaderChange {

        private var tabLayoutRef: WeakReference<TabLayout>? = null

        init {
            this.tabLayoutRef = WeakReference(tabLayout)
        }

        override fun onHeaderChange(po: Int) {
            val tabLayout = tabLayoutRef?.get()
            tabLayout?.getTabAt(po)?.select()

        }
    }

    inner class AdapterDataObserver : RecyclerView.AdapterDataObserver() {
        override fun onChanged() {
            this@PinnedTabMediator.populateTabsRecycleViewAdapter()
        }
    }
}