package com.demoproj.ui.screen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.mvvmwithhilt.module.dto.FilterRes
import com.demoproj.R
import com.demoproj.viewmodel.HomeScreenViewModel
import kotlinx.android.synthetic.main.activity_splash_screen.*

class FilterListActivity : AppCompatActivity()  {

    var mFilterres: FilterRes? = null
    var mViewModel: HomeScreenViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContentView(R.layout.layout_bottomsheet_itemlist)
        mViewModel = ViewModelProviders.of(this@FilterListActivity).get(HomeScreenViewModel::class.java)
        mViewModel?.filterResLivedata?.value = mFilterres
        mViewModel?.mSelectedAccCount?.value = 0
        mViewModel?.mSelectedBrandCount?.value = 0
        mViewModel?.mSelectedLocationCount?.value = 0
        btn_filter?.setOnClickListener {
            val addPhotoBottomDialogFragment: ApplyFilterBottomDialogFragment =
                ApplyFilterBottomDialogFragment.newInstance()
            addPhotoBottomDialogFragment.show(
                supportFragmentManager,
                "AddPhotoBottomDialogFragment"
            )
        }

    }


}