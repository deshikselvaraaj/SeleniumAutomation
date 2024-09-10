package com.utils.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelColumnClear {
    private static Logger logger = LogManager.getLogger();

    public void clearColumn() {
        String filePath = "test-output\\Selenium_Automation_Testcases_Report_v1.0.xlsx";
        String sheetName = "AutomationWebManager";
        String columnName1 = "Test Execution Status";
        String columnName2 = "Executed Date";
        String columnName3 = "Comments";

        FileInputStream fis;
        Workbook workbook;

        try {
            fis = new FileInputStream(filePath);
            workbook = new XSSFWorkbook(fis);
            Sheet sheet = workbook.getSheet(sheetName);

            int columnIndex1 = -1;
            int columnIndex2 = -1;
            int columnIndex3 = -1;
            Row headerRow = sheet.getRow(0); // Assuming the column headers are in the first row

            // Find the column indices by iterating through the header row
            for (Cell cell : headerRow) {
                String headerValue = cell.getStringCellValue();
                if (headerValue.equalsIgnoreCase(columnName1)) {
                    columnIndex1 = cell.getColumnIndex();
                } else if (headerValue.equalsIgnoreCase(columnName2)) {
                    columnIndex2 = cell.getColumnIndex();
                } else if (headerValue.equalsIgnoreCase(columnName3)) {
                    columnIndex3 = cell.getColumnIndex();
                }
            }

            if (columnIndex1 != -1) {
                // Clear the values in the "Test Execution Status" column
                for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                    Row row = sheet.getRow(i);
                    Cell cell = row.getCell(columnIndex1);
                    if (cell != null) {
                        cell.setCellValue("");
                    }
                }

                logger.info("Values in the 'Test Execution Status' column cleared successfully.");
            } else {
                logger.info("Column not found: " + columnName1);
            }

            if (columnIndex2 != -1) {
                // Clear the values in the "Executed Date" column
                for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                    Row row = sheet.getRow(i);
                    Cell cell = row.getCell(columnIndex2);
                    if (cell != null) {
                        cell.setCellValue("");
                    }
                }

                logger.info("Values in the 'Executed Date' column cleared successfully.");
            } else {
                logger.info("Column not found: " + columnName2);
            }

            if (columnIndex3 != -1) {
                // Clear the values in the "Comments" column
                for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                    Row row = sheet.getRow(i);
                    Cell cell = row.getCell(columnIndex3);
                    if (cell != null) {
                        cell.setCellValue("");
                    }
                }

                logger.info("Values in the 'Comments' column cleared successfully.");
            } else {
                logger.info("Column not found: " + columnName3);
            }

            FileOutputStream fos = new FileOutputStream(filePath);
            workbook.write(fos);
            workbook.close();
            fos.close();

            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
