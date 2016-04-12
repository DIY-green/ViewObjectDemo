/*
 * Copyright (C) 2016 jiashuangkuaizi, Inc.
 */
package com.diygreen.viewobjectdemo;

import android.text.Html;
import android.text.Spanned;

/**
 * Description:
 * <br/>Program Name:
 * <br/>Date: 2016年3月7日
 *
 * @author 李旺成
 * @version 1.0
 */

public class WeatherVO {

    private boolean isSuccess;
    private int weatherInfoIcon;
    private Spanned weatherInfoText;
    private String relativeHumidity; //  "hum": "20%", //相对湿度（%）
    private int temperatureIcon; // "tmp": "32", //温度 温度图标
    private String airPressure; // "pres": "1001", //气压
    private String precipitation; // 降水量
    private String visibility; // "vis": "10", //能见度（km）
    private String windDirectionAngle; // "deg": "10", //风向（360度）
    private String windDirection; // "dir": "北风", //风向
    private String windPower; // "sc": "3级", //风力
    private String windSpeed; // "spd": "15" //风速（kmph）

    public WeatherVO() {}

    public WeatherVO(WeatherBean weatherBean) {
        if (weatherBean == null) return;
        isSuccess = "ok".equals(weatherBean.getStatus());
        int condCode = Integer.parseInt(weatherBean.getNow().getCond().getCode());
        String condCodeColorStr = "";
        if (condCode < 0) {
            weatherInfoIcon = R.mipmap.ic_snow;
            condCodeColorStr = "#000066";
        } else if (condCode < 60) {
            weatherInfoIcon = R.mipmap.ic_rain;
            condCodeColorStr = "#009900";
        } else if (condCode < 90) {
            weatherInfoIcon = R.mipmap.ic_cloudy;
            condCodeColorStr = "#993300";
        } else {
            weatherInfoIcon = R.mipmap.ic_sunshine;
            condCodeColorStr = "#cccc00";
        }
        weatherInfoText = Html.fromHtml("<font color='"+condCodeColorStr+"'>"+weatherBean.getNow().getCond().getTxt()+"</font>");
        relativeHumidity = "相对湿度：" + weatherBean.getNow().getHum();
        int tmpInt = Integer.parseInt(weatherBean.getNow().getTmp());
        if (tmpInt < 15) {
            temperatureIcon = R.mipmap.ic_lowtemperature;
        } else if (tmpInt < 33) {
            temperatureIcon = R.mipmap.ic_thermophilic;
        } else {
            temperatureIcon = R.mipmap.ic_hightemperature;
        }
        airPressure = "气压：" + weatherBean.getNow().getPres();
        precipitation = "降水量：" + weatherBean.getNow().getPcpn();
        visibility = "能见度：" + weatherBean.getNow().getVis() + " KM";
        windDirectionAngle = "风向角度：" + weatherBean.getNow().getWind().getDeg();
        windDirection = "风向：" + weatherBean.getNow().getWind().getDir();
        windPower = "风力：" + weatherBean.getNow().getWind().getSc();
        windSpeed = "风速" + weatherBean.getNow().getWind().getSpd();
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public int getWeatherInfoIcon() {
        return weatherInfoIcon;
    }

    public Spanned getWeatherInfoText() {
        return weatherInfoText;
    }

    public String getRelativeHumidity() {
        return relativeHumidity;
    }

    public int getTemperatureIcon() {
        return temperatureIcon;
    }

    public String getAirPressure() {
        return airPressure;
    }

    public String getPrecipitation() {
        return precipitation;
    }

    public String getVisibility() {
        return visibility;
    }

    public String getWindDirectionAngle() {
        return windDirectionAngle;
    }

    public String getWindDirection() {
        return windDirection;
    }

    public String getWindPower() {
        return windPower;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    @Override
    public String toString() {
        return "WeatherVO{" +
                "airPressure='" + airPressure + '\'' +
                ", isSuccess=" + isSuccess +
                ", weatherInfoIcon=" + weatherInfoIcon +
                ", weatherInfoText=" + weatherInfoText +
                ", relativeHumidity='" + relativeHumidity + '\'' +
                ", temperatureIcon=" + temperatureIcon +
                ", precipitation='" + precipitation + '\'' +
                ", visibility='" + visibility + '\'' +
                ", windDirectionAngle='" + windDirectionAngle + '\'' +
                ", windDirection='" + windDirection + '\'' +
                ", windPower='" + windPower + '\'' +
                ", windSpeed='" + windSpeed + '\'' +
                '}';
    }
}
