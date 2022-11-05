package com.demoproj.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mvvmwithhilt.module.dto.FilterDataItem
import com.demoproj.R
import com.demoproj.listener.OnItemClickListener
import kotlinx.android.synthetic.main.item_filter_selected_item.view.*


class ZoneListAdapter(
    var context: Context?,
    val listener: OnItemClickListener,
    var mList: ArrayList<FilterDataItem>
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ZoneListViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_filter_selected_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder?.itemView?.tv_selected_item?.text = "${mList?.get(position)?.companyName}"
    }

    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }


    override fun getItemCount(): Int {
        return mList.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    class ZoneListViewHolder(view: View) : RecyclerView.ViewHolder(view)


}
