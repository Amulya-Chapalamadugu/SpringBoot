package com.love2code.aopdemo.dao;

import com.love2code.aopdemo.Account;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AccountDAOImpl implements AccountDAO{

    private String name;
    private String serviceCode;

    public String getName() {
        System.out.println("In get name");
        return name;
    }

    public void setName(String name) {
        System.out.println("In set name");
        this.name = name;
    }

    public String getServiceCode() {
        System.out.println("In get service code");
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        System.out.println("In set service code");
        this.serviceCode = serviceCode;
    }

    @Override
    public List<Account> findAccounts() {
        /*List<Account> myAccounts = new ArrayList<>();

        //create sample accounts
        Account acc1 = new Account("Abhi","Gold");
        Account acc2 = new Account("Ajay","Platinum");
        Account acc3 = new Account("Anu","Silver");

        //add them to the list

        myAccounts.add(acc1);
        myAccounts.add(acc2);
        myAccounts.add(acc3);

        return myAccounts;*/
        return findAccounts(false);
    }

    @Override
    public List<Account> findAccounts(boolean tripWire) {

        //for academic purposes simulate an exception
        if(tripWire)
            throw new RuntimeException("No soup For you");


        List<Account> myAccounts = new ArrayList<>();

        //create sample accounts
        Account acc1 = new Account("Abhi","Gold");
        Account acc2 = new Account("Ajay","Platinum");
        Account acc3 = new Account("Anu","Silver");

        //add them to the list

        myAccounts.add(acc1);
        myAccounts.add(acc2);
        myAccounts.add(acc3);

        return myAccounts;

    }
    @Override
    public void addAccount(Account theAccount, boolean vipFlag) {
        System.out.println(getClass()+ ": Doing my work at db");
    }

    @Override
    public boolean doWork() {

        System.out.println(getClass()+": doWork()");
        return false;
    }
}
