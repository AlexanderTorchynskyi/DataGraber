package ua.torrino;


import java.lang.String;
import me.postaddict.instagram.scraper.model.Account;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.*;

import java.io.*;

import java.lang.reflect.Field;
import java.util.*;

public class ExcelBasicUserInfo extends Excel {

    private File file;
    private Account account;
    private XSSFRow row;
    private Map<String, java.lang.String> innerMap;
    private Map<String,Map<String,String>> basicMap;
    private List<String> keyList;
    private List<String> listValues;
    private String userNameKey = "userName";

    public ExcelBasicUserInfo(File file, Account account) {
        this.file = file;
        this.account = account;
    }
    public Map<String, Map<String, String>> getBasicMap() {
        return basicMap;
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
        //Setting up width;
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

    /*
    *The view of back result is Map<userName,Map<field,value of filed>>
    *So it's readable and pretty easy to get all data you need by providing
    * username as key;
    *
    */
    public boolean readFromFile() throws IOException {
        boolean firstIn = true;
        int index = 0;
        listValues = new ArrayList<String>();
        keyList = new ArrayList<>();
        innerMap = new LinkedHashMap<>();
        basicMap = new LinkedHashMap<>();
        FileInputStream fileInputStream = new FileInputStream(new File("DataGraberBasic.xlsx"));
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
        XSSFSheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.iterator();
        while(rowIterator.hasNext()) {
            row = (XSSFRow) rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();
            while (cellIterator.hasNext()){
                //Read the columns from spreadsheet;
                if(firstIn){
                    keyList.add(cellIterator.next().getStringCellValue());
                }
                else {
                    listValues.add(cellIterator.next().getStringCellValue());
                    innerMap.put(keyList.get(index),listValues.get(index));
                    index++;
                }
            }
            if(!listValues.isEmpty()) {
                userNameKey = listValues.get(0);
                basicMap.put(userNameKey, innerMap);
            }
            listValues = new ArrayList<>();
            firstIn = false;
        }
        return true;
    }
}

