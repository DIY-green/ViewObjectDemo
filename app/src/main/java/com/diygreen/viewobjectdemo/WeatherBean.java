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

public class WeatherBean implements IWeatherVO {


    /**
     * status : ok
     * now : {"cond":{"code":"100","txt":"晴"},"fl":"30","hum":"20%","pcpn":"0.0","pres":"1001","tmp":"32","vis":"10","wind":{"deg":"10","dir":"北风","sc":"3级","spd":"15"}}
     */

    private String status;
    /**
     * cond : {"code":"100","txt":"晴"}
     * fl : 30
     * hum : 20%
     * pcpn : 0.0
     * pres : 1001
     * tmp : 32
     * vis : 10
     * wind : {"deg":"10","dir":"北风","sc":"3级","spd":"15"}
     */

    private NowBean now;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public NowBean getNow() {
        return now;
    }

    public void setNow(NowBean now) {
        this.now = now;
    }

    public static class NowBean {
        /**
         * code : 100
         * txt : 晴
         */

        private CondBean cond;
        private String fl;
        private String hum;
        private String pcpn;
        private String pres;
        private String tmp;
        private String vis;
        /**
         * deg : 10
         * dir : 北风
         * sc : 3级
         * spd : 15
         */

        private WindBean wind;

        public CondBean getCond() {
            return cond;
        }

        public void setCond(CondBean cond) {
            this.cond = cond;
        }

        public String getFl() {
            return fl;
        }

        public void setFl(String fl) {
            this.fl = fl;
        }

        public String getHum() {
            return hum;
        }

        public void setHum(String hum) {
            this.hum = hum;
        }

        public String getPcpn() {
            return pcpn;
        }

        public void setPcpn(String pcpn) {
            this.pcpn = pcpn;
        }

        public String getPres() {
            return pres;
        }

        public void setPres(String pres) {
            this.pres = pres;
        }

        public String getTmp() {
            return tmp;
        }

        public void setTmp(String tmp) {
            this.tmp = tmp;
        }

        public String getVis() {
            return vis;
        }

        public void setVis(String vis) {
            this.vis = vis;
        }

        public WindBean getWind() {
            return wind;
        }

        public void setWind(WindBean wind) {
            this.wind = wind;
        }

        public static class CondBean {
            private String code;
            private String txt;

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getTxt() {
                return txt;
            }

            public void setTxt(String txt) {
                this.txt = txt;
            }

            @Override
            public String toString() {
                return "CondBean{" +
                        "code='" + code + '\'' +
                        ", txt='" + txt + '\'' +
                        '}';
            }
        }

        public static class WindBean {
            private String deg;
            private String dir;
            private String sc;
            private String spd;

            public String getDeg() {
                return deg;
            }

            public void setDeg(String deg) {
                this.deg = deg;
            }

            public String getDir() {
                return dir;
            }

            public void setDir(String dir) {
                this.dir = dir;
            }

            public String getSc() {
                return sc;
            }

            public void setSc(String sc) {
                this.sc = sc;
            }

            public String getSpd() {
                return spd;
            }

            public void setSpd(String spd) {
                this.spd = spd;
            }

            @Override
            public String toString() {
                return "WindBean{" +
                        "deg='" + deg + '\'' +
                        ", dir='" + dir + '\'' +
                        ", sc='" + sc + '\'' +
                        ", spd='" + spd + '\'' +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "NowBean{" +
                    "cond=" + cond +
                    ", fl='" + fl + '\'' +
                    ", hum='" + hum + '\'' +
                    ", pcpn='" + pcpn + '\'' +
                    ", pres='" + pres + '\'' +
                    ", tmp='" + tmp + '\'' +
                    ", vis='" + vis + '\'' +
                    ", wind=" + wind +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "WeatherBean{" +
                "now=" + now +
                ", status='" + status + '\'' +
                '}';
    }

    //==========实现 VO 接口==========
    @Override
    public boolean isSuccess() {
        return "ok".equals(status);
    }

    @Override
    public int getWeatherInfoIcon() {
        int weatherInfoIcon;
        int condCode = Integer.parseInt(getNow().getCond().getCode());
        if (condCode < 0) {
            weatherInfoIcon = R.mipmap.ic_snow;
        } else if (condCode < 60) {
            weatherInfoIcon = R.mipmap.ic_rain;
        } else if (condCode < 90) {
            weatherInfoIcon = R.mipmap.ic_cloudy;
        } else {
            weatherInfoIcon = R.mipmap.ic_sunshine;
        }
        return weatherInfoIcon;
    }

    @Override
    public Spanned getWeatherInfoText() {
        Spanned weatherInfoText;
        int condCode = Integer.parseInt(getNow().getCond().getCode());
        String condCodeColorStr = "";
        if (condCode < 0) {
            condCodeColorStr = "#000066";
        } else if (condCode < 60) {
            condCodeColorStr = "#009900";
        } else if (condCode < 90) {
            condCodeColorStr = "#993300";
        } else {
            condCodeColorStr = "#cccc00";
        }
        weatherInfoText = Html.fromHtml("<font color='"+condCodeColorStr+"'>"+getNow().getCond().getTxt()+"</font>");
        return weatherInfoText;
    }

    @Override
    public String getRelativeHumidity() {
        return "相对湿度：" + getNow().getHum();
    }

    @Override
    public int getTemperatureIcon() {
        int temperatureIcon;
        int tmpInt = Integer.parseInt(getNow().getTmp());
        if (tmpInt < 15) {
            temperatureIcon = R.mipmap.ic_lowtemperature;
        } else if (tmpInt < 33) {
            temperatureIcon = R.mipmap.ic_thermophilic;
        } else {
            temperatureIcon = R.mipmap.ic_hightemperature;
        }
        return temperatureIcon;
    }

    @Override
    public String getAirPressure() {
        return "气压：" + getNow().getPres();
    }

    @Override
    public String getPrecipitation() {
        return "降水量：" + getNow().getPcpn();
    }

    @Override
    public String getVisibility() {
        return "能见度：" + getNow().getVis() + " KM";
    }

    @Override
    public String getWindDirectionAngle() {
        return "风向角度：" + getNow().getWind().getDeg();
    }

    @Override
    public String getWindDirection() {
        return "风向：" + getNow().getWind().getDir();
    }

    @Override
    public String getWindPower() {
        return "风力：" + getNow().getWind().getSc();
    }

    @Override
    public String getWindSpeed() {
        return "风速" + getNow().getWind().getSpd();
    }

}
