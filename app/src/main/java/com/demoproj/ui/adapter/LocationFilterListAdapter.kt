package com.demoproj.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mvvmwithhilt.module.dto.LocationNameListItem
import com.demoproj.R
import com.demoproj.listener.OnItemClickListener
import kotlinx.android.synthetic.main.item_filter_item.view.*


class LocationFilterListAdapter(
    var context: Context?,
    val listener: OnItemClickListener,
    var mList: ArrayList<LocationNameListItem>
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ZoneListViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_filter_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder?.itemView?.chk_filter_item?.text = "${mList?.get(position)?.locationName}"
        holder?.itemView?.chk_filter_item?.isChecked = mList?.get(position)?.isSelected
        holder?.itemView?.chk_filter_item?.setOnCheckedChangeListener { compoundButton, b ->
            mList?.get(position)?.isSelected = b
            listener?.onClick(position, holder?.itemView?.chk_filter_item, mList?.get(position))
        }
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
