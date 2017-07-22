package comuxi.example.administrator.panda_channel.mode.Panda_TextBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/7/17.
 */

public class Chian_item_Path_TextBean implements Serializable {

    private List<LiveBean> live;

    public List<LiveBean> getLive() {
        return live;
    }

    public void setLive(List<LiveBean> live) {
        this.live = live;
    }

    public static class LiveBean implements Serializable{
        /**
         * title : 迷魂台
         * brief : 张家界风景区位于湖南省张家界市，武陵源风景名胜区南部，这里的异峰巧石、山泉飞瀑、深林奇树及珍禽异兽共同组成了一幅幅相映成趣、妩媚动人的自然绝景。
         * image : http://p1.img.cctvpic.com/photoAlbum/page/performance/img/2016/9/9/1473408290877_412.jpg
         * id : zjjmht
         * order : 1
         */

        private String title;
        private String brief;
        private String image;
        private String id;
        private String order;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getBrief() {
            return brief;
        }

        public void setBrief(String brief) {
            this.brief = brief;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getOrder() {
            return order;
        }

        public void setOrder(String order) {
            this.order = order;
        }
    }
}
