package com.demoproj.listener

import android.view.View


interface OnItemClickListener {
    fun onClick(pos: Int, view: View, obj: Any)
}