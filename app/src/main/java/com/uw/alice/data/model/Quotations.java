package com.uw.alice.data.model;

import java.io.Serializable;
import java.util.List;

public class Quotations implements Serializable {

    private static final long serialVersionUID = -2542857778175829250L;


    /**
     * showapi_res_error :
     * showapi_res_id : 874a6b006c9e4aa58ae43b5ed660e34f
     * showapi_res_code : 0
     * showapi_res_body : {"ret_code":0,"ret_message":"Success","data":[{"english":"Sharp tools make good work.","chinese":"工欲善其事，必先利其器。"},{"english":"Believe in the will of the God. There is nothing in this world that happened by accident.","chinese":"相信上帝的意志，相信他的世界中发生的一切都是早有安排。"},{"english":"I just never read between the lines.","chinese":"我从未读懂我们之间的距离。"},{"english":"Finally, you opened its mouth before I put a hand","chinese":"最后是你开了口 我才放了手。"},{"english":"If I had to forget your eyes before it was dark.","chinese":"如果天黑之前来得及 我要忘了你的眼睛。"},{"english":"No matter how the result is, at least try.","chinese":"不管结果是怎样，至少尝试了。"},{"english":"Disappear a memory. And leaving is unforgettable memories.","chinese":"消失的是记忆。而留下的才是刻骨铭心的回忆。"},{"english":"We draw further apart, but the same.","chinese":"我们背道而驰，最后却殊途同归。"},{"english":"The happy blood is unable the backflow centripetal dirty.","chinese":"幸福的血液无法回流向心脏。"},{"english":"Being nice to someone you dislike doesn't mean you're a hypocritical people. It means you're mature enough to tolerate your dislike towards them.","chinese":"能够善待不太喜欢的人，并不代表你虚伪，而意味着你内心成熟到可以容纳这些不喜欢。"}]}
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
         * data : [{"english":"Sharp tools make good work.","chinese":"工欲善其事，必先利其器。"},{"english":"Believe in the will of the God. There is nothing in this world that happened by accident.","chinese":"相信上帝的意志，相信他的世界中发生的一切都是早有安排。"},{"english":"I just never read between the lines.","chinese":"我从未读懂我们之间的距离。"},{"english":"Finally, you opened its mouth before I put a hand","chinese":"最后是你开了口 我才放了手。"},{"english":"If I had to forget your eyes before it was dark.","chinese":"如果天黑之前来得及 我要忘了你的眼睛。"},{"english":"No matter how the result is, at least try.","chinese":"不管结果是怎样，至少尝试了。"},{"english":"Disappear a memory. And leaving is unforgettable memories.","chinese":"消失的是记忆。而留下的才是刻骨铭心的回忆。"},{"english":"We draw further apart, but the same.","chinese":"我们背道而驰，最后却殊途同归。"},{"english":"The happy blood is unable the backflow centripetal dirty.","chinese":"幸福的血液无法回流向心脏。"},{"english":"Being nice to someone you dislike doesn't mean you're a hypocritical people. It means you're mature enough to tolerate your dislike towards them.","chinese":"能够善待不太喜欢的人，并不代表你虚伪，而意味着你内心成熟到可以容纳这些不喜欢。"}]
         */

        private int ret_code;
        private String ret_message;
        private List<DataBean> data;

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

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * english : Sharp tools make good work.
             * chinese : 工欲善其事，必先利其器。
             */

            private String english;
            private String chinese;

            public String getEnglish() {
                return english;
            }

            public void setEnglish(String english) {
                this.english = english;
            }

            public String getChinese() {
                return chinese;
            }

            public void setChinese(String chinese) {
                this.chinese = chinese;
            }
        }
    }
}
