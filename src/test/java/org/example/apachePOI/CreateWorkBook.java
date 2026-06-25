package org.example.apachePOI;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class CreateWorkBook {

    @Test
    public void createSheet() throws IOException {
        // Always use try-with-resources for safety
        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            // Create at least one sheet
            Sheet sheet = workbook.createSheet("FirstSheet");

            // (Optional) Put something in cell A1 to verify content
            sheet.createRow(0).createCell(0).setCellValue("Shiva!");

            // Write to file via try-with-resources
            File outFile = new File("CreateWorkbook.xlsx");
            try (FileOutputStream out = new FileOutputStream(outFile)) {
                workbook.write(out);  // write BEFORE closing streams
                out.flush();          // optional, explicit flush
            }
            // workbook is auto-closed here
        }




        Assert.assertEquals("","","Login is failed");

        System.out.println("createworkbook.xlsx written successfully");

        SoftAssert soft = new SoftAssert();
        soft.assertEquals("Actual","Expected");
        soft.assertAll();

    }

}
