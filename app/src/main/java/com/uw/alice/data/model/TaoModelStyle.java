package com.uw.alice.data.model;

import java.io.Serializable;
import java.util.List;

public class TaoModelStyle implements Serializable {

    private static final long serialVersionUID = 5178516407371987101L;


    /**
     * showapi_res_error :
     * showapi_res_id : 5b95af9d362d475593c7828f5b91a87c
     * showapi_res_code : 0
     * showapi_res_body : {"ret_code":0,"allTypeList":["欧美","韩版","日系","英伦","OL风","学院","淑女","性感","复古","街头","休闲","民族","甜美","运动","可爱","大码","中老年","其他"],"num":18}
     */

    private String showapi_res_error;
    private String showapi_res_id;
    private int showapi_res_code;
    private ShowapiResBodyBean showapi_res_body;

    public String getShowapi_res_error() {
        return showapi_res_error;
    }

    public void setShowapi_res_error(String showapi_res_error) {
        this.showapi_res_error = showapi_res_error;
    }

    public String getShowapi_res_id() {
        return showapi_res_id;
    }

    public void setShowapi_res_id(String showapi_res_id) {
        this.showapi_res_id = showapi_res_id;
    }

    public int getShowapi_res_code() {
        return showapi_res_code;
    }

    public void setShowapi_res_code(int showapi_res_code) {
        this.showapi_res_code = showapi_res_code;
    }

    public ShowapiResBodyBean getShowapi_res_body() {
        return showapi_res_body;
    }

    public void setShowapi_res_body(ShowapiResBodyBean showapi_res_body) {
        this.showapi_res_body = showapi_res_body;
    }

    public static class ShowapiResBodyBean {
        /**
         * ret_code : 0
         * allTypeList : ["欧美","韩版","日系","英伦","OL风","学院","淑女","性感","复古","街头","休闲","民族","甜美","运动","可爱","大码","中老年","其他"]
         * num : 18
         */

        private int ret_code;
        private int num;
        private List<String> allTypeList;

        public int getRet_code() {
            return ret_code;
        }

        public void setRet_code(int ret_code) {
            this.ret_code = ret_code;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public List<String> getAllTypeList() {
            return allTypeList;
        }

        public void setAllTypeList(List<String> allTypeList) {
            this.allTypeList = allTypeList;
        }
    }
}
