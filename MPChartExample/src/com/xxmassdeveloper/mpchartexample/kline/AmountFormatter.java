package com.xxmassdeveloper.mpchartexample.kline;

import android.util.Log;

import com.github.mikephil.charting.charts.BarLineChartBase;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.text.SimpleDateFormat;
import java.util.List;

public class AmountFormatter implements IAxisValueFormatter {

    private List<BarEntry> list;
    private BarEntry barEntry;
    private int size;

    public AmountFormatter(List<BarEntry> list) {
        this.list = list;
        size = list.size();
        barEntry = new BarEntry(0, 0, new KlineInfo());
    }

    public AmountFormatter() {
    }

    private BarLineChartBase<?> chart;

    public AmountFormatter(BarLineChartBase<?> chart) {
        this.chart = chart;
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        Log.d("TimeFormatter1", value + "=====");
        int index = (int) value;
        if (index < size) {
            barEntry = list.get(index);
        }
        return String.format("%.2f",((KlineInfo)barEntry.getData()).getAmount());
    }
}
