package com.test.testcoolrocket.network.service

import com.test.testcoolrocket.network.pojo.ResultResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface PointsService {
    @POST("pointsList")
    fun getPoints(
        @Query("version") id: Double = 1.1,
        @Query("count") count: Int,
        @Body body: String = ""
    ): Call<ResultResponse>
}