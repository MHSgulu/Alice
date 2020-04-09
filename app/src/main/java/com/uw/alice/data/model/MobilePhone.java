package com.uw.alice.data.model;

import java.io.Serializable;

public class MobilePhone implements Serializable {

    private static final long serialVersionUID = -4396575470114820835L;

    /**
     * code : 10000
     * charge : false
     * msg : 查询成功
     * result : {"status":0,"msg":"ok","result":{"shouji":"13456755448","province":"浙江","city":"杭州","company":"中国移动","cardtype":"GSM","areacode":"0571"}}
     */

    private String code;
    private boolean charge;
    private String msg;
    private ResultBeanX result;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isCharge() {
        return charge;
    }

    public void setCharge(boolean charge) {
        this.charge = charge;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ResultBeanX getResult() {
        return result;
    }

    public void setResult(ResultBeanX result) {
        this.result = result;
    }

    public static class ResultBeanX {
        /**
         * status : 0
         * msg : ok
         * result : {"shouji":"13456755448","province":"浙江","city":"杭州","company":"中国移动","cardtype":"GSM","areacode":"0571"}
         */

        private int status;
        private String msg;
        private ResultBean result;

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
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
             * shouji : 13456755448
             * province : 浙江
             * city : 杭州
             * company : 中国移动
             * cardtype : GSM
             * areacode : 0571
             */

            private String shouji;
            private String province;
            private String city;
            private String company;
            private String cardtype;
            private String areacode;

            public String getShouji() {
                return shouji;
            }

            public void setShouji(String shouji) {
                this.shouji = shouji;
            }

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getCompany() {
                return company;
            }

            public void setCompany(String company) {
                this.company = company;
            }

            public String getCardtype() {
                return cardtype;
            }

            public void setCardtype(String cardtype) {
                this.cardtype = cardtype;
            }

            public String getAreacode() {
                return areacode;
            }

            public void setAreacode(String areacode) {
                this.areacode = areacode;
            }
        }
    }
}
