package com.itboye.cardmanage.bean;

import java.util.ArrayList;
import java.util.List;

public class HomeBean {

    private List<NoticeBean> notice;
    private List<ApplyCardBean> apply_card;
    private List<ApplyCardBean> carousel;
    private List<ApplyCardBean> lend;
    private List<ApplyCardBean> homeTop = new ArrayList<>();

    public List<ApplyCardBean> getHomeTop() {
        return homeTop;
    }

    public void setHomeTop(List<ApplyCardBean> homeTop) {
        this.homeTop = homeTop;
    }

    public List<NoticeBean> getNotice() {
        return notice;
    }

    public void setNotice(List<NoticeBean> notice) {
        this.notice = notice;
    }

    public List<ApplyCardBean> getApply_card() {
        return apply_card;
    }

    public void setApply_card(List<ApplyCardBean> apply_card) {
        this.apply_card = apply_card;
    }

    public List<ApplyCardBean> getCarousel() {
        return carousel;
    }

    public void setCarousel(List<ApplyCardBean> carousel) {
        this.carousel = carousel;
    }

    public List<ApplyCardBean> getLend() {
        return lend;
    }

    public void setLend(List<ApplyCardBean> lend) {
        this.lend = lend;
    }

    public static class NoticeBean {
        /**
         * content : 1111
         * id : 5
         * dtreeType : announce
         * title : 1111
         * summary : 1111
         * createTime : 1561709556
         * extra : 1111
         */

        private String content;
        private String id;
        private String dtreeType;
        private String title;
        private String summary;
        private String createTime;
        private String extra;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getDtreeType() {
            return dtreeType;
        }

        public void setDtreeType(String dtreeType) {
            this.dtreeType = dtreeType;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getExtra() {
            return extra;
        }

        public void setExtra(String extra) {
            this.extra = extra;
        }
    }

    public static class ApplyCardBean {
        /**
         * id : 21
         * jump_type : url
         * jump_url : www.baidu.com
         * img_url : http://img.361fit.cn/uploads/banner/20190627/0913055d1488a148937.png
         * w : 100
         * h : 100
         */
        public ApplyCardBean() {

        }

        public ApplyCardBean(String title, int img_res, String jump_type, String jump_url) {
            this.setTitle(title);
            this.setImg_res(img_res);
            this.setJump_type(jump_type);
            this.setJump_url(jump_url);
        }

        private int id;
        private String jump_type;
        private String jump_url;
        private String img_url;
        private int img_res;
        private String title;
        private int w;
        private int h;

        public int getImg_res() {
            return img_res;
        }

        public void setImg_res(int img_res) {
            this.img_res = img_res;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getJump_type() {
            return jump_type;
        }

        public void setJump_type(String jump_type) {
            this.jump_type = jump_type;
        }

        public String getJump_url() {
            return jump_url;
        }

        public void setJump_url(String jump_url) {
            this.jump_url = jump_url;
        }

        public String getImg_url() {
            return img_url;
        }

        public void setImg_url(String img_url) {
            this.img_url = img_url;
        }

        public int getW() {
            return w;
        }

        public void setW(int w) {
            this.w = w;
        }

        public int getH() {
            return h;
        }

        public void setH(int h) {
            this.h = h;
        }
    }

}
