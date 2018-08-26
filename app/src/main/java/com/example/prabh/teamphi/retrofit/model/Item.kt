package com.example.prabh.teamphi.retrofit.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Item
{
    @SerializedName("Id")
    @Expose
    val itemId:String?=null

    @SerializedName("TaskId")
    @Expose
    val taskId:String?=null

    @SerializedName("ItemName")
    @Expose
    val itemName:String?=null

    @SerializedName("ItemType")
    @Expose
    val itemType:String?=null

    @SerializedName("Price")
    @Expose
    val price:String?=null

    @SerializedName("Quantity")
    @Expose
    val quantity:String?=null

    @SerializedName("PurchaseDate")
    @Expose
    val purchaseDate:String?=null

    @SerializedName("isApproved")
    @Expose
    val isApproved:Boolean?=null

    @SerializedName("BillImageURL")
    @Expose
    val billImageUrl:String?=null

}