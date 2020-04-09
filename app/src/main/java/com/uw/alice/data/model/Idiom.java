package com.uw.alice.data.model;

import java.io.Serializable;

public class Idiom implements Serializable{

    private static final long serialVersionUID = -3504026839278609717L;

    /**
     * showapi_res_error :
     * showapi_res_id : e93f43d7294f4899a1d3ae68572645de
     * showapi_res_code : 0
     * showapi_res_body : {"ret_code":0,"ret_message":"Success","data":{"title":"掩耳盗铃","spell":"yǎn  ěr  dào  líng","content":"掩：遮蔽，遮盖；盗：偷。偷铃铛怕别人听见而捂住自己的耳朵。比喻自己欺骗自己，明明掩盖不住的事情偏要想法子掩盖。","derivation":"《吕氏春秋·自知》：\u201c百姓有得钟者，欲负而走，则钟大不可负。以椎毁之，钟况然有声。恐人闻之而夺己也，遽掩其耳。\u201d","samples":"硬把汉奸合法化了，只是掩耳盗铃的笨拙的把戏。 ★闻一多《谨防汉奸合法化》"}}
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
         * data : {"title":"掩耳盗铃","spell":"yǎn  ěr  dào  líng","content":"掩：遮蔽，遮盖；盗：偷。偷铃铛怕别人听见而捂住自己的耳朵。比喻自己欺骗自己，明明掩盖不住的事情偏要想法子掩盖。","derivation":"《吕氏春秋·自知》：\u201c百姓有得钟者，欲负而走，则钟大不可负。以椎毁之，钟况然有声。恐人闻之而夺己也，遽掩其耳。\u201d","samples":"硬把汉奸合法化了，只是掩耳盗铃的笨拙的把戏。 ★闻一多《谨防汉奸合法化》"}
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
             * title : 掩耳盗铃
             * spell : yǎn  ěr  dào  líng
             * content : 掩：遮蔽，遮盖；盗：偷。偷铃铛怕别人听见而捂住自己的耳朵。比喻自己欺骗自己，明明掩盖不住的事情偏要想法子掩盖。
             * derivation : 《吕氏春秋·自知》：“百姓有得钟者，欲负而走，则钟大不可负。以椎毁之，钟况然有声。恐人闻之而夺己也，遽掩其耳。”
             * samples : 硬把汉奸合法化了，只是掩耳盗铃的笨拙的把戏。 ★闻一多《谨防汉奸合法化》
             */

            private String title;
            private String spell;
            private String content;
            private String derivation;
            private String samples;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getSpell() {
                return spell;
            }

            public void setSpell(String spell) {
                this.spell = spell;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getDerivation() {
                return derivation;
            }

            public void setDerivation(String derivation) {
                this.derivation = derivation;
            }

            public String getSamples() {
                return samples;
            }

            public void setSamples(String samples) {
                this.samples = samples;
            }
        }
    }
}
