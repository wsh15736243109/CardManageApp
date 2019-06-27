package com.itboye.cardmanage.model;

import android.app.Application;
import android.support.annotation.NonNull;
import me.goldze.mvvmhabit.base.BaseViewModel;

import java.io.Serializable;

public class CardManageModel implements Serializable {


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
}
