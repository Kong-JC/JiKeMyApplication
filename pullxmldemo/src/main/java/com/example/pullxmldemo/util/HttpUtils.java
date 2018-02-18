package com.example.pullxmldemo.util;

import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 网络访问工具类
 */
public class HttpUtils {

    private static final String TAG = "HttpUtils";

    /**
     * 根据网络地址获取数据
     *
     * @param path 网址
     * @return 网络数据字节数组
     */
    public static byte[] getHttpResult(String path) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        String str;
        try {
            URL url = new URL(path);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setReadTimeout(5000);
            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                InputStream inputStream = connection.getInputStream();
                int temp = 0;
                byte[] buff = new byte[1024];
                while ((temp = inputStream.read(buff)) != -1) {
                    outputStream.write(buff, 0, temp);
                }
            }
        } catch (Exception e) {
            try {
                str = new String(data.getBytes(), "utf-8");
//                Log.d(TAG, "getHttpResult: str:" + str);
                byte[] bytes = str.getBytes();
                outputStream.write(bytes);
            } catch (IOException e1) {
                Log.e(TAG, "getHttpResult: 本地缓存资源失效！", e);
            }
            Log.e(TAG, "getHttpResult: 请求失败！", e);
        }
        return outputStream.toByteArray();
    }

    public static final String data = "<rss version=\"2.0\">\n" +
            "<channel>\n" +
            "<title/>\n" +
            "<link>http://m.xinjunshi.net</link>\n" +
            "<description>没指定分类！</description>\n" +
            "<language>zh-cn</language>\n" +
            "<generator>\n" +
            "<![CDATA[\n" +
            "Copyright &lt;span style=&quot;font-family:Arial&quot;&gt;&amp;copy;&lt;/span&gt; 2002-2010 xinjunshi.com, All Rights Reserved\n" +
            "]]>\n" +
            "</generator>\n" +
            "<webmaster>desdev@vip.qq.com</webmaster>\n" +
            "<item>\n" +
            "<title>\n" +
            "<![CDATA[ 中俄回击美日韩同盟 同时抵日侦查 ]]>\n" +
            "</title>\n" +
            "<link>http://m.xinjunshi.net/a/20170421/10254610.html</link>\n" +
            "<description>\n" +
            "<![CDATA[\n" +
            "中俄海军军舰举行海上联合军事演习。 日本防卫省统合幕僚监部网站18日发布公告称，北京时间4月18日，经日本第14护卫队松雪号护卫舰确认，中国海军054A型护卫舰529舰、530舰及综合补给舰882舰在种子岛(日本发射中心)偏东约50公里处海域向西北偏北航行。随后，\n" +
            "]]>\n" +
            "</description>\n" +
            "<image>\n" +
            "http://m.xinjunshi.com/uploads/170421/15958-1F4211451494F.jpg\n" +
            "</image>\n" +
            "<pubDate>2017-04-21</pubDate>\n" +
            "<category>幻灯</category>\n" +
            "<author>xjs_lm</author>\n" +
            "<comments>未知</comments>\n" +
            "</item>\n" +
            "<item>\n" +
            "<title>\n" +
            "<![CDATA[ 中国海军之后空军也开始“下饺子” ]]>\n" +
            "</title>\n" +
            "<link>http://m.xinjunshi.net/a/20170421/10254522.html</link>\n" +
            "<description>\n" +
            "<![CDATA[\n" +
            "日前，中航工业陕飞(陕西飞机工业集团有限公司)总装脉动生产线实现首架机总装交付，这标志着生产线试运行全线打通，也标志着陕飞产能提升取得了重大阶段性成果。 飞机总装脉动生产线是一种按节拍移动的装配线，运用精益制造思想，对装配过程进行流程再设计、\n" +
            "]]>\n" +
            "</description>\n" +
            "<image>\n" +
            "http://m.xinjunshi.com/uploads/allimg/170421/21-1F4210Z2220-L.jpg\n" +
            "</image>\n" +
            "<pubDate>2017-04-21</pubDate>\n" +
            "<category>幻灯</category>\n" +
            "<author>爆料君</author>\n" +
            "<comments>未知</comments>\n" +
            "</item>\n" +
            "<item>\n" +
            "<title>\n" +
            "<![CDATA[ 韩国颠倒是非 自称历史超过五千年！ ]]>\n" +
            "</title>\n" +
            "<link>http://m.xinjunshi.net/a/20170421/10254521.html</link>\n" +
            "<description>\n" +
            "<![CDATA[\n" +
            "玷污体育之名 前段时间我们报道过，韩国媒体担心中国队和中国的游客不去观看平昌冬奥会，从而导致韩国的旅游收入锐减，因此在媒体上表示中国队应该遵循体育精神前来参加冬奥会，同时韩国也非常欢迎中国游客前来观看冬奥会，并且在韩国进行旅游消费。韩媒改口\n" +
            "]]>\n" +
            "</description>\n" +
            "<image>\n" +
            "http://m.xinjunshi.com/uploads/allimg/170421/21-1F4210Z1130-L.jpg\n" +
            "</image>\n" +
            "<pubDate>2017-04-21</pubDate>\n" +
            "<category>幻灯</category>\n" +
            "<author>爆料君</author>\n" +
            "<comments>未知</comments>\n" +
            "</item>\n" +
            "<item>\n" +
            "<title>\n" +
            "<![CDATA[ 轰20传好消息 其威慑力曝光让人惊叹 ]]>\n" +
            "</title>\n" +
            "<link>http://m.xinjunshi.net/a/20170421/10254520.html</link>\n" +
            "<description>\n" +
            "<![CDATA[\n" +
            "美国空军抢先一步发展远端打击轰炸机，中国的未来轰炸机计划也紧追在后，美国《航空和空间技术周刊》网站点名，中国研发新一代轰-20隐身战略轰炸机，是以携载巡航导弹为主，可能是与歼-20超音速隐形战斗攻击机搭档使用的机型。 军事专家预测，距离中国空军司\n" +
            "]]>\n" +
            "</description>\n" +
            "<image>\n" +
            "http://m.xinjunshi.com/uploads/allimg/170421/21-1F4210Z0100-L.jpg\n" +
            "</image>\n" +
            "<pubDate>2017-04-21</pubDate>\n" +
            "<category>幻灯</category>\n" +
            "<author>爆料君</author>\n" +
            "<comments>未知</comments>\n" +
            "</item>\n" +
            "<item>\n" +
            "<title>\n" +
            "<![CDATA[ 朝为何有恃无恐？中俄尚未达成一致 ]]>\n" +
            "</title>\n" +
            "<link>http://m.xinjunshi.net/a/20170421/10254519.html</link>\n" +
            "<description>\n" +
            "<![CDATA[\n" +
            "朝鲜人民军 李科夫：我们刚才看到朝鲜说今后还要试射导弹而且还变成了周播节目，每周一次。而美国也丝毫没有示弱的意思，如何来解读现在半岛的僵局呢? 何亮亮：目前朝鲜半岛这个局势确实是仍然是一个僵局，只不过这个僵局有了一些新的特点。这个新的特点就是\n" +
            "]]>\n" +
            "</description>\n" +
            "<image>\n" +
            "http://m.xinjunshi.com/uploads/allimg/170421/21-1F4210UQ70-L.jpg\n" +
            "</image>\n" +
            "<pubDate>2017-04-21</pubDate>\n" +
            "<category>幻灯</category>\n" +
            "<author>爆料君</author>\n" +
            "<comments>未知</comments>\n" +
            "</item>\n" +
            "<item>\n" +
            "<title>\n" +
            "<![CDATA[ 振奋！国产航母比辽宁舰多带一杀器 ]]>\n" +
            "</title>\n" +
            "<link>http://m.xinjunshi.net/a/20170421/10254518.html</link>\n" +
            "<description>\n" +
            "<![CDATA[\n" +
            "图为辽宁舰上的战机 随着国产001A型航母下水日期的临近，大家的注意力也从何时下水转到了载机数量上，纷纷猜测新航母能带多少舰载机。有说歼-15增加到36架的，也有说载机数量达到50架的。这两种说法是否靠谱，下面我们就来分析一下。 辽宁号航母的载机数量\n" +
            "]]>\n" +
            "</description>\n" +
            "<image>\n" +
            "http://m.xinjunshi.com/uploads/allimg/170421/21-1F4210UA30-L.jpg\n" +
            "</image>\n" +
            "<pubDate>2017-04-21</pubDate>\n" +
            "<category>幻灯</category>\n" +
            "<author>爆料君</author>\n" +
            "<comments>未知</comments>\n" +
            "</item>\n" +
            "<item>\n" +
            "<title>\n" +
            "<![CDATA[ 中国弃朝后果严重 不只失去缓冲区 ]]>\n" +
            "</title>\n" +
            "<link>http://m.xinjunshi.net/a/20170420/10254484.html</link>\n" +
            "<description>\n" +
            "<![CDATA[\n" +
            "朝鲜半岛局势持续紧张，引起国际社会高度关注。对于如何化解朝核危局，国际社会一直存在着中国应承担更多责任等论调，国内则有一些人表现出厌朝情绪，甚至认为应果断弃朝。越是局势紧张的时候，越是需要对这些片面意见或违背我国外交理念和实践的主张予以反\n" +
            "]]>\n" +
            "</description>\n" +
            "<image>\n" +
            "http://m.xinjunshi.com/uploads/170420/15958-1F420150440432.jpg\n" +
            "</image>\n" +
            "<pubDate>2017-04-21</pubDate>\n" +
            "<category>幻灯</category>\n" +
            "<author>爆料君</author>\n" +
            "<comments>未知</comments>\n" +
            "</item>\n" +
            "<item>\n" +
            "<title>\n" +
            "<![CDATA[ 中国弃朝后果严重 不只失去缓冲区 ]]>\n" +
            "</title>\n" +
            "<link>http://m.xinjunshi.net/a/20170420/10254446.html</link>\n" +
            "<description>\n" +
            "<![CDATA[\n" +
            "朝鲜半岛局势持续紧张，引起国际社会高度关注。对于如何化解朝核危局，国际社会一直存在着中国应承担更多责任等论调，国内则有一些人表现出厌朝情绪，甚至认为应果断弃朝。越是局势紧张的时候，越是需要对这些片面意见或违背我国外交理念和实践的主张予以反\n" +
            "]]>\n" +
            "</description>\n" +
            "<image>\n" +
            "http://m.xinjunshi.com/uploads/170420/15958-1F420150440432.jpg\n" +
            "</image>\n" +
            "<pubDate>2017-04-20</pubDate>\n" +
            "<category>幻灯</category>\n" +
            "<author>xjs_lm</author>\n" +
            "<comments>未知</comments>\n" +
            "</item>\n" +
            "<item>\n" +
            "<title>\n" +
            "<![CDATA[ 转机出现？小弟断言美不对朝动武，川普躺枪 ]]>\n" +
            "</title>\n" +
            "<link>http://m.xinjunshi.net/a/20170420/10254391.html</link>\n" +
            "<description>\n" +
            "<![CDATA[\n" +
            "进入四月以来，半岛局势风云突变，大有一副愈演愈烈的态势。 当前，旨在针对朝鲜军事威胁的美韩联合军演举行正酣，反观朝鲜方面，小金同志同样也是很不消停。就在近日，朝鲜举行了史上最大规模的阅兵活动，高调宣扬其军事实力。美韩与朝鲜彼此不相让，可谓是\n" +
            "]]>\n" +
            "</description>\n" +
            "<image>\n" +
            "http://m.xinjunshi.com/uploads/allimg/170420/55086-1F4201255530-L.jpg\n" +
            "</image>\n" +
            "<pubDate>2017-04-20</pubDate>\n" +
            "<category>幻灯</category>\n" +
            "<author>镇宇</author>\n" +
            "<comments>未知</comments>\n" +
            "</item>\n" +
            "<item>\n" +
            "<title>\n" +
            "<![CDATA[ 普京送华大礼：中俄破裂不攻自破！ ]]>\n" +
            "</title>\n" +
            "<link>http://m.xinjunshi.net/a/20170420/10254368.html</link>\n" +
            "<description>\n" +
            "<![CDATA[\n" +
            "俄军指挥中心内部画面 俄罗斯联邦武装力量作战指挥中心是俄军全军除战略火箭军外的指挥管理中心，它可实时调度监控全军的训练演习，并在低烈度小规模冲突中，担负作战指挥功能。在局部战争或全面战争时，作战指挥将转入地下指挥中心。 就是这样一个重要指挥\n" +
            "]]>\n" +
            "</description>\n" +
            "<image>\n" +
            "http://m.xinjunshi.com/uploads/allimg/170420/21-1F4200Z6140-L.jpg\n" +
            "</image>\n" +
            "<pubDate>2017-04-20</pubDate>\n" +
            "<category>幻灯</category>\n" +
            "<author>爆料君</author>\n" +
            "<comments>未知</comments>\n" +
            "</item>\n" +
            "</channel>\n" +
            "</rss>";

}













