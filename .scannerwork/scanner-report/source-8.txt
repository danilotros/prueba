package util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.NumberToTextConverter;


public class ExcelReader {

    private Sheet sheet;

    public ExcelReader(String excelFilePath,String sheetName ) throws IOException {
        this.sheet=getSheetByName(excelFilePath, sheetName);
    }

    public List<Map<String, String>> getData() {

        return readSheet();
    }



    public Sheet getSheetByName(String excelFilePath, String sheetName) throws IOException {
         return getWorkBook(excelFilePath).getSheet(sheetName);

    }



    private Workbook getWorkBook(String excelFilePath) throws IOException {
        return WorkbookFactory.create(new File(excelFilePath));
    }

    private List<Map<String, String>> readSheet() {
        Row row;
        int totalRow = sheet.getPhysicalNumberOfRows();
        List<Map<String, String>> excelRows = new ArrayList<>();
        int headerRowNumber = getHeaderRowNumber(sheet);
        if (headerRowNumber != -1) {
            int totalColumn = sheet.getRow(headerRowNumber).getLastCellNum();
            int setCurrentRow = 1;
            for (int currentRow = setCurrentRow; currentRow <= totalRow; currentRow++) {
                row = getRow(sheet, sheet.getFirstRowNum() + currentRow);
                LinkedHashMap<String, String> columnMapdata = new LinkedHashMap<>();
                for (int currentColumn = 0; currentColumn < totalColumn; currentColumn++) {
                    columnMapdata.putAll(getCellValue(sheet, row, currentColumn));
                }
                excelRows.add(columnMapdata);
            }
        }
        return excelRows;
    }

    private int getHeaderRowNumber(Sheet sheet) {
        Row row;
        int totalRow = sheet.getLastRowNum();
        for (int currentRow = 0; currentRow <= totalRow + 1; currentRow++) {
            row = getRow(sheet, currentRow);
            if (row != null) {
                int totalColumn = row.getLastCellNum();
                for (int currentColumn = 0; currentColumn < totalColumn; currentColumn++) {
                    Cell cell;
                    cell = row.getCell(currentColumn, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    switch (cell.getCellType()){

                        case STRING:
                        case NUMERIC:
                        case BLANK:
                        case BOOLEAN:
                        case ERROR:
                            return row.getRowNum();
                        default:
                            break;
                    }

                }
            }
        }
        return (-1);
    }

    private Row getRow(Sheet sheet, int rowNumber) {
        return sheet.getRow(rowNumber);
    }

    public  int findRow( String cellContent) {
        for (Row row : sheet) {
            for (Cell cell : row) {
                if (cell.getCellType() == CellType.STRING && cell.getRichStringCellValue().getString().trim().equals(cellContent)) {

                        return row.getRowNum();

                }
            }
        }
        return 0;
    }

    private LinkedHashMap<String, String> getCellValue(Sheet sheet, Row row, int currentColumn) {
        LinkedHashMap<String, String> columnMapdata = new LinkedHashMap<>();
        Cell cell;
        if (row == null) {
            if (sheet.getRow(sheet.getFirstRowNum()).getCell(currentColumn, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK)
                    .getCellType() != CellType.BLANK) {
                String columnHeaderName = sheet.getRow(sheet.getFirstRowNum()).getCell(currentColumn)
                        .getStringCellValue();
                columnMapdata.put(columnHeaderName, "");
            }
        } else {

            cell = row.getCell(currentColumn, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
            switch (cell.getCellType()){

                case STRING:
                    valueString(cell,columnMapdata);
                    break;
                case NUMERIC:
                    valueNumeric(cell,columnMapdata);
                    break;
                case BLANK:
                    valueBlank(cell,columnMapdata);
                    break;
                case BOOLEAN:
                    valueBoolean(cell,columnMapdata);
                    break;
                case ERROR:
                    valueError(cell,columnMapdata);
                    break;

                default:
                    break;
            }

        }
        return columnMapdata;
    }

    private void valueError(Cell cell, LinkedHashMap<String, String> columnMapdata) {
        if (sheet.getRow(sheet.getFirstRowNum())
                .getCell(cell.getColumnIndex(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK)
                .getCellType() != CellType.BLANK) {
            String columnHeaderName = sheet.getRow(sheet.getFirstRowNum()).getCell(cell.getColumnIndex())
                    .getStringCellValue();
            columnMapdata.put(columnHeaderName, Byte.toString(cell.getErrorCellValue()));
        }
    }

    private void valueBoolean(Cell cell, LinkedHashMap<String, String> columnMapdata) {
        if (sheet.getRow(sheet.getFirstRowNum())
                .getCell(cell.getColumnIndex(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK)
                .getCellType() != CellType.BLANK) {
            String columnHeaderName = sheet.getRow(sheet.getFirstRowNum()).getCell(cell.getColumnIndex())
                    .getStringCellValue();
            columnMapdata.put(columnHeaderName, Boolean.toString(cell.getBooleanCellValue()));
        }
    }

    private void valueBlank(Cell cell, LinkedHashMap<String, String> columnMapdata) {
        if (sheet.getRow(sheet.getFirstRowNum())
                .getCell(cell.getColumnIndex(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK)
                .getCellType() != CellType.BLANK) {
            String columnHeaderName = sheet.getRow(sheet.getFirstRowNum()).getCell(cell.getColumnIndex())
                    .getStringCellValue();
            columnMapdata.put(columnHeaderName, "");
        }
    }

    private void valueNumeric(Cell cell, LinkedHashMap<String, String> columnMapdata) {
        if (sheet.getRow(sheet.getFirstRowNum())
                .getCell(cell.getColumnIndex(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK)
                .getCellType() != CellType.BLANK) {
            String columnHeaderName = sheet.getRow(sheet.getFirstRowNum()).getCell(cell.getColumnIndex())
                    .getStringCellValue();
            columnMapdata.put(columnHeaderName, NumberToTextConverter.toText(cell.getNumericCellValue()));
        }
    }

    private void valueString(Cell cell, LinkedHashMap<String, String> columnMapdata) {
        if (sheet.getRow(sheet.getFirstRowNum())
                .getCell(cell.getColumnIndex(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK)
                .getCellType() != CellType.BLANK) {
            String columnHeaderName = sheet.getRow(sheet.getFirstRowNum()).getCell(cell.getColumnIndex())
                    .getStringCellValue();
            columnMapdata.put(columnHeaderName, cell.getStringCellValue());
        }
    }


}