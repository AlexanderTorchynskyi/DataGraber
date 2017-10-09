package ua.torrino;


import java.util.Scanner;

class UserNameScan {
    private AccountChecker accountChecker;
    private String LoggName;

    UserNameScan() {  }

        String scan(){
        System.out.println("Hello little douchebag. Give me the fucking username of this bitch you want to have in a dock");
        System.out.print("Account Log: ");
        Scanner in = new Scanner(System.in);
        LoggName = in.nextLine();
        System.out.println("Gotcha");
        System.out.println(LoggName);
        return LoggName;
    }
    public String getLoggName() {
        return LoggName;
    }
    boolean checkAccount(){
        accountChecker = new AccountChecker(LoggName);
        return accountChecker.checkExistence() && accountChecker.isNotPrivate();
    }
}
