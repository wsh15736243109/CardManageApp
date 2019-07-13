package com.itboye.cardmanage.bean;

public class CardBankInfo {

    /**
     * validated : 1
     * bank : CZBANK
     * bankName : 浙商银行
     * bankImg : https://apimg.alipay.com/combo.png?d=cashier&t=CZBANK
     * cardType : DC
     * cardTypeName : 储蓄卡
     */

    private int validated;
    private String bank;
    private String bankName;
    private String bankImg;
    private String cardType;
    private String cardTypeName;

    public int getValidated() {
        return validated;
    }

    public void setValidated(int validated) {
        this.validated = validated;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankImg() {
        return bankImg;
    }

    public void setBankImg(String bankImg) {
        this.bankImg = bankImg;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCardTypeName() {
        return cardTypeName;
    }

    public void setCardTypeName(String cardTypeName) {
        this.cardTypeName = cardTypeName;
    }
}
