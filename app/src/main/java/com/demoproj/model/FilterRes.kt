package com.mvvmwithhilt.module.dto


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class FilterRes(
    @SerializedName("filterData")
    val filterData: ArrayList<FilterDataItem>?,
    @SerializedName("errorCode")
    val errorCode: String = "",
    @SerializedName("message")
    val message: String = "",
    @SerializedName("status")
    val status: String = ""
)

@Parcelize
data class MerchantNumberItem(
    @SerializedName("mid")
    val mid: String = "",
    @SerializedName("outletNumber")
    val outletNumber: List<String>?
) : Parcelable

@Parcelize
data class BrandNameListItem(
    @SerializedName("locationNameList")
    val locationNameList: ArrayList<LocationNameListItem>?,
    @SerializedName("brandName")
    val brandName: String = "",
    var isSelected: Boolean = false,
    var accountIndex:Int =0,
    var selfIndex:Int =0
) : Parcelable

@Parcelize
data class FilterDataItem(
    @SerializedName("Cif")
    val cif: String = "",
    @SerializedName("companyName")
    val companyName: String = "",
    @SerializedName("hierarchy")
    val hierarchy: ArrayList<HierarchyItem>?
) : Parcelable

@Parcelize
data class LocationNameListItem(
    @SerializedName("merchantNumber")
    val merchantNumber: List<MerchantNumberItem>?,
    @SerializedName("locationName")
    val locationName: String = "",
    var isSelected: Boolean = false,
    var accountIndex:Int =0,
    var brandIndex:Int =0,
    var selfIndex:Int =0
) : Parcelable

@Parcelize
data class HierarchyItem(
    @SerializedName("brandNameList")
    val brandNameList: ArrayList<BrandNameListItem>?,
    @SerializedName("accountNumber")
    val accountNumber: String = "",
    var isSelected: Boolean = false
) : Parcelable


