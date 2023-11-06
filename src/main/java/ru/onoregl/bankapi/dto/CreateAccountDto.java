package ru.onoregl.bankapi.dto;

public class CreateAccountDto {
    private  String userid;
    private double balance ;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
