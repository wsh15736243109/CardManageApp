package com.itboye.cardmanage.bean;

import java.util.List;

public class NoticeBean {

    /**
     * list : [{"content":"test2","msgStatus":"0","toUid":"-2","id":"4","dtreeType":"announce","title":"test2","summary":"test2","createTime":"1561535719","fromUid":"3","sendTime":"1561535703012","extra":"test2"}]
     * count : 1
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
         * content : test2
         * msgStatus : 0
         * toUid : -2
         * id : 4
         * dtreeType : announce
         * title : test2
         * summary : test2
         * createTime : 1561535719
         * fromUid : 3
         * sendTime : 1561535703012
         * extra : test2
         */

        private String content;
        private String msgStatus;
        private String toUid;
        private String id;
        private String dtreeType;
        private String title;
        private String summary;
        private String createTime;
        private String fromUid;
        private String sendTime;
        private String extra;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getMsgStatus() {
            return msgStatus;
        }

        public void setMsgStatus(String msgStatus) {
            this.msgStatus = msgStatus;
        }

        public String getToUid() {
            return toUid;
        }

        public void setToUid(String toUid) {
            this.toUid = toUid;
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

        public String getFromUid() {
            return fromUid;
        }

        public void setFromUid(String fromUid) {
            this.fromUid = fromUid;
        }

        public String getSendTime() {
            return sendTime;
        }

        public void setSendTime(String sendTime) {
            this.sendTime = sendTime;
        }

        public String getExtra() {
            return extra;
        }

        public void setExtra(String extra) {
            this.extra = extra;
        }
    }
}
