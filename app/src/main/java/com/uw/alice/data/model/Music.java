package com.uw.alice.data.model;

import java.io.Serializable;
import java.util.List;

public class Music implements Serializable {

    private static final long serialVersionUID = 6746150673216078137L;


    /**
     * count : 1
     * start : 0
     * total : 77
     * musics : [{"rating":{"max":10,"average":"8.0","numRaters":13485,"min":0},"author":[{"name":"刺猬"},{"name":"Hedgehog"}],"alt_title":"Blue Day Dreaming","image":"https://img1.doubanio.com/view/subject/s/public/s3628158.jpg","tags":[{"count":2562,"name":"刺猬"},{"count":1052,"name":"中国摇滚"},{"count":1022,"name":"摩登天空"},{"count":843,"name":"内地"},{"count":720,"name":"Rock"},{"count":650,"name":"摇滚"},{"count":387,"name":"2009"},{"count":360,"name":"中国"}],"mobile_link":"https://m.douban.com/music/subject/3566567/","attrs":{"publisher":["摩登天空"],"singer":["刺猬","Hedgehog"],"version":["专辑"],"pubdate":["2009-03-28"],"title":["白日梦蓝"],"media":["CD"],"tracks":["01.白日梦蓝 Blue Daydreaming\n02.春天来了 In Spring\n03.爱之过往 Love Past\n04.假象 Pseudomorph\n05.树 Tree\n06.告诉他们我爱你 Tell Them I Love You\n07.圣诞最后 Christmas Ends\n08.电影 Film\n09.胖女孩儿想 Fat Girl Thinks\n10.24小时摇滚聚会 24 Hours Rock Party\n11.最后一班车 Waiting For The Last Bus\n12.金色年华，无限伤感 Golden Age, Infinite Sadness"],"discs":["1"]},"title":"白日梦蓝","alt":"https://music.douban.com/subject/3566567/","id":"3566567"}]
     */

    private int count;
    private int start;
    private int total;
    private List<MusicsBean> musics;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<MusicsBean> getMusics() {
        return musics;
    }

    public void setMusics(List<MusicsBean> musics) {
        this.musics = musics;
    }

    public static class MusicsBean {
        /**
         * rating : {"max":10,"average":"8.0","numRaters":13485,"min":0}
         * author : [{"name":"刺猬"},{"name":"Hedgehog"}]
         * alt_title : Blue Day Dreaming
         * image : https://img1.doubanio.com/view/subject/s/public/s3628158.jpg
         * tags : [{"count":2562,"name":"刺猬"},{"count":1052,"name":"中国摇滚"},{"count":1022,"name":"摩登天空"},{"count":843,"name":"内地"},{"count":720,"name":"Rock"},{"count":650,"name":"摇滚"},{"count":387,"name":"2009"},{"count":360,"name":"中国"}]
         * mobile_link : https://m.douban.com/music/subject/3566567/
         * attrs : {"publisher":["摩登天空"],"singer":["刺猬","Hedgehog"],"version":["专辑"],"pubdate":["2009-03-28"],"title":["白日梦蓝"],"media":["CD"],"tracks":["01.白日梦蓝 Blue Daydreaming\n02.春天来了 In Spring\n03.爱之过往 Love Past\n04.假象 Pseudomorph\n05.树 Tree\n06.告诉他们我爱你 Tell Them I Love You\n07.圣诞最后 Christmas Ends\n08.电影 Film\n09.胖女孩儿想 Fat Girl Thinks\n10.24小时摇滚聚会 24 Hours Rock Party\n11.最后一班车 Waiting For The Last Bus\n12.金色年华，无限伤感 Golden Age, Infinite Sadness"],"discs":["1"]}
         * title : 白日梦蓝
         * alt : https://music.douban.com/subject/3566567/
         * id : 3566567
         */

        private RatingBean rating;
        private String alt_title;
        private String image;
        private String mobile_link;
        private AttrsBean attrs;
        private String title;
        private String alt;
        private String id;
        private List<AuthorBean> author;
        private List<TagsBean> tags;

        public RatingBean getRating() {
            return rating;
        }

        public void setRating(RatingBean rating) {
            this.rating = rating;
        }

        public String getAlt_title() {
            return alt_title;
        }

        public void setAlt_title(String alt_title) {
            this.alt_title = alt_title;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getMobile_link() {
            return mobile_link;
        }

        public void setMobile_link(String mobile_link) {
            this.mobile_link = mobile_link;
        }

        public AttrsBean getAttrs() {
            return attrs;
        }

        public void setAttrs(AttrsBean attrs) {
            this.attrs = attrs;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getAlt() {
            return alt;
        }

        public void setAlt(String alt) {
            this.alt = alt;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public List<AuthorBean> getAuthor() {
            return author;
        }

        public void setAuthor(List<AuthorBean> author) {
            this.author = author;
        }

        public List<TagsBean> getTags() {
            return tags;
        }

        public void setTags(List<TagsBean> tags) {
            this.tags = tags;
        }

        public static class RatingBean {
            /**
             * max : 10
             * average : 8.0
             * numRaters : 13485
             * min : 0
             */

            private int max;
            private String average;
            private int numRaters;
            private int min;

            public int getMax() {
                return max;
            }

            public void setMax(int max) {
                this.max = max;
            }

            public String getAverage() {
                return average;
            }

            public void setAverage(String average) {
                this.average = average;
            }

            public int getNumRaters() {
                return numRaters;
            }

            public void setNumRaters(int numRaters) {
                this.numRaters = numRaters;
            }

            public int getMin() {
                return min;
            }

            public void setMin(int min) {
                this.min = min;
            }
        }

        public static class AttrsBean {
            private List<String> publisher;
            private List<String> singer;
            private List<String> version;
            private List<String> pubdate;
            private List<String> title;
            private List<String> media;
            private List<String> tracks;
            private List<String> discs;

            public List<String> getPublisher() {
                return publisher;
            }

            public void setPublisher(List<String> publisher) {
                this.publisher = publisher;
            }

            public List<String> getSinger() {
                return singer;
            }

            public void setSinger(List<String> singer) {
                this.singer = singer;
            }

            public List<String> getVersion() {
                return version;
            }

            public void setVersion(List<String> version) {
                this.version = version;
            }

            public List<String> getPubdate() {
                return pubdate;
            }

            public void setPubdate(List<String> pubdate) {
                this.pubdate = pubdate;
            }

            public List<String> getTitle() {
                return title;
            }

            public void setTitle(List<String> title) {
                this.title = title;
            }

            public List<String> getMedia() {
                return media;
            }

            public void setMedia(List<String> media) {
                this.media = media;
            }

            public List<String> getTracks() {
                return tracks;
            }

            public void setTracks(List<String> tracks) {
                this.tracks = tracks;
            }

            public List<String> getDiscs() {
                return discs;
            }

            public void setDiscs(List<String> discs) {
                this.discs = discs;
            }
        }

        public static class AuthorBean {
            /**
             * name : 刺猬
             */

            private String name;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }

        public static class TagsBean {
            /**
             * count : 2562
             * name : 刺猬
             */

            private int count;
            private String name;

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
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
