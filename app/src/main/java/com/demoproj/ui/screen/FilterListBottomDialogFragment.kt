package com.demoproj.ui.screen

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.annotation.Nullable
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.mvvmwithhilt.module.dto.*
import com.demoproj.R
import com.demoproj.listener.OnItemClickListener
import com.demoproj.ui.adapter.AccFilterListAdapter
import com.demoproj.ui.adapter.BrandFilterListAdapter
import com.demoproj.ui.adapter.LocationFilterListAdapter
import com.demoproj.viewmodel.HomeScreenViewModel
import kotlinx.android.synthetic.main.layout_bottomsheet_itemlist.*


class FilterListBottomDialogFragment : BottomSheetDialogFragment(), OnItemClickListener {

    var mViewModel: HomeScreenViewModel? = null
    var mFilterres: FilterRes? = null
    var mSelectedCategory = ""
    var mSelectedAccCount = 0
    var mSelectedBrandCount = 0
    var mSelectedLocationCount = 0
    val mAccountList: ArrayList<HierarchyItem>? = arrayListOf()
    val mBrandNameList: ArrayList<BrandNameListItem>? = arrayListOf()
    val mLocationNameList: ArrayList<LocationNameListItem>? = arrayListOf()
    var mAccFilterListAdapter:AccFilterListAdapter? = null
    var mBrandFilterListAdapter:BrandFilterListAdapter? = null
    var mLocationFilterListAdapter:LocationFilterListAdapter? = null
    @Nullable
    override fun onCreateView(
        inflater: LayoutInflater,
        @Nullable container: ViewGroup?,
        @Nullable savedInstanceState: Bundle?
    ): View? {

        // get the views and attach the listener
        return inflater.inflate(
            R.layout.layout_bottomsheet_itemlist, container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel = ViewModelProviders.of(activity!!).get(HomeScreenViewModel::class.java)

        setupDataToUi()
        iv_close?.setOnClickListener {
            dismiss()
        }
        text_add_to_filter?.setOnClickListener {
            dismiss()
        }
        edt_search?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(query: Editable?) {
                if(!query?.isNullOrEmpty()!!){
                    when (mViewModel?.mSelectedCategory?.value) {
                        "acc" -> {
                            val filteredAccList: List<HierarchyItem>? = mAccountList?.filter {it-> it?.accountNumber?.contains(query.toString(), true)}
                            rv_filter_item.layoutManager =
                                LinearLayoutManager(
                                    activity!!,
                                    RecyclerView.VERTICAL,
                                    false
                                )
                            rv_filter_item.adapter = AccFilterListAdapter(
                                activity,
                                this@FilterListBottomDialogFragment,
                                filteredAccList as ArrayList<HierarchyItem>
                            )
                        }
                        "brand" -> {
                            val filteredList: List<BrandNameListItem>? = mBrandNameList?.filter {it-> it?.brandName?.contains(query.toString(), true)}
                            rv_filter_item.layoutManager =
                                LinearLayoutManager(
                                    activity!!,
                                    RecyclerView.VERTICAL,
                                    false
                                )
                            rv_filter_item.adapter = BrandFilterListAdapter(
                                activity,
                                this@FilterListBottomDialogFragment,
                                filteredList as ArrayList<BrandNameListItem>
                            )
                        }
                        "location" -> {
                            val filteredList: List<LocationNameListItem>? = mLocationNameList?.filter {it-> it?.locationName?.contains(query.toString(), true)}
                            rv_filter_item.layoutManager =
                                LinearLayoutManager(
                                    activity!!,
                                    RecyclerView.VERTICAL,
                                    false
                                )
                            rv_filter_item.adapter = LocationFilterListAdapter(
                                activity,
                                this@FilterListBottomDialogFragment,
                                filteredList as ArrayList<LocationNameListItem>
                            )
                        }
                    }
                }else{
                    setupDataToUi()
                }
            }
        })
        tv_clear_all?.setOnClickListener {
//            clearAllSelected(mViewModel?.mSelectedCategory?.value!!)
            edt_search?.setText("")
        }
        checkBox_selectAll?.setOnCheckedChangeListener { compoundButton, b ->
            if (b) {
                selectAllSelected(mViewModel?.mSelectedCategory?.value!!)
            } else {
                clearAllSelected(mViewModel?.mSelectedCategory?.value!!)
            }
        }

    }

    companion object {
        fun newInstance(): FilterListBottomDialogFragment {
            return FilterListBottomDialogFragment()
        }
    }

    override fun onClick(pos: Int, view: View, obj: Any) {
        if (obj != null && obj is HierarchyItem) {
            mViewModel?.filterResLivedata?.value?.filterData?.get(0)?.hierarchy?.get(pos)?.isSelected =
                obj?.isSelected
            if (obj?.isSelected) {
                mViewModel?.mSelectedAccCount?.value = mViewModel?.mSelectedAccCount?.value!! + 1
            } else {
                mViewModel?.mSelectedAccCount?.value = mViewModel?.mSelectedAccCount?.value!! - 1
            }
            mViewModel?.mSelectedAccPos?.value = pos!!
        } else if (obj != null && obj is BrandNameListItem) {
            mViewModel?.filterResLivedata?.value?.filterData?.get(0)?.hierarchy?.get(obj?.accountIndex)?.brandNameList?.get(
                obj?.selfIndex
            )?.isSelected = obj?.isSelected
            if (obj?.isSelected) {
                mViewModel?.mSelectedBrandCount?.value =
                    mViewModel?.mSelectedBrandCount?.value!! + 1
            } else {
                mViewModel?.mSelectedBrandCount?.value =
                    mViewModel?.mSelectedBrandCount?.value!! - 1
            }
            mViewModel?.mSelectedBrandPos?.value = pos!!
        } else if (obj != null && obj is LocationNameListItem) {
            mViewModel?.filterResLivedata?.value?.filterData?.get(0)?.hierarchy?.get(obj?.accountIndex)?.brandNameList?.get(
                obj?.brandIndex
            )?.locationNameList?.get(obj?.selfIndex)?.isSelected = obj?.isSelected
            if (obj?.isSelected) {
                mViewModel?.mSelectedLocationCount?.value =
                    mViewModel?.mSelectedLocationCount?.value!! + 1
            } else {
                mViewModel?.mSelectedLocationCount?.value =
                    mViewModel?.mSelectedLocationCount?.value!! - 1
            }
            mViewModel?.mSelectedLocationPos?.value = pos!!
        }
    }

    fun setupDataToUi(){
        mViewModel?.mSelectedCategory?.observe(this, Observer<String> {
            mSelectedCategory = it
            when (it) {
                "acc" -> {
                    updateSelectionCount()
                    rv_filter_item.layoutManager =
                        LinearLayoutManager(
                            activity!!,
                            RecyclerView.VERTICAL,
                            false
                        )
                    mAccFilterListAdapter = AccFilterListAdapter(
                        activity,
                        this,
                        mViewModel?.filterResLivedata?.value?.filterData?.get(0)?.hierarchy!!
                    )
                    rv_filter_item.adapter = mAccFilterListAdapter
                }
                "brand" -> {
                    if (mViewModel?.mSelectedAccCount?.value!! > 0) {
                        updateSelectionCount()
                        rv_filter_item.layoutManager =
                            LinearLayoutManager(
                                activity!!,
                                RecyclerView.VERTICAL,
                                false
                            )
                        mBrandFilterListAdapter = BrandFilterListAdapter(
                            activity,
                            this, mBrandNameList!!
                        )
                        rv_filter_item.adapter = mBrandFilterListAdapter
                    }
                }
                "location" -> {
                    if (mViewModel?.mSelectedAccCount?.value!! >= 0 && mViewModel?.mSelectedBrandCount?.value!! >= 0) {
                        updateSelectionCount()
                        rv_filter_item.layoutManager =
                            LinearLayoutManager(
                                activity!!,
                                RecyclerView.VERTICAL,
                                false
                            )
                        mLocationFilterListAdapter = LocationFilterListAdapter(
                            activity,
                            this, mLocationNameList!!
                        )
                        rv_filter_item.adapter = mLocationFilterListAdapter
                    }
                }
            }
        })
    }

    fun clearAllSelected(type: String) {
        mViewModel?.filterResLivedata?.value?.filterData?.get(0)?.hierarchy?.forEachIndexed { accountIndex, acc ->
            if (type?.equals("acc"))
                mViewModel?.filterResLivedata?.value?.filterData?.get(0)?.hierarchy?.get(
                    accountIndex
                )?.isSelected = false
            acc?.brandNameList?.forEachIndexed { branchIndex, brandNameListItem ->
                if (type?.equals("brand"))
                    mViewModel?.filterResLivedata?.value?.filterData?.get(0)?.hierarchy?.get(
                        accountIndex
                    )?.brandNameList?.get(branchIndex)?.isSelected = false
                brandNameListItem?.locationNameList?.forEachIndexed { locationindex, locationNameListItem ->
                    if (type?.equals("location"))
                        mViewModel?.filterResLivedata?.value?.filterData?.get(0)?.hierarchy?.get(
                            accountIndex
                        )?.brandNameList?.get(branchIndex)?.locationNameList?.get(locationindex)?.isSelected =
                            false
                }
            }
        }
        if (type?.equals("acc")) {
            mViewModel?.mSelectedAccCount?.value = 0
            mAccFilterListAdapter?.notifyDataSetChanged()
        }
        if (type?.equals("brand")) {
            mViewModel?.mSelectedBrandCount?.value = 0
            mBrandFilterListAdapter?.notifyDataSetChanged()
        }
        if (type?.equals("location")) {
            mViewModel?.mSelectedLocationCount?.value = 0
            mLocationFilterListAdapter?.notifyDataSetChanged()
        }
    }

    fun selectAllSelected(type: String) {
        mViewModel?.filterResLivedata?.value?.filterData?.get(0)?.hierarchy?.forEachIndexed { accountIndex, acc ->
            if (type?.equals("acc")) {
                mViewModel?.filterResLivedata?.value?.filterData?.get(0)?.hierarchy?.get(
                    accountIndex
                )?.isSelected = true
                mViewModel?.mSelectedAccCount?.value = mViewModel?.mSelectedAccCount?.value!! + 1
            }
            acc?.brandNameList?.forEachIndexed { branchIndex, brandNameListItem ->
                if (type?.equals("brand")) {
                    mViewModel?.filterResLivedata?.value?.filterData?.get(0)?.hierarchy?.get(
                        accountIndex
                    )?.brandNameList?.get(branchIndex)?.isSelected = true
                    mViewModel?.mSelectedBrandCount?.value =
                        mViewModel?.mSelectedBrandCount?.value!! + 1
                }
                brandNameListItem?.locationNameList?.forEachIndexed { locationindex, locationNameListItem ->
                    if (type?.equals("location")) {
                        mViewModel?.filterResLivedata?.value?.filterData?.get(0)?.hierarchy?.get(
                            accountIndex
                        )?.brandNameList?.get(branchIndex)?.locationNameList?.get(locationindex)?.isSelected =
                            true
                        mViewModel?.mSelectedLocationCount?.value =
                            mViewModel?.mSelectedLocationCount?.value!! + 1
                    }
                }
            }
        }
        if (type?.equals("acc")) {
            mViewModel?.mSelectedAccCount?.value = 0
            mAccFilterListAdapter?.notifyDataSetChanged()
        }
        if (type?.equals("brand")) {
            mViewModel?.mSelectedBrandCount?.value = 0
            mBrandFilterListAdapter?.notifyDataSetChanged()
        }
        if (type?.equals("location")) {
            mViewModel?.mSelectedLocationCount?.value = 0
            mLocationFilterListAdapter?.notifyDataSetChanged()
        }
    }

    fun updateSelectionCount() {
        mViewModel?.mSelectedAccCount?.value = 0
        mViewModel?.mSelectedBrandCount?.value = 0
        mViewModel?.mSelectedLocationCount?.value = 0
        mViewModel?.filterResLivedata?.value?.filterData?.get(0)?.hierarchy?.forEachIndexed { accountIndex, acc ->
            mAccountList?.add(acc)
            if (acc?.isSelected) {
                mViewModel?.mSelectedAccCount?.value = mViewModel?.mSelectedAccCount?.value!! + 1
            }
            acc?.brandNameList?.forEachIndexed { branchIndex, brandNameListItem ->
                brandNameListItem?.accountIndex = accountIndex
                brandNameListItem?.selfIndex = branchIndex
                mBrandNameList?.add(brandNameListItem)
                if (brandNameListItem?.isSelected) {
                    mViewModel?.mSelectedBrandCount?.value =
                        mViewModel?.mSelectedBrandCount?.value!! + 1
                }
                brandNameListItem?.locationNameList?.forEachIndexed { locationindex, locationNameListItem ->
                    locationNameListItem?.accountIndex = accountIndex
                    locationNameListItem?.brandIndex = branchIndex
                    locationNameListItem?.selfIndex = locationindex
                    mLocationNameList?.add(locationNameListItem)
                    if (locationNameListItem?.isSelected) {
                        mViewModel?.mSelectedLocationCount?.value =
                            mViewModel?.mSelectedLocationCount?.value!! + 1
                    }
                }
            }
        }
    }
}