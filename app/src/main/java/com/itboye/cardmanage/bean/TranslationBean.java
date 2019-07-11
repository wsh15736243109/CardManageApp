package com.itboye.cardmanage.bean;

public class TranslationBean {

    /**
     * id : 42
     * order_code : 13772019062506125911702682
     * amount : 2000
     * create_time : 1561443179
     * note : 20元订单测试
     * order_type : quick_pay_d0
     * notify_status : 0
     * withdraw_status : 0
     * suc_proc_status : 0
     * _pay_card_no : *6948
     * _withdraw_card_no : *2202
     */

    private int id;
    private String order_code;
    private int amount;
    private double sys_fee;
    private long create_time;
    private String note;
    private String order_type;
    private int notify_status;
    private int withdraw_status;
    private int suc_proc_status;
    private String _pay_card_no;
    private String _withdraw_card_no;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrder_code() {
        return order_code;
    }

    public void setOrder_code(String order_code) {
        this.order_code = order_code;
    }

    public double getSys_fee() {
        return sys_fee;
    }

    public void setSys_fee(double sys_fee) {
        this.sys_fee = sys_fee;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public long getCreate_time() {
        return create_time;
    }

    public void setCreate_time(long create_time) {
        this.create_time = create_time;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getOrder_type() {
        return order_type;
    }

    public void setOrder_type(String order_type) {
        this.order_type = order_type;
    }

    public int getNotify_status() {
        return notify_status;
    }

    public void setNotify_status(int notify_status) {
        this.notify_status = notify_status;
    }

    public int getWithdraw_status() {
        return withdraw_status;
    }

    public void setWithdraw_status(int withdraw_status) {
        this.withdraw_status = withdraw_status;
    }

    public int getSuc_proc_status() {
        return suc_proc_status;
    }

    public void setSuc_proc_status(int suc_proc_status) {
        this.suc_proc_status = suc_proc_status;
    }

    public String get_pay_card_no() {
        return _pay_card_no;
    }

    public void set_pay_card_no(String _pay_card_no) {
        this._pay_card_no = _pay_card_no;
    }

    public String get_withdraw_card_no() {
        return _withdraw_card_no;
    }

    public void set_withdraw_card_no(String _withdraw_card_no) {
        this._withdraw_card_no = _withdraw_card_no;
    }
}
