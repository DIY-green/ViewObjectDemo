package com.diygreen.viewobjectdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private ImageView mWeatherinfoIV;
    private TextView mWeatherInfoTV;
    private TextView mRelativeHumidityTV;
    private ImageView mTemperatureIV;
    private TextView mAirPressureTV;
    private TextView mPrecipitationTV;
    private TextView mVisibilityTV;
    private TextView mWindDirectionAngleTV;
    private TextView mWindDirectionTV;
    private TextView mWindPowerTV;
    private TextView mWindSpeedTV;
    private ProgressBar mLoadingPB;
    private RadioGroup mTypeRG;

    private WeatherBean mWeatherBean;
    private WeatherVO mVOClass;
    private IWeatherVO mVOInterface;
    private int mType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
    }

    private void initView() {
        mWeatherinfoIV = (ImageView) findViewById(R.id.iv_weatherinfo);
        mWeatherInfoTV = (TextView) findViewById(R.id.tv_weatheriinfo);
        mRelativeHumidityTV = (TextView) findViewById(R.id.tv_relativehumidity);
        mTemperatureIV = (ImageView) findViewById(R.id.iv_temperature);
        mAirPressureTV = (TextView) findViewById(R.id.tv_airpressure);
        mPrecipitationTV = (TextView) findViewById(R.id.tv_precipitation);
        mVisibilityTV = (TextView) findViewById(R.id.tv_visibility);
        mWindDirectionAngleTV = (TextView) findViewById(R.id.tv_winddirectionangle);
        mWindDirectionTV = (TextView) findViewById(R.id.tv_winddirection);
        mWindPowerTV = (TextView) findViewById(R.id.tv_windpower);
        mWindSpeedTV = (TextView) findViewById(R.id.tv_windspeed);
        mLoadingPB = (ProgressBar) findViewById(R.id.pb_loading);
        mTypeRG = (RadioGroup) findViewById(R.id.rg_type);
    }

    private void initListener() {
        mTypeRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_voclass:
                        mType = 0;
                        break;
                    case R.id.rb_vointerface:
                        mType = 1;
                        break;
                    case R.id.rb_vomethod:
                        mType = 2;
                        break;
                }
            }
        });
    }

    private void fillDataByVOClass() {
        if (mVOClass == null) return;
        mWeatherinfoIV.setImageResource(mVOClass.getWeatherInfoIcon());
        mWeatherInfoTV.setText(mVOClass.getWeatherInfoText());
        mRelativeHumidityTV.setText(mVOClass.getRelativeHumidity());
        mTemperatureIV.setImageResource(mVOClass.getTemperatureIcon());
        mAirPressureTV.setText(mVOClass.getAirPressure());
        mPrecipitationTV.setText(mVOClass.getPrecipitation());
        mVisibilityTV.setText(mVOClass.getVisibility());
        mWindDirectionAngleTV.setText(mVOClass.getWindDirectionAngle());
        mWindDirectionTV.setText(mVOClass.getWindDirection());
        mWindPowerTV.setText(mVOClass.getWindPower());
        mWindSpeedTV.setText(mVOClass.getWindSpeed());
    }

    private void fillDataByVOInterface() {
        if (mVOInterface == null) return;
        mWeatherinfoIV.setImageResource(mVOInterface.getWeatherInfoIcon());
        mWeatherInfoTV.setText(mVOInterface.getWeatherInfoText());
        mRelativeHumidityTV.setText(mVOInterface.getRelativeHumidity());
        mTemperatureIV.setImageResource(mVOInterface.getTemperatureIcon());
        mAirPressureTV.setText(mVOInterface.getAirPressure());
        mPrecipitationTV.setText(mVOInterface.getPrecipitation());
        mVisibilityTV.setText(mVOInterface.getVisibility());
        mWindDirectionAngleTV.setText(mVOInterface.getWindDirectionAngle());
        mWindDirectionTV.setText(mVOInterface.getWindDirection());
        mWindPowerTV.setText(mVOInterface.getWindPower());
        mWindSpeedTV.setText(mVOInterface.getWindSpeed());
    }

    private void fillDataByVOMethod() {
        if (mWeatherBean == null) return;
        mWeatherinfoIV.setImageResource(mWeatherBean.getWeatherInfoIcon());
        mWeatherInfoTV.setText(mWeatherBean.getWeatherInfoText());
        mRelativeHumidityTV.setText(mWeatherBean.getRelativeHumidity());
        mTemperatureIV.setImageResource(mWeatherBean.getTemperatureIcon());
        mAirPressureTV.setText(mWeatherBean.getAirPressure());
        mPrecipitationTV.setText(mWeatherBean.getPrecipitation());
        mVisibilityTV.setText(mWeatherBean.getVisibility());
        mWindDirectionAngleTV.setText(mWeatherBean.getWindDirectionAngle());
        mWindDirectionTV.setText(mWeatherBean.getWindDirection());
        mWindPowerTV.setText(mWeatherBean.getWindPower());
        mWindSpeedTV.setText(mWeatherBean.getWindSpeed());

    }

    public void getWeather(View v) {
        getWeatherFromNet();
    }

    private void getWeatherFromNet() {
        mLoadingPB.setVisibility(View.VISIBLE);
        mLoadingPB.postDelayed(new Runnable() {
            @Override
            public void run() {
                parseWeatherStr();
                mLoadingPB.setVisibility(View.GONE);
            }
        }, 3000);
    }

    private void parseWeatherStr() {
        String weatherJsonStr = "";
        switch (mType) {
            case 0:
                weatherJsonStr = getResources().getString(R.string.weather_json1);
                break;
            case 1:
                weatherJsonStr = getResources().getString(R.string.weather_json2);
                break;
            case 2:
                weatherJsonStr = getResources().getString(R.string.weather_json3);
                break;
        }
        Log.e(TAG, weatherJsonStr);
        Gson gson = new Gson();
        mWeatherBean = gson.fromJson(weatherJsonStr, WeatherBean.class);
        mVOClass = new WeatherVO(mWeatherBean);
        mVOInterface = mWeatherBean;
        WeatherVO weatherVO2 = VOConverterUtil.getWeatherVOFromWeatherBean(mWeatherBean);

        Log.e(TAG, mWeatherBean.toString());
        Log.e(TAG, mVOClass.toString());
        Log.e(TAG, weatherVO2.toString());

        switch (mType) {
            case 0:
                fillDataByVOClass();
                break;
            case 1:
                fillDataByVOInterface();
                break;
            case 2:
                fillDataByVOMethod();
                break;
        }
    }
}
