package com.lmg_dubai.PLM.library;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelLibrary {
	
	/**
	 * Method to return the number of rows in the excel sheet using parameters-file path and sheet name
	 * @param sPath
	 * @param sSheet
	 * @return
	 */
	public static int getExcelRowCount(String sPath, String sSheet) {
		int iRowNum = 0;
		try {
			FileInputStream fis = new FileInputStream(sPath);
			Workbook wb = (Workbook) WorkbookFactory.create(fis);
			Sheet sht = wb.getSheet(sSheet);
			iRowNum = sht.getLastRowNum();
			// System.out.println(sht.getLastRowNum());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return iRowNum;
	}
	
	/**
	 * Method to fetch excel data using parameters-file path, sheet name, row number and column number
	 * @param sPath
	 * @param sSheet
	 * @param rowNo
	 * @param cellNo
	 * @return 
	 */
	public static String getExcelData(String sPath, String sSheet, int rowNo, int cellNo) {
		DataFormatter dataFormatter = new DataFormatter();
		int iRowNum = 0;
		String data = null;
		try {
			FileInputStream fis = new FileInputStream(sPath);
			// Workbook wb = new XSSFWorkbook(fis);
			Workbook wb = (Workbook) WorkbookFactory.create(fis);
			Sheet sht = wb.getSheet(sSheet);
			// FormulaEvaluator evaluator = wb.getCreationHelper().createFormulaEvaluator();
			iRowNum = sht.getLastRowNum();
			if (rowNo <= iRowNum) {
				Cell cell = sht.getRow(rowNo).getCell(cellNo);
				// System.out.println(cellValue);
				data = dataFormatter.formatCellValue(cell);
				/*
				 * switch(cell.getCellType()) { case Cell.CELL_TYPE_STRING: data =
				 * cell.getStringCellValue(); break; case Cell.CELL_TYPE_NUMERIC: if
				 * (DateUtil.isCellDateFormatted(cell)) { data =
				 * cell.getDateCellValue().toString(); } else { data =
				 * Double.toString(cell.getNumericCellValue()); } break;
				 * 
				 * }
				 */
				// data = sht.getRow(rowNo).getCell(cellNo).getStringCellValue();
			} else {
				System.out.println("Please provide the valid Row Count");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}
	
	/**
	 * Method to write on excel sheet using parameters-file path, sheet name, row number, column number and text value to be written
	 * @param sPath
	 * @param sSheet
	 * @param rowNo
	 * @param cellNo
	 * @param value
	 */
	public static void writeExcelData(String sPath, String sSheet, int rowNo, int cellNo, String value) {
		FileInputStream fis =null;
		try {
			fis = new FileInputStream(sPath);
			Workbook wb = (Workbook) WorkbookFactory.create(fis);
			Sheet sht = wb.getSheet(sSheet);
			Row row = null;
			Cell cell = null;
			if (sht.getRow(rowNo) != null) {
				// row = sh.getRow(i);
				row = sht.getRow(rowNo);
				if (row.getCell(cellNo) != null) {
					cell = row.getCell(cellNo);
					cell.setCellValue(value);
				} else {
					row.createCell(cellNo).setCellValue(value);
				}
			} else {
				row = sht.createRow(rowNo);
				row.createCell(cellNo).setCellValue(value);
			}
			FileOutputStream fos = new FileOutputStream(sPath);
			wb.write(fos);
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(null != fis)
			{
				try {
					fis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * Method to write Array data to excel sheet using parameters-file path, sheet name and Array Text Value
	 * @param sPath
	 * @param sSheet
	 * @param value
	 */
	public static void writeExcelData(String sPath, String sSheet, ArrayList<ArrayList<String>> value) {
		try {
			FileInputStream fis = new FileInputStream(sPath);
			Workbook wb = (Workbook) WorkbookFactory.create(fis);
			Sheet sht = wb.getSheet(sSheet);
			Row row = null;
			for (int i = 0; i < value.size(); i++) {
				ArrayList<String> columnData = new ArrayList<String>();
				columnData = value.get(i);
				//System.out.println(columnData);
				for (int j = 0; j < columnData.size(); j++) {
					if (sht.getRow(j) != null) {
						// row = sh.getRow(i);
						sht.getRow(j).createCell(i).setCellValue(columnData.get(j));
					} else {
						row = sht.createRow(j);
						row.createCell(i).setCellValue(columnData.get(j));
					}
				}
			}
			FileOutputStream fos = new FileOutputStream(sPath);
			wb.write(fos);
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Method to return number of cells in a particular row of excel sheet using parameters-file path, sheet name and row number
	 * @param sPath
	 * @param sSheet
	 * @param rowNo
	 * @return
	 */
	public static int getExcelCellCount(String sPath, String sSheet, int rowNo) {
		int iRowNum = 0;
		int cellCount = 0;
		try {
			FileInputStream fis = new FileInputStream(sPath);
			Workbook wb = (Workbook) WorkbookFactory.create(fis);
			Sheet sht = wb.getSheet(sSheet);
			iRowNum = sht.getLastRowNum();
			if (rowNo <= iRowNum) {
				cellCount = sht.getRow(rowNo).getLastCellNum();
				// System.out.println(cellCount);
			} else {
				System.out.println("Please provide the valid Row Count");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return cellCount;
	}

	/**
	 * This method writes the data into the excel without changing the format
	 * @param sPath
	 * @param sSheet
	 * @param rowNo
	 * @param cellNo
	 * @param value
	 * @author Amjath khan
	 */
	public static void writeExcel(String sPath, String sSheet, int rowNo, int cellNo, String value) {
		try {
			FileInputStream fis = new FileInputStream(sPath);
			Workbook wb = (Workbook) WorkbookFactory.create(fis);
			Sheet sht = wb.getSheet(sSheet);
			Row row = null;
			Cell cell = null;
			if (sht.getRow(rowNo) != null) {
				// row = sh.getRow(i);
				row = sht.getRow(rowNo);
				if (row.getCell(cellNo) != null) {
					cell = row.getCell(cellNo);
					cell.setCellValue(Double.parseDouble(value));
				} else {
					row.createCell(cellNo).setCellValue(Double.parseDouble(value));
				}
			} else {
				row = sht.createRow(rowNo);
				row.createCell(cellNo).setCellValue(Double.parseDouble(value));
			}
			FileOutputStream fos = new FileOutputStream(sPath);
			wb.write(fos);
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method is used to clear the cell content in a particular excel sheet using parameters-file path, sheet name, row number and cell number
	 * @author Mithun M
	 * @param sPath
	 * @param sSheet
	 * @param rowNo
	 * @param cellNo
	 */
	public static void clearCellContent(String sPath, String sSheet, int rowNo, int cellNo )
	{
		try {
			String value=null;
			FileInputStream fis = new FileInputStream(sPath);
			Workbook wb = (Workbook) WorkbookFactory.create(fis);
			Sheet sht = wb.getSheet(sSheet);
			Row row = null;
			Cell cell = null;
			if (sht.getRow(rowNo) != null) {
				// row = sh.getRow(i);
				row = sht.getRow(rowNo);
				if (row.getCell(cellNo) != null) 
				{
					cell = row.getCell(cellNo);
					cell.setCellValue(value);
				} 
				FileOutputStream fos = new FileOutputStream(sPath);
				wb.write(fos);
				fos.close();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static int getHeaderTextColumnNumberInSpecifiedRow(String sPath, String sheetName, int rowNumberToSearch, String searchHeaderText) throws Exception, InvalidFormatException {
		int colIndex = -1;
		try {
            // Load the Excel file
            FileInputStream fis = new FileInputStream(sPath);
            Workbook wb = (Workbook) WorkbookFactory.create(fis);
			Sheet sht = wb.getSheet(sheetName);
			Row row = sht.getRow(rowNumberToSearch);
			
			for(Cell cell: row) {
			//System.out.println("Cell values : "+cell.getStringCellValue());
			if(cell.getStringCellValue().trim().equalsIgnoreCase(searchHeaderText)) {
				colIndex = cell.getColumnIndex();
				System.out.println("Header Column Index is : "+colIndex);
				break;
			  }
			}
            // Close the workbook and input stream
			wb.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
		return colIndex;
	}
	
	public static int getNullColumnIndexInSpecifiedRow(String sPath, String sheetName, int rowNumberToSearch, int columnToStart) throws Exception, InvalidFormatException {
		int colIndex=-1;
		try {
            // Load the Excel file
            FileInputStream fis = new FileInputStream(sPath);
            Workbook wb = (Workbook) WorkbookFactory.create(fis);
			Sheet sht = wb.getSheet(sheetName);
			System.out.println("Attribute Text Row No Start from Excel:"+rowNumberToSearch);
			Row row = sht.getRow(rowNumberToSearch);
//			System.out.println("Excel Row: "+row);XML
			System.out.println("Column No Start from Excel:"+columnToStart);
			
			for (int columnIndex =columnToStart; columnIndex <= (columnToStart+100); columnIndex++) {
				System.out.println("Excel Col: "+row.getCell(columnIndex));
				Thread.sleep(1000);
				Cell cell = row.getCell(columnIndex);                    
				if (cell.getStringCellValue()=="") { 
					colIndex = cell.getColumnIndex();
					System.out.println("Column No of Null from Excel:"+colIndex);
					break;
				}
			}
			

			
            // Close the workbook and input stream
			wb.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
		return colIndex;
	}
	/**
	 * Method to get Null Cell after iterating over cells from specified row and column Index
	 * @param sPath
	 * @param sheetName
	 * @param rowNumberToSearch
	 * @param columnToStart
	 * @return 
	 * @throws Exception
	 * @throws InvalidFormatException
	 */
	public static int getNullColumnIndex(String sPath, String sheetName, int rowNumberToSearch, int columnToStart) throws Exception, InvalidFormatException {
            try
            {
            // Load the Excel file
            FileInputStream fis = new FileInputStream(sPath);
            Workbook workbook = WorkbookFactory.create(fis);

            int rowIndex = rowNumberToSearch;   
            System.out.println("Row Index: "+rowIndex);
            System.out.println("Col Index Start: "+columnToStart);
            Sheet sheet = workbook.getSheet(sheetName);
            Row row = sheet.getRow(rowIndex);

            if (row != null) {
                // Iterate over the cells in the row
                for (int columnIndex = columnToStart; columnIndex < row.getLastCellNum(); columnIndex++) {
                    Cell cell = row.getCell(columnIndex);
                    //if (cell == null) {
                    if(getExcelData(sPath, sheetName, rowNumberToSearch, columnIndex).length()==0) {
                        System.out.println("Found null cell at column index: " + columnIndex);
                        return columnIndex;
                    }
                }
            } else {
                System.out.println("Row not found.");
            }
            workbook.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Could not find null cell at specified index");
		return -1;
	}
	
	/**
	 * Method to get number of rows to check 
	 * @param sPath
	 * @param sheetName
	 * @param row
	 * @param col
	 * @return
	 */
	public static int getNumberOfRowsToCheck(String sPath, String sheetName, int row, int col) {
		String rowsToCheck = getExcelData(sPath, sheetName, row, col);
		String[] rows = rowsToCheck.split("-");
		return Integer.parseInt(rows[rows.length-1].trim());
	}
	
	public static ArrayList<String> getAllDataInARowBasedOnHeader(String sPath, String sheetName, int headerRow, int headerCol) {
		try {			
			 // Load the Excel file
            FileInputStream fis = new FileInputStream(sPath);
            Workbook workbook = WorkbookFactory.create(fis);

			Sheet sheet = workbook.getSheet(sheetName);
			Row row = sheet.getRow(headerRow);
			int numberOfValues = row.getPhysicalNumberOfCells()-1;//Eliminate Row Header Text
			//System.out.println("Number of Values: "+numberOfValues);
			ArrayList<String> allTextList = new ArrayList<String>();
			for(int i=0; i<numberOfValues; i++) {
				//Get text and add
				if(getExcelData(sPath,sheetName, headerRow, i+1).length()!=0) { //Clear All cells in Excel sheet if "" encountered
				allTextList.add(getExcelData(sPath, sheetName, headerRow, i+1));
				}
			}
			System.out.println("Excel Data List Includes: "+allTextList);
			return allTextList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
