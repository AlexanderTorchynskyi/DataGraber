package ua.torrino;


import me.postaddict.instagram.scraper.domain.Account;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelWritter {
    private File file;
    private Account account;

    public ExcelWritter(File file, Account account) {
        this.file = file;
        this.account = account;
    }

    public boolean writeInFile() {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Data from Account");
        Object[][] data = {
                {"douchebag id", "douchebag name", "douchebag biograph","douchebag ugly avatar url","The ugly media of douchebag"},
                {account.id, account.fullName, account.biography, account.profilePicUrl,account, "null"}
        };

        int rowNum = 0;
        System.out.println("Creating excel");

        for (Object[] datum : data) {
            Row row = sheet.createRow(rowNum++);
            int colNum = 0;
            for (Object field : datum) {
                Cell cell = row.createCell(colNum++);
                if (field instanceof String) {
                    cell.setCellValue((String) field);
                } else if (field instanceof Long) {
                    cell.setCellValue((Long) field);
                }
            }
        }

        try {
            FileOutputStream outputStream = new FileOutputStream(file.getName());
            workbook.write(outputStream);
            workbook.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Done");
        return true;
    }
}

