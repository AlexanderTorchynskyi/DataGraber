package ua.torrino;

import me.postaddict.instagram.scraper.domain.Account;

import java.io.IOException;


 class AccountChecker {

    private String LoggIn;

     AccountChecker(String LoggIn){
        this.LoggIn = LoggIn;
    }

   boolean checkExistence(){
       return check();
    }

    boolean isNotPrivate(){
        Account account = new Account();
        return !account.isPrivate;
    }

    private boolean check(){
        try {
            AnnonInstance.getInstance().getAccountByUsername(LoggIn);
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return  false;
        }
        return true;
    }
}
