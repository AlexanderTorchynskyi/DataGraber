package ua.torrino;


import me.postaddict.instagram.scraper.domain.Account;
import org.apache.poi.xssf.usermodel.*;
import java.io.File;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;


public class ExcelBasicUserInfo extends Excel {
    private File file;
    private Account account;

    public ExcelBasicUserInfo(File file, Account account) {
        this.file = file;
        this.account = account;
    }

    private String getField(Account account, Field fields) throws NoSuchFieldException, IllegalAccessException {
        String accountValue = null;
        try {
            accountValue = account.getClass().getField(fields.getName()).get(account).toString();
        } catch (NullPointerException e) {
            accountValue = "empty";
        }
        return accountValue;
    }

    public boolean writeInFile() throws IOException, NoSuchFieldException, IllegalAccessException {
        //Get the header properties for our excel file;
        Field[] header = account.getClass().getFields();

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet spreadsheet = workbook.createSheet("DataGraber");
        //Setting up widtha
        for(int i = 0; i< header.length;i++) {
            spreadsheet.setColumnWidth(i, 6000);
        }
        //Creating 2 rows for. The first for names of fields and the second is for values;
        XSSFRow row  = spreadsheet.createRow((short)0);
        for(int i  = 0; i<header.length; i++){
            row.createCell(i).setCellValue(header[i].getName());
        }
        row = spreadsheet.createRow((short)1);
        for (int i = 0;i<header.length;i++){
            row.createCell(i).setCellValue(getField(account,header[i]));
        }

        FileOutputStream out = new FileOutputStream(
                new File("DataGraberBasic.xlsx"));
        workbook.write(out);
        out.close();
        System.out.println("DataGraberBasic.xlsx written successfully");
        return true;
    }
    // Getting Data back;
    public boolean readFromFile() {
        return false;
    }
}

