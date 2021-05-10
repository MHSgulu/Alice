package com.uw.alice.entity;

public class IPLocationEntity {

    /**
     * ip : 39.67.146.21
     * location : {"city":"临沂","country_code":"CN","country_name":"中国","latitude":"35.057011","longitude":"118.335617","province":"山东"}
     */

    private String ip;
    private LocationBean location;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public LocationBean getLocation() {
        return location;
    }

    public void setLocation(LocationBean location) {
        this.location = location;
    }

    public static class LocationBean {
        /**
         * city : 临沂
         * country_code : CN
         * country_name : 中国
         * latitude : 35.057011
         * longitude : 118.335617
         * province : 山东
         */

        private String city;
        private String country_code;
        private String country_name;
        private String latitude;
        private String longitude;
        private String province;

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCountry_code() {
            return country_code;
        }

        public void setCountry_code(String country_code) {
            this.country_code = country_code;
        }

        public String getCountry_name() {
            return country_name;
        }

        public void setCountry_name(String country_name) {
            this.country_name = country_name;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }
    }
}
