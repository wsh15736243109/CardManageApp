package com.itboye.cardmanage.bean;

import com.itboye.cardmanage.model.CardManageModel;

import java.util.List;

public class RepaymentDetailBean {

    /**
     * _entity_version : 1
     * id : 6
     * uid : 54
     * days : 1
     * total_fee : 171
     * plan_status : running
     * prestore_money : 10171
     * prestore_card_id : 54
     * prestore_card_info : {"_entity_version":1,"id":54,"uid":54,"card_type":2,"card_no":"6217856200041637567","opening_bank":"中国银行","branch_bank":"中国银行股份有限公司湖州南街支行","name":"吴锦波","id_no":"350582199402232051","mobile":"18368041445","create_time":1562038143,"update_time":1562551504,"card_usage":2,"card_code":"","verify":1,"front_img":"http://img.361fit.cn/uploads/id_card/20190702/0326255d1acee18df10.jpg","master":1,"front_img_id":"20190702-1124-75f409ee-c39e-42bf-bba7-4a73475233ce","bill_date":0,"repayment_date":0,"cvn2":"","expire_date":"","status":1,"branch_no":"104336010016","pay_agree_id":"0e2607b2084247feac74bb767c82ec6e","withdraw_agree_id":"e35a1e69868f4491b743567af1c73144"}
     * create_time : 1562556344
     * update_time : 1562556344
     * next_proc_time : 1562642744
     * next_proc_day : 20190709
     * money : 10000
     * repay_total_money : 10000
     * repay_count : 0
     * failed_log :
     * _card : [{"id":4,"plan_id":"6","card_id":"56","card_no":"6225757544320758","card_bill_day":"19","card_repayment_day":"1","deposit_money":"0","withdraw_money":"0"}]
     */

    private int _entity_version;
    private int id;
    private int uid;
    private int days;
    private double total_fee;
    private double fee;
    private double balance;
    private String plan_status;
    private double prestore_money;
    private int prestore_card_id;
    private CardManageModel prestore_card_info;
    private int create_time;
    private int update_time;
    private int next_proc_time;
    private int next_proc_day;
    private double money;
    private int repay_total_money;
    private int repay_count;
    private String failed_log;
    private List<CardManageModel> _card;

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int get_entity_version() {
        return _entity_version;
    }

    public void set_entity_version(int _entity_version) {
        this._entity_version = _entity_version;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public double getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(double total_fee) {
        this.total_fee = total_fee;
    }

    public String getPlan_status() {
        return plan_status;
    }

    public void setPlan_status(String plan_status) {
        this.plan_status = plan_status;
    }

    public double getPrestore_money() {
        return prestore_money;
    }

    public void setPrestore_money(double prestore_money) {
        this.prestore_money = prestore_money;
    }

    public int getPrestore_card_id() {
        return prestore_card_id;
    }

    public void setPrestore_card_id(int prestore_card_id) {
        this.prestore_card_id = prestore_card_id;
    }

    public CardManageModel getPrestore_card_info() {
        return prestore_card_info;
    }

    public void setPrestore_card_info(CardManageModel prestore_card_info) {
        this.prestore_card_info = prestore_card_info;
    }

    public int getCreate_time() {
        return create_time;
    }

    public void setCreate_time(int create_time) {
        this.create_time = create_time;
    }

    public int getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(int update_time) {
        this.update_time = update_time;
    }

    public int getNext_proc_time() {
        return next_proc_time;
    }

    public void setNext_proc_time(int next_proc_time) {
        this.next_proc_time = next_proc_time;
    }

    public int getNext_proc_day() {
        return next_proc_day;
    }

    public void setNext_proc_day(int next_proc_day) {
        this.next_proc_day = next_proc_day;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public int getRepay_total_money() {
        return repay_total_money;
    }

    public void setRepay_total_money(int repay_total_money) {
        this.repay_total_money = repay_total_money;
    }

    public int getRepay_count() {
        return repay_count;
    }

    public void setRepay_count(int repay_count) {
        this.repay_count = repay_count;
    }

    public String getFailed_log() {
        return failed_log;
    }

    public void setFailed_log(String failed_log) {
        this.failed_log = failed_log;
    }

    public List<CardManageModel> get_card() {
        return _card;
    }

    public void set_card(List<CardManageModel> _card) {
        this._card = _card;
    }

//    public static class PrestoreCardInfoBean {
//        /**
//         * _entity_version : 1
//         * id : 54
//         * uid : 54
//         * card_type : 2
//         * card_no : 6217856200041637567
//         * opening_bank : 中国银行
//         * branch_bank : 中国银行股份有限公司湖州南街支行
//         * name : 吴锦波
//         * id_no : 350582199402232051
//         * mobile : 18368041445
//         * create_time : 1562038143
//         * update_time : 1562551504
//         * card_usage : 2
//         * card_code :
//         * verify : 1
//         * front_img : http://img.361fit.cn/uploads/id_card/20190702/0326255d1acee18df10.jpg
//         * master : 1
//         * front_img_id : 20190702-1124-75f409ee-c39e-42bf-bba7-4a73475233ce
//         * bill_date : 0
//         * repayment_date : 0
//         * cvn2 :
//         * expire_date :
//         * status : 1
//         * branch_no : 104336010016
//         * pay_agree_id : 0e2607b2084247feac74bb767c82ec6e
//         * withdraw_agree_id : e35a1e69868f4491b743567af1c73144
//         */
//
//        private int _entity_version;
//        private int id;
//        private int uid;
//        private int card_type;
//        private String card_no;
//        private String opening_bank;
//        private String branch_bank;
//        private String name;
//        private String id_no;
//        private String mobile;
//        private int create_time;
//        private int update_time;
//        private int card_usage;
//        private String card_code;
//        private int verify;
//        private String front_img;
//        private int master;
//        private String front_img_id;
//        private int bill_date;
//        private int repayment_date;
//        private String cvn2;
//        private String expire_date;
//        private int status;
//        private String branch_no;
//        private String pay_agree_id;
//        private String withdraw_agree_id;
//
//        public int get_entity_version() {
//            return _entity_version;
//        }
//
//        public void set_entity_version(int _entity_version) {
//            this._entity_version = _entity_version;
//        }
//
//        public int getId() {
//            return id;
//        }
//
//        public void setId(int id) {
//            this.id = id;
//        }
//
//        public int getUid() {
//            return uid;
//        }
//
//        public void setUid(int uid) {
//            this.uid = uid;
//        }
//
//        public int getCard_type() {
//            return card_type;
//        }
//
//        public void setCard_type(int card_type) {
//            this.card_type = card_type;
//        }
//
//        public String getCard_no() {
//            return card_no;
//        }
//
//        public void setCard_no(String card_no) {
//            this.card_no = card_no;
//        }
//
//        public String getOpening_bank() {
//            return opening_bank;
//        }
//
//        public void setOpening_bank(String opening_bank) {
//            this.opening_bank = opening_bank;
//        }
//
//        public String getBranch_bank() {
//            return branch_bank;
//        }
//
//        public void setBranch_bank(String branch_bank) {
//            this.branch_bank = branch_bank;
//        }
//
//        public String getName() {
//            return name;
//        }
//
//        public void setName(String name) {
//            this.name = name;
//        }
//
//        public String getId_no() {
//            return id_no;
//        }
//
//        public void setId_no(String id_no) {
//            this.id_no = id_no;
//        }
//
//        public String getMobile() {
//            return mobile;
//        }
//
//        public void setMobile(String mobile) {
//            this.mobile = mobile;
//        }
//
//        public int getCreate_time() {
//            return create_time;
//        }
//
//        public void setCreate_time(int create_time) {
//            this.create_time = create_time;
//        }
//
//        public int getUpdate_time() {
//            return update_time;
//        }
//
//        public void setUpdate_time(int update_time) {
//            this.update_time = update_time;
//        }
//
//        public int getCard_usage() {
//            return card_usage;
//        }
//
//        public void setCard_usage(int card_usage) {
//            this.card_usage = card_usage;
//        }
//
//        public String getCard_code() {
//            return card_code;
//        }
//
//        public void setCard_code(String card_code) {
//            this.card_code = card_code;
//        }
//
//        public int getVerify() {
//            return verify;
//        }
//
//        public void setVerify(int verify) {
//            this.verify = verify;
//        }
//
//        public String getFront_img() {
//            return front_img;
//        }
//
//        public void setFront_img(String front_img) {
//            this.front_img = front_img;
//        }
//
//        public int getMaster() {
//            return master;
//        }
//
//        public void setMaster(int master) {
//            this.master = master;
//        }
//
//        public String getFront_img_id() {
//            return front_img_id;
//        }
//
//        public void setFront_img_id(String front_img_id) {
//            this.front_img_id = front_img_id;
//        }
//
//        public int getBill_date() {
//            return bill_date;
//        }
//
//        public void setBill_date(int bill_date) {
//            this.bill_date = bill_date;
//        }
//
//        public int getRepayment_date() {
//            return repayment_date;
//        }
//
//        public void setRepayment_date(int repayment_date) {
//            this.repayment_date = repayment_date;
//        }
//
//        public String getCvn2() {
//            return cvn2;
//        }
//
//        public void setCvn2(String cvn2) {
//            this.cvn2 = cvn2;
//        }
//
//        public String getExpire_date() {
//            return expire_date;
//        }
//
//        public void setExpire_date(String expire_date) {
//            this.expire_date = expire_date;
//        }
//
//        public int getStatus() {
//            return status;
//        }
//
//        public void setStatus(int status) {
//            this.status = status;
//        }
//
//        public String getBranch_no() {
//            return branch_no;
//        }
//
//        public void setBranch_no(String branch_no) {
//            this.branch_no = branch_no;
//        }
//
//        public String getPay_agree_id() {
//            return pay_agree_id;
//        }
//
//        public void setPay_agree_id(String pay_agree_id) {
//            this.pay_agree_id = pay_agree_id;
//        }
//
//        public String getWithdraw_agree_id() {
//            return withdraw_agree_id;
//        }
//
//        public void setWithdraw_agree_id(String withdraw_agree_id) {
//            this.withdraw_agree_id = withdraw_agree_id;
//        }
//    }

//    public static class CardBean {
//        /**
//         * id : 4
//         * plan_id : 6
//         * card_id : 56
//         * card_no : 6225757544320758
//         * card_bill_day : 19
//         * card_repayment_day : 1
//         * deposit_money : 0
//         * withdraw_money : 0
//         */
//
//        private int id;
//        private String plan_id;
//        private String card_id;
//        private String card_no;
//        private String card_bill_day;
//        private String card_repayment_day;
//        private String deposit_money;
//        private String withdraw_money;
//
//        public int getId() {
//            return id;
//        }
//
//        public void setId(int id) {
//            this.id = id;
//        }
//
//        public String getPlan_id() {
//            return plan_id;
//        }
//
//        public void setPlan_id(String plan_id) {
//            this.plan_id = plan_id;
//        }
//
//        public String getCard_id() {
//            return card_id;
//        }
//
//        public void setCard_id(String card_id) {
//            this.card_id = card_id;
//        }
//
//        public String getCard_no() {
//            return card_no;
//        }
//
//        public void setCard_no(String card_no) {
//            this.card_no = card_no;
//        }
//
//        public String getCard_bill_day() {
//            return card_bill_day;
//        }
//
//        public void setCard_bill_day(String card_bill_day) {
//            this.card_bill_day = card_bill_day;
//        }
//
//        public String getCard_repayment_day() {
//            return card_repayment_day;
//        }
//
//        public void setCard_repayment_day(String card_repayment_day) {
//            this.card_repayment_day = card_repayment_day;
//        }
//
//        public String getDeposit_money() {
//            return deposit_money;
//        }
//
//        public void setDeposit_money(String deposit_money) {
//            this.deposit_money = deposit_money;
//        }
//
//        public String getWithdraw_money() {
//            return withdraw_money;
//        }
//
//        public void setWithdraw_money(String withdraw_money) {
//            this.withdraw_money = withdraw_money;
//        }
//    }
}
