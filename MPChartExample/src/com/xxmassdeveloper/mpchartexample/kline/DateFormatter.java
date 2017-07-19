package com.xxmassdeveloper.mpchartexample.kline;

import android.util.Log;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.text.SimpleDateFormat;

public class DateFormatter implements IAxisValueFormatter {

    private SimpleDateFormat format;

    public DateFormatter(){
        format = new SimpleDateFormat("MM-dd");
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        return format.format(value);
    }

}
