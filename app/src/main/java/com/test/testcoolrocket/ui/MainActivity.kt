package com.test.testcoolrocket.ui

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.test.testcoolrocket.R
import com.test.testcoolrocket.di.Scopes
import com.test.testcoolrocket.network.pojo.PointResponse
import com.test.testcoolrocket.ui.adapters.PointsAdapter
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

    private lateinit var pointsRecycler: RecyclerView
    private lateinit var pAdapter: PointsAdapter
    private lateinit var lManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Toothpick.inject(this, Toothpick.openScope(Scopes.APP))
        setContentView(R.layout.activity_main)
        viewModel = injectViewModel(factory)
        initViews()
        initListeners()
    }

    private fun initViews() {
        lManager = GridLayoutManager(this, 4)
        pAdapter = PointsAdapter()
        pointsRecycler = findViewById<RecyclerView>(R.id.activity_main_pointsRecycler).apply {
            layoutManager = lManager
            adapter = pAdapter
        }
        viewModel.ld.observe(this, Observer { response ->
            pAdapter.updateAdapterList(response.points.points)
            updateChart(response.points.points.sortedBy {
                it.x
            })
        })
    }

    private fun initListeners() {
        activity_main_points_hideButton.setOnClickListener {
            when (activity_main_pointsRecycler.visibility) {
                View.GONE -> {
                    activity_main_pointsRecycler.visibility = View.VISIBLE
                    activity_main_points_hideButton.setImageResource(R.drawable.ic_arrow_drop_up_24dp)
                }
                View.VISIBLE -> {
                    activity_main_pointsRecycler.visibility = View.GONE
                    activity_main_points_hideButton.setImageResource(R.drawable.ic_arrow_drop_down_24dp)
                }
            }
        }
        activity_main_chart_hideButton.setOnClickListener {
            when (activity_main_pointsChart.visibility) {
                View.GONE -> {
                    activity_main_pointsChart.visibility = View.VISIBLE
                    activity_main_chart_hideButton.setImageResource(R.drawable.ic_arrow_drop_up_24dp)
                }
                View.VISIBLE -> {
                    activity_main_pointsChart.visibility = View.GONE
                    activity_main_chart_hideButton.setImageResource(R.drawable.ic_arrow_drop_down_24dp)
                }
            }
        }
    }

    private fun updateChart(list: List<PointResponse>) {
        val entries = mutableListOf<Entry>()
        list.forEach {
            entries.add(Entry(it.x.toFloat(), it.y.toFloat()))
        }
        val dataSet = LineDataSet(entries, "Координаты")
        dataSet.color = Color.GRAY
        activity_main_pointsChart.data = LineData(dataSet)
        activity_main_pointsChart.invalidate()
    }
}
