package com.itboye.cardmanage.bean;

import java.util.List;

public class BannerBean {

    /**
     * count : 3
     * list : [{"id":19,"position":"002001002","uid":"2","create_time":"1561630141","update_time":"1561630141","sort":1,"start_time":"1561630131","end_time":"1561630136","jump_url":"www.baidu.com","img_url":"http://img.361fit.cn/uploads/banner/20190627/0913055d1488a148937.png","title":"tset3","jump_type":"url","w":100,"h":100,"click_nums":""},{"id":22,"position":"002001002","uid":"2","create_time":"1561964825","update_time":"1561964825","sort":1,"start_time":"1561964811000","end_time":"1561964813000","jump_url":"www.baidu.com","img_url":"http://img.361fit.cn/uploads/banner/20190627/0914375d1488fd5fa2c.png","title":"test111","jump_type":"url","w":100,"h":100,"click_nums":""},{"id":24,"position":"002001002","uid":"2","create_time":"1561965288","update_time":"1561965288","sort":1,"start_time":"1561965275000","end_time":"1561965278000","jump_url":"www.baidu.com","img_url":"http://img.361fit.cn/uploads/banner/20190627/0944425d14900a8c2ab.png","title":"test13","jump_type":"url","w":100,"h":100,"click_nums":""}]
     */

    private String count;
    private List<ListBean> list;

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : 19
         * position : 002001002
         * uid : 2
         * create_time : 1561630141
         * update_time : 1561630141
         * sort : 1
         * start_time : 1561630131
         * end_time : 1561630136
         * jump_url : www.baidu.com
         * img_url : http://img.361fit.cn/uploads/banner/20190627/0913055d1488a148937.png
         * title : tset3
         * jump_type : url
         * w : 100
         * h : 100
         * click_nums :
         */

        private int id;
        private String position;
        private String uid;
        private String create_time;
        private String update_time;
        private int sort;
        private String start_time;
        private String end_time;
        private String jump_url;
        private String img_url;
        private String title;
        private String jump_type;
        private int w;
        private int h;
        private String click_nums;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(String update_time) {
            this.update_time = update_time;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public String getStart_time() {
            return start_time;
        }

        public void setStart_time(String start_time) {
            this.start_time = start_time;
        }

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
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

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getJump_type() {
            return jump_type;
        }

        public void setJump_type(String jump_type) {
            this.jump_type = jump_type;
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

        public String getClick_nums() {
            return click_nums;
        }

        public void setClick_nums(String click_nums) {
            this.click_nums = click_nums;
        }
    }
}
