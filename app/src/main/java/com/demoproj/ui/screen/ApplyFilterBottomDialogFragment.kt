package com.demoproj.ui.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.mvvmwithhilt.module.dto.CategoryPojo
import com.mvvmwithhilt.module.dto.FilterRes
import com.demoproj.R
import com.demoproj.listener.OnItemClickListener
import com.demoproj.ui.adapter.CategoryListAdapter
import com.demoproj.ui.adapter.ZoneListAdapter
import com.demoproj.viewmodel.HomeScreenViewModel
import kotlinx.android.synthetic.main.layout_bottomsheet_filter.*
import kotlinx.android.synthetic.main.layout_bottomsheet_filter.iv_close


class ApplyFilterBottomDialogFragment : BottomSheetDialogFragment(), OnItemClickListener {

    var mViewModel: HomeScreenViewModel? = null
    var mFilterres: FilterRes? = null
    var mSelectedAccCount = 0
    var mSelectedBrandCount = 0
    var mSelectedLocationCount = 0
    var mCategoryCountList: ArrayList<CategoryPojo> = arrayListOf()
    var mCategoryListAdapter:CategoryListAdapter? = null

    @Nullable
    override fun onCreateView(
        inflater: LayoutInflater,
        @Nullable container: ViewGroup?,
        @Nullable savedInstanceState: Bundle?
    ): View? {

        // get the views and attach the listener
        return inflater.inflate(
            R.layout.layout_bottomsheet_filter, container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel = ViewModelProviders.of(activity!!).get(HomeScreenViewModel::class.java)
        mViewModel?.filterResLivedata?.observe(this, Observer<FilterRes> {
            mFilterres = it
            rv_zones.layoutManager =
                LinearLayoutManager(
                    activity!!,
                    RecyclerView.HORIZONTAL,
                    false
                )
            rv_zones.adapter = ZoneListAdapter(
                activity,
                this,
                it?.filterData!!
            )
        })
        mViewModel?.mSelectedAccCount?.observe(this, Observer<Int> {
            mSelectedAccCount = it
            tv_count_acc?.text = "${it}"
            setUpCounts(0,it)
        })
        mViewModel?.mSelectedBrandCount?.observe(this, Observer<Int> {
            mSelectedBrandCount = it
            tv_count_brand?.text = "${it}"
            setUpCounts(1,it)
        })
        mViewModel?.mSelectedLocationCount?.observe(this, Observer<Int> {
            mSelectedLocationCount = it
            tv_count_location?.text = "${it}"
            setUpCounts(2,it)
        })
        setUpCounts()
        iv_close?.setOnClickListener {
            dismiss()
        }
        tv_select_acc_no?.setOnClickListener {
            mViewModel?.mSelectedCategory?.value = "acc"
            val filterBottomDialogFragment: FilterListBottomDialogFragment =
                FilterListBottomDialogFragment.newInstance()
            filterBottomDialogFragment?.isCancelable = false
            filterBottomDialogFragment.show(
                activity?.supportFragmentManager,
                "filterBottomDialogFragment"
            )
        }
        tv_select_brand?.setOnClickListener {
            mViewModel?.mSelectedCategory?.value = "brand"
            val filterBottomDialogFragment: FilterListBottomDialogFragment =
                FilterListBottomDialogFragment.newInstance()
            filterBottomDialogFragment?.isCancelable = false
            filterBottomDialogFragment.show(
                activity?.supportFragmentManager,
                "filterBottomDialogFragment"
            )
        }
        tv_select_location?.setOnClickListener {
            mViewModel?.mSelectedCategory?.value = "location"
            val filterBottomDialogFragment: FilterListBottomDialogFragment =
                FilterListBottomDialogFragment.newInstance()
            filterBottomDialogFragment?.isCancelable = false
            filterBottomDialogFragment.show(
                activity?.supportFragmentManager,
                "filterBottomDialogFragment"
            )
        }

        tv_clear?.setOnClickListener {
            clearAllSelectedItems()
        }
    }

    companion object {
        fun newInstance(): ApplyFilterBottomDialogFragment {
            return ApplyFilterBottomDialogFragment()
        }
    }

    fun clearAllSelectedItems() {
        mViewModel?.mSelectedAccCount?.value = 0
        mViewModel?.mSelectedBrandCount?.value = 0
        mViewModel?.mSelectedLocationCount?.value = 0
        mViewModel?.filterResLivedata?.value?.filterData?.get(0)?.hierarchy?.forEachIndexed { accountIndex, acc ->
            mViewModel?.filterResLivedata?.value?.filterData?.get(0)?.hierarchy?.get(accountIndex)?.isSelected =
                false
            acc?.brandNameList?.forEachIndexed { branchIndex, brandNameListItem ->
                mViewModel?.filterResLivedata?.value?.filterData?.get(0)?.hierarchy?.get(
                    accountIndex
                )?.brandNameList?.get(branchIndex)?.isSelected = false
                brandNameListItem?.locationNameList?.forEachIndexed { locationindex, locationNameListItem ->
                    mViewModel?.filterResLivedata?.value?.filterData?.get(0)?.hierarchy?.get(
                        accountIndex
                    )?.brandNameList?.get(branchIndex)?.locationNameList?.get(locationindex)?.isSelected =
                        false
                }
            }
        }
    }

    override fun onClick(pos: Int, view: View, obj: Any) {

    }

    fun setUpCounts(pos: Int = 0, count:Int = 0){
        if(mCategoryCountList?.size==3){
            mCategoryCountList?.get(pos)?.selectedCount = count
            mCategoryListAdapter?.notifyDataSetChanged()
        }else{
            mCategoryCountList?.add(
                CategoryPojo(
                    categoryName = "Acc no",
                    selectedCount = mViewModel?.mSelectedAccCount?.value!!
                )
            )
            mCategoryCountList?.add(
                CategoryPojo(
                    categoryName = "Brand",
                    selectedCount = mViewModel?.mSelectedBrandCount?.value!!
                )
            )
            mCategoryCountList?.add(
                CategoryPojo(
                    categoryName = "Location",
                    selectedCount = mViewModel?.mSelectedLocationCount?.value!!
                )
            )
            rv_category_count.layoutManager =
                LinearLayoutManager(
                    activity!!,
                    RecyclerView.HORIZONTAL,
                    false
                )
            mCategoryListAdapter = CategoryListAdapter(
                activity,
                this,
                mCategoryCountList
            )
            rv_category_count.adapter = mCategoryListAdapter
        }
    }
}