package com.aa.project2

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.formatter.ValueFormatter
import java.text.DecimalFormat


class ChartActivity : AppCompatActivity() {
    lateinit var barChart: BarChart
    lateinit var barEntriesList: ArrayList<BarEntry>
    lateinit var barEntriesList2: ArrayList<BarEntry>
    lateinit var barEntriesList3: ArrayList<BarEntry>
    lateinit var barData: BarData
    lateinit var barDataSet: BarDataSet
    lateinit var barDataSet2: BarDataSet
    lateinit var barDataSet3: BarDataSet
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chart)
        barChart = findViewById(R.id.idBarChart)
        getBarChartData()

        //barChart.invalidate()

        barDataSet = BarDataSet(barEntriesList, "Smart Phone")
        barDataSet2 = BarDataSet(barEntriesList2, "Notebook")
        barDataSet3 = BarDataSet(barEntriesList3, "Tablet")
        barDataSet.color = Color.GREEN
        barDataSet2.color = Color.BLUE
        barDataSet3.color = Color.DKGRAY

        barData = BarData(barDataSet)
        barData.addDataSet(barDataSet2)
        barData.addDataSet(barDataSet3)
        barChart.data = barData


        barDataSet.valueTextColor = Color.BLACK


        // on below line we are setting text size
        barDataSet.valueTextSize = 12f
        barDataSet2.valueTextSize = 12f
        barDataSet3.valueTextSize = 12f
        // on below line we are enabling description as false
        barChart.description.isEnabled = false


        val rightYAxis = barChart.axisRight
        rightYAxis.isEnabled = false
        //barChart.getXAxis().isEnabled = false
        val xAxisLabel = ArrayList<String>()
        xAxisLabel.add("111")//ตัวฟรีไม่แสดงผล จำเป็นต้องมี
        xAxisLabel.add("April")
        xAxisLabel.add("May")
        xAxisLabel.add("June")
        xAxisLabel.add("July")
        xAxisLabel.add("August")
        //xAxisLabel.add("Dark Ped")
        val v = object : ValueFormatter() {
            override fun getFormattedValue(value: Float): String {
                val index = value.toInt()
                if(index<xAxisLabel.size) {
                    return xAxisLabel.get(index)
                }else{
                    return ""
                }
            }
        }
        val vv  = object :IndexAxisValueFormatter(){
            override fun getFormattedValue(value: Float): String {
                val index = value.toInt()
                if(index<xAxisLabel.size) {
                    return xAxisLabel.get(index)
                }else{
                    return ""
                }
            }
        }

        val BAR_SPACE = 0.03f
        val BAR_WIDTH = 0.3f
        val BAR_COUNT = 3 // the number of bars in 1 record
        barData.barWidth  = BAR_WIDTH
        //val groupSpace = 0.35f
        val groupSpace:Float = 1f - (BAR_SPACE + BAR_WIDTH) * BAR_COUNT
        barChart.groupBars(0.5f, groupSpace, BAR_SPACE) //ถ้ามีข้อมูลแค่อันเดียวในแต่ group เอาบรรทัดนี้ออก

        val xAxis = barChart.getXAxis()
        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(1f);//เพิ่มบรรทัดนี้ เพื่อไม่เอาตัวแรก
        //xAxis.setLabelCount(6);
        val vvv = IndexAxisValueFormatter(xAxisLabel)
        xAxis.valueFormatter = v

        barChart.xAxis.position = XAxis.XAxisPosition.BOTTOM
        barChart.notifyDataSetChanged();
        barChart.invalidate();
    }
    private fun getBarChartData() {
        barEntriesList = ArrayList()
        barEntriesList2 = ArrayList()
        barEntriesList3 = ArrayList()
        // on below line we are adding data
        // to our bar entries list
        barEntriesList.add(BarEntry(1f, 60f))
        barEntriesList.add(BarEntry(2f, 22f))
        barEntriesList.add(BarEntry(3f, 24f))
        barEntriesList.add(BarEntry(4f, 45f))
        barEntriesList.add(BarEntry(5f, 34f))

        barEntriesList2.add(BarEntry(1f, 14f))
        barEntriesList2.add(BarEntry(2f, 32f))
        barEntriesList2.add(BarEntry(3f, 16f))
        barEntriesList2.add(BarEntry(4f, 54f))
        barEntriesList2.add(BarEntry(5f, 42f))

        barEntriesList3.add(BarEntry(1f, 45f))
        barEntriesList3.add(BarEntry(2f, 32f))
        barEntriesList3.add(BarEntry(3f, 64f))
        barEntriesList3.add(BarEntry(4f, 33f))
        barEntriesList3.add(BarEntry(5f, 70f))
    }
}