package com.xxmassdeveloper.mpchartexample;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.CandleStickChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.CandleData;
import com.github.mikephil.charting.data.CandleDataSet;
import com.github.mikephil.charting.data.CandleEntry;
import com.xxmassdeveloper.mpchartexample.kline.AmountFormatter;
import com.xxmassdeveloper.mpchartexample.kline.KlineInfo;
import com.xxmassdeveloper.mpchartexample.kline.TimeFormatter1;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MarketInfoActivity extends Activity {

    private CandleStickChart candleStickChart;
    private BarChart barChart;
    private Calendar calendar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market_info);
        candleStickChart = (CandleStickChart) findViewById(R.id.candleStickChart);
        barChart = (BarChart) findViewById(R.id.barChart);

        initCandleChart();
        initBarChart();
        addData();
    }


    public void addData() {
        CandleData candleData = candleStickChart.getCandleData();
        CandleDataSet candleDataSet = (CandleDataSet) candleData.getDataSetByIndex(0);



//        if (candleData == null) {
//            candleDataSet = createSet();
//            candleData.addDataSet(candleDataSet);
//        }
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, 2017);
        c.set(Calendar.MONTH, 1);
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.set(Calendar.HOUR, 10);
        c.set(Calendar.MINUTE, 55);
        c.set(Calendar.SECOND, 0);


        KlineInfo klineInfo;
        List<BarEntry> barEntryList = new ArrayList<>();
        for (int i = 0; i < 280; i++) {
            float open = (float) (0.01 * i);
            float close = (float) (0.02 * i);
            float low = (float) (0.005 * i);
            float high = (float) (0.025 * i);
            c.set(Calendar.MINUTE, i);
            klineInfo = new KlineInfo();
            klineInfo.setId(c.getTimeInMillis());
            klineInfo.setAmount(i);
            CandleEntry candleEntry = new CandleEntry(i, high, low, open, close, klineInfo);

            candleDataSet.addEntry(candleEntry);
            BarEntry barEntry = new BarEntry(i, 5, klineInfo);
            barEntryList.add(barEntry);
        }
        BarDataSet barDataSet;
        if (barChart.getBarData() != null && barChart.getBarData().getDataSetCount() > 0) {
            BarData barData = barChart.getBarData();
            barDataSet = (BarDataSet) barData.getDataSetByIndex(0);
            barDataSet.setValues(barEntryList);
        }else{
            barDataSet = new BarDataSet(barEntryList, "barData");
            BarData barData = new BarData(barDataSet);
            barChart.setData(barData);
        }



        candleData.notifyDataChanged();
        XAxis xAxis = candleStickChart.getXAxis();
        xAxis.setValueFormatter(new TimeFormatter1(candleDataSet.getValues()));

        candleStickChart.notifyDataSetChanged();
        candleStickChart.setVisibleXRangeMaximum(30);
        candleStickChart.setVisibleXRangeMinimum(8);
        candleStickChart.moveViewTo(candleData.getEntryCount() - 30, 0f, YAxis.AxisDependency.LEFT);

        barDataSet.notifyDataSetChanged();
        YAxis yAxis = barChart.getAxisRight();
        yAxis.setValueFormatter(new AmountFormatter(barDataSet.getValues()));
        barChart.notifyDataSetChanged();
        barChart.setVisibleXRangeMaximum(30);
        barChart.setVisibleXRangeMinimum(8);
        barChart.moveViewTo(candleData.getEntryCount() - 30, 0f, YAxis.AxisDependency.LEFT);
    }


    public void initCandleChart() {
        calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2017);
        calendar.set(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR, 10);
        calendar.set(Calendar.MINUTE, 55);
        calendar.set(Calendar.SECOND, 0);

        //y轴不可缩放
        candleStickChart.setScaleYEnabled(false);
        //不显示说明
        candleStickChart.getDescription().setEnabled(false);
        //不显示图例说明
        candleStickChart.getLegend().setEnabled(false);

        //设置x轴的
        XAxis xAxis = candleStickChart.getXAxis();
        xAxis.setLabelCount(6, true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(new TimeFormatter1());
        //设置y轴
        YAxis yAxisRight = candleStickChart.getAxisRight();
        yAxisRight.setLabelCount(5, true);
        yAxisRight.setAxisMinimum(0);
        YAxis xAxisLeft = candleStickChart.getAxisLeft();
        xAxisLeft.setEnabled(false);


        CandleData candleData = new CandleData(createSet());
        candleStickChart.setData(candleData);


        candleStickChart.notifyDataSetChanged();
    }

    public CandleDataSet createSet() {
        CandleDataSet candleDataSet = new CandleDataSet(null, "candle");
        candleDataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
        candleDataSet.setShadowColor(Color.DKGRAY);
        candleDataSet.setShadowWidth(0.7f);
        candleDataSet.setDecreasingColor(Color.GREEN);
        candleDataSet.setDecreasingPaintStyle(Paint.Style.FILL);
        candleDataSet.setIncreasingColor(Color.RED);
        candleDataSet.setIncreasingPaintStyle(Paint.Style.STROKE);
        candleDataSet.setNeutralColor(Color.RED);
        candleDataSet.setDrawValues(false);
        return candleDataSet;
    }


    public void initBarChart() {
        XAxis xAxis = barChart.getXAxis();
        xAxis.setEnabled(false);

        YAxis rightYAxis = barChart.getAxisRight();
        rightYAxis.setLabelCount(3, false);
        rightYAxis.setDrawGridLines(true);
        rightYAxis.setAxisMinimum(0);

        YAxis leftYAxis = barChart.getAxisLeft();
        leftYAxis.setEnabled(false);

        barChart.setScaleYEnabled(false);
        barChart.getDescription().setEnabled(false);
        barChart.getLegend().setEnabled(false);

//        BarData barData = new BarData(createBarDataSet());
//        barChart.setData(barData);
    }

    public BarDataSet createBarDataSet() {
        BarDataSet barDataSet = new BarDataSet(new ArrayList<BarEntry>(), "barData");
        return barDataSet;
    }
}
