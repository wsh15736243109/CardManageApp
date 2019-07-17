package com.itboye.cardmanage.bean;

public class UserBalanceBean {

    /**
     * self_wallet : 6.09
     * zmf_wallet : 6.15
     * withdrawAmount : 6.09
     */

    private double self_wallet;
    private double zmf_wallet;
    private double withdrawAmount;

    public double getSelf_wallet() {
        return self_wallet;
    }

    public void setSelf_wallet(double self_wallet) {
        this.self_wallet = self_wallet;
    }

    public double getZmf_wallet() {
        return zmf_wallet;
    }

    public void setZmf_wallet(double zmf_wallet) {
        this.zmf_wallet = zmf_wallet;
    }

    public double getWithdrawAmount() {
        return withdrawAmount;
    }

    public void setWithdrawAmount(double withdrawAmount) {
        this.withdrawAmount = withdrawAmount;
    }
}
