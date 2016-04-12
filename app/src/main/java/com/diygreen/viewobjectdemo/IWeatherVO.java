/*
 * Copyright (C) 2016 jiashuangkuaizi, Inc.
 */
package com.diygreen.viewobjectdemo;

import android.text.Spanned;

/**
 * Description:
 * <br/>Program Name:
 * <br/>Date: 2016年3月7日
 *
 * @author 李旺成
 * @version 1.0
 */

public interface IWeatherVO {

    boolean isSuccess(); // "status": "ok", //接口状态
    int getWeatherInfoIcon(); // "code": "100", //天气状况代码 假设 <0 下雪， < 60 雨，大于 >60 < 90 阴， > 90 晴
    Spanned getWeatherInfoText(); // "txt": "晴" //天气状况描述 天气的文本描述
    String getRelativeHumidity(); //  "hum": "20%", //相对湿度（%）
    int getTemperatureIcon(); // "tmp": "32", //温度 温度图标
    String getAirPressure(); // "pres": "1001", //气压
    String getPrecipitation(); // 降水量
    String getVisibility(); // "vis": "10", //能见度（km）
    String getWindDirectionAngle(); // "deg": "10", //风向（360度）
    String getWindDirection(); // "dir": "北风", //风向
    String getWindPower(); // "sc": "3级", //风力
    String getWindSpeed(); // "spd": "15" //风速（kmph）

}
