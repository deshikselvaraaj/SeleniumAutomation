package com.utils.tests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelReportGeneration {

	private static Logger logger = LogManager.getLogger();

	
	/** This method will recieve the value present in the row and test status which will update the Test Execution Status column
	 * The rowValue actual data and "Test Execution Status" should be present in the spreadsheet.
	 * Values in Test Execution Status column will be cleared using ExcelColumnClear class which is invoked using before suite annotation.
	 * @param rowValue
	 * @param testStatus
	 * @author Deshik - MX Techies
	 */
	public void updateExcel(String rowValue, boolean status, String comments) {
	    logger.info("Excel report generation started.");
	    logger.info("Arguments received.");
	    logger.info("Row Value: " + rowValue + "\tTest Status: " + status);

	    String filePath = "test-output\\Selenium_Automation_Testcases_Report_v1.0.xlsx"; // This is a template
	    String sheetName = "AutomationWebManager"; // Replace with the actual sheet name

	    String testStatus = status ? "Pass" : "Fail";
	    String columnValue = "Test Execution Status";
	    String executedDateColumn = "Executed Date";
	    String commentsColumn = "Comments"; // Added column for comments

	    logger.info("Test status: " + testStatus);

	    try (FileInputStream fis = new FileInputStream(filePath);
	         Workbook workbook = new XSSFWorkbook(fis)) {

	        logger.info("Getting the file path of the spreadsheet");

	        // Get the sheet
	        Sheet sheet = workbook.getSheet(sheetName);
	        logger.info("Getting the sheet name.");

	        // Search for the cells containing Row Value and Column Name
	        Cell rowCell = findCellByValue(sheet, rowValue);
	        Cell columnCell = findCellByValue(sheet, columnValue);
	        Cell executedDateColumnCell = findCellByValue(sheet, executedDateColumn);
	        Cell commentsColumnCell = findCellByValue(sheet, commentsColumn); // Added cell for comments column

	        logger.info("Finding the row and column of Test Case class name and Test Execution Status.");

	        // Update the intersecting column
	        if (rowCell != null && columnCell != null && executedDateColumnCell != null && commentsColumnCell != null) {
	            int rowIndex = rowCell.getRowIndex(); // Use getRowIndex() instead of getColumnIndex()
	            int columnIndex = columnCell.getColumnIndex();
	            int executedOnColumnIndex = executedDateColumnCell.getColumnIndex();
	            int commentsColumnIndex = commentsColumnCell.getColumnIndex(); // Added index for comments column

	            // Get the intersecting row
	            Row intersectingRow = sheet.getRow(rowIndex);

	            // Update the intersecting column cell
	            Cell intersectingCell = intersectingRow.getCell(columnIndex);
	            if (intersectingCell == null) {
	                intersectingCell = intersectingRow.createCell(columnIndex);
	            }
	            intersectingCell.setCellValue(testStatus);

	            // Update the executed date column cell in the intersecting row
	            Cell executedOnCell = intersectingRow.getCell(executedOnColumnIndex);
	            if (executedOnCell == null) {
	                executedOnCell = intersectingRow.createCell(executedOnColumnIndex);
	            }
	            executedOnCell.setCellValue(LocalDate.now().toString());

	            // Update the comments column cell in the intersecting row
	            Cell commentsCell = intersectingRow.getCell(commentsColumnIndex);
	            if (commentsCell == null) {
	                commentsCell = intersectingRow.createCell(commentsColumnIndex);
	            }
	            commentsCell.setCellValue(comments);

	            logger.info("Cell found: " + intersectingCell.getAddress());
	            logger.info("Test Status and Comments updated in the intersecting columns.");
	        } else {
	            logger.info("Cell not found.");
	        }

	        // Save the changes to the workbook
	        try (FileOutputStream fos = new FileOutputStream(filePath)) {
	            workbook.write(fos);
	            logger.info("Closing FileOutputStream.");
	        }

	        logger.info("Closing Workbook.");

	    } catch (FileNotFoundException e) {
	        logger.error(e.getMessage());
	    } catch (IOException e) {
	        logger.error(e.getMessage());
	    }
	}
	
	// Helper method to find a cell by its value in a sheet
    private static Cell findCellByValue(Sheet sheet, String value) {
    	logger.info("Finding cell by value.");
        for (Row row : sheet) {
            for (Cell cell : row) {
                if (cell.getCellType() == CellType.STRING && value.equals(cell.getStringCellValue())) {
                	logger.info("Cell found. Returning cell " + cell + ".");
                    return cell;
                }
            }
        }
        logger.info("Cell not found. Returning null.");
        return null;
    }
}
