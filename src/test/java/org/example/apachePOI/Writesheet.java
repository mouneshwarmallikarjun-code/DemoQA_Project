package org.example.apachePOI;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.*;

public class Writesheet {
    /*
    Emp Id Emp Name Designation
Tp01 Shiva Technical Manager
Tp02 Ram QA Lead
Tp03 Mahadev Technical Writer
Tp04 Meenkashi Technical Writer
Tp05 Krishna Technical Writer
     */
    @Test
    public void excelWrite() throws Exception {//Create blank workbook
        XSSFWorkbook workbook = new XSSFWorkbook();
        //Create a blank sheet
        XSSFSheet spreadsheet = workbook.createSheet(
                "Employee Info");
        //Create row object
        XSSFRow row;
        //This data needs to be written (Object[])
        //Each object Rep. rows cell value
        Map<String, Object[]> empinfo = new TreeMap<>();
        empinfo.put("1", new Object[]{"EMP ID", "EMP NAME", "DESIGNATION"});
        empinfo.put("2", new Object[]{"CTS01", "Shiva", "Technical Manager"});
        empinfo.put("3", new Object[]{"CTS02", "Ram", "QA Lead"});
        empinfo.put("4", new Object[]{"CTS03", "Mahadev", "Technical Writer"});
        empinfo.put("5", new Object[]{"CTS04", "Meenakshi", "Technical Writer"});
        empinfo.put("6", new Object[]{"CTS05", "Krishna", "Technical Writer"});
        //Iterate over data and write to sheet
        Set<String> keyid = empinfo.keySet();
        int rowid = 0;
        for (String key : keyid) {
            row = spreadsheet.createRow(rowid++); //Creates new row
            Object[] objectArr = empinfo.get(key);
            int cellid = 0;
            for (Object obj : objectArr) {
                Cell cell = row.createCell(cellid++);
                cell.setCellValue((String) obj);
            }
        }
        //Write the workbook in file system
        FileOutputStream out = new FileOutputStream(
                new File("Writesheet.xlsx"));
        workbook.write(out);
        out.close();
        System.out.println(
                "Writesheet.xlsx written successfully");


        //Fetch row2 values from a sheet
        try (FileInputStream fis = new FileInputStream("Writesheet.xlsx");
             XSSFWorkbook wb = new XSSFWorkbook(fis)) {
            XSSFSheet sheet = wb.getSheet("Employee Info"); // or wb.getSheetAt(0)
            XSSFRow row2 = sheet.getRow(1); // 0-based index

            if (row2 != null) {
                DataFormatter fmt = new DataFormatter();
                String empId = fmt.formatCellValue(row2.getCell(0));
                String empName = fmt.formatCellValue(row2.getCell(1));
                String designation = fmt.formatCellValue(row2.getCell(2));

                System.out.println("Row 2 (from file) -> " + empId + ", " + empName + ", " + designation);
            }
        }

    }


}