package com.capstone.suitmediatestmsib.retrofit
import com.capstone.suitmediatestmsib.response.ApiResponse
import com.capstone.suitmediatestmsib.response.DataItem
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("users?page=1&per_page=10")
    fun getApi(

    ): Call<ApiResponse>

//    @FormUrlEncoded
//    @Headers("Authorization: token 12345")
//    @POST("review")
//    fun postReview(
//        @Field("id") id: String,
//        @Field("name") name: String,
//        @Field("review") review: String
//    ): Call<DataItem>
}