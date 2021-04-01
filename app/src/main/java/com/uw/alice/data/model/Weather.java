package com.uw.alice.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Weather {


    /**
     * code : 10000
     * charge : false
     * msg : 查询成功
     * data : {"status":0,"msg":"ok","result":{"city":"上海","cityid":24,"citycode":"101020100","date":"2021-04-01","week":"星期四","weather":"阴","temp":"17","temphigh":"19","templow":"16","img":"2","humidity":"93","pressure":"1007","windspeed":"0.8","winddirect":"东北风","windpower":"1级","updatetime":"2021-04-01 16:30:00","index":[{"iname":"空调指数","ivalue":"较少开启","detail":"您将感到很舒适，一般不需要开启空调。"},{"iname":"运动指数","ivalue":"较不宜","detail":"有降水，推荐您在室内进行各种健身休闲运动，若坚持户外运动，须注意保暖并携带雨具。"},{"iname":"紫外线指数","ivalue":"最弱","detail":"属弱紫外线辐射天气，无需特别防护。若长期在户外，建议涂擦SPF在8-12之间的防晒护肤品。"},{"iname":"感冒指数","ivalue":"较易发","detail":"天气较凉，较易发生感冒，请适当增加衣服。体质较弱的朋友尤其应该注意防护。"},{"iname":"洗车指数","ivalue":"不宜","detail":"不宜洗车，未来24小时内有雨，如果在此期间洗车，雨水和路上的泥水可能会再次弄脏您的爱车。"},{"iname":"空气污染扩散指数","ivalue":"良","detail":"气象条件有利于空气污染物稀释、扩散和清除，可在室外正常活动。"},{"iname":"穿衣指数","ivalue":"较冷","detail":"建议着厚外套加毛衣等服装。年老体弱者宜着大衣、呢外套加羊毛衫。"}],"aqi":{"so2":"4","so224":"4","no2":"50","no224":"32","co":"0.620","co24":"0.560","o3":"49","o38":"62","o324":"78","pm10":"48","pm1024":"43","pm2_5":"36","pm2_524":"27","iso2":"2","ino2":"25","ico":"7","io3":"16","io38":"31","ipm10":"47","ipm2_5":"52","aqi":"52","primarypollutant":"PM2.5","quality":"良","timepoint":"2021-04-01 16:00:00","aqiinfo":{"level":"二级","color":"#FFFF00","affect":"空气质量可接受，但某些污染物可能对极少数异常敏感人群健康有较弱影响","measure":"极少数异常敏感人群应减少户外活动"}},"daily":[{"date":"2021-04-01","week":"星期四","sunrise":"05:45","sunset":"18:13","night":{"weather":"小雨","templow":"16","img":"7","winddirect":"东南风","windpower":"微风"},"day":{"weather":"小雨","temphigh":"19","img":"7","winddirect":"东南风","windpower":"微风"}},{"date":"2021-04-02","week":"星期五","sunrise":"05:44","sunset":"18:14","night":{"weather":"小雨","templow":"16","img":"7","winddirect":"北风","windpower":"微风"},"day":{"weather":"小雨","temphigh":"21","img":"7","winddirect":"北风","windpower":"微风"}},{"date":"2021-04-03","week":"星期六","sunrise":"05:42","sunset":"18:14","night":{"weather":"小雨","templow":"13","img":"7","winddirect":"西北风","windpower":"微风"},"day":{"weather":"小雨","temphigh":"21","img":"7","winddirect":"南风","windpower":"微风"}},{"date":"2021-04-04","week":"星期日","sunrise":"05:41","sunset":"18:15","night":{"weather":"晴","templow":"10","img":"0","winddirect":"西北风","windpower":"微风"},"day":{"weather":"多云","temphigh":"17","img":"1","winddirect":"北风","windpower":"微风"}},{"date":"2021-04-05","week":"星期一","sunrise":"05:40","sunset":"18:16","night":{"weather":"阴","templow":"12","img":"2","winddirect":"西风","windpower":"微风"},"day":{"weather":"阴","temphigh":"15","img":"2","winddirect":"西南风","windpower":"微风"}},{"date":"2021-04-06","week":"星期二","sunrise":"05:39","sunset":"18:16","night":{"weather":"阴","templow":"11","img":"2","winddirect":"东南风","windpower":"微风"},"day":{"weather":"阴","temphigh":"18","img":"2","winddirect":"东风","windpower":"微风"}},{"date":"2021-04-07","week":"星期三","sunrise":"05:37","sunset":"18:17","night":{"weather":"阴","templow":"11","img":"2","winddirect":"北风","windpower":"4-5级"},"day":{"weather":"小雨","temphigh":"17","img":"7","winddirect":"北风","windpower":"微风"}}],"hourly":[{"time":"16:00","weather":"多云","temp":"16","img":"1"},{"time":"17:00","weather":"阴","temp":"16","img":"2"},{"time":"18:00","weather":"阴","temp":"16","img":"2"},{"time":"19:00","weather":"阴","temp":"16","img":"2"},{"time":"20:00","weather":"阴","temp":"17","img":"2"},{"time":"21:00","weather":"阴","temp":"17","img":"2"},{"time":"22:00","weather":"阴","temp":"16","img":"2"},{"time":"23:00","weather":"阴","temp":"16","img":"2"},{"time":"0:00","weather":"阴","temp":"16","img":"2"},{"time":"1:00","weather":"阴","temp":"16","img":"2"},{"time":"2:00","weather":"阴","temp":"16","img":"2"},{"time":"3:00","weather":"小雨","temp":"16","img":"7"},{"time":"4:00","weather":"小雨","temp":"16","img":"7"},{"time":"5:00","weather":"小雨","temp":"16","img":"7"},{"time":"6:00","weather":"阴","temp":"16","img":"2"},{"time":"7:00","weather":"阴","temp":"16","img":"2"},{"time":"8:00","weather":"阴","temp":"17","img":"2"},{"time":"9:00","weather":"阴","temp":"18","img":"2"},{"time":"10:00","weather":"阴","temp":"18","img":"2"},{"time":"11:00","weather":"阴","temp":"19","img":"2"},{"time":"12:00","weather":"阴","temp":"20","img":"2"},{"time":"13:00","weather":"中雨","temp":"20","img":"8"},{"time":"14:00","weather":"中雨","temp":"21","img":"8"},{"time":"15:00","weather":"阴","temp":"20","img":"2"}]}}
     * requestId : 81ab6f5e104d42b484d581017dab15c0
     */

    @SerializedName("code")
    private String code;
    @SerializedName("charge")
    private Boolean charge;
    @SerializedName("msg")
    private String msg;
    @SerializedName("data")   //这个地方人为修改了之后 就不要动了 换回原来的字段反而会报错 原来字段result重复了所以手动在json解析网站里编辑字段key
    private DataBean data;
    @SerializedName("requestId")
    private String requestId;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Boolean getCharge() {
        return charge;
    }

    public void setCharge(Boolean charge) {
        this.charge = charge;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public static class DataBean {
        /**
         * status : 0
         * msg : ok
         * result : {"city":"上海","cityid":24,"citycode":"101020100","date":"2021-04-01","week":"星期四","weather":"阴","temp":"17","temphigh":"19","templow":"16","img":"2","humidity":"93","pressure":"1007","windspeed":"0.8","winddirect":"东北风","windpower":"1级","updatetime":"2021-04-01 16:30:00","index":[{"iname":"空调指数","ivalue":"较少开启","detail":"您将感到很舒适，一般不需要开启空调。"},{"iname":"运动指数","ivalue":"较不宜","detail":"有降水，推荐您在室内进行各种健身休闲运动，若坚持户外运动，须注意保暖并携带雨具。"},{"iname":"紫外线指数","ivalue":"最弱","detail":"属弱紫外线辐射天气，无需特别防护。若长期在户外，建议涂擦SPF在8-12之间的防晒护肤品。"},{"iname":"感冒指数","ivalue":"较易发","detail":"天气较凉，较易发生感冒，请适当增加衣服。体质较弱的朋友尤其应该注意防护。"},{"iname":"洗车指数","ivalue":"不宜","detail":"不宜洗车，未来24小时内有雨，如果在此期间洗车，雨水和路上的泥水可能会再次弄脏您的爱车。"},{"iname":"空气污染扩散指数","ivalue":"良","detail":"气象条件有利于空气污染物稀释、扩散和清除，可在室外正常活动。"},{"iname":"穿衣指数","ivalue":"较冷","detail":"建议着厚外套加毛衣等服装。年老体弱者宜着大衣、呢外套加羊毛衫。"}],"aqi":{"so2":"4","so224":"4","no2":"50","no224":"32","co":"0.620","co24":"0.560","o3":"49","o38":"62","o324":"78","pm10":"48","pm1024":"43","pm2_5":"36","pm2_524":"27","iso2":"2","ino2":"25","ico":"7","io3":"16","io38":"31","ipm10":"47","ipm2_5":"52","aqi":"52","primarypollutant":"PM2.5","quality":"良","timepoint":"2021-04-01 16:00:00","aqiinfo":{"level":"二级","color":"#FFFF00","affect":"空气质量可接受，但某些污染物可能对极少数异常敏感人群健康有较弱影响","measure":"极少数异常敏感人群应减少户外活动"}},"daily":[{"date":"2021-04-01","week":"星期四","sunrise":"05:45","sunset":"18:13","night":{"weather":"小雨","templow":"16","img":"7","winddirect":"东南风","windpower":"微风"},"day":{"weather":"小雨","temphigh":"19","img":"7","winddirect":"东南风","windpower":"微风"}},{"date":"2021-04-02","week":"星期五","sunrise":"05:44","sunset":"18:14","night":{"weather":"小雨","templow":"16","img":"7","winddirect":"北风","windpower":"微风"},"day":{"weather":"小雨","temphigh":"21","img":"7","winddirect":"北风","windpower":"微风"}},{"date":"2021-04-03","week":"星期六","sunrise":"05:42","sunset":"18:14","night":{"weather":"小雨","templow":"13","img":"7","winddirect":"西北风","windpower":"微风"},"day":{"weather":"小雨","temphigh":"21","img":"7","winddirect":"南风","windpower":"微风"}},{"date":"2021-04-04","week":"星期日","sunrise":"05:41","sunset":"18:15","night":{"weather":"晴","templow":"10","img":"0","winddirect":"西北风","windpower":"微风"},"day":{"weather":"多云","temphigh":"17","img":"1","winddirect":"北风","windpower":"微风"}},{"date":"2021-04-05","week":"星期一","sunrise":"05:40","sunset":"18:16","night":{"weather":"阴","templow":"12","img":"2","winddirect":"西风","windpower":"微风"},"day":{"weather":"阴","temphigh":"15","img":"2","winddirect":"西南风","windpower":"微风"}},{"date":"2021-04-06","week":"星期二","sunrise":"05:39","sunset":"18:16","night":{"weather":"阴","templow":"11","img":"2","winddirect":"东南风","windpower":"微风"},"day":{"weather":"阴","temphigh":"18","img":"2","winddirect":"东风","windpower":"微风"}},{"date":"2021-04-07","week":"星期三","sunrise":"05:37","sunset":"18:17","night":{"weather":"阴","templow":"11","img":"2","winddirect":"北风","windpower":"4-5级"},"day":{"weather":"小雨","temphigh":"17","img":"7","winddirect":"北风","windpower":"微风"}}],"hourly":[{"time":"16:00","weather":"多云","temp":"16","img":"1"},{"time":"17:00","weather":"阴","temp":"16","img":"2"},{"time":"18:00","weather":"阴","temp":"16","img":"2"},{"time":"19:00","weather":"阴","temp":"16","img":"2"},{"time":"20:00","weather":"阴","temp":"17","img":"2"},{"time":"21:00","weather":"阴","temp":"17","img":"2"},{"time":"22:00","weather":"阴","temp":"16","img":"2"},{"time":"23:00","weather":"阴","temp":"16","img":"2"},{"time":"0:00","weather":"阴","temp":"16","img":"2"},{"time":"1:00","weather":"阴","temp":"16","img":"2"},{"time":"2:00","weather":"阴","temp":"16","img":"2"},{"time":"3:00","weather":"小雨","temp":"16","img":"7"},{"time":"4:00","weather":"小雨","temp":"16","img":"7"},{"time":"5:00","weather":"小雨","temp":"16","img":"7"},{"time":"6:00","weather":"阴","temp":"16","img":"2"},{"time":"7:00","weather":"阴","temp":"16","img":"2"},{"time":"8:00","weather":"阴","temp":"17","img":"2"},{"time":"9:00","weather":"阴","temp":"18","img":"2"},{"time":"10:00","weather":"阴","temp":"18","img":"2"},{"time":"11:00","weather":"阴","temp":"19","img":"2"},{"time":"12:00","weather":"阴","temp":"20","img":"2"},{"time":"13:00","weather":"中雨","temp":"20","img":"8"},{"time":"14:00","weather":"中雨","temp":"21","img":"8"},{"time":"15:00","weather":"阴","temp":"20","img":"2"}]}
         */

        @SerializedName("status")
        private Integer status;
        @SerializedName("msg")
        private String msg;
        @SerializedName("result")
        private ResultBean result;

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public ResultBean getResult() {
            return result;
        }

        public void setResult(ResultBean result) {
            this.result = result;
        }

        public static class ResultBean {
            /**
             * city : 上海
             * cityid : 24
             * citycode : 101020100
             * date : 2021-04-01
             * week : 星期四
             * weather : 阴
             * temp : 17
             * temphigh : 19
             * templow : 16
             * img : 2
             * humidity : 93
             * pressure : 1007
             * windspeed : 0.8
             * winddirect : 东北风
             * windpower : 1级
             * updatetime : 2021-04-01 16:30:00
             * index : [{"iname":"空调指数","ivalue":"较少开启","detail":"您将感到很舒适，一般不需要开启空调。"},{"iname":"运动指数","ivalue":"较不宜","detail":"有降水，推荐您在室内进行各种健身休闲运动，若坚持户外运动，须注意保暖并携带雨具。"},{"iname":"紫外线指数","ivalue":"最弱","detail":"属弱紫外线辐射天气，无需特别防护。若长期在户外，建议涂擦SPF在8-12之间的防晒护肤品。"},{"iname":"感冒指数","ivalue":"较易发","detail":"天气较凉，较易发生感冒，请适当增加衣服。体质较弱的朋友尤其应该注意防护。"},{"iname":"洗车指数","ivalue":"不宜","detail":"不宜洗车，未来24小时内有雨，如果在此期间洗车，雨水和路上的泥水可能会再次弄脏您的爱车。"},{"iname":"空气污染扩散指数","ivalue":"良","detail":"气象条件有利于空气污染物稀释、扩散和清除，可在室外正常活动。"},{"iname":"穿衣指数","ivalue":"较冷","detail":"建议着厚外套加毛衣等服装。年老体弱者宜着大衣、呢外套加羊毛衫。"}]
             * aqi : {"so2":"4","so224":"4","no2":"50","no224":"32","co":"0.620","co24":"0.560","o3":"49","o38":"62","o324":"78","pm10":"48","pm1024":"43","pm2_5":"36","pm2_524":"27","iso2":"2","ino2":"25","ico":"7","io3":"16","io38":"31","ipm10":"47","ipm2_5":"52","aqi":"52","primarypollutant":"PM2.5","quality":"良","timepoint":"2021-04-01 16:00:00","aqiinfo":{"level":"二级","color":"#FFFF00","affect":"空气质量可接受，但某些污染物可能对极少数异常敏感人群健康有较弱影响","measure":"极少数异常敏感人群应减少户外活动"}}
             * daily : [{"date":"2021-04-01","week":"星期四","sunrise":"05:45","sunset":"18:13","night":{"weather":"小雨","templow":"16","img":"7","winddirect":"东南风","windpower":"微风"},"day":{"weather":"小雨","temphigh":"19","img":"7","winddirect":"东南风","windpower":"微风"}},{"date":"2021-04-02","week":"星期五","sunrise":"05:44","sunset":"18:14","night":{"weather":"小雨","templow":"16","img":"7","winddirect":"北风","windpower":"微风"},"day":{"weather":"小雨","temphigh":"21","img":"7","winddirect":"北风","windpower":"微风"}},{"date":"2021-04-03","week":"星期六","sunrise":"05:42","sunset":"18:14","night":{"weather":"小雨","templow":"13","img":"7","winddirect":"西北风","windpower":"微风"},"day":{"weather":"小雨","temphigh":"21","img":"7","winddirect":"南风","windpower":"微风"}},{"date":"2021-04-04","week":"星期日","sunrise":"05:41","sunset":"18:15","night":{"weather":"晴","templow":"10","img":"0","winddirect":"西北风","windpower":"微风"},"day":{"weather":"多云","temphigh":"17","img":"1","winddirect":"北风","windpower":"微风"}},{"date":"2021-04-05","week":"星期一","sunrise":"05:40","sunset":"18:16","night":{"weather":"阴","templow":"12","img":"2","winddirect":"西风","windpower":"微风"},"day":{"weather":"阴","temphigh":"15","img":"2","winddirect":"西南风","windpower":"微风"}},{"date":"2021-04-06","week":"星期二","sunrise":"05:39","sunset":"18:16","night":{"weather":"阴","templow":"11","img":"2","winddirect":"东南风","windpower":"微风"},"day":{"weather":"阴","temphigh":"18","img":"2","winddirect":"东风","windpower":"微风"}},{"date":"2021-04-07","week":"星期三","sunrise":"05:37","sunset":"18:17","night":{"weather":"阴","templow":"11","img":"2","winddirect":"北风","windpower":"4-5级"},"day":{"weather":"小雨","temphigh":"17","img":"7","winddirect":"北风","windpower":"微风"}}]
             * hourly : [{"time":"16:00","weather":"多云","temp":"16","img":"1"},{"time":"17:00","weather":"阴","temp":"16","img":"2"},{"time":"18:00","weather":"阴","temp":"16","img":"2"},{"time":"19:00","weather":"阴","temp":"16","img":"2"},{"time":"20:00","weather":"阴","temp":"17","img":"2"},{"time":"21:00","weather":"阴","temp":"17","img":"2"},{"time":"22:00","weather":"阴","temp":"16","img":"2"},{"time":"23:00","weather":"阴","temp":"16","img":"2"},{"time":"0:00","weather":"阴","temp":"16","img":"2"},{"time":"1:00","weather":"阴","temp":"16","img":"2"},{"time":"2:00","weather":"阴","temp":"16","img":"2"},{"time":"3:00","weather":"小雨","temp":"16","img":"7"},{"time":"4:00","weather":"小雨","temp":"16","img":"7"},{"time":"5:00","weather":"小雨","temp":"16","img":"7"},{"time":"6:00","weather":"阴","temp":"16","img":"2"},{"time":"7:00","weather":"阴","temp":"16","img":"2"},{"time":"8:00","weather":"阴","temp":"17","img":"2"},{"time":"9:00","weather":"阴","temp":"18","img":"2"},{"time":"10:00","weather":"阴","temp":"18","img":"2"},{"time":"11:00","weather":"阴","temp":"19","img":"2"},{"time":"12:00","weather":"阴","temp":"20","img":"2"},{"time":"13:00","weather":"中雨","temp":"20","img":"8"},{"time":"14:00","weather":"中雨","temp":"21","img":"8"},{"time":"15:00","weather":"阴","temp":"20","img":"2"}]
             */

            @SerializedName("city")
            private String city;
            @SerializedName("cityid")
            private Integer cityid;
            @SerializedName("citycode")
            private String citycode;
            @SerializedName("date")
            private String date;
            @SerializedName("week")
            private String week;
            @SerializedName("weather")
            private String weather;
            @SerializedName("temp")
            private String temp;
            @SerializedName("temphigh")
            private String temphigh;
            @SerializedName("templow")
            private String templow;
            @SerializedName("img")
            private String img;
            @SerializedName("humidity")
            private String humidity;
            @SerializedName("pressure")
            private String pressure;
            @SerializedName("windspeed")
            private String windspeed;
            @SerializedName("winddirect")
            private String winddirect;
            @SerializedName("windpower")
            private String windpower;
            @SerializedName("updatetime")
            private String updatetime;
            @SerializedName("index")
            private List<IndexBean> index;
            @SerializedName("aqi")
            private AqiBean aqi;
            @SerializedName("daily")
            private List<DailyBean> daily;
            @SerializedName("hourly")
            private List<HourlyBean> hourly;

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public Integer getCityid() {
                return cityid;
            }

            public void setCityid(Integer cityid) {
                this.cityid = cityid;
            }

            public String getCitycode() {
                return citycode;
            }

            public void setCitycode(String citycode) {
                this.citycode = citycode;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getWeek() {
                return week;
            }

            public void setWeek(String week) {
                this.week = week;
            }

            public String getWeather() {
                return weather;
            }

            public void setWeather(String weather) {
                this.weather = weather;
            }

            public String getTemp() {
                return temp;
            }

            public void setTemp(String temp) {
                this.temp = temp;
            }

            public String getTemphigh() {
                return temphigh;
            }

            public void setTemphigh(String temphigh) {
                this.temphigh = temphigh;
            }

            public String getTemplow() {
                return templow;
            }

            public void setTemplow(String templow) {
                this.templow = templow;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getHumidity() {
                return humidity;
            }

            public void setHumidity(String humidity) {
                this.humidity = humidity;
            }

            public String getPressure() {
                return pressure;
            }

            public void setPressure(String pressure) {
                this.pressure = pressure;
            }

            public String getWindspeed() {
                return windspeed;
            }

            public void setWindspeed(String windspeed) {
                this.windspeed = windspeed;
            }

            public String getWinddirect() {
                return winddirect;
            }

            public void setWinddirect(String winddirect) {
                this.winddirect = winddirect;
            }

            public String getWindpower() {
                return windpower;
            }

            public void setWindpower(String windpower) {
                this.windpower = windpower;
            }

            public String getUpdatetime() {
                return updatetime;
            }

            public void setUpdatetime(String updatetime) {
                this.updatetime = updatetime;
            }

            public List<IndexBean> getIndex() {
                return index;
            }

            public void setIndex(List<IndexBean> index) {
                this.index = index;
            }

            public AqiBean getAqi() {
                return aqi;
            }

            public void setAqi(AqiBean aqi) {
                this.aqi = aqi;
            }

            public List<DailyBean> getDaily() {
                return daily;
            }

            public void setDaily(List<DailyBean> daily) {
                this.daily = daily;
            }

            public List<HourlyBean> getHourly() {
                return hourly;
            }

            public void setHourly(List<HourlyBean> hourly) {
                this.hourly = hourly;
            }

            public static class AqiBean {
                /**
                 * so2 : 4
                 * so224 : 4
                 * no2 : 50
                 * no224 : 32
                 * co : 0.620
                 * co24 : 0.560
                 * o3 : 49
                 * o38 : 62
                 * o324 : 78
                 * pm10 : 48
                 * pm1024 : 43
                 * pm2_5 : 36
                 * pm2_524 : 27
                 * iso2 : 2
                 * ino2 : 25
                 * ico : 7
                 * io3 : 16
                 * io38 : 31
                 * ipm10 : 47
                 * ipm2_5 : 52
                 * aqi : 52
                 * primarypollutant : PM2.5
                 * quality : 良
                 * timepoint : 2021-04-01 16:00:00
                 * aqiinfo : {"level":"二级","color":"#FFFF00","affect":"空气质量可接受，但某些污染物可能对极少数异常敏感人群健康有较弱影响","measure":"极少数异常敏感人群应减少户外活动"}
                 */

                @SerializedName("so2")
                private String so2;
                @SerializedName("so224")
                private String so224;
                @SerializedName("no2")
                private String no2;
                @SerializedName("no224")
                private String no224;
                @SerializedName("co")
                private String co;
                @SerializedName("co24")
                private String co24;
                @SerializedName("o3")
                private String o3;
                @SerializedName("o38")
                private String o38;
                @SerializedName("o324")
                private String o324;
                @SerializedName("pm10")
                private String pm10;
                @SerializedName("pm1024")
                private String pm1024;
                @SerializedName("pm2_5")
                private String pm25;
                @SerializedName("pm2_524")
                private String pm2524;
                @SerializedName("iso2")
                private String iso2;
                @SerializedName("ino2")
                private String ino2;
                @SerializedName("ico")
                private String ico;
                @SerializedName("io3")
                private String io3;
                @SerializedName("io38")
                private String io38;
                @SerializedName("ipm10")
                private String ipm10;
                @SerializedName("ipm2_5")
                private String ipm25;
                @SerializedName("aqi")
                private String aqi;
                @SerializedName("primarypollutant")
                private String primarypollutant;
                @SerializedName("quality")
                private String quality;
                @SerializedName("timepoint")
                private String timepoint;
                @SerializedName("aqiinfo")
                private AqiinfoBean aqiinfo;

                public String getSo2() {
                    return so2;
                }

                public void setSo2(String so2) {
                    this.so2 = so2;
                }

                public String getSo224() {
                    return so224;
                }

                public void setSo224(String so224) {
                    this.so224 = so224;
                }

                public String getNo2() {
                    return no2;
                }

                public void setNo2(String no2) {
                    this.no2 = no2;
                }

                public String getNo224() {
                    return no224;
                }

                public void setNo224(String no224) {
                    this.no224 = no224;
                }

                public String getCo() {
                    return co;
                }

                public void setCo(String co) {
                    this.co = co;
                }

                public String getCo24() {
                    return co24;
                }

                public void setCo24(String co24) {
                    this.co24 = co24;
                }

                public String getO3() {
                    return o3;
                }

                public void setO3(String o3) {
                    this.o3 = o3;
                }

                public String getO38() {
                    return o38;
                }

                public void setO38(String o38) {
                    this.o38 = o38;
                }

                public String getO324() {
                    return o324;
                }

                public void setO324(String o324) {
                    this.o324 = o324;
                }

                public String getPm10() {
                    return pm10;
                }

                public void setPm10(String pm10) {
                    this.pm10 = pm10;
                }

                public String getPm1024() {
                    return pm1024;
                }

                public void setPm1024(String pm1024) {
                    this.pm1024 = pm1024;
                }

                public String getPm25() {
                    return pm25;
                }

                public void setPm25(String pm25) {
                    this.pm25 = pm25;
                }

                public String getPm2524() {
                    return pm2524;
                }

                public void setPm2524(String pm2524) {
                    this.pm2524 = pm2524;
                }

                public String getIso2() {
                    return iso2;
                }

                public void setIso2(String iso2) {
                    this.iso2 = iso2;
                }

                public String getIno2() {
                    return ino2;
                }

                public void setIno2(String ino2) {
                    this.ino2 = ino2;
                }

                public String getIco() {
                    return ico;
                }

                public void setIco(String ico) {
                    this.ico = ico;
                }

                public String getIo3() {
                    return io3;
                }

                public void setIo3(String io3) {
                    this.io3 = io3;
                }

                public String getIo38() {
                    return io38;
                }

                public void setIo38(String io38) {
                    this.io38 = io38;
                }

                public String getIpm10() {
                    return ipm10;
                }

                public void setIpm10(String ipm10) {
                    this.ipm10 = ipm10;
                }

                public String getIpm25() {
                    return ipm25;
                }

                public void setIpm25(String ipm25) {
                    this.ipm25 = ipm25;
                }

                public String getAqi() {
                    return aqi;
                }

                public void setAqi(String aqi) {
                    this.aqi = aqi;
                }

                public String getPrimarypollutant() {
                    return primarypollutant;
                }

                public void setPrimarypollutant(String primarypollutant) {
                    this.primarypollutant = primarypollutant;
                }

                public String getQuality() {
                    return quality;
                }

                public void setQuality(String quality) {
                    this.quality = quality;
                }

                public String getTimepoint() {
                    return timepoint;
                }

                public void setTimepoint(String timepoint) {
                    this.timepoint = timepoint;
                }

                public AqiinfoBean getAqiinfo() {
                    return aqiinfo;
                }

                public void setAqiinfo(AqiinfoBean aqiinfo) {
                    this.aqiinfo = aqiinfo;
                }

                public static class AqiinfoBean {
                    /**
                     * level : 二级
                     * color : #FFFF00
                     * affect : 空气质量可接受，但某些污染物可能对极少数异常敏感人群健康有较弱影响
                     * measure : 极少数异常敏感人群应减少户外活动
                     */

                    @SerializedName("level")
                    private String level;
                    @SerializedName("color")
                    private String color;
                    @SerializedName("affect")
                    private String affect;
                    @SerializedName("measure")
                    private String measure;

                    public String getLevel() {
                        return level;
                    }

                    public void setLevel(String level) {
                        this.level = level;
                    }

                    public String getColor() {
                        return color;
                    }

                    public void setColor(String color) {
                        this.color = color;
                    }

                    public String getAffect() {
                        return affect;
                    }

                    public void setAffect(String affect) {
                        this.affect = affect;
                    }

                    public String getMeasure() {
                        return measure;
                    }

                    public void setMeasure(String measure) {
                        this.measure = measure;
                    }
                }
            }

            public static class IndexBean {
                /**
                 * iname : 空调指数
                 * ivalue : 较少开启
                 * detail : 您将感到很舒适，一般不需要开启空调。
                 */

                @SerializedName("iname")
                private String iname;
                @SerializedName("ivalue")
                private String ivalue;
                @SerializedName("detail")
                private String detail;

                public String getIname() {
                    return iname;
                }

                public void setIname(String iname) {
                    this.iname = iname;
                }

                public String getIvalue() {
                    return ivalue;
                }

                public void setIvalue(String ivalue) {
                    this.ivalue = ivalue;
                }

                public String getDetail() {
                    return detail;
                }

                public void setDetail(String detail) {
                    this.detail = detail;
                }
            }

            public static class DailyBean {
                /**
                 * date : 2021-04-01
                 * week : 星期四
                 * sunrise : 05:45
                 * sunset : 18:13
                 * night : {"weather":"小雨","templow":"16","img":"7","winddirect":"东南风","windpower":"微风"}
                 * day : {"weather":"小雨","temphigh":"19","img":"7","winddirect":"东南风","windpower":"微风"}
                 */

                @SerializedName("date")
                private String date;
                @SerializedName("week")
                private String week;
                @SerializedName("sunrise")
                private String sunrise;
                @SerializedName("sunset")
                private String sunset;
                @SerializedName("night")
                private NightBean night;
                @SerializedName("day")
                private DayBean day;

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public String getWeek() {
                    return week;
                }

                public void setWeek(String week) {
                    this.week = week;
                }

                public String getSunrise() {
                    return sunrise;
                }

                public void setSunrise(String sunrise) {
                    this.sunrise = sunrise;
                }

                public String getSunset() {
                    return sunset;
                }

                public void setSunset(String sunset) {
                    this.sunset = sunset;
                }

                public NightBean getNight() {
                    return night;
                }

                public void setNight(NightBean night) {
                    this.night = night;
                }

                public DayBean getDay() {
                    return day;
                }

                public void setDay(DayBean day) {
                    this.day = day;
                }

                public static class NightBean {
                    /**
                     * weather : 小雨
                     * templow : 16
                     * img : 7
                     * winddirect : 东南风
                     * windpower : 微风
                     */

                    @SerializedName("weather")
                    private String weather;
                    @SerializedName("templow")
                    private String templow;
                    @SerializedName("img")
                    private String img;
                    @SerializedName("winddirect")
                    private String winddirect;
                    @SerializedName("windpower")
                    private String windpower;

                    public String getWeather() {
                        return weather;
                    }

                    public void setWeather(String weather) {
                        this.weather = weather;
                    }

                    public String getTemplow() {
                        return templow;
                    }

                    public void setTemplow(String templow) {
                        this.templow = templow;
                    }

                    public String getImg() {
                        return img;
                    }

                    public void setImg(String img) {
                        this.img = img;
                    }

                    public String getWinddirect() {
                        return winddirect;
                    }

                    public void setWinddirect(String winddirect) {
                        this.winddirect = winddirect;
                    }

                    public String getWindpower() {
                        return windpower;
                    }

                    public void setWindpower(String windpower) {
                        this.windpower = windpower;
                    }
                }

                public static class DayBean {
                    /**
                     * weather : 小雨
                     * temphigh : 19
                     * img : 7
                     * winddirect : 东南风
                     * windpower : 微风
                     */

                    @SerializedName("weather")
                    private String weather;
                    @SerializedName("temphigh")
                    private String temphigh;
                    @SerializedName("img")
                    private String img;
                    @SerializedName("winddirect")
                    private String winddirect;
                    @SerializedName("windpower")
                    private String windpower;

                    public String getWeather() {
                        return weather;
                    }

                    public void setWeather(String weather) {
                        this.weather = weather;
                    }

                    public String getTemphigh() {
                        return temphigh;
                    }

                    public void setTemphigh(String temphigh) {
                        this.temphigh = temphigh;
                    }

                    public String getImg() {
                        return img;
                    }

                    public void setImg(String img) {
                        this.img = img;
                    }

                    public String getWinddirect() {
                        return winddirect;
                    }

                    public void setWinddirect(String winddirect) {
                        this.winddirect = winddirect;
                    }

                    public String getWindpower() {
                        return windpower;
                    }

                    public void setWindpower(String windpower) {
                        this.windpower = windpower;
                    }
                }
            }

            public static class HourlyBean {
                /**
                 * time : 16:00
                 * weather : 多云
                 * temp : 16
                 * img : 1
                 */

                @SerializedName("time")
                private String time;
                @SerializedName("weather")
                private String weather;
                @SerializedName("temp")
                private String temp;
                @SerializedName("img")
                private String img;

                public String getTime() {
                    return time;
                }

                public void setTime(String time) {
                    this.time = time;
                }

                public String getWeather() {
                    return weather;
                }

                public void setWeather(String weather) {
                    this.weather = weather;
                }

                public String getTemp() {
                    return temp;
                }

                public void setTemp(String temp) {
                    this.temp = temp;
                }

                public String getImg() {
                    return img;
                }

                public void setImg(String img) {
                    this.img = img;
                }
            }
        }
    }
}
