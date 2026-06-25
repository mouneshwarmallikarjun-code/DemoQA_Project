package org.example.apachePOI;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelReader {

    @Test(groups = {"Regression"})
    public void excelReader() {
        String filePath ="C:\\Users\\2445477\\IdeaProjects\\DemoQA_Project\\src\\test\\resources\\TestData.xlsx";
        //String filePath=System.getProperty("user.dir")+".\\resources\\TestData.xlsx";
       // String sheetName = "Sheet1"; // or use index

        try (FileInputStream fis = new FileInputStream(filePath);
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0); // or workbook.getSheetAt(0)
            if (sheet == null) {
                System.out.println("Sheet not found: ");
                return;
            }

            for (Row row : sheet) {
                for (Cell cell : row) {
                    System.out.print(getCellValueAsString(cell) + "\t");
                }
                System.out.println();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Safely convert cell to String representation
    private static String getCellValueAsString(Cell cell) {
        if (cell == null) return "";

        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();

            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    // Format dates as needed
                    return cell.getLocalDateTimeCellValue().toString();
                } else {
                    // Avoid scientific notation if you need exact formatting
                    return String.valueOf(cell.getNumericCellValue());
                }

            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());

            case BLANK:
                return "";

            default:
                return cell.toString();
        }
    }

}
