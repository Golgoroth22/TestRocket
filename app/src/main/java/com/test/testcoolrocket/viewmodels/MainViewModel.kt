package com.test.testcoolrocket.viewmodels

import androidx.lifecycle.ViewModel
import com.test.testcoolrocket.repositories.PointsRepository

class MainViewModel(repository: PointsRepository) : ViewModel() {
    val ld = repository.result
    var isThisFirstStart = true
}