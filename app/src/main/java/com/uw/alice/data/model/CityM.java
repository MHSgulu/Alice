package com.uw.alice.data.model;

public class CityM {

    private String cityName;
    private String airQuality;
    private String nowTemp;
    private String highTemp;
    private String lowTemp;
    private String weather;

    public CityM(String cityName) {
        this.cityName = cityName;
    }

    public CityM(String cityName, String airQuality, String nowTemp, String highTemp, String lowTemp, String weather) {
        this.cityName = cityName;
        this.airQuality = airQuality;
        this.nowTemp = nowTemp;
        this.highTemp = highTemp;
        this.lowTemp = lowTemp;
        this.weather = weather;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getAirQuality() {
        return airQuality;
    }

    public void setAirQuality(String airQuality) {
        this.airQuality = airQuality;
    }

    public String getNowTemp() {
        return nowTemp;
    }

    public void setNowTemp(String nowTemp) {
        this.nowTemp = nowTemp;
    }

    public String getHighTemp() {
        return highTemp;
    }

    public void setHighTemp(String highTemp) {
        this.highTemp = highTemp;
    }

    public String getLowTemp() {
        return lowTemp;
    }

    public void setLowTemp(String lowTemp) {
        this.lowTemp = lowTemp;
    }
}
