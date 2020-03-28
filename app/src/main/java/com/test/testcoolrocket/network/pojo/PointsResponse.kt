package com.test.testcoolrocket.network.pojo

import com.squareup.moshi.Json

data class PointsResponse(
    @Json(name = "points") val points: List<PointResponse>
)