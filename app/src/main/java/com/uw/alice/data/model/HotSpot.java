package com.uw.alice.data.model;

import java.io.Serializable;
import java.util.List;

public class HotSpot implements Serializable {

    private static final long serialVersionUID = -941359547921678837L;


    /**
     * code : 10000
     * charge : false
     * msg : 查询成功
     * result : {"showapi_res_error":"","showapi_res_id":"ba886e38ac8647b590078d34cffc7feb","showapi_res_code":0,"showapi_res_body":{"ret_code":0,"list":[{"num":"1","level":"3124852","trend":"rise","name":"伊朗外长被美国拒签"},{"num":"2","level":"1833766","trend":"rise","name":"2020央视春晚"},{"num":"3","level":"1687161","trend":"rise","name":"暴雪蓝色预警继续"},{"num":"4","level":"1567600","trend":"rise","name":"国产特斯拉交付"},{"num":"5","level":"1304010","trend":"rise","name":"东京奥运会海报"},{"num":"6","level":"1114477","trend":"rise","name":"京阿尼开始拆除"},{"num":"7","level":"1018306","trend":"rise","name":"林更新活跃像粉头"},{"num":"8","level":"990696","trend":"rise","name":"雷神为澳山火捐款"},{"num":"9","level":"868591","trend":"fall","name":"苏莱曼尼葬礼推迟"},{"num":"10","level":"822295","trend":"rise","name":"国考成绩"},{"num":"11","level":"713596","trend":"fall","name":"爱情公寓不再续集"},{"num":"12","level":"711808","trend":"rise","name":"权志龙恋情"},{"num":"13","level":"687131","trend":"rise","name":"X1解散"},{"num":"14","level":"622029","trend":"rise","name":"湖人单场20记盖帽"},{"num":"15","level":"508156","trend":"rise","name":"马思唯公布恋情"},{"num":"16","level":"473912","trend":"rise","name":"堪培拉浓烟锁城"},{"num":"17","level":"452214","trend":"rise","name":"农民工工资条例"},{"num":"18","level":"428559","trend":"rise","name":"小丑获剧情类影帝"},{"num":"19","level":"419338","trend":"rise","name":"明道哥哥自杀计划"},{"num":"20","level":"418895","trend":"rise","name":"明道哥哥尸检结果"},{"num":"21","level":"412387","trend":"fall","name":"海底捞吃出烟头"},{"num":"22","level":"393622","trend":"rise","name":"澳山火烟雾至南美"},{"num":"23","level":"385310","trend":"fall","name":"宜家抽屉压死男童"},{"num":"24","level":"339717","trend":"fall","name":"周冬雨戴口罩领奖"},{"num":"25","level":"306231","trend":"rise","name":"马丽孕肚写真"},{"num":"26","level":"298019","trend":"fall","name":"郑爽工作室声明"},{"num":"27","level":"286726","trend":"fall","name":"孟晚舟案或将终结"},{"num":"28","level":"281327","trend":"fall","name":"昆明至攀枝花动车"},{"num":"29","level":"266267","trend":"fall","name":"马斯克感谢中国"},{"num":"30","level":"264291","trend":"rise","name":"池志强逝世"},{"num":"31","level":"255139","trend":"fall","name":"甜馨领唱萤火虫"},{"num":"32","level":"245139","trend":"rise","name":"陆文昔怀孕"},{"num":"33","level":"226895","trend":"fall","name":"百度地图春运预测"},{"num":"34","level":"222299","trend":"fall","name":"第77届金球奖红毯"},{"num":"35","level":"221323","trend":"rise","name":"伊朗退出伊核协议"},{"num":"36","level":"218169","trend":"rise","name":"郭德纲5岁小儿子近照"},{"num":"37","level":"216864","trend":"fall","name":"金球奖亚裔影后"},{"num":"38","level":"209106","trend":"fall","name":"德百年建筑起火"},{"num":"39","level":"191903","trend":"fall","name":"美军航母逼近伊朗"},{"num":"40","level":"189670","trend":"fall","name":"澳大利亚射杀骆驼"},{"num":"41","level":"168362","trend":"fall","name":"民警鞠梓离世"},{"num":"42","level":"161997","trend":"fall","name":"马斯克跳舞"},{"num":"43","level":"148892","trend":"fall","name":"武磊登上电影频道"},{"num":"44","level":"147874","trend":"fall","name":"爱情公寓5发布会"},{"num":"45","level":"140390","trend":"fall","name":"彭博举报案宣判"},{"num":"46","level":"136214","trend":"fall","name":"中国万吨级巡逻船"},{"num":"47","level":"135603","trend":"fall","name":"苏州十全街塌陷"},{"num":"48","level":"130585","trend":"fall","name":"薪酬保密合理吗"},{"num":"49","level":"129457","trend":"fall","name":"白石麻衣将毕业"},{"num":"50","level":"122933","trend":"fall","name":"CUBA全明星"}]}}
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
         * showapi_res_error :
         * showapi_res_id : ba886e38ac8647b590078d34cffc7feb
         * showapi_res_code : 0
         * showapi_res_body : {"ret_code":0,"list":[{"num":"1","level":"3124852","trend":"rise","name":"伊朗外长被美国拒签"},{"num":"2","level":"1833766","trend":"rise","name":"2020央视春晚"},{"num":"3","level":"1687161","trend":"rise","name":"暴雪蓝色预警继续"},{"num":"4","level":"1567600","trend":"rise","name":"国产特斯拉交付"},{"num":"5","level":"1304010","trend":"rise","name":"东京奥运会海报"},{"num":"6","level":"1114477","trend":"rise","name":"京阿尼开始拆除"},{"num":"7","level":"1018306","trend":"rise","name":"林更新活跃像粉头"},{"num":"8","level":"990696","trend":"rise","name":"雷神为澳山火捐款"},{"num":"9","level":"868591","trend":"fall","name":"苏莱曼尼葬礼推迟"},{"num":"10","level":"822295","trend":"rise","name":"国考成绩"},{"num":"11","level":"713596","trend":"fall","name":"爱情公寓不再续集"},{"num":"12","level":"711808","trend":"rise","name":"权志龙恋情"},{"num":"13","level":"687131","trend":"rise","name":"X1解散"},{"num":"14","level":"622029","trend":"rise","name":"湖人单场20记盖帽"},{"num":"15","level":"508156","trend":"rise","name":"马思唯公布恋情"},{"num":"16","level":"473912","trend":"rise","name":"堪培拉浓烟锁城"},{"num":"17","level":"452214","trend":"rise","name":"农民工工资条例"},{"num":"18","level":"428559","trend":"rise","name":"小丑获剧情类影帝"},{"num":"19","level":"419338","trend":"rise","name":"明道哥哥自杀计划"},{"num":"20","level":"418895","trend":"rise","name":"明道哥哥尸检结果"},{"num":"21","level":"412387","trend":"fall","name":"海底捞吃出烟头"},{"num":"22","level":"393622","trend":"rise","name":"澳山火烟雾至南美"},{"num":"23","level":"385310","trend":"fall","name":"宜家抽屉压死男童"},{"num":"24","level":"339717","trend":"fall","name":"周冬雨戴口罩领奖"},{"num":"25","level":"306231","trend":"rise","name":"马丽孕肚写真"},{"num":"26","level":"298019","trend":"fall","name":"郑爽工作室声明"},{"num":"27","level":"286726","trend":"fall","name":"孟晚舟案或将终结"},{"num":"28","level":"281327","trend":"fall","name":"昆明至攀枝花动车"},{"num":"29","level":"266267","trend":"fall","name":"马斯克感谢中国"},{"num":"30","level":"264291","trend":"rise","name":"池志强逝世"},{"num":"31","level":"255139","trend":"fall","name":"甜馨领唱萤火虫"},{"num":"32","level":"245139","trend":"rise","name":"陆文昔怀孕"},{"num":"33","level":"226895","trend":"fall","name":"百度地图春运预测"},{"num":"34","level":"222299","trend":"fall","name":"第77届金球奖红毯"},{"num":"35","level":"221323","trend":"rise","name":"伊朗退出伊核协议"},{"num":"36","level":"218169","trend":"rise","name":"郭德纲5岁小儿子近照"},{"num":"37","level":"216864","trend":"fall","name":"金球奖亚裔影后"},{"num":"38","level":"209106","trend":"fall","name":"德百年建筑起火"},{"num":"39","level":"191903","trend":"fall","name":"美军航母逼近伊朗"},{"num":"40","level":"189670","trend":"fall","name":"澳大利亚射杀骆驼"},{"num":"41","level":"168362","trend":"fall","name":"民警鞠梓离世"},{"num":"42","level":"161997","trend":"fall","name":"马斯克跳舞"},{"num":"43","level":"148892","trend":"fall","name":"武磊登上电影频道"},{"num":"44","level":"147874","trend":"fall","name":"爱情公寓5发布会"},{"num":"45","level":"140390","trend":"fall","name":"彭博举报案宣判"},{"num":"46","level":"136214","trend":"fall","name":"中国万吨级巡逻船"},{"num":"47","level":"135603","trend":"fall","name":"苏州十全街塌陷"},{"num":"48","level":"130585","trend":"fall","name":"薪酬保密合理吗"},{"num":"49","level":"129457","trend":"fall","name":"白石麻衣将毕业"},{"num":"50","level":"122933","trend":"fall","name":"CUBA全明星"}]}
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
             * list : [{"num":"1","level":"3124852","trend":"rise","name":"伊朗外长被美国拒签"},{"num":"2","level":"1833766","trend":"rise","name":"2020央视春晚"},{"num":"3","level":"1687161","trend":"rise","name":"暴雪蓝色预警继续"},{"num":"4","level":"1567600","trend":"rise","name":"国产特斯拉交付"},{"num":"5","level":"1304010","trend":"rise","name":"东京奥运会海报"},{"num":"6","level":"1114477","trend":"rise","name":"京阿尼开始拆除"},{"num":"7","level":"1018306","trend":"rise","name":"林更新活跃像粉头"},{"num":"8","level":"990696","trend":"rise","name":"雷神为澳山火捐款"},{"num":"9","level":"868591","trend":"fall","name":"苏莱曼尼葬礼推迟"},{"num":"10","level":"822295","trend":"rise","name":"国考成绩"},{"num":"11","level":"713596","trend":"fall","name":"爱情公寓不再续集"},{"num":"12","level":"711808","trend":"rise","name":"权志龙恋情"},{"num":"13","level":"687131","trend":"rise","name":"X1解散"},{"num":"14","level":"622029","trend":"rise","name":"湖人单场20记盖帽"},{"num":"15","level":"508156","trend":"rise","name":"马思唯公布恋情"},{"num":"16","level":"473912","trend":"rise","name":"堪培拉浓烟锁城"},{"num":"17","level":"452214","trend":"rise","name":"农民工工资条例"},{"num":"18","level":"428559","trend":"rise","name":"小丑获剧情类影帝"},{"num":"19","level":"419338","trend":"rise","name":"明道哥哥自杀计划"},{"num":"20","level":"418895","trend":"rise","name":"明道哥哥尸检结果"},{"num":"21","level":"412387","trend":"fall","name":"海底捞吃出烟头"},{"num":"22","level":"393622","trend":"rise","name":"澳山火烟雾至南美"},{"num":"23","level":"385310","trend":"fall","name":"宜家抽屉压死男童"},{"num":"24","level":"339717","trend":"fall","name":"周冬雨戴口罩领奖"},{"num":"25","level":"306231","trend":"rise","name":"马丽孕肚写真"},{"num":"26","level":"298019","trend":"fall","name":"郑爽工作室声明"},{"num":"27","level":"286726","trend":"fall","name":"孟晚舟案或将终结"},{"num":"28","level":"281327","trend":"fall","name":"昆明至攀枝花动车"},{"num":"29","level":"266267","trend":"fall","name":"马斯克感谢中国"},{"num":"30","level":"264291","trend":"rise","name":"池志强逝世"},{"num":"31","level":"255139","trend":"fall","name":"甜馨领唱萤火虫"},{"num":"32","level":"245139","trend":"rise","name":"陆文昔怀孕"},{"num":"33","level":"226895","trend":"fall","name":"百度地图春运预测"},{"num":"34","level":"222299","trend":"fall","name":"第77届金球奖红毯"},{"num":"35","level":"221323","trend":"rise","name":"伊朗退出伊核协议"},{"num":"36","level":"218169","trend":"rise","name":"郭德纲5岁小儿子近照"},{"num":"37","level":"216864","trend":"fall","name":"金球奖亚裔影后"},{"num":"38","level":"209106","trend":"fall","name":"德百年建筑起火"},{"num":"39","level":"191903","trend":"fall","name":"美军航母逼近伊朗"},{"num":"40","level":"189670","trend":"fall","name":"澳大利亚射杀骆驼"},{"num":"41","level":"168362","trend":"fall","name":"民警鞠梓离世"},{"num":"42","level":"161997","trend":"fall","name":"马斯克跳舞"},{"num":"43","level":"148892","trend":"fall","name":"武磊登上电影频道"},{"num":"44","level":"147874","trend":"fall","name":"爱情公寓5发布会"},{"num":"45","level":"140390","trend":"fall","name":"彭博举报案宣判"},{"num":"46","level":"136214","trend":"fall","name":"中国万吨级巡逻船"},{"num":"47","level":"135603","trend":"fall","name":"苏州十全街塌陷"},{"num":"48","level":"130585","trend":"fall","name":"薪酬保密合理吗"},{"num":"49","level":"129457","trend":"fall","name":"白石麻衣将毕业"},{"num":"50","level":"122933","trend":"fall","name":"CUBA全明星"}]
             */

            private int ret_code;
            private List<ListBean> list;

            public int getRet_code() {
                return ret_code;
            }

            public void setRet_code(int ret_code) {
                this.ret_code = ret_code;
            }

            public List<ListBean> getList() {
                return list;
            }

            public void setList(List<ListBean> list) {
                this.list = list;
            }

            public static class ListBean {
                /**
                 * num : 1
                 * level : 3124852
                 * trend : rise
                 * name : 伊朗外长被美国拒签
                 */

                private String num;
                private String level;
                private String trend;
                private String name;

                public String getNum() {
                    return num;
                }

                public void setNum(String num) {
                    this.num = num;
                }

                public String getLevel() {
                    return level;
                }

                public void setLevel(String level) {
                    this.level = level;
                }

                public String getTrend() {
                    return trend;
                }

                public void setTrend(String trend) {
                    this.trend = trend;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }
            }
        }
    }
}
