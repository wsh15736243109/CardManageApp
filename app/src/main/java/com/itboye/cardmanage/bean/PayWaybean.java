package com.itboye.cardmanage.bean;

import java.util.List;

public class PayWaybean {

    /**
     * id : 1
     * title : 百货服务
     * fee_per : 0.50
     * fixed_fee : 1.00
     * day_time_start : 0
     * day_time_end : 2359
     * single_limit_money : 20000
     * single_card_day_limit_money : 20000
     * day_limit_money : 50000
     * channel_code : 487000
     * channel_agent : zmf
     * _grade : [{"id":1,"title":"普通会员","fee_per":"0.60","fixed_fee":"2.00","day_time_start":"0","day_time_end":"2359","grade_code":"1","channel_id":"1","channel_code":"487000","start_amt":1,"end_amt":10000},{"id":2,"title":"VIP会员","fee_per":"0.50","fixed_fee":"1.00","day_time_start":"0","day_time_end":"2359","grade_code":"2","channel_id":"1","channel_code":"487000","start_amt":1,"end_amt":10000}]
     */

    private int id;
    private String title;
    private String fee_per;
    private String fixed_fee;
    private String day_time_start;
    private String day_time_end;
    private int single_limit_money;
    private int single_card_day_limit_money;
    private int day_limit_money;
    private String channel_code;
    private String channel_agent;
    private List<GradeBean> _grade;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFee_per() {
        return fee_per;
    }

    public void setFee_per(String fee_per) {
        this.fee_per = fee_per;
    }

    public String getFixed_fee() {
        return fixed_fee;
    }

    public void setFixed_fee(String fixed_fee) {
        this.fixed_fee = fixed_fee;
    }

    public String getDay_time_start() {
        return day_time_start;
    }

    public void setDay_time_start(String day_time_start) {
        this.day_time_start = day_time_start;
    }

    public String getDay_time_end() {
        return day_time_end;
    }

    public void setDay_time_end(String day_time_end) {
        this.day_time_end = day_time_end;
    }

    public int getSingle_limit_money() {
        return single_limit_money;
    }

    public void setSingle_limit_money(int single_limit_money) {
        this.single_limit_money = single_limit_money;
    }

    public int getSingle_card_day_limit_money() {
        return single_card_day_limit_money;
    }

    public void setSingle_card_day_limit_money(int single_card_day_limit_money) {
        this.single_card_day_limit_money = single_card_day_limit_money;
    }

    public int getDay_limit_money() {
        return day_limit_money;
    }

    public void setDay_limit_money(int day_limit_money) {
        this.day_limit_money = day_limit_money;
    }

    public String getChannel_code() {
        return channel_code;
    }

    public void setChannel_code(String channel_code) {
        this.channel_code = channel_code;
    }

    public String getChannel_agent() {
        return channel_agent;
    }

    public void setChannel_agent(String channel_agent) {
        this.channel_agent = channel_agent;
    }

    public List<GradeBean> get_grade() {
        return _grade;
    }

    public void set_grade(List<GradeBean> _grade) {
        this._grade = _grade;
    }

    public static class GradeBean {
        /**
         * id : 1
         * title : 普通会员
         * fee_per : 0.60
         * fixed_fee : 2.00
         * day_time_start : 0
         * day_time_end : 2359
         * grade_code : 1
         * channel_id : 1
         * channel_code : 487000
         * start_amt : 1
         * end_amt : 10000
         */

        private int id;
        private String title;
        private double fee_per;
        private String fixed_fee;
        private String day_time_start;
        private String day_time_end;
        private String grade_code;
        private String channel_id;
        private String channel_code;
        private int start_amt;
        private int end_amt;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public double getFee_per() {
            return fee_per;
        }

        public void setFee_per(double fee_per) {
            this.fee_per = fee_per;
        }

        public String getFixed_fee() {
            return fixed_fee;
        }

        public void setFixed_fee(String fixed_fee) {
            this.fixed_fee = fixed_fee;
        }

        public String getDay_time_start() {
            return day_time_start;
        }

        public void setDay_time_start(String day_time_start) {
            this.day_time_start = day_time_start;
        }

        public String getDay_time_end() {
            return day_time_end;
        }

        public void setDay_time_end(String day_time_end) {
            this.day_time_end = day_time_end;
        }

        public String getGrade_code() {
            return grade_code;
        }

        public void setGrade_code(String grade_code) {
            this.grade_code = grade_code;
        }

        public String getChannel_id() {
            return channel_id;
        }

        public void setChannel_id(String channel_id) {
            this.channel_id = channel_id;
        }

        public String getChannel_code() {
            return channel_code;
        }

        public void setChannel_code(String channel_code) {
            this.channel_code = channel_code;
        }

        public int getStart_amt() {
            return start_amt;
        }

        public void setStart_amt(int start_amt) {
            this.start_amt = start_amt;
        }

        public int getEnd_amt() {
            return end_amt;
        }

        public void setEnd_amt(int end_amt) {
            this.end_amt = end_amt;
        }
    }
}
