package ua.torrino;

import me.postaddict.instagram.scraper.AnonymousInsta;
import me.postaddict.instagram.scraper.domain.Account;
import me.postaddict.instagram.scraper.domain.Media;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelUserMedia extends Excel {

    private File file;
    private AnonymousInsta client;
    private Account account;
    private String logName;

    public ExcelUserMedia(File file , AnonymousInsta client,String logName) {
        this.client = client;
        this.file = file;
        this.logName = logName;
    }

    public boolean writeInFile() throws IOException, NoSuchFieldException, IllegalAccessException {

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet spreadsheet = workbook.createSheet("DataGraberMedia");
        XSSFRow row = spreadsheet.createRow(0);
        row.createCell(0).setCellValue("Media urls");
        row.createCell(1).setCellValue("Captions");
        spreadsheet.setColumnWidth(0,23000);
        spreadsheet.setColumnWidth(1,18000);

        List<Media> mediaList = client.getMedias(logName,client.getAccountByUsername(logName).mediaCount);
       for(int i = 0;i<mediaList.size();i++) {
           row = spreadsheet.createRow(i+1);
           row.createCell(0).setCellValue(String.valueOf(mediaList.get(i).imageUrls.high));
           row.createCell(1).setCellValue(String.valueOf(mediaList.get(i).caption));
       }
        FileOutputStream out = new FileOutputStream(
                new File("DataGraberMedia.xlsx"));
        workbook.write(out);
        out.close();
        return true;
    }

    public boolean readFromFile() {
        return false;
    }
}
