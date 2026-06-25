package org.example.apachePOI;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

import java.io.FileInputStream;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ApachePOI {

    public static void main(String[] args) throws IOException {
        //public void apachePOI () throws IOException {

            // Path of the Excel file

            String path = "C:\\Users\\2445477\\IdeaProjects\\DemoQA_Project\\src\\test\\resources\\TestData.xlsx";

            FileInputStream fs = new FileInputStream(path);

            Workbook wb = new XSSFWorkbook(fs);
            Sheet sheet1 = wb.getSheetAt(0);
            int lastRow = sheet1.getLastRowNum();
            for (int i = 0; i <= lastRow; i++) {
                Row row = sheet1.getRow(i);
                Cell cell = row.createCell(0);
                cell.setCellValue("Cognizant");
            }
            FileOutputStream fos = new FileOutputStream(path);
            wb.write(fos);
            fos.close();
        }
    }


