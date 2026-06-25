package org.example.apachePOI;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class OpenExistingWorkBook {

    @Test
    public void openSheet()

    {
        {
            File file = new File("CreateWorkbook.xlsx");
            FileInputStream fIP = null;
            try {
                fIP = new FileInputStream(file);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
//Get the workbook instance for XLSX file
            try {
                XSSFWorkbook workbook = new XSSFWorkbook(fIP);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            if (file.isFile() && file.exists()) {
                System.out.println(
                        "CreateWorkbook.xlsx file open successfully.");
            } else {
                System.out.println(
                        "Error to open openworkbook.xlsx file.");
            }
        }
    }

}
