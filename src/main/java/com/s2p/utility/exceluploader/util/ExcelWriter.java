package com.s2p.utility.exceluploader.util;

import com.s2p.utility.exceluploader.constant.ExcelType;
import com.s2p.utility.exceluploader.logger.Logger;
import com.s2p.utility.exceluploader.model.Data;
import com.s2p.utility.exceluploader.model.MetaData;
import com.s2p.utility.exceluploader.model.TableData;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ExcelWriter {

    private static ExcelWriter excelWriter = new ExcelWriter();

    private static Logger logger = Logger.getLogger();

    private ExcelWriter() {
    }

    public static ExcelWriter getExcelWriter() {
        return excelWriter;
    }

    public File writeToXLSExcel(MetaData metaData, Data data, String folderName) throws IOException {
        TableData tableData = Converter.getConverter().getTableDataFromMetaData(metaData, data);
        String fileName = folderName + File.separator + metaData.getName() + "." + ExcelType.XLS.extension();
        File file = new File(fileName);
        Workbook workbook = createHSSFWorkBook(tableData);
        workbook.write(new FileOutputStream(file));
        logger.info("Created xls file : "+file.getAbsolutePath());
        return file;
    }

    public File writeToXLSXExcel(MetaData metaData, Data data, String folderName) throws IOException {
        TableData tableData = Converter.getConverter().getTableDataFromMetaData(metaData, data);
        String fileName = folderName + File.separator + metaData.getName() + "." + ExcelType.XLS.extension();
        File file = new File(fileName);
        Workbook workbook = createXSSFWorkBook(tableData);
        workbook.write(new FileOutputStream(file));
        logger.info("Created xlsx file : "+file.getAbsolutePath());
        return file;
    }

    private Workbook createHSSFWorkBook(TableData tableData) {
        Workbook workbook = new HSSFWorkbook();
        createSheet("data", workbook, tableData);
        return workbook;
    }

    private Workbook createXSSFWorkBook(TableData tableData) {
        Workbook workbook = new XSSFWorkbook();
        createSheet("data", workbook, tableData);
        return workbook;
    }

    private Sheet createSheet(String sheetName, Workbook workbook, TableData tableData) {
        Sheet sheet = workbook.createSheet(sheetName);
        int rowNum = 1;

        // create header row
        createRow(rowNum++, sheet, tableData.getHeaders());

        for (Map<String, String> dataRow : tableData.getData()) {
            // create data row
            createRow(rowNum++, sheet, tableData.getHeaders(), dataRow);
        }
        return sheet;
    }

    private Row createRow(int rowNum, Sheet sheet, List<String> headers, Map<String, String> rowData) {
        int columnNum = 1;
        Row row = sheet.createRow(rowNum);
        for (String header : headers) {
            createCell(columnNum++, row, rowData.get(header));
        }
        return row;
    }

    private Row createRow(int rowNum, Sheet sheet, List<String> dataRow) {
        int columnNum = 1;
        Row row = sheet.createRow(rowNum);
        for (String data : dataRow) {
            createCell(columnNum++, row, data);
        }
        return row;
    }

    private Cell createCell(int columnNum, Row row, Object value) {
        Cell cell = row.createCell(columnNum);
        if (value != null) {
            cell.setCellValue((String) value);
        }
        return cell;
    }

    void main(String[] args) throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("data");

        Object[][] bookData = {
                {"Head First Java", "Kathy Serria", 79},
                {"Effective Java", "Joshua Bloch", 36},
                {"Clean Code", "Robert martin", 42},
                {"Thinking in Java", "Bruce Eckel", 35},
        };

        int rowCount = 0;

        for (Object[] aBook : bookData) {
            Row row = sheet.createRow(++rowCount);

            int columnCount = 0;

            for (Object field : aBook) {
                Cell cell = row.createCell(++columnCount);
                if (field instanceof String) {
                    cell.setCellValue((String) field);
                } else if (field instanceof Integer) {
                    cell.setCellValue((Integer) field);
                }
            }

        }


        try (FileOutputStream outputStream = new FileOutputStream("JavaBooks.xlsx")) {
            workbook.write(outputStream);
        }
    }



}
