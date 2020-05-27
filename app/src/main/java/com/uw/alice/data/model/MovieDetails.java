package com.uw.alice.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieDetails {


    /**
     * rating : {"max":10,"average":7.5,"details":{"1":49,"3":1405,"2":233,"5":938,"4":1705},"stars":"40","min":0}
     * reviews_count : 215
     * videos : [{"source":{"literal":"bilibili","pic":"http://img9.doubanio.com/f/movie/f536fe0ea1cbb0914658ae803125d078351f9047/pics/movie/video-bilibili.png","name":"哔哩哔哩"},"sample_link":"https://www.bilibili.com/bangumi/play/ss31779?bsource=douban","video_id":"ss31779","need_pay":false}]
     * wish_count : 21843
     * original_title : ヴァイオレット・エヴァーガーデン 外伝 - 永遠と自動手記人形 -
     * blooper_urls : ["http://vt1.doubanio.com/202005261820/144198567deb853a3e42073e9e536732/view/movie/M/302570530.mp4"]
     * collect_count : 36522
     * images : {"small":"http://img9.doubanio.com/view/photo/s_ratio_poster/public/p2578722076.webp","large":"http://img9.doubanio.com/view/photo/s_ratio_poster/public/p2578722076.webp","medium":"http://img9.doubanio.com/view/photo/s_ratio_poster/public/p2578722076.webp"}
     * douban_site :
     * year : 2019
     * popular_comments : [{"rating":{"max":5,"value":3,"min":0},"useful_count":286,"author":{"uid":"148010430","avatar":"http://img1.doubanio.com/icon/u148010430-8.jpg","signature":"","alt":"https://www.douban.com/people/148010430/","id":"148010430","name":"白胡椒哇"},"subject_id":"33424345","content":"对京紫或者京都都没有太大的感想，反正是感觉各方面平庸没有任何可取之处的动画作品，虽然作画精致光影细腻情感真诚节奏舒缓，但我就是全程面无表情内心毫无波动甚至有点嫌热。番剧版本的人类圣经世纪霸权八万八之类的京吹言论基本可以忽略，影片本身也就是，平庸二字足以完整概括。虽然有京阿尼的大火，但我也没有情怀加成，整体上故事人物各方面在我这儿都平庸至极，不能及格。","created_at":"2019-12-26 23:44:04","id":"2103409719"},{"rating":{"max":5,"value":4,"min":0},"useful_count":348,"author":{"uid":"Colin_Wang","avatar":"http://img9.doubanio.com/icon/u2160141-4.jpg","signature":"青山不老雪白头 绿水无忧风皱面","alt":"https://www.douban.com/people/Colin_Wang/","id":"2160141","name":"科林"},"subject_id":"33424345","content":"百合情真，姐妹情深，好细腻好唯美的一部女性主题动画作品，就连全片唯一的男性角色都穿得那么骚~~~","created_at":"2020-01-05 00:20:12","id":"2120428231"},{"rating":{"max":5,"value":3,"min":0},"useful_count":503,"author":{"uid":"fulandefanqie","avatar":"http://img3.doubanio.com/icon/u46262268-22.jpg","signature":"永远年轻.永远热泪盈眶.","alt":"https://www.douban.com/people/fulandefanqie/","id":"46262268","name":"牛排口味的饼干"},"subject_id":"33424345","content":"1 非粉甚至不是漫迷，这片日本口碑爆炸但实际还是源自对工作室的情怀部分。说实话我不是很能get到里面的任何情感，无论是圣母女主还是姐妹情，也不是青春期问题，也没有战争背景的沉重感。真的很迷，一脸懵逼。2 京阿尼的价值，到底在哪。","created_at":"2019-09-09 23:30:45","id":"1946312232"},{"rating":{"max":5,"value":4,"min":0},"useful_count":93,"author":{"uid":"CammyWhite","avatar":"http://img1.doubanio.com/icon/u1895572-128.jpg","signature":"芬兰\u2014\u2014社恐者的国度\u2026\u2026","alt":"https://www.douban.com/people/CammyWhite/","id":"1895572","name":"★☆☆☆☆"},"subject_id":"33424345","content":"（９／１０）剧场版本身还是挺不错的，虽然故事结构不如迪斯尼作品来得成熟，但在这年头能看到类似世界名著剧场一样气息的作品已经很难得。突然发现薇尔莉特也许是动画界近年来脾气最好的主角之一了吧\u2026\u2026PS：中途有薇尔莉特的男装打扮。","created_at":"2020-01-04 12:00:17","id":"1961926989"}]
     * alt : https://movie.douban.com/subject/33424345/
     * id : 33424345
     * mobile_url : https://movie.douban.com/subject/33424345/mobile
     * photos_count : 97
     * pubdate : 2020-01-10
     * title : 紫罗兰永恒花园外传：永远与自动手记人偶
     * do_count : null
     * has_video : true
     * share_url : http://m.douban.com/movie/subject/33424345
     * seasons_count : null
     * languages : ["日语"]
     * schedule_url : https://movie.douban.com/subject/33424345/cinema/
     * writers : [{"avatars":{"small":"http://img1.doubanio.com/f/movie/ca527386eb8c4e325611e22dfcb04cc116d6b423/pics/movie/celebrity-default-small.png","large":"http://img3.doubanio.com/f/movie/63acc16ca6309ef191f0378faf793d1096a3e606/pics/movie/celebrity-default-large.png","medium":"http://img1.doubanio.com/f/movie/8dd0c794499fe925ae2ae89ee30cd225750457b4/pics/movie/celebrity-default-medium.png"},"name_en":"Kana Akatsuki","name":"晓佳奈","alt":"https://movie.douban.com/celebrity/1386928/","id":"1386928"},{"avatars":{"small":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p10532.webp","large":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p10532.webp","medium":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p10532.webp"},"name_en":"Reiko Yoshida","name":"吉田玲子","alt":"https://movie.douban.com/celebrity/1275219/","id":"1275219"},{"avatars":{"small":"http://img1.doubanio.com/f/movie/ca527386eb8c4e325611e22dfcb04cc116d6b423/pics/movie/celebrity-default-small.png","large":"http://img3.doubanio.com/f/movie/63acc16ca6309ef191f0378faf793d1096a3e606/pics/movie/celebrity-default-large.png","medium":"http://img1.doubanio.com/f/movie/8dd0c794499fe925ae2ae89ee30cd225750457b4/pics/movie/celebrity-default-medium.png"},"name_en":"Tatsuhiko Urahata","name":"浦畑达彦","alt":"https://movie.douban.com/celebrity/1314505/","id":"1314505"},{"avatars":{"small":"http://img1.doubanio.com/f/movie/ca527386eb8c4e325611e22dfcb04cc116d6b423/pics/movie/celebrity-default-small.png","large":"http://img3.doubanio.com/f/movie/63acc16ca6309ef191f0378faf793d1096a3e606/pics/movie/celebrity-default-large.png","medium":"http://img1.doubanio.com/f/movie/8dd0c794499fe925ae2ae89ee30cd225750457b4/pics/movie/celebrity-default-medium.png"},"name_en":"Takaaki Suzuki","name":"铃木贵昭","alt":"https://movie.douban.com/celebrity/1428916/","id":"1428916"}]
     * pubdates : ["2019-09-06(日本)","2020-01-10(中国大陆)"]
     * website : violet-evergarden.jp/sidestory/
     * tags : ["京阿尼","治愈","动画","日本","文艺","成长","剧场版","2019","音乐","爱情"]
     * has_schedule : true
     * durations : ["91分钟"]
     * genres : ["剧情","动画"]
     * collection : null
     * trailers : [{"medium":"http://img1.doubanio.com/img/trailer/medium/2578742488.jpg?","title":"中国预告片：定档版 (中文字幕)","subject_id":"33424345","alt":"https://movie.douban.com/trailer/257279/","small":"http://img1.doubanio.com/img/trailer/small/2578742488.jpg?","resource_url":"http://vt1.doubanio.com/202005261820/600357f4d578ada130f77a2ef4c313dd/view/movie/M/302570279.mp4","id":"257279"},{"medium":"http://img1.doubanio.com/img/trailer/medium/2578742039.jpg?","title":"中国先行版 (中文字幕)","subject_id":"33424345","alt":"https://movie.douban.com/trailer/257277/","small":"http://img1.doubanio.com/img/trailer/small/2578742039.jpg?","resource_url":"http://vt1.doubanio.com/202005261820/69d17dda9a774b5fee1d10c87084bb8e/view/movie/M/302570277.mp4","id":"257277"},{"medium":"http://img3.doubanio.com/img/trailer/medium/2565637781.jpg?","title":"先行版","subject_id":"33424345","alt":"https://movie.douban.com/trailer/251321/","small":"http://img3.doubanio.com/img/trailer/small/2565637781.jpg?","resource_url":"http://vt1.doubanio.com/202005261820/b3f7c9e04c60a121a4bc538a3dc1b400/view/movie/M/302510321.mp4","id":"251321"}]
     * episodes_count : null
     * trailer_urls : ["http://vt1.doubanio.com/202005261820/600357f4d578ada130f77a2ef4c313dd/view/movie/M/302570279.mp4","http://vt1.doubanio.com/202005261820/69d17dda9a774b5fee1d10c87084bb8e/view/movie/M/302570277.mp4","http://vt1.doubanio.com/202005261820/b3f7c9e04c60a121a4bc538a3dc1b400/view/movie/M/302510321.mp4"]
     * has_ticket : true
     * bloopers : [{"medium":"http://img9.doubanio.com/img/trailer/medium/2580120886.jpg?","title":"MV：中文宣传曲《Amy》 (中文字幕)","subject_id":"33424345","alt":"https://movie.douban.com/trailer/257530/","small":"http://img9.doubanio.com/img/trailer/small/2580120886.jpg?","resource_url":"http://vt1.doubanio.com/202005261820/144198567deb853a3e42073e9e536732/view/movie/M/302570530.mp4","id":"257530"}]
     * clip_urls : ["http://vt1.doubanio.com/202005261820/c553c804f5b6117f4e27e477014aba8e/view/movie/M/302570626.mp4"]
     * current_season : null
     * casts : [{"avatars":{"small":"http://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1370586618.47.webp","large":"http://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1370586618.47.webp","medium":"http://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1370586618.47.webp"},"name_en":"Yui Ishikawa","name":"石川由依","alt":"https://movie.douban.com/celebrity/1329107/","id":"1329107"},{"avatars":{"small":"http://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p4964.webp","large":"http://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p4964.webp","medium":"http://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p4964.webp"},"name_en":"Minori Chihara","name":"茅原实里","alt":"https://movie.douban.com/celebrity/1042757/","id":"1042757"},{"avatars":{"small":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p21931.webp","large":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p21931.webp","medium":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p21931.webp"},"name_en":"Aya Endô","name":"远藤绫","alt":"https://movie.douban.com/celebrity/1008446/","id":"1008446"},{"avatars":{"small":"http://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p51585.webp","large":"http://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p51585.webp","medium":"http://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p51585.webp"},"name_en":"Minako Kotobuki","name":"寿美菜子","alt":"https://movie.douban.com/celebrity/1275208/","id":"1275208"}]
     * countries : ["日本"]
     * mainland_pubdate : 2020-01-10
     * photos : [{"thumb":"https://img1.doubanio.com/view/photo/m/public/p2571650067.webp","image":"https://img1.doubanio.com/view/photo/l/public/p2571650067.webp","cover":"https://img1.doubanio.com/view/photo/sqs/public/p2571650067.webp","alt":"https://movie.douban.com/photos/photo/2571650067/","id":"2571650067","icon":"https://img1.doubanio.com/view/photo/s/public/p2571650067.webp"},{"thumb":"https://img3.doubanio.com/view/photo/m/public/p2571650060.webp","image":"https://img3.doubanio.com/view/photo/l/public/p2571650060.webp","cover":"https://img3.doubanio.com/view/photo/sqs/public/p2571650060.webp","alt":"https://movie.douban.com/photos/photo/2571650060/","id":"2571650060","icon":"https://img3.doubanio.com/view/photo/s/public/p2571650060.webp"},{"thumb":"https://img9.doubanio.com/view/photo/m/public/p2597298005.webp","image":"https://img9.doubanio.com/view/photo/l/public/p2597298005.webp","cover":"https://img9.doubanio.com/view/photo/sqs/public/p2597298005.webp","alt":"https://movie.douban.com/photos/photo/2597298005/","id":"2597298005","icon":"https://img9.doubanio.com/view/photo/s/public/p2597298005.webp"},{"thumb":"https://img1.doubanio.com/view/photo/m/public/p2593086169.webp","image":"https://img1.doubanio.com/view/photo/l/public/p2593086169.webp","cover":"https://img1.doubanio.com/view/photo/sqs/public/p2593086169.webp","alt":"https://movie.douban.com/photos/photo/2593086169/","id":"2593086169","icon":"https://img1.doubanio.com/view/photo/s/public/p2593086169.webp"},{"thumb":"https://img9.doubanio.com/view/photo/m/public/p2593083194.webp","image":"https://img9.doubanio.com/view/photo/l/public/p2593083194.webp","cover":"https://img9.doubanio.com/view/photo/sqs/public/p2593083194.webp","alt":"https://movie.douban.com/photos/photo/2593083194/","id":"2593083194","icon":"https://img9.doubanio.com/view/photo/s/public/p2593083194.webp"},{"thumb":"https://img3.doubanio.com/view/photo/m/public/p2591956290.webp","image":"https://img3.doubanio.com/view/photo/l/public/p2591956290.webp","cover":"https://img3.doubanio.com/view/photo/sqs/public/p2591956290.webp","alt":"https://movie.douban.com/photos/photo/2591956290/","id":"2591956290","icon":"https://img3.doubanio.com/view/photo/s/public/p2591956290.webp"},{"thumb":"https://img1.doubanio.com/view/photo/m/public/p2591956288.webp","image":"https://img1.doubanio.com/view/photo/l/public/p2591956288.webp","cover":"https://img1.doubanio.com/view/photo/sqs/public/p2591956288.webp","alt":"https://movie.douban.com/photos/photo/2591956288/","id":"2591956288","icon":"https://img1.doubanio.com/view/photo/s/public/p2591956288.webp"},{"thumb":"https://img3.doubanio.com/view/photo/m/public/p2591956283.webp","image":"https://img3.doubanio.com/view/photo/l/public/p2591956283.webp","cover":"https://img3.doubanio.com/view/photo/sqs/public/p2591956283.webp","alt":"https://movie.douban.com/photos/photo/2591956283/","id":"2591956283","icon":"https://img3.doubanio.com/view/photo/s/public/p2591956283.webp"},{"thumb":"https://img3.doubanio.com/view/photo/m/public/p2591956282.webp","image":"https://img3.doubanio.com/view/photo/l/public/p2591956282.webp","cover":"https://img3.doubanio.com/view/photo/sqs/public/p2591956282.webp","alt":"https://movie.douban.com/photos/photo/2591956282/","id":"2591956282","icon":"https://img3.doubanio.com/view/photo/s/public/p2591956282.webp"},{"thumb":"https://img3.doubanio.com/view/photo/m/public/p2591956281.webp","image":"https://img3.doubanio.com/view/photo/l/public/p2591956281.webp","cover":"https://img3.doubanio.com/view/photo/sqs/public/p2591956281.webp","alt":"https://movie.douban.com/photos/photo/2591956281/","id":"2591956281","icon":"https://img3.doubanio.com/view/photo/s/public/p2591956281.webp"}]
     * summary : 作为“战争机器”长大的女主人公薇尔莉特·伊芙加登，在战争中失去双臂，并与收养她的少佐分离，在装上义肢后，成为邮政公司一名从事代写书信工作的 “自动手记人偶”。在车马缓慢、只能以信件传递感情的年代里，“自动手记人偶”通过与委托人接触交流，代写书信，以优美准确的文字传递着人们最为真挚的感情。外传的故事中，薇尔莉特·伊芙加登接到了一桩特殊的委托工作……
     * clips : [{"medium":"http://img3.doubanio.com/img/trailer/medium/2580360861.jpg?","title":"片段 (中文字幕)","subject_id":"33424345","alt":"https://movie.douban.com/trailer/257626/","small":"http://img3.doubanio.com/img/trailer/small/2580360861.jpg?","resource_url":"http://vt1.doubanio.com/202005261820/c553c804f5b6117f4e27e477014aba8e/view/movie/M/302570626.mp4","id":"257626"}]
     * subtype : movie
     * directors : [{"avatars":{"small":"http://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1564396200.09.webp","large":"http://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1564396200.09.webp","medium":"http://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1564396200.09.webp"},"name_en":"Fujita Haruka","name":"藤田春香","alt":"https://movie.douban.com/celebrity/1420526/","id":"1420526"}]
     * comments_count : 10680
     * popular_reviews : [{"rating":{"max":5,"value":5,"min":0},"title":"关于\u201c自我感动\u201d","subject_id":"33424345","author":{"uid":"153222869","avatar":"http://img9.doubanio.com/icon/u153222869-5.jpg","signature":"","alt":"https://www.douban.com/people/153222869/","id":"153222869","name":"FANzard"},"summary":"你们的感动不是虚假的，留下的眼泪也不必因为别人的评价而\u201c适可而止\u201d，他人没有受到触动并不代表他更高级。 因为纵火案导致了很多人来观影吧，对一些人来说既然花钱看了电影那自然是要发表一下评论的，毕竟情怀...","alt":"https://movie.douban.com/review/12157460/","id":"12157460"},{"rating":{"max":5,"value":5,"min":0},"title":"他们曾经活过的证明，这是他们活过的证明，逝者安息。","subject_id":"33424345","author":{"uid":"180334674","avatar":"http://img1.doubanio.com/icon/user_normal.jpg","signature":"","alt":"https://www.douban.com/people/180334674/","id":"180334674","name":"啊啊啊啊啊"},"summary":"2019年7月17日，紫罗兰永恒花园外传顺利制作完成。 2019年7月18日，这天是日本动漫史上有史以来最黑暗的一天，京都动画第一工作室被人为纵火烧毁，导致35人死亡，多人受伤。 2019年9月6日，《紫罗兰永恒花园 外传...","alt":"https://movie.douban.com/review/10480895/","id":"10480895"},{"rating":{"max":5,"value":5,"min":0},"title":"机械手与打字机","subject_id":"33424345","author":{"uid":"youraisemedown","avatar":"http://img3.doubanio.com/icon/u55697424-11.jpg","signature":"","alt":"https://www.douban.com/people/youraisemedown/","id":"55697424","name":"张茶客"},"summary":"外传几乎是旧时代机械的狂欢\u2014\u2014从薇尔莉特的机械手，到具有同样机械结构的打字机，再到邮递员的摩托车、原始的电梯、贝尔电话、建造中的电波塔......无时无刻不伴随着怀旧的浪漫气息和时代变迁之感。这些老式的...","alt":"https://movie.douban.com/review/12160673/","id":"12160673"},{"rating":{"max":5,"value":5,"min":0},"title":"虽然评分有分歧但是个人认为这已经是这部电影能做到的极限了","subject_id":"33424345","author":{"uid":"159516175","avatar":"http://img9.doubanio.com/icon/u159516175-4.jpg","signature":"","alt":"https://www.douban.com/people/159516175/","id":"159516175","name":"wxq"},"summary":"版权归作者所有，任何形式转载请联系作者。 作者：山吹紗綾（来自豆瓣） 来源：https://movie.douban.com/review/12160005/ Bilibili：复读-基，如果在B站看见专栏，则是这个昵称 如果您踩了这个影评，欢迎并请求...","alt":"https://movie.douban.com/review/12160005/","id":"12160005"},{"rating":{"max":5,"value":3,"min":0},"title":"好似一顿唯美平淡的下午茶，消磨时光养生正好。电影？算了吧","subject_id":"33424345","author":{"uid":"174218123","avatar":"http://img3.doubanio.com/icon/u174218123-1.jpg","signature":"","alt":"https://www.douban.com/people/174218123/","id":"174218123","name":"豆战圣佛"},"summary":"剧场版和番剧的风格和制作都非常相似，说好听点叫\u201c还是内味\u201d，说难听点叫\u201c缺点全都没改\u201d。原本被诟病的节奏慢没起伏的缺点在剧场版里被完整地保留了下来，本就是单元剧的分散剧情在剧场版里没有给一个总结或...","alt":"https://movie.douban.com/review/12159786/","id":"12159786"},{"rating":{"max":5,"value":3,"min":0},"title":"马云爸爸快来找我吧的瞎鸡毛写","subject_id":"33424345","author":{"uid":"30638150","avatar":"http://img3.doubanio.com/icon/u30638150-41.jpg","signature":"豆你妹瓣","alt":"https://www.douban.com/people/30638150/","id":"30638150","name":"ノ( ゜-゜ノ)"},"summary":"啊啊啊啊，最近好颓啊，但是还是要打起精神啊，都不要理我，我就是瞎叨叨一下。 之前完全没有看过这个番也完全不知道讲的什么，但是我坚信好的电影就算不看前面依然可以get到乐趣，当然看之前看过的朋友给我讲了...","alt":"https://movie.douban.com/review/12177561/","id":"12177561"},{"rating":{"max":5,"value":5,"min":0},"title":"京紫外传的简单感想","subject_id":"33424345","author":{"uid":"lemonclover","avatar":"http://img3.doubanio.com/icon/u2736221-2.jpg","signature":"柠檬","alt":"https://www.douban.com/people/lemonclover/","id":"2736221","name":"柠檬味棉花糖"},"summary":"这是2020年在电影院看的第一场电影。 画面十分精致细腻，是京阿尼一贯的水准，虽然总感觉不如TV版那么惊艳，但也是相当优秀了，在大银幕上看京阿尼十分满足。配乐超棒，圆舞曲和情节配合得刚刚好，让人都不禁想跟...","alt":"https://movie.douban.com/review/12160119/","id":"12160119"},{"rating":{"max":5,"value":5,"min":0},"title":"紫罗兰永恒花园:  京阿尼给予你们最后的爱","subject_id":"33424345","author":{"uid":"152355894","avatar":"http://img3.doubanio.com/icon/u152355894-1.jpg","signature":"","alt":"https://www.douban.com/people/152355894/","id":"152355894","name":"ta心无我"},"summary":"这部紫罗兰外传是在七月十八日大火之前就已经完成的成片，庆幸得以保存并且上映；因此我想说，与其在不断挑剔它剧情的薄弱，不如好好的享受吧，享受京阿尼式的感动，享受它完美的音乐和画面，享受京阿尼带给你的...","alt":"https://movie.douban.com/review/12156404/","id":"12156404"},{"rating":{"max":5,"value":5,"min":0},"title":"再来一次，但不仅如此","subject_id":"33424345","author":{"uid":"44885227","avatar":"http://img9.doubanio.com/icon/u44885227-85.jpg","signature":"爱我与被我爱，自己选择吧！","alt":"https://www.douban.com/people/44885227/","id":"44885227","name":"Unescapable"},"summary":"《紫罗兰永恒花园》的每个故事都有自己的独立叙事，但实际上都在书写薇尔莉特。这种结构在剧场版中也不例外\u2014\u2014从几个不同的侧面，剧场版对主线故事（或说薇尔莉特本身）的意犹未尽之处进行了展开和补完。如果说...","alt":"https://movie.douban.com/review/12146111/","id":"12146111"},{"rating":{"max":5,"value":4,"min":0},"title":"同样是送信送件，京都动画传递着幸福与治愈，再看看我们的邮政(","subject_id":"33424345","author":{"uid":"qiankunyi","avatar":"http://img1.doubanio.com/icon/u3489077-28.jpg","signature":"絢辻さんは裏表のない素敵な人","alt":"https://www.douban.com/people/qiankunyi/","id":"3489077","name":"钱坤一"},"summary":"没看过TV也不影响观看剧场版，温柔治愈，还不错。 故事不谈剧透，简单说就是薇尔莉特与她的同事们，用职业精神，影响了一对被迫分离的姐妹各自的人生，不仅维系了姐妹的感情，也将她们与更多人\u201c连接\u201d在一起。 ...","alt":"https://movie.douban.com/review/12157151/","id":"12157151"}]
     * ratings_count : 30542
     * aka : ["Violet Evergarden: Eternity and the Auto Memories Doll"]
     */

    private RatingBean rating;
    private int reviews_count;
    private int wish_count;
    private String original_title;
    private int collect_count;
    private ImagesBean images;
    private String douban_site;
    private String year;
    private String alt;
    private String id;
    private String mobile_url;
    private int photos_count;
    private String pubdate;
    private String title;
    private Object do_count;
    private boolean has_video;
    private String share_url;
    private Object seasons_count;
    private String schedule_url;
    private String website;
    private boolean has_schedule;
    private Object collection;
    private Object episodes_count;
    private boolean has_ticket;
    private Object current_season;
    private String mainland_pubdate;
    private String summary;
    private String subtype;
    private int comments_count;
    private int ratings_count;
    private List<VideosBean> videos;
    private List<String> blooper_urls;
    private List<PopularCommentsBean> popular_comments;
    private List<String> languages;
    private List<WritersBean> writers;
    private List<String> pubdates;
    private List<String> tags;
    private List<String> durations;
    private List<String> genres;
    private List<TrailersBean> trailers;
    private List<String> trailer_urls;
    private List<BloopersBean> bloopers;
    private List<String> clip_urls;
    private List<CastsBean> casts;
    private List<String> countries;
    private List<PhotosBean> photos;
    private List<ClipsBean> clips;
    private List<DirectorsBean> directors;
    private List<PopularReviewsBean> popular_reviews;
    private List<String> aka;

    public RatingBean getRating() {
        return rating;
    }

    public void setRating(RatingBean rating) {
        this.rating = rating;
    }

    public int getReviews_count() {
        return reviews_count;
    }

    public void setReviews_count(int reviews_count) {
        this.reviews_count = reviews_count;
    }

    public int getWish_count() {
        return wish_count;
    }

    public void setWish_count(int wish_count) {
        this.wish_count = wish_count;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public int getCollect_count() {
        return collect_count;
    }

    public void setCollect_count(int collect_count) {
        this.collect_count = collect_count;
    }

    public ImagesBean getImages() {
        return images;
    }

    public void setImages(ImagesBean images) {
        this.images = images;
    }

    public String getDouban_site() {
        return douban_site;
    }

    public void setDouban_site(String douban_site) {
        this.douban_site = douban_site;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
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

    public String getMobile_url() {
        return mobile_url;
    }

    public void setMobile_url(String mobile_url) {
        this.mobile_url = mobile_url;
    }

    public int getPhotos_count() {
        return photos_count;
    }

    public void setPhotos_count(int photos_count) {
        this.photos_count = photos_count;
    }

    public String getPubdate() {
        return pubdate;
    }

    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Object getDo_count() {
        return do_count;
    }

    public void setDo_count(Object do_count) {
        this.do_count = do_count;
    }

    public boolean isHas_video() {
        return has_video;
    }

    public void setHas_video(boolean has_video) {
        this.has_video = has_video;
    }

    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }

    public Object getSeasons_count() {
        return seasons_count;
    }

    public void setSeasons_count(Object seasons_count) {
        this.seasons_count = seasons_count;
    }

    public String getSchedule_url() {
        return schedule_url;
    }

    public void setSchedule_url(String schedule_url) {
        this.schedule_url = schedule_url;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public boolean isHas_schedule() {
        return has_schedule;
    }

    public void setHas_schedule(boolean has_schedule) {
        this.has_schedule = has_schedule;
    }

    public Object getCollection() {
        return collection;
    }

    public void setCollection(Object collection) {
        this.collection = collection;
    }

    public Object getEpisodes_count() {
        return episodes_count;
    }

    public void setEpisodes_count(Object episodes_count) {
        this.episodes_count = episodes_count;
    }

    public boolean isHas_ticket() {
        return has_ticket;
    }

    public void setHas_ticket(boolean has_ticket) {
        this.has_ticket = has_ticket;
    }

    public Object getCurrent_season() {
        return current_season;
    }

    public void setCurrent_season(Object current_season) {
        this.current_season = current_season;
    }

    public String getMainland_pubdate() {
        return mainland_pubdate;
    }

    public void setMainland_pubdate(String mainland_pubdate) {
        this.mainland_pubdate = mainland_pubdate;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public int getComments_count() {
        return comments_count;
    }

    public void setComments_count(int comments_count) {
        this.comments_count = comments_count;
    }

    public int getRatings_count() {
        return ratings_count;
    }

    public void setRatings_count(int ratings_count) {
        this.ratings_count = ratings_count;
    }

    public List<VideosBean> getVideos() {
        return videos;
    }

    public void setVideos(List<VideosBean> videos) {
        this.videos = videos;
    }

    public List<String> getBlooper_urls() {
        return blooper_urls;
    }

    public void setBlooper_urls(List<String> blooper_urls) {
        this.blooper_urls = blooper_urls;
    }

    public List<PopularCommentsBean> getPopular_comments() {
        return popular_comments;
    }

    public void setPopular_comments(List<PopularCommentsBean> popular_comments) {
        this.popular_comments = popular_comments;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public List<WritersBean> getWriters() {
        return writers;
    }

    public void setWriters(List<WritersBean> writers) {
        this.writers = writers;
    }

    public List<String> getPubdates() {
        return pubdates;
    }

    public void setPubdates(List<String> pubdates) {
        this.pubdates = pubdates;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<String> getDurations() {
        return durations;
    }

    public void setDurations(List<String> durations) {
        this.durations = durations;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public List<TrailersBean> getTrailers() {
        return trailers;
    }

    public void setTrailers(List<TrailersBean> trailers) {
        this.trailers = trailers;
    }

    public List<String> getTrailer_urls() {
        return trailer_urls;
    }

    public void setTrailer_urls(List<String> trailer_urls) {
        this.trailer_urls = trailer_urls;
    }

    public List<BloopersBean> getBloopers() {
        return bloopers;
    }

    public void setBloopers(List<BloopersBean> bloopers) {
        this.bloopers = bloopers;
    }

    public List<String> getClip_urls() {
        return clip_urls;
    }

    public void setClip_urls(List<String> clip_urls) {
        this.clip_urls = clip_urls;
    }

    public List<CastsBean> getCasts() {
        return casts;
    }

    public void setCasts(List<CastsBean> casts) {
        this.casts = casts;
    }

    public List<String> getCountries() {
        return countries;
    }

    public void setCountries(List<String> countries) {
        this.countries = countries;
    }

    public List<PhotosBean> getPhotos() {
        return photos;
    }

    public void setPhotos(List<PhotosBean> photos) {
        this.photos = photos;
    }

    public List<ClipsBean> getClips() {
        return clips;
    }

    public void setClips(List<ClipsBean> clips) {
        this.clips = clips;
    }

    public List<DirectorsBean> getDirectors() {
        return directors;
    }

    public void setDirectors(List<DirectorsBean> directors) {
        this.directors = directors;
    }

    public List<PopularReviewsBean> getPopular_reviews() {
        return popular_reviews;
    }

    public void setPopular_reviews(List<PopularReviewsBean> popular_reviews) {
        this.popular_reviews = popular_reviews;
    }

    public List<String> getAka() {
        return aka;
    }

    public void setAka(List<String> aka) {
        this.aka = aka;
    }

    public static class RatingBean {
        /**
         * max : 10
         * average : 7.5
         * details : {"1":49,"3":1405,"2":233,"5":938,"4":1705}
         * stars : 40
         * min : 0
         */

        private int max;
        private double average;
        private DetailsBean details;
        private String stars;
        private int min;

        public int getMax() {
            return max;
        }

        public void setMax(int max) {
            this.max = max;
        }

        public double getAverage() {
            return average;
        }

        public void setAverage(double average) {
            this.average = average;
        }

        public DetailsBean getDetails() {
            return details;
        }

        public void setDetails(DetailsBean details) {
            this.details = details;
        }

        public String getStars() {
            return stars;
        }

        public void setStars(String stars) {
            this.stars = stars;
        }

        public int getMin() {
            return min;
        }

        public void setMin(int min) {
            this.min = min;
        }

        public static class DetailsBean {
            /**
             * 1 : 49
             * 3 : 1405
             * 2 : 233
             * 5 : 938
             * 4 : 1705
             */

            @SerializedName("1")
            private int _$1;
            @SerializedName("3")
            private int _$3;
            @SerializedName("2")
            private int _$2;
            @SerializedName("5")
            private int _$5;
            @SerializedName("4")
            private int _$4;

            public int get_$1() {
                return _$1;
            }

            public void set_$1(int _$1) {
                this._$1 = _$1;
            }

            public int get_$3() {
                return _$3;
            }

            public void set_$3(int _$3) {
                this._$3 = _$3;
            }

            public int get_$2() {
                return _$2;
            }

            public void set_$2(int _$2) {
                this._$2 = _$2;
            }

            public int get_$5() {
                return _$5;
            }

            public void set_$5(int _$5) {
                this._$5 = _$5;
            }

            public int get_$4() {
                return _$4;
            }

            public void set_$4(int _$4) {
                this._$4 = _$4;
            }
        }
    }

    public static class ImagesBean {
        /**
         * small : http://img9.doubanio.com/view/photo/s_ratio_poster/public/p2578722076.webp
         * large : http://img9.doubanio.com/view/photo/s_ratio_poster/public/p2578722076.webp
         * medium : http://img9.doubanio.com/view/photo/s_ratio_poster/public/p2578722076.webp
         */

        private String small;
        private String large;
        private String medium;

        public String getSmall() {
            return small;
        }

        public void setSmall(String small) {
            this.small = small;
        }

        public String getLarge() {
            return large;
        }

        public void setLarge(String large) {
            this.large = large;
        }

        public String getMedium() {
            return medium;
        }

        public void setMedium(String medium) {
            this.medium = medium;
        }
    }

    public static class VideosBean {
        /**
         * source : {"literal":"bilibili","pic":"http://img9.doubanio.com/f/movie/f536fe0ea1cbb0914658ae803125d078351f9047/pics/movie/video-bilibili.png","name":"哔哩哔哩"}
         * sample_link : https://www.bilibili.com/bangumi/play/ss31779?bsource=douban
         * video_id : ss31779
         * need_pay : false
         */

        private SourceBean source;
        private String sample_link;
        private String video_id;
        private boolean need_pay;

        public SourceBean getSource() {
            return source;
        }

        public void setSource(SourceBean source) {
            this.source = source;
        }

        public String getSample_link() {
            return sample_link;
        }

        public void setSample_link(String sample_link) {
            this.sample_link = sample_link;
        }

        public String getVideo_id() {
            return video_id;
        }

        public void setVideo_id(String video_id) {
            this.video_id = video_id;
        }

        public boolean isNeed_pay() {
            return need_pay;
        }

        public void setNeed_pay(boolean need_pay) {
            this.need_pay = need_pay;
        }

        public static class SourceBean {
            /**
             * literal : bilibili
             * pic : http://img9.doubanio.com/f/movie/f536fe0ea1cbb0914658ae803125d078351f9047/pics/movie/video-bilibili.png
             * name : 哔哩哔哩
             */

            private String literal;
            private String pic;
            private String name;

            public String getLiteral() {
                return literal;
            }

            public void setLiteral(String literal) {
                this.literal = literal;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }

    public static class PopularCommentsBean {
        /**
         * rating : {"max":5,"value":3,"min":0}
         * useful_count : 286
         * author : {"uid":"148010430","avatar":"http://img1.doubanio.com/icon/u148010430-8.jpg","signature":"","alt":"https://www.douban.com/people/148010430/","id":"148010430","name":"白胡椒哇"}
         * subject_id : 33424345
         * content : 对京紫或者京都都没有太大的感想，反正是感觉各方面平庸没有任何可取之处的动画作品，虽然作画精致光影细腻情感真诚节奏舒缓，但我就是全程面无表情内心毫无波动甚至有点嫌热。番剧版本的人类圣经世纪霸权八万八之类的京吹言论基本可以忽略，影片本身也就是，平庸二字足以完整概括。虽然有京阿尼的大火，但我也没有情怀加成，整体上故事人物各方面在我这儿都平庸至极，不能及格。
         * created_at : 2019-12-26 23:44:04
         * id : 2103409719
         */

        private RatingBeanX rating;
        private int useful_count;
        private AuthorBean author;
        private String subject_id;
        private String content;
        private String created_at;
        private String id;

        public RatingBeanX getRating() {
            return rating;
        }

        public void setRating(RatingBeanX rating) {
            this.rating = rating;
        }

        public int getUseful_count() {
            return useful_count;
        }

        public void setUseful_count(int useful_count) {
            this.useful_count = useful_count;
        }

        public AuthorBean getAuthor() {
            return author;
        }

        public void setAuthor(AuthorBean author) {
            this.author = author;
        }

        public String getSubject_id() {
            return subject_id;
        }

        public void setSubject_id(String subject_id) {
            this.subject_id = subject_id;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public static class RatingBeanX {
            /**
             * max : 5
             * value : 3
             * min : 0
             */

            private int max;
            private int value;
            private int min;

            public int getMax() {
                return max;
            }

            public void setMax(int max) {
                this.max = max;
            }

            public int getValue() {
                return value;
            }

            public void setValue(int value) {
                this.value = value;
            }

            public int getMin() {
                return min;
            }

            public void setMin(int min) {
                this.min = min;
            }
        }

        public static class AuthorBean {
            /**
             * uid : 148010430
             * avatar : http://img1.doubanio.com/icon/u148010430-8.jpg
             * signature :
             * alt : https://www.douban.com/people/148010430/
             * id : 148010430
             * name : 白胡椒哇
             */

            private String uid;
            private String avatar;
            private String signature;
            private String alt;
            private String id;
            private String name;

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public String getSignature() {
                return signature;
            }

            public void setSignature(String signature) {
                this.signature = signature;
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

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }

    public static class WritersBean {
        /**
         * avatars : {"small":"http://img1.doubanio.com/f/movie/ca527386eb8c4e325611e22dfcb04cc116d6b423/pics/movie/celebrity-default-small.png","large":"http://img3.doubanio.com/f/movie/63acc16ca6309ef191f0378faf793d1096a3e606/pics/movie/celebrity-default-large.png","medium":"http://img1.doubanio.com/f/movie/8dd0c794499fe925ae2ae89ee30cd225750457b4/pics/movie/celebrity-default-medium.png"}
         * name_en : Kana Akatsuki
         * name : 晓佳奈
         * alt : https://movie.douban.com/celebrity/1386928/
         * id : 1386928
         */

        private AvatarsBean avatars;
        private String name_en;
        private String name;
        private String alt;
        private String id;

        public AvatarsBean getAvatars() {
            return avatars;
        }

        public void setAvatars(AvatarsBean avatars) {
            this.avatars = avatars;
        }

        public String getName_en() {
            return name_en;
        }

        public void setName_en(String name_en) {
            this.name_en = name_en;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
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

        public static class AvatarsBean {
            /**
             * small : http://img1.doubanio.com/f/movie/ca527386eb8c4e325611e22dfcb04cc116d6b423/pics/movie/celebrity-default-small.png
             * large : http://img3.doubanio.com/f/movie/63acc16ca6309ef191f0378faf793d1096a3e606/pics/movie/celebrity-default-large.png
             * medium : http://img1.doubanio.com/f/movie/8dd0c794499fe925ae2ae89ee30cd225750457b4/pics/movie/celebrity-default-medium.png
             */

            private String small;
            private String large;
            private String medium;

            public String getSmall() {
                return small;
            }

            public void setSmall(String small) {
                this.small = small;
            }

            public String getLarge() {
                return large;
            }

            public void setLarge(String large) {
                this.large = large;
            }

            public String getMedium() {
                return medium;
            }

            public void setMedium(String medium) {
                this.medium = medium;
            }
        }
    }

    public static class TrailersBean {
        /**
         * medium : http://img1.doubanio.com/img/trailer/medium/2578742488.jpg?
         * title : 中国预告片：定档版 (中文字幕)
         * subject_id : 33424345
         * alt : https://movie.douban.com/trailer/257279/
         * small : http://img1.doubanio.com/img/trailer/small/2578742488.jpg?
         * resource_url : http://vt1.doubanio.com/202005261820/600357f4d578ada130f77a2ef4c313dd/view/movie/M/302570279.mp4
         * id : 257279
         */

        private String medium;
        private String title;
        private String subject_id;
        private String alt;
        private String small;
        private String resource_url;
        private String id;

        public String getMedium() {
            return medium;
        }

        public void setMedium(String medium) {
            this.medium = medium;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSubject_id() {
            return subject_id;
        }

        public void setSubject_id(String subject_id) {
            this.subject_id = subject_id;
        }

        public String getAlt() {
            return alt;
        }

        public void setAlt(String alt) {
            this.alt = alt;
        }

        public String getSmall() {
            return small;
        }

        public void setSmall(String small) {
            this.small = small;
        }

        public String getResource_url() {
            return resource_url;
        }

        public void setResource_url(String resource_url) {
            this.resource_url = resource_url;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }

    public static class BloopersBean {
        /**
         * medium : http://img9.doubanio.com/img/trailer/medium/2580120886.jpg?
         * title : MV：中文宣传曲《Amy》 (中文字幕)
         * subject_id : 33424345
         * alt : https://movie.douban.com/trailer/257530/
         * small : http://img9.doubanio.com/img/trailer/small/2580120886.jpg?
         * resource_url : http://vt1.doubanio.com/202005261820/144198567deb853a3e42073e9e536732/view/movie/M/302570530.mp4
         * id : 257530
         */

        private String medium;
        private String title;
        private String subject_id;
        private String alt;
        private String small;
        private String resource_url;
        private String id;

        public String getMedium() {
            return medium;
        }

        public void setMedium(String medium) {
            this.medium = medium;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSubject_id() {
            return subject_id;
        }

        public void setSubject_id(String subject_id) {
            this.subject_id = subject_id;
        }

        public String getAlt() {
            return alt;
        }

        public void setAlt(String alt) {
            this.alt = alt;
        }

        public String getSmall() {
            return small;
        }

        public void setSmall(String small) {
            this.small = small;
        }

        public String getResource_url() {
            return resource_url;
        }

        public void setResource_url(String resource_url) {
            this.resource_url = resource_url;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }

    public static class CastsBean {
        /**
         * avatars : {"small":"http://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1370586618.47.webp","large":"http://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1370586618.47.webp","medium":"http://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1370586618.47.webp"}
         * name_en : Yui Ishikawa
         * name : 石川由依
         * alt : https://movie.douban.com/celebrity/1329107/
         * id : 1329107
         */

        private AvatarsBeanX avatars;
        private String name_en;
        private String name;
        private String alt;
        private String id;

        public AvatarsBeanX getAvatars() {
            return avatars;
        }

        public void setAvatars(AvatarsBeanX avatars) {
            this.avatars = avatars;
        }

        public String getName_en() {
            return name_en;
        }

        public void setName_en(String name_en) {
            this.name_en = name_en;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
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

        public static class AvatarsBeanX {
            /**
             * small : http://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1370586618.47.webp
             * large : http://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1370586618.47.webp
             * medium : http://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1370586618.47.webp
             */

            private String small;
            private String large;
            private String medium;

            public String getSmall() {
                return small;
            }

            public void setSmall(String small) {
                this.small = small;
            }

            public String getLarge() {
                return large;
            }

            public void setLarge(String large) {
                this.large = large;
            }

            public String getMedium() {
                return medium;
            }

            public void setMedium(String medium) {
                this.medium = medium;
            }
        }
    }

    public static class PhotosBean {
        /**
         * thumb : https://img1.doubanio.com/view/photo/m/public/p2571650067.webp
         * image : https://img1.doubanio.com/view/photo/l/public/p2571650067.webp
         * cover : https://img1.doubanio.com/view/photo/sqs/public/p2571650067.webp
         * alt : https://movie.douban.com/photos/photo/2571650067/
         * id : 2571650067
         * icon : https://img1.doubanio.com/view/photo/s/public/p2571650067.webp
         */

        private String thumb;
        private String image;
        private String cover;
        private String alt;
        private String id;
        private String icon;

        public String getThumb() {
            return thumb;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
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

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }
    }

    public static class ClipsBean {
        /**
         * medium : http://img3.doubanio.com/img/trailer/medium/2580360861.jpg?
         * title : 片段 (中文字幕)
         * subject_id : 33424345
         * alt : https://movie.douban.com/trailer/257626/
         * small : http://img3.doubanio.com/img/trailer/small/2580360861.jpg?
         * resource_url : http://vt1.doubanio.com/202005261820/c553c804f5b6117f4e27e477014aba8e/view/movie/M/302570626.mp4
         * id : 257626
         */

        private String medium;
        private String title;
        private String subject_id;
        private String alt;
        private String small;
        private String resource_url;
        private String id;

        public String getMedium() {
            return medium;
        }

        public void setMedium(String medium) {
            this.medium = medium;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSubject_id() {
            return subject_id;
        }

        public void setSubject_id(String subject_id) {
            this.subject_id = subject_id;
        }

        public String getAlt() {
            return alt;
        }

        public void setAlt(String alt) {
            this.alt = alt;
        }

        public String getSmall() {
            return small;
        }

        public void setSmall(String small) {
            this.small = small;
        }

        public String getResource_url() {
            return resource_url;
        }

        public void setResource_url(String resource_url) {
            this.resource_url = resource_url;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }

    public static class DirectorsBean {
        /**
         * avatars : {"small":"http://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1564396200.09.webp","large":"http://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1564396200.09.webp","medium":"http://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1564396200.09.webp"}
         * name_en : Fujita Haruka
         * name : 藤田春香
         * alt : https://movie.douban.com/celebrity/1420526/
         * id : 1420526
         */

        private AvatarsBeanXX avatars;
        private String name_en;
        private String name;
        private String alt;
        private String id;

        public AvatarsBeanXX getAvatars() {
            return avatars;
        }

        public void setAvatars(AvatarsBeanXX avatars) {
            this.avatars = avatars;
        }

        public String getName_en() {
            return name_en;
        }

        public void setName_en(String name_en) {
            this.name_en = name_en;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
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

        public static class AvatarsBeanXX {
            /**
             * small : http://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1564396200.09.webp
             * large : http://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1564396200.09.webp
             * medium : http://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1564396200.09.webp
             */

            private String small;
            private String large;
            private String medium;

            public String getSmall() {
                return small;
            }

            public void setSmall(String small) {
                this.small = small;
            }

            public String getLarge() {
                return large;
            }

            public void setLarge(String large) {
                this.large = large;
            }

            public String getMedium() {
                return medium;
            }

            public void setMedium(String medium) {
                this.medium = medium;
            }
        }
    }

    public static class PopularReviewsBean {
        /**
         * rating : {"max":5,"value":5,"min":0}
         * title : 关于“自我感动”
         * subject_id : 33424345
         * author : {"uid":"153222869","avatar":"http://img9.doubanio.com/icon/u153222869-5.jpg","signature":"","alt":"https://www.douban.com/people/153222869/","id":"153222869","name":"FANzard"}
         * summary : 你们的感动不是虚假的，留下的眼泪也不必因为别人的评价而“适可而止”，他人没有受到触动并不代表他更高级。 因为纵火案导致了很多人来观影吧，对一些人来说既然花钱看了电影那自然是要发表一下评论的，毕竟情怀...
         * alt : https://movie.douban.com/review/12157460/
         * id : 12157460
         */

        private RatingBeanXX rating;
        private String title;
        private String subject_id;
        private AuthorBeanX author;
        private String summary;
        private String alt;
        private String id;

        public RatingBeanXX getRating() {
            return rating;
        }

        public void setRating(RatingBeanXX rating) {
            this.rating = rating;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSubject_id() {
            return subject_id;
        }

        public void setSubject_id(String subject_id) {
            this.subject_id = subject_id;
        }

        public AuthorBeanX getAuthor() {
            return author;
        }

        public void setAuthor(AuthorBeanX author) {
            this.author = author;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
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

        public static class RatingBeanXX {
            /**
             * max : 5
             * value : 5
             * min : 0
             */

            private int max;
            private int value;
            private int min;

            public int getMax() {
                return max;
            }

            public void setMax(int max) {
                this.max = max;
            }

            public int getValue() {
                return value;
            }

            public void setValue(int value) {
                this.value = value;
            }

            public int getMin() {
                return min;
            }

            public void setMin(int min) {
                this.min = min;
            }
        }

        public static class AuthorBeanX {
            /**
             * uid : 153222869
             * avatar : http://img9.doubanio.com/icon/u153222869-5.jpg
             * signature :
             * alt : https://www.douban.com/people/153222869/
             * id : 153222869
             * name : FANzard
             */

            private String uid;
            private String avatar;
            private String signature;
            private String alt;
            private String id;
            private String name;

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public String getSignature() {
                return signature;
            }

            public void setSignature(String signature) {
                this.signature = signature;
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

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
