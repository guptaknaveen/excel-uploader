package com.s2p.utility.exceluploader.util;

import com.s2p.utility.exceluploader.logger.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class ExcelReader {
    private static ExcelReader excelReader = new ExcelReader();

    private static Logger logger = Logger.getLogger();

    private ExcelReader() {

    }

    public static ExcelReader getExcelReader() {
        return excelReader;
    }

    public List<Map<String, Object>> readXLXSData(File file) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);

        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);

        //Get first/desired sheet from the workbook
        XSSFSheet sheet = workbook.getSheetAt(0);

        Iterator<Row> rowIterator = sheet.iterator();

        List<Map<String, Object>> data = new ArrayList<>();
        List<String> columns = getColumns(rowIterator);

        int index;
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            //For each row, iterate through all the columns
            Iterator<Cell> cellIterator = row.cellIterator();

            index = 0;
            Map<String, Object> rowData = new HashMap<>();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                rowData.put(columns.get(index++), cell.getStringCellValue());
            }
            data.add(rowData);
        }

        fileInputStream.close();

        logger.info(data);

        return data;
    }

    public List<Map<String, Object>> readXLSData(File file) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);

        HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);

        //Get first/desired sheet from the workbook
        HSSFSheet sheet = workbook.getSheetAt(0);

        Iterator<Row> rowIterator = sheet.iterator();

        List<Map<String, Object>> data = new ArrayList<>();
        List<String> columns = getColumns(rowIterator);

        int index;
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            //For each row, iterate through all the columns
            Iterator<Cell> cellIterator = row.cellIterator();

            index = 0;
            Map<String, Object> rowData = new HashMap<>();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                rowData.put(columns.get(index++), getCellValue(cell));
            }
            data.add(rowData);
        }

        fileInputStream.close();

        logger.info(data);

        return data;
    }

    private List<String> getColumns(Iterator<Row> rowIterator) {
        List<String> columns = new ArrayList<>();
        if (rowIterator.hasNext()) {
            Row row = rowIterator.next();

            Iterator<Cell> cellIterator = row.cellIterator();

            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                columns.add((String) getCellValue(cell));
            }
        }
        return columns;
    }

    private Object getCellValue(Cell cell) {
        switch (cell.getCellType())
        {
            case Cell.CELL_TYPE_NUMERIC:
                return cell.getNumericCellValue();
            case Cell.CELL_TYPE_BOOLEAN:
                return cell.getBooleanCellValue();
            case Cell.CELL_TYPE_STRING:
            default:
                return cell.getStringCellValue();
        }
    }
}