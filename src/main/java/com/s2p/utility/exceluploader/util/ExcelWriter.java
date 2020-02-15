package com.s2p.utility.exceluploader.util;

import com.s2p.utility.exceluploader.constant.ExcelType;
import com.s2p.utility.exceluploader.logger.Logger;
import com.s2p.utility.exceluploader.model.Data;
import com.s2p.utility.exceluploader.model.MetaData;
import com.s2p.utility.exceluploader.model.TableData;
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
        String fileName = folderName +
                File.separator +
                metaData.getName() + "_" + System.currentTimeMillis() +
                "." + ExcelType.XLS.extension();
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
        int rowNum = 0;

        // create header row
        createRow(rowNum++, sheet, tableData.getHeaders());

        for (Map<String, String> dataRow : tableData.getData()) {
            // create data row
            createRow(rowNum++, sheet, tableData.getHeaders(), dataRow);
        }
        return sheet;
    }

    private Row createRow(int rowNum, Sheet sheet, List<String> headers, Map<String, String> rowData) {
        int columnNum = 0;
        Row row = sheet.createRow(rowNum);
        for (String header : headers) {
            createCell(columnNum++, row, rowData.get(header));
        }
        return row;
    }

    private Row createRow(int rowNum, Sheet sheet, List<String> dataRow) {
        int columnNum = 0;
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
}
