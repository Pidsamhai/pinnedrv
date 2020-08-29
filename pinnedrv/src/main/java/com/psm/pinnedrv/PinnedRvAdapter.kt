package com.psm.pinnedrv

import androidx.recyclerview.widget.RecyclerView

abstract class PinnedRvAdapter<VH: RecyclerView.ViewHolder> : RecyclerView.Adapter<VH>() {

    val items = mutableListOf<DataSet>()

    val header
        get() = items.filter {
            when(it) {
                is Header -> true
                else -> false
            }
        }

    override fun getItemViewType(position: Int): Int = when (items[position]) {
        is Header -> HEADER
        is Child -> CHILD
    }

    companion object {
        const val HEADER = 0
        const val CHILD = 1
    }

    override fun getItemCount():Int = items.size
}