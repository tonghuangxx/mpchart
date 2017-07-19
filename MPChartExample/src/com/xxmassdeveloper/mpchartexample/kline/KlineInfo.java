package com.xxmassdeveloper.mpchartexample.kline;

import android.support.annotation.NonNull;


import java.io.Serializable;

/**
 * K线数据实体类
 */

public class KlineInfo implements Comparable<KlineInfo>, KlineDisplay, Serializable {
    private static final long serialVersionUID = 5947303349079107482L;

    private long id; //时间戳

    private long ts;

    private double high;

    private double low;

    private double open;

    private double close;

    private double amount;

    private long count;

    private double vol;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public double getOpen() {
        return open;
    }

    public void setOpen(double open) {
        this.open = open;
    }

    public double getClose() {
        return close;
    }

    public void setClose(double close) {
        this.close = close;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public double getVol() {
        return vol;
    }

    public void setVol(double vol) {
        this.vol = vol;
    }

    public long getTs() {
        return ts;
    }

    public void setTs(long ts) {
        this.ts = ts;
    }

    @Override
    public int compareTo(@NonNull KlineInfo another) {
        if (this.id > another.id) {
            return 1;
        } else {
            return -1;
        }
    }

    @Override
    public double getValue() {
        return close;
    }

    @Override
    public long getTime() {
        return id;
    }
}
