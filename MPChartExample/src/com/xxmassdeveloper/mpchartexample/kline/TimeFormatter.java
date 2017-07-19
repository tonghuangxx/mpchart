package com.xxmassdeveloper.mpchartexample.kline;

import android.util.Log;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.text.SimpleDateFormat;

public class TimeFormatter implements IAxisValueFormatter {

    private SimpleDateFormat format;
    private long referenceTimestamp; //最小时间

    public TimeFormatter(long referenceTimestamp) {
        format = new SimpleDateFormat("HH:mm");
        this.referenceTimestamp = referenceTimestamp;
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        long convertedTimestamp = (long) value;
        Log.d("TimeFormatter", convertedTimestamp + referenceTimestamp + "");
        return format.format(convertedTimestamp + referenceTimestamp);
    }
}
