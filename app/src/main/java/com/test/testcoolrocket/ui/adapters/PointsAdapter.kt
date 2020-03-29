package com.test.testcoolrocket.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.test.testcoolrocket.R
import com.test.testcoolrocket.network.pojo.PointResponse


class PointsAdapter : RecyclerView.Adapter<PointsAdapter.PointViewHolder>() {
    private var adapterList = emptyList<PointResponse>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PointViewHolder {
        return PointViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_point_layout, parent, false)
        )
    }

    override fun getItemCount() = adapterList.size

    override fun onBindViewHolder(holder: PointViewHolder, position: Int) {
        holder.bind(adapterList[position])
    }

    fun updateAdapterList(list: List<PointResponse>) {
        adapterList = list
        notifyDataSetChanged()
    }

    inner class PointViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title: TextView = itemView.findViewById(R.id.item_point_titleText)

        fun bind(point: PointResponse) {
            "x : ${point.x}\ny : ${(point.y)}".also {
                title.text = it
            }
        }
    }
}