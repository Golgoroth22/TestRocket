package com.test.testcoolrocket.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.test.testcoolrocket.R
import com.test.testcoolrocket.di.Scopes
import com.test.testcoolrocket.network.pojo.ResultResponse
import com.test.testcoolrocket.utils.injectViewModel
import com.test.testcoolrocket.viewmodels.MainViewModel
import com.test.testcoolrocket.viewmodels.factories.MainViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import toothpick.Toothpick
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var factory: MainViewModelFactory
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Toothpick.inject(this, Toothpick.openScope(Scopes.APP))
        setContentView(R.layout.activity_main)
        viewModel = injectViewModel(factory)
        viewModel.ld.observe(this, Observer<ResultResponse> { response ->
            response.points.points.forEach {
                activity_main_titleText.append(it.toString())
            }
        })
    }
}
