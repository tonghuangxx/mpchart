package com.xxmassdeveloper.mpchartexample.kline;

import android.util.Log;

import com.github.mikephil.charting.charts.BarLineChartBase;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.data.CandleData;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.text.SimpleDateFormat;
import java.util.List;

public class TimeFormatter1 implements IAxisValueFormatter {

    private SimpleDateFormat format;
    private List<CandleEntry> list;
    private CandleEntry candleEntry;
    private int size;

    public TimeFormatter1(List<CandleEntry> list) {
        format = new SimpleDateFormat("HH:mm");
        this.list = list;
        size = list.size();
        candleEntry = new CandleEntry(0, 0, 0, 0, 0, new KlineInfo());
    }

    public TimeFormatter1() {
        format = new SimpleDateFormat("HH:mm");
    }

    private BarLineChartBase<?> chart;

    public TimeFormatter1(BarLineChartBase<?> chart) {
        this.chart = chart;
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        Log.d("TimeFormatter1", value + "=====");
        int index = (int) value;
        if (index < size) {
            candleEntry = list.get(index);
        }
        return format.format(((KlineInfo)candleEntry.getData()).getId());
    }
}
