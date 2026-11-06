package com.love2code.aopdemo.dao;

import org.springframework.stereotype.Repository;

@Repository
public class MembershipDAOImpl implements MembershipDAO{
    @Override
    public boolean addAccount() {

        System.out.println(getClass()+ ": Doing my work at db : Adding membership account");
        return  true;
    }

    @Override
    public void goToSleep() {
        System.out.println(getClass()+ ": I'm going to sleep now, See yall");
    }
}
