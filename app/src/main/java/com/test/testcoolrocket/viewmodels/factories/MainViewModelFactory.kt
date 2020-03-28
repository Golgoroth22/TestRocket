package com.test.testcoolrocket.viewmodels.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.test.testcoolrocket.repositories.PointsRepository
import com.test.testcoolrocket.viewmodels.MainViewModel
import javax.inject.Inject

class MainViewModelFactory @Inject constructor(private val repository: PointsRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }
}