/*
 * Copyright (C) 2016 jiashuangkuaizi, Inc.
 */
package com.diygreen.viewobjectdemo;

/**
 * Description:
 * <br/>Program Name:
 * <br/>Date: 2016年3月7日
 *
 * @author 李旺成
 * @version 1.0
 */

public class VOConverterUtil {

    public static WeatherVO getWeatherVOFromWeatherBean(WeatherBean weatherBean) {
        // 这里偷个懒
        WeatherVO weatherVO = new WeatherVO(weatherBean);
        return weatherVO;
    }

}
