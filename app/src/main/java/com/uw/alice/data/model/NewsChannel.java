package com.uw.alice.data.model;

import java.io.Serializable;
import java.util.List;

public class NewsChannel implements Serializable {

    private static final long serialVersionUID = -5054753578992420116L;


    /**
     * code : 10000
     * charge : false
     * msg : 查询成功
     * result : {"status":0,"msg":"ok","result":["头条","新闻","国内","国际","政治","财经","体育","娱乐","军事","教育","科技","NBA","股票","星座","女性","健康","育儿"]}
     */

    private String code;
    private boolean charge;
    private String msg;
    private ResultBean result;

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

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * status : 0
         * msg : ok
         * result : ["头条","新闻","国内","国际","政治","财经","体育","娱乐","军事","教育","科技","NBA","股票","星座","女性","健康","育儿"]
         */

        private int status;
        private String msg;
        private List<String> result;

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

        public List<String> getResult() {
            return result;
        }

        public void setResult(List<String> result) {
            this.result = result;
        }
    }
}
