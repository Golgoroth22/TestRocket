package com.test.testcoolrocket.repositories

import androidx.lifecycle.MutableLiveData
import com.test.testcoolrocket.network.pojo.ResultResponse
import com.test.testcoolrocket.network.service.PointsService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class PointsRepository(service: PointsService) {
    val result = MutableLiveData<ResultResponse>()

    init {
        service.getPoints(count = 20)
            .enqueue(object : Callback<ResultResponse> {
                override fun onFailure(call: Call<ResultResponse>, t: Throwable) {
                    Timber.i("MainActivity onCreate onFailure ${t.localizedMessage}")
                    t.printStackTrace()
                }

                override fun onResponse(
                    call: Call<ResultResponse>,
                    response: Response<ResultResponse>
                ) {
                    Timber.i("MainActivity onCreate onResponse ${response.body()}")
                    if (response.body()?.result == 0) {
                        result.postValue(response.body())
                    }
                }
            })
    }
}