package com.uw.alice.data.model;

import java.io.Serializable;

public class Wallpaper implements Serializable{

    private static final long serialVersionUID = 7227751657916259228L;

    /**
     * showapi_res_error :
     * showapi_res_id : 0d25e886dce042f5a78e4902f6182699
     * showapi_res_code : 0
     * showapi_res_body : {"ret_code":0,"ret_message":"Success","data":{"title":null,"subtitle":null,"description":null,"copyright":"加德满都布达纳特塔鸟瞰图，尼泊尔 (© Maksim Semin/Shutterstock)","date":"20200115","img_1366":"http://showapi.mmno.com/api/showapi.bing/img_1366","img_1920":"http://showapi.mmno.com/api/showapi.bing/img_1920"}}
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
         * ret_message : Success
         * data : {"title":null,"subtitle":null,"description":null,"copyright":"加德满都布达纳特塔鸟瞰图，尼泊尔 (© Maksim Semin/Shutterstock)","date":"20200115","img_1366":"http://showapi.mmno.com/api/showapi.bing/img_1366","img_1920":"http://showapi.mmno.com/api/showapi.bing/img_1920"}
         */

        private int ret_code;
        private String ret_message;
        private DataBean data;

        public int getRet_code() {
            return ret_code;
        }

        public void setRet_code(int ret_code) {
            this.ret_code = ret_code;
        }

        public String getRet_message() {
            return ret_message;
        }

        public void setRet_message(String ret_message) {
            this.ret_message = ret_message;
        }

        public DataBean getData() {
            return data;
        }

        public void setData(DataBean data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * title : null
             * subtitle : null
             * description : null
             * copyright : 加德满都布达纳特塔鸟瞰图，尼泊尔 (© Maksim Semin/Shutterstock)
             * date : 20200115
             * img_1366 : http://showapi.mmno.com/api/showapi.bing/img_1366
             * img_1920 : http://showapi.mmno.com/api/showapi.bing/img_1920
             */

            private Object title;
            private Object subtitle;
            private Object description;
            private String copyright;
            private String date;
            private String img_1366;
            private String img_1920;

            public Object getTitle() {
                return title;
            }

            public void setTitle(Object title) {
                this.title = title;
            }

            public Object getSubtitle() {
                return subtitle;
            }

            public void setSubtitle(Object subtitle) {
                this.subtitle = subtitle;
            }

            public Object getDescription() {
                return description;
            }

            public void setDescription(Object description) {
                this.description = description;
            }

            public String getCopyright() {
                return copyright;
            }

            public void setCopyright(String copyright) {
                this.copyright = copyright;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getImg_1366() {
                return img_1366;
            }

            public void setImg_1366(String img_1366) {
                this.img_1366 = img_1366;
            }

            public String getImg_1920() {
                return img_1920;
            }

            public void setImg_1920(String img_1920) {
                this.img_1920 = img_1920;
            }
        }
    }
}
