package com.demoproj.viewmodel

import androidx.lifecycle.MutableLiveData
import com.mvvmwithhilt.module.dto.FilterRes


class HomeScreenViewModel : BaseViewModel(){

    var filterResLivedata = MutableLiveData<FilterRes>()
    var mSelectedAccCount = MutableLiveData<Int>()
    var mSelectedBrandCount = MutableLiveData<Int>()
    var mSelectedLocationCount = MutableLiveData<Int>()
    var mSelectedCategory = MutableLiveData<String>() //acc,brand,location
    var mSelectedPos = MutableLiveData<Int>()
    var mSelectedAccPos = MutableLiveData<Int>()
    var mSelectedBrandPos = MutableLiveData<Int>()
    var mSelectedLocationPos = MutableLiveData<Int>()

}