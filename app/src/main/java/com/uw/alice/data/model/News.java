package com.uw.alice.data.model;

import java.io.Serializable;
import java.util.List;

public class News implements Serializable {

    private static final long serialVersionUID = 6623273900915860659L;

    /**
     * code : 10000
     * charge : false
     * msg : 查询成功
     * result : {"status":0,"msg":"ok","result":{"channel":"娱乐","num":1,"list":[{"title":"王宝强十五年后与刘德华再合作 发文称其是榜样","time":"2020-01-03 13:23:11","src":"网易娱乐","category":"ent","pic":"https://cms-bucket.ws.126.net/2020/0103/8d25218ap00q3imw5004ic0009c005uc.png","content":"<div class=\"content\">\n            <div class=\"page js-page on\">\n              <div class=\"photo\">\n                  <a href=\"http://cms-bucket.ws.126.net/2020/0103/35e20224j00q3imv5001hc000hi00hcc.jpg\">\n                      <img alt=\"王宝强微博感谢刘德华\" src='http://cms-bucket.ws.126.net/2020/0103/35e20224j00q3imv5001hc000hi00hcc.jpg'>\n                      <span>王宝强微博感谢刘德华<\/span>\n                  <\/a>\n              <\/div><p><b>网易娱乐1月3日报道 <\/b>1月3日，<a href=\"http://3g.163.com/touch/idol/star71\"><font color=\"#009ce5\">王宝强<\/font><\/a>更新微博谈和偶像刘德华的两次合作：15年前，您教我演戏，15年后，您教我跳舞。这么多年，您一直是我的榜样。王宝强还晒出多张自己与刘德华的合照，包括早前《天下无贼》的剧照，以及新电影《唐人街探案3》的送福曲海报。<\/p><div class=\"photo\">\n                  <a href=\"http://cms-bucket.ws.126.net/2020/0103/d9201f7cj00q3imv5002ec000hi00lkc.jpg\">\n                      <img alt=\"两次合作\" src='http://cms-bucket.ws.126.net/2020/0103/d9201f7cj00q3imv5002ec000hi00lkc.jpg'>\n                      <span>两次合作<\/span>\n                  <\/a>\n              <\/div><p>据悉，电影《唐人街探案3》的拜年送福曲《恭喜发财2020》由刘德华、王宝强一同出演，这也是刘德华和王宝强继《天下无贼》后时隔十五年的再次合体。<\/p>\n              <div class=\"otitle_editor\">\n \n                <p class=\"editor\">(责任编辑：李思_NBJ11322)<\/p>\n              <\/div>\n            <\/div>\n          <\/div>\n        <\/div>","url":"http://3g.163.com/ent/20/0103/13/F1VI3E4200038FO9.html","weburl":"http://ent.163.com/20/0103/13/F1VI3E4200038FO9.html"}]}}
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
         * result : {"channel":"娱乐","num":1,"list":[{"title":"王宝强十五年后与刘德华再合作 发文称其是榜样","time":"2020-01-03 13:23:11","src":"网易娱乐","category":"ent","pic":"https://cms-bucket.ws.126.net/2020/0103/8d25218ap00q3imw5004ic0009c005uc.png","content":"<div class=\"content\">\n            <div class=\"page js-page on\">\n              <div class=\"photo\">\n                  <a href=\"http://cms-bucket.ws.126.net/2020/0103/35e20224j00q3imv5001hc000hi00hcc.jpg\">\n                      <img alt=\"王宝强微博感谢刘德华\" src='http://cms-bucket.ws.126.net/2020/0103/35e20224j00q3imv5001hc000hi00hcc.jpg'>\n                      <span>王宝强微博感谢刘德华<\/span>\n                  <\/a>\n              <\/div><p><b>网易娱乐1月3日报道 <\/b>1月3日，<a href=\"http://3g.163.com/touch/idol/star71\"><font color=\"#009ce5\">王宝强<\/font><\/a>更新微博谈和偶像刘德华的两次合作：15年前，您教我演戏，15年后，您教我跳舞。这么多年，您一直是我的榜样。王宝强还晒出多张自己与刘德华的合照，包括早前《天下无贼》的剧照，以及新电影《唐人街探案3》的送福曲海报。<\/p><div class=\"photo\">\n                  <a href=\"http://cms-bucket.ws.126.net/2020/0103/d9201f7cj00q3imv5002ec000hi00lkc.jpg\">\n                      <img alt=\"两次合作\" src='http://cms-bucket.ws.126.net/2020/0103/d9201f7cj00q3imv5002ec000hi00lkc.jpg'>\n                      <span>两次合作<\/span>\n                  <\/a>\n              <\/div><p>据悉，电影《唐人街探案3》的拜年送福曲《恭喜发财2020》由刘德华、王宝强一同出演，这也是刘德华和王宝强继《天下无贼》后时隔十五年的再次合体。<\/p>\n              <div class=\"otitle_editor\">\n \n                <p class=\"editor\">(责任编辑：李思_NBJ11322)<\/p>\n              <\/div>\n            <\/div>\n          <\/div>\n        <\/div>","url":"http://3g.163.com/ent/20/0103/13/F1VI3E4200038FO9.html","weburl":"http://ent.163.com/20/0103/13/F1VI3E4200038FO9.html"}]}
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
             * channel : 娱乐
             * num : 1
             * list : [{"title":"王宝强十五年后与刘德华再合作 发文称其是榜样","time":"2020-01-03 13:23:11","src":"网易娱乐","category":"ent","pic":"https://cms-bucket.ws.126.net/2020/0103/8d25218ap00q3imw5004ic0009c005uc.png","content":"<div class=\"content\">\n            <div class=\"page js-page on\">\n              <div class=\"photo\">\n                  <a href=\"http://cms-bucket.ws.126.net/2020/0103/35e20224j00q3imv5001hc000hi00hcc.jpg\">\n                      <img alt=\"王宝强微博感谢刘德华\" src='http://cms-bucket.ws.126.net/2020/0103/35e20224j00q3imv5001hc000hi00hcc.jpg'>\n                      <span>王宝强微博感谢刘德华<\/span>\n                  <\/a>\n              <\/div><p><b>网易娱乐1月3日报道 <\/b>1月3日，<a href=\"http://3g.163.com/touch/idol/star71\"><font color=\"#009ce5\">王宝强<\/font><\/a>更新微博谈和偶像刘德华的两次合作：15年前，您教我演戏，15年后，您教我跳舞。这么多年，您一直是我的榜样。王宝强还晒出多张自己与刘德华的合照，包括早前《天下无贼》的剧照，以及新电影《唐人街探案3》的送福曲海报。<\/p><div class=\"photo\">\n                  <a href=\"http://cms-bucket.ws.126.net/2020/0103/d9201f7cj00q3imv5002ec000hi00lkc.jpg\">\n                      <img alt=\"两次合作\" src='http://cms-bucket.ws.126.net/2020/0103/d9201f7cj00q3imv5002ec000hi00lkc.jpg'>\n                      <span>两次合作<\/span>\n                  <\/a>\n              <\/div><p>据悉，电影《唐人街探案3》的拜年送福曲《恭喜发财2020》由刘德华、王宝强一同出演，这也是刘德华和王宝强继《天下无贼》后时隔十五年的再次合体。<\/p>\n              <div class=\"otitle_editor\">\n \n                <p class=\"editor\">(责任编辑：李思_NBJ11322)<\/p>\n              <\/div>\n            <\/div>\n          <\/div>\n        <\/div>","url":"http://3g.163.com/ent/20/0103/13/F1VI3E4200038FO9.html","weburl":"http://ent.163.com/20/0103/13/F1VI3E4200038FO9.html"}]
             */

            private String channel;
            private int num;
            private List<ListBean> list;

            public String getChannel() {
                return channel;
            }

            public void setChannel(String channel) {
                this.channel = channel;
            }

            public int getNum() {
                return num;
            }

            public void setNum(int num) {
                this.num = num;
            }

            public List<ListBean> getList() {
                return list;
            }

            public void setList(List<ListBean> list) {
                this.list = list;
            }

            public static class ListBean {
                /**
                 * title : 王宝强十五年后与刘德华再合作 发文称其是榜样
                 * time : 2020-01-03 13:23:11
                 * src : 网易娱乐
                 * category : ent
                 * pic : https://cms-bucket.ws.126.net/2020/0103/8d25218ap00q3imw5004ic0009c005uc.png
                 * content : <div class="content">
                 <div class="page js-page on">
                 <div class="photo">
                 <a href="http://cms-bucket.ws.126.net/2020/0103/35e20224j00q3imv5001hc000hi00hcc.jpg">
                 <img alt="王宝强微博感谢刘德华" src='http://cms-bucket.ws.126.net/2020/0103/35e20224j00q3imv5001hc000hi00hcc.jpg'>
                 <span>王宝强微博感谢刘德华</span>
                 </a>
                 </div><p><b>网易娱乐1月3日报道 </b>1月3日，<a href="http://3g.163.com/touch/idol/star71"><font color="#009ce5">王宝强</font></a>更新微博谈和偶像刘德华的两次合作：15年前，您教我演戏，15年后，您教我跳舞。这么多年，您一直是我的榜样。王宝强还晒出多张自己与刘德华的合照，包括早前《天下无贼》的剧照，以及新电影《唐人街探案3》的送福曲海报。</p><div class="photo">
                 <a href="http://cms-bucket.ws.126.net/2020/0103/d9201f7cj00q3imv5002ec000hi00lkc.jpg">
                 <img alt="两次合作" src='http://cms-bucket.ws.126.net/2020/0103/d9201f7cj00q3imv5002ec000hi00lkc.jpg'>
                 <span>两次合作</span>
                 </a>
                 </div><p>据悉，电影《唐人街探案3》的拜年送福曲《恭喜发财2020》由刘德华、王宝强一同出演，这也是刘德华和王宝强继《天下无贼》后时隔十五年的再次合体。</p>
                 <div class="otitle_editor">

                 <p class="editor">(责任编辑：李思_NBJ11322)</p>
                 </div>
                 </div>
                 </div>
                 </div>
                 * url : http://3g.163.com/ent/20/0103/13/F1VI3E4200038FO9.html
                 * weburl : http://ent.163.com/20/0103/13/F1VI3E4200038FO9.html
                 */

                private String title;
                private String time;
                private String src;
                private String category;
                private String pic;
                private String content;
                private String url;
                private String weburl;

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getTime() {
                    return time;
                }

                public void setTime(String time) {
                    this.time = time;
                }

                public String getSrc() {
                    return src;
                }

                public void setSrc(String src) {
                    this.src = src;
                }

                public String getCategory() {
                    return category;
                }

                public void setCategory(String category) {
                    this.category = category;
                }

                public String getPic() {
                    return pic;
                }

                public void setPic(String pic) {
                    this.pic = pic;
                }

                public String getContent() {
                    return content;
                }

                public void setContent(String content) {
                    this.content = content;
                }

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

                public String getWeburl() {
                    return weburl;
                }

                public void setWeburl(String weburl) {
                    this.weburl = weburl;
                }
            }
        }
    }
}
