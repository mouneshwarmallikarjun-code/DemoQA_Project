package org.example.apachePOI;

import io.qameta.allure.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

@Epic("Apache POI Testing using TestNG")
@Feature("Excel reader")
public class ReadSheet {

    @Story("Apache POI read rows rows")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Description : Verify the excel read operation")
    @Test(groups = {"Regression"})
    public void readAllRows() throws Exception {
        try (FileInputStream fis = new FileInputStream("Writesheet.xlsx");
             XSSFWorkbook wb = new XSSFWorkbook(fis)) {
            XSSFSheet sheet = wb.getSheet("Employee Info"); // or wb.getSheetAt(0)
            DataFormatter formatter = new DataFormatter();

            List<List<String>> table = new ArrayList<>();

            int firstRow = sheet.getFirstRowNum();
            int lastRow = sheet.getLastRowNum();

            for (int r = firstRow; r <= lastRow; r++) {
                Row row = sheet.getRow(r);
                if (row == null) {
                    table.add(new ArrayList<>()); // represent empty row if needed
                    continue;
                }

                List<String> cells = new ArrayList<>();
                short lastCell = row.getLastCellNum(); // count of cells
                for (int c = 0; c < lastCell; c++) {
                    Cell cell = row.getCell(c, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                    String text = formatter.formatCellValue(cell);
                    cells.add(text);
                }
                table.add(cells);
            }

            // Print the table row by row
            for (int i = 0; i < table.size(); i++) {
                System.out.println("Row " + i + " -> " + String.join(" | ", table.get(i)));
            }
        }
    }
}
