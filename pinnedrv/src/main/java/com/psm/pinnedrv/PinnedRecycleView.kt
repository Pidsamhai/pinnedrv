package com.psm.pinnedrv

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout

class PinnedRecycleView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyle: Int = 0
) : RecyclerView(context, attributeSet, defStyle) {

    private var onHeaderChange: OnHeaderChange? = null
    var pinnedAdapter: PinnedRvAdapter<*>? = null
        set(value) {
            this.adapter = value
            field = value
        }
    private var cachePo = -1
    private var tabLayout: TabLayout? = null

    fun setHeaderChange(onHeaderChange: OnHeaderChange) {
        this.onHeaderChange = onHeaderChange
    }

    fun clearHeaderChangeListener() {
        this.onHeaderChange = null
    }

    override fun onScrollChanged(l: Int, t: Int, oldl: Int, oldt: Int) {
        val vsPo = (layoutManager as LinearLayoutManager?)?.findFirstVisibleItemPosition() ?: return
        when(val h = pinnedAdapter?.items?.get(vsPo)) {
            is Header -> {
                if (vsPo != cachePo) {
                    onHeaderChange?.onHeaderChange(calculateItemChange(vsPo))
                    cachePo = vsPo
                    tabLayout?.getTabAt(pinnedAdapter?.header?.indexOf(h) ?: return)?.select()
                }
            }
        }
        super.onScrollChanged(l, t, oldl, oldt)
    }

    private fun calculateItemChange(po: Int): Int {
        val header = (adapter as PinnedRvAdapter).header
        val items = (adapter as PinnedRvAdapter).items
        return header.indexOf(items[po])
    }

    interface OnHeaderChange {
        fun onHeaderChange(po: Int)
    }

}