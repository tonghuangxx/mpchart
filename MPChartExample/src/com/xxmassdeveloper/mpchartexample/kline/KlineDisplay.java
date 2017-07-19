package com.xxmassdeveloper.mpchartexample.kline;

/**
 * Created by wangsai on 2016/12/21.
 */

public interface KlineDisplay {
    /**
     * Y轴的值
     *
     * @return
     */
    double getValue();

    /**
     * linux时间
     *
     * @return
     */
    long getTime();
}
