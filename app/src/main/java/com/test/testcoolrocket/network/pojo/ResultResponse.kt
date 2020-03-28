package com.test.testcoolrocket.network.pojo

import com.squareup.moshi.Json

data class ResultResponse(
    @Json(name = "result") val result: Int,
    @Json(name = "response") val points: PointsResponse
)