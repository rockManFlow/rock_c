package com.kuark.tool.base.vo;

/**
 * Created by caoqingyuan on 2017/3/7.
 */
public class HxbCheckFilePojo {
    //tran_date,account_flow_no，tran_type,amount,card_no,user_name,bank_name,balance,abstracts,tran_time
    private String tranDate;
    private String accountFlowNo;
    private String tranType;
    private String amount;
    private String cardNo;
    private String userName;
    private String bankName;
    private String balance;
    private String abstracts;
    private String tranTime;

    public String getAbstracts() {
        return abstracts;
    }

    public void setAbstracts(String abstracts) {
        this.abstracts = abstracts;
    }

    public String getAccountFlowNo() {
        return accountFlowNo;
    }

    public void setAccountFlowNo(String accountFlowNo) {
        this.accountFlowNo = accountFlowNo;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getTranDate() {
        return tranDate;
    }

    public void setTranDate(String tranDate) {
        this.tranDate = tranDate;
    }

    public String getTranType() {
        return tranType;
    }

    public void setTranType(String tranType) {
        this.tranType = tranType;
    }

    public String getTranTime() {
        return tranTime;
    }

    public void setTranTime(String tranTime) {
        this.tranTime = tranTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
