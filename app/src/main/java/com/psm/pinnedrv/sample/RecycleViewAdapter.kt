package com.psm.pinnedrv.sample

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.psm.pinnedrv.*
import com.psm.pinnedrv.PinnedRvAdapter.Companion.HEADER
import kotlinx.android.synthetic.main.header.view.*
import kotlinx.android.synthetic.main.product.view.*

class RecycleViewAdapter : PinnedRvAdapter<RecycleViewAdapter.VH>() {

    class VH(v: View): RecyclerView.ViewHolder(v)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return when (viewType) {
            HEADER -> VH(
                LayoutInflater.from(parent.context).inflate(R.layout.header, parent, false)
            )
            else -> VH(
                LayoutInflater.from(parent.context).inflate(R.layout.product, parent, false)
            )
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: VH, position: Int) {
        when (val item = items[position]) {
            is Header -> {
                (item as Category)
                holder.itemView.header.text = item.categoryName
            }
            is Child -> {
                holder.itemView.apply {
                    (item as Product)
                    name.text = item.name
                    price.text = "$ %,.2f".format(item.price)
                }
            }
        }
    }
}