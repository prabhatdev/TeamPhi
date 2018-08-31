package com.example.prabh.teamphi.retrofit.handler

import android.support.annotation.Keep
import com.example.prabh.teamphi.retrofit.model.*
import io.reactivex.Observable
import retrofit2.http.*

@Keep
interface ApiInterface {

    @FormUrlEncoded
    @POST("api/Users/Login")
    fun loginUser(@Field("Username") userName: String, @Field("Password") password: String): Observable<LoginResult>

    @FormUrlEncoded
    @POST("api/Users/RegisterUser")
    fun registerUser(@Field("Username") userName: String, @Field("Name") name: String, @Field("Designation") designation: String, @Field("Password") password: String): Observable<RegisterUser>

    @GET("api/Task/GetAllTasks")
    fun getAllTasks(@Header("token") token: String): Observable<TaskUser>

    @FormUrlEncoded
    @POST("api/Task/AddTask")
    fun sendTask(@Header("token") token: String, @Field("TaskName") taskName: String, @Field("CreatedForUsername") createdForUsername: String, @Query("Username") userName: String): Observable<AddTask>

    @GET("api/Task/GetItemsFor")
    fun getItems(@Header("token") toke: String, @Query("TaskId") taskId: String): Observable<ItemsResult>

    @FormUrlEncoded
    @POST("api/Task/AddItem")
    fun sendItem(@Header("token") token: String, @Field("TaskId") taskId: String, @Field("ItemName") itemName: String,
                 @Field("ItemType") itemType: String, @Field("Price") price: Double, @Field("Quantity") quantity: Int,
                 @Field("PurchaseDate") purchaseDate: String, @Field("BillImageURL") billImageURL: String, @Query("Username") userName: String): Observable<AddItem>


    @GET("api/Types/GetItemTypes")
    fun getItemType(@Header("token") token: String): Observable<ItemType>

    @GET("api/AdminTask/GetForVerification")
    fun getItemVerification(@Header("token") token: String, @Query("Username") userName: String):Observable<VerifyItem>

    @POST("api/AdminTask/VerifyItem")
    @FormUrlEncoded
    fun approveItems(@Header("token")token:String,@Field("TaskId") taskId:String,@Field("ItemId") itemId:String,@Query("Username") userName:String):Observable<ApproveItem>
}