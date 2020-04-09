package com.uw.alice.data.model;

import java.io.Serializable;
import java.util.List;

public class IdiomKeyword implements Serializable {

    private static final long serialVersionUID = 4120869165546142694L;

    /**
     * showapi_res_error :
     * showapi_res_id : 179fcfd0c9644976ae3dff662919e32e
     * showapi_res_code : 0
     * showapi_res_body : {"ret_message":"Success","ret_code":0,"last_page":7,"total":65,"data":[{"title":"爱别离苦"},{"title":"爱博而情不专"},{"title":"爱不忍释"},{"title":"爱不释手"},{"title":"爱才如渴"},{"title":"爱才如命"},{"title":"爱才若渴"},{"title":"爱财如命"},{"title":"爱富嫌贫"},{"title":"爱国如家"}]}
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
         * ret_message : Success
         * ret_code : 0
         * last_page : 7
         * total : 65
         * data : [{"title":"爱别离苦"},{"title":"爱博而情不专"},{"title":"爱不忍释"},{"title":"爱不释手"},{"title":"爱才如渴"},{"title":"爱才如命"},{"title":"爱才若渴"},{"title":"爱财如命"},{"title":"爱富嫌贫"},{"title":"爱国如家"}]
         */

        private String ret_message;
        private int ret_code;
        private int last_page;
        private int total;
        private List<DataBean> data;

        public String getRet_message() {
            return ret_message;
        }

        public void setRet_message(String ret_message) {
            this.ret_message = ret_message;
        }

        public int getRet_code() {
            return ret_code;
        }

        public void setRet_code(int ret_code) {
            this.ret_code = ret_code;
        }

        public int getLast_page() {
            return last_page;
        }

        public void setLast_page(int last_page) {
            this.last_page = last_page;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * title : 爱别离苦
             */

            private String title;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }
    }
}
