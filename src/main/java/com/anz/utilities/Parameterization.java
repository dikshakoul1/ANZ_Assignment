package com.anz.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Base64;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Parameterization {

    public static Map<String, HashMap<String, String>> ExcelObjectReader(String sheetName)
    {
        Map<String, HashMap<String, String>> data = new HashMap<String, HashMap<String, String>>();
        try {
            String filePath=System.getProperty("user.dir")+"\\TestData\\Locator.xlsx";
            FileInputStream file= new FileInputStream(new File(filePath));
            @SuppressWarnings("resource")
            XSSFWorkbook workbook= new XSSFWorkbook(file);
            XSSFSheet sheet= workbook.getSheet(sheetName);
            sheet.getLastRowNum();
            Iterator<Row> ir=sheet.rowIterator();
            while(ir.hasNext()) {
                Row currentRow= ir.next();
                String name = null, selector=null, selector_type = null;
                HashMap<String, String> currentHash = new HashMap<String, String>();
                for(int i=0;i<currentRow.getLastCellNum();) {
                    name= currentRow.getCell(i).getStringCellValue();
                    selector_type= currentRow.getCell(i+1).getStringCellValue();
                    selector= currentRow.getCell(i+2).getStringCellValue();
                    currentHash.put(selector_type, selector);
                    break;
                }
                data.put(name,currentHash);
            }
            file.close();
        }catch(Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    public static HashMap<String,String> getEnvDetails() {
        HashMap<String, String> value=new HashMap<String,String>();
        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\data.properties"));
            value.put("Browser", prop.getProperty("browser"));
            value.put("URL", prop.getProperty("URL"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }
	
    public static String Decoder(String encodedpassword) {
        Base64.Decoder decoder = Base64.getDecoder();  
        String password = new String(decoder.decode(encodedpassword)); 
        return password;
    }
}
