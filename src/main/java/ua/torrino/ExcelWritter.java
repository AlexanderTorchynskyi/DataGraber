package ua.torrino;


import me.postaddict.instagram.scraper.domain.Account;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.*;

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

    public boolean writeInFile() throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet spreadsheet = workbook.createSheet("DataGraber");
        XSSFCellStyle style1 = workbook.createCellStyle();

        for(int i = 0; i< 8;i++) {
            spreadsheet.setColumnWidth(i, 4000);
        }
        spreadsheet.setColumnWidth(4, 8000);

        XSSFRow row = spreadsheet.createRow((short) 0);
        XSSFCell cell = (XSSFCell) row.createCell((short) 0);
        cell.setCellValue("user_id");
        row = spreadsheet.createRow((short) 1);
        row.createCell(0).setCellValue(account.id);

        row = spreadsheet.getRow(0);
        row.createCell(1).setCellValue("user_name");
        row = spreadsheet.getRow(1);
        row.createCell(1).setCellValue(account.username);

        row = spreadsheet.getRow(0);
        row.createCell(2).setCellValue("full_name");
        row = spreadsheet.getRow(1);
        row.createCell(2).setCellValue(account.fullName);

        row = spreadsheet.getRow(0);
        row.createCell(3).setCellValue("bio");
        row = spreadsheet.getRow(1);
        cell = (XSSFCell) row.createCell(3);
        cell.setCellValue(account.biography);

        row = spreadsheet.getRow(0);
        row.createCell(4).setCellValue("profile_pic_url");
        row = spreadsheet.getRow(1);
        row.createCell(4).setCellValue(account.profilePicUrl);

        row = spreadsheet.getRow(0);
        row.createCell(5).setCellValue("media_count");
        row = spreadsheet.getRow(1);
        row.createCell(5).setCellValue(account.mediaCount);

        row = spreadsheet.getRow(0);
        row.createCell(6).setCellValue("following_count");
        row = spreadsheet.getRow(1);
        row.createCell(6).setCellValue(account.followsCount);

        row = spreadsheet.getRow(0);
        row.createCell(7).setCellValue("followed_by");
        row = spreadsheet.getRow(1);
        row.createCell(7).setCellValue(account.followedByCount);

        row = spreadsheet.getRow(0);
        row.createCell(8).setCellValue("is_private");
        row = spreadsheet.getRow(1);
        row.createCell(8).setCellValue(account.isPrivate);

        FileOutputStream out = new FileOutputStream(
                new File("cellstyle.xlsx"));
        workbook.write(out);
        out.close();
        System.out.println("cellstyle.xlsx written successfully");
        return true;
    }
}

