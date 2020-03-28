package com.test.testcoolrocket.network.pojo

import com.squareup.moshi.Json

data class PointResponse(
    @Json(name = "x") val x: Double,
    @Json(name = "y") val y: Double
)