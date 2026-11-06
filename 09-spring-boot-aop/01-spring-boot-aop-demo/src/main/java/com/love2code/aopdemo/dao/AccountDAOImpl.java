package com.love2code.aopdemo.dao;

import com.love2code.aopdemo.Account;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDAOImpl implements AccountDAO{

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
