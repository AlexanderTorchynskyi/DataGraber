package ua.torrino;
import me.postaddict.instagram.scraper.AnonymousInsta;

import me.postaddict.instagram.scraper.model.Account;

import java.io.File;
import java.io.IOException;
import java.util.List;


public class Main {

    private static Account account;
    private static AnonymousInsta client;
    private static ExcelBasicUserInfo excelBasicUserInfo;
    private static ExcelUserMedia excelUserMedia;

    public static void main(String[] args) throws IOException, NoSuchFieldException, IllegalAccessException {

        client = AnnonInstance.getInstance();
        client.basePage();

        UserNameScan userNameScan = new UserNameScan();
        String logName = userNameScan.scan();
       // String logName = "alexander_tor";

        if(userNameScan.checkAccount()) {
            account = client.getAccountByUsername(logName);
//            System.out.println(client.getLocationMediasById("",));
            excelBasicUserInfo = new ExcelBasicUserInfo(new File("DataGraber","DataGraber.xlsx"),account);
            excelBasicUserInfo.writeInFile();
            excelBasicUserInfo.readFromFile();

            excelUserMedia = new ExcelUserMedia(new File("DataGraberMedia","DataGraberMedia.xlsx"),client,logName);
            excelUserMedia.writeInFile();
            excelUserMedia.readFromFile();
            System.err.println(excelBasicUserInfo.getBasicMap());
            //System.out.println(excelBasicUserInfo.getBasicInfoMap().values());
            System.out.println( excelUserMedia.getCaptionsFromFile());
            System.out.println(excelUserMedia.getMediaURLsFromFile());

//
//            System.out.println("douchebag id:\t" + account.id);
//            System.out.println("douchebag name:\t" + account.username);
//            System.out.println("douchebag biography:\t" + account.biography);
//            System.out.println("douchebag ugly avatar url:\t" + account.profilePicUrl);
//            System.out.println("The ugly media of douchebag");
//            List<Media> medias = client.getMedias(logName, account.mediaCount);
//            for (int i = 0; i < account.mediaCount; i++)
//                System.out.println(i + ": " + medias.get(i).imageUrls.high);
        }
    }
}
