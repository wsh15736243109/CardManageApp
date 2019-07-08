package com.itboye.cardmanage.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CardManageModel implements Serializable {


    /**
     * id : 7
     * days : 2
     * total_fee : 291
     * plan_status : running
     * prestore_money : 10291
     * prestore_card_id : 54
     * prestore_card_info : {"_entity_version":1,"id":54,"uid":54,"card_type":2,"card_no":"6217856200041637567","opening_bank":"中国银行","branch_bank":"中国银行股份有限公司湖州南街支行","name":"吴锦波","id_no":"350582199402232051","mobile":"18368041445","create_time":1562038143,"update_time":1562551504,"card_usage":2,"card_code":"","verify":1,"front_img":"http:\/\/img.361fit.cn\/uploads\/id_card\/20190702\/0326255d1acee18df10.jpg","master":1,"front_img_id":"20190702-1124-75f409ee-c39e-42bf-bba7-4a73475233ce","bill_date":0,"repayment_date":0,"cvn2":"","expire_date":"","status":1,"branch_no":"104336010016","pay_agree_id":"0e2607b2084247feac74bb767c82ec6e","withdraw_agree_id":"e35a1e69868f4491b743567af1c73144"}
     * next_proc_time : 1562642757
     * next_proc_day : 20190709
     * money : 10000
     * repay_total_money : 20000
     * repay_count : 0
     * failed_log :
     */

    private int days;
    private double total_fee;
    private String plan_status;
    private double prestore_money;
    private String prestore_card_id;
    private String prestore_card_info;
    private String next_proc_time;
    private String next_proc_day;
    private double money;
    private double repay_total_money;
    private String repay_count;
    private String failed_log;
    /**
     * id : 4
     * plan_id : 6
     * card_id : 56
     * card_bill_day : 19
     * card_repayment_day : 1
     * deposit_money : 0
     * withdraw_money : 0
     */

    private String plan_id;
    private String card_id;
    private int card_bill_day;
    private int card_repayment_day;
    private double deposit_money;
    private double withdraw_money;


    public String getChooseType() {
        return chooseType;
    }

    public void setChooseType(String chooseType) {
        this.chooseType = chooseType;
    }

    /**
     * id : 5
     * uid : 13
     * card_type : 2
     * card_no : 6222********02
     * opening_bank : 杭州西湖
     * branch_bank : 杭州西湖
     * name : 周波
     * id_no : 3303****039
     * mobile : 138****6033
     * create_time : 1559375903
     * update_time : 1560752786
     * card_usage : 3
     * card_code :
     * verify : -1
     * front_img : http://www.baidu.com
     * master : 0
     * front_img_id : 20190603-1502-7a525013-44af-46cd-93ea-5e0dae61596c
     * bill_date : 0
     * repayment_date : 0
     * cvn2 : qt0zDIpnYT4=
     * expire_date :
     * status : 1
     * branch_no : 104****077
     * pay_agree_id :
     */

    String chooseType = "1";
    boolean isCheck;

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    private String id;
    private String uid;
    private int card_type;
    private String card_no;
    private String opening_bank;
    private String branch_bank;
    private String name;
    private String id_no;
    private String mobile;
    private String create_time;
    private String update_time;
    private int card_usage;
    private String card_code;
    private int verify;
    private String front_img;
    private int master;
    private String front_img_id;
    private String bill_date;
    private String repayment_date;
    private String cvn2;
    private String expire_date;
    private int status;
    private String branch_no;
    private String pay_agree_id;
    private String withdraw_agree_id;



    public String getWithdraw_agree_id() {
        return withdraw_agree_id;
    }

    public void setWithdraw_agree_id(String withdraw_agree_id) {
        this.withdraw_agree_id = withdraw_agree_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getCard_type() {
        return card_type;
    }

    public void setCard_type(int card_type) {
        this.card_type = card_type;
    }

    public String getCard_no() {
        return card_no;
    }

    public void setCard_no(String card_no) {
        this.card_no = card_no;
    }

    public String getOpening_bank() {
        return opening_bank;
    }

    public void setOpening_bank(String opening_bank) {
        this.opening_bank = opening_bank;
    }

    public String getBranch_bank() {
        return branch_bank;
    }

    public void setBranch_bank(String branch_bank) {
        this.branch_bank = branch_bank;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId_no() {
        return id_no;
    }

    public void setId_no(String id_no) {
        this.id_no = id_no;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
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

    public int getCard_usage() {
        return card_usage;
    }

    public void setCard_usage(int card_usage) {
        this.card_usage = card_usage;
    }

    public String getCard_code() {
        return card_code;
    }

    public void setCard_code(String card_code) {
        this.card_code = card_code;
    }

    public int getVerify() {
        return verify;
    }

    public void setVerify(int verify) {
        this.verify = verify;
    }

    public String getFront_img() {
        return front_img;
    }

    public void setFront_img(String front_img) {
        this.front_img = front_img;
    }

    public int getMaster() {
        return master;
    }

    public void setMaster(int master) {
        this.master = master;
    }

    public String getFront_img_id() {
        return front_img_id;
    }

    public void setFront_img_id(String front_img_id) {
        this.front_img_id = front_img_id;
    }

    public String getBill_date() {
        return bill_date;
    }

    public void setBill_date(String bill_date) {
        this.bill_date = bill_date;
    }

    public String getRepayment_date() {
        return repayment_date;
    }

    public void setRepayment_date(String repayment_date) {
        this.repayment_date = repayment_date;
    }

    public String getCvn2() {
        return cvn2;
    }

    public void setCvn2(String cvn2) {
        this.cvn2 = cvn2;
    }

    public String getExpire_date() {
        return expire_date;
    }

    public void setExpire_date(String expire_date) {
        this.expire_date = expire_date;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getBranch_no() {
        return branch_no;
    }

    public void setBranch_no(String branch_no) {
        this.branch_no = branch_no;
    }

    public String getPay_agree_id() {
        return pay_agree_id;
    }

    public void setPay_agree_id(String pay_agree_id) {
        this.pay_agree_id = pay_agree_id;
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

    public String getPrestore_card_id() {
        return prestore_card_id;
    }

    public void setPrestore_card_id(String prestore_card_id) {
        this.prestore_card_id = prestore_card_id;
    }

    public String getPrestore_card_info() {
        return prestore_card_info;
    }

    public void setPrestore_card_info(String prestore_card_info) {
        this.prestore_card_info = prestore_card_info;
    }

    public String getNext_proc_time() {
        return next_proc_time;
    }

    public void setNext_proc_time(String next_proc_time) {
        this.next_proc_time = next_proc_time;
    }

    public String getNext_proc_day() {
        return next_proc_day;
    }

    public void setNext_proc_day(String next_proc_day) {
        this.next_proc_day = next_proc_day;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public double getRepay_total_money() {
        return repay_total_money;
    }

    public void setRepay_total_money(double repay_total_money) {
        this.repay_total_money = repay_total_money;
    }

    public String getRepay_count() {
        return repay_count;
    }

    public void setRepay_count(String repay_count) {
        this.repay_count = repay_count;
    }

    public String getFailed_log() {
        return failed_log;
    }

    public void setFailed_log(String failed_log) {
        this.failed_log = failed_log;
    }


    public String getPlan_id() {
        return plan_id;
    }

    public void setPlan_id(String plan_id) {
        this.plan_id = plan_id;
    }

    public String getCard_id() {
        return card_id;
    }

    public void setCard_id(String card_id) {
        this.card_id = card_id;
    }

    public int getCard_bill_day() {
        return card_bill_day;
    }

    public void setCard_bill_day(int card_bill_day) {
        this.card_bill_day = card_bill_day;
    }

    public int getCard_repayment_day() {
        return card_repayment_day;
    }

    public void setCard_repayment_day(int card_repayment_day) {
        this.card_repayment_day = card_repayment_day;
    }

    public double getDeposit_money() {
        return deposit_money;
    }

    public void setDeposit_money(double deposit_money) {
        this.deposit_money = deposit_money;
    }

    public double getWithdraw_money() {
        return withdraw_money;
    }

    public void setWithdraw_money(double withdraw_money) {
        this.withdraw_money = withdraw_money;
    }
}
