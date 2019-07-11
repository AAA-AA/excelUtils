package github.com.excel.utils;

import github.com.excel.core.ExcelExporter;
import github.com.excel.core.ExcelImport;
import github.com.excel.model.ExportModel;
import github.com.excel.support.EndRow;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA ^_^
 * Author : renhongqiang
 * Date: 2017/7/1 12:19
 * Email: renhongqiang1397@gmail.com
 */
@Slf4j
public final class ExcelUtils {
    private ExcelUtils() {
    }
    public static String getValueForString(Cell cell) {
        String str = null;
        if(cell != null) {
            switch(cell.getCellType()) {
                case 0:
                    DecimalFormat df = new DecimalFormat("0");
                    str = df.format(cell.getNumericCellValue());
                    break;
                case 1:
                    str = cell.getStringCellValue();
            }
        }

        if(str != null) {
            str = str.trim();
        }

        return str;
    }

    public static Integer getValueForInteger(Cell cell) {
        Integer value = null;
        if(cell != null) {
            switch(cell.getCellType()) {
                case 0:
                    value = Integer.valueOf((int)cell.getNumericCellValue());
                    break;
                case 1:
                    try {
                        value = Integer.valueOf(Integer.parseInt(cell.getStringCellValue().trim()));
                    } catch (Exception var3) {
                        log.error("字符串转数字异常,原始值为：" + cell.getStringCellValue(), var3);
                    }
            }
        }

        return value;
    }

    public static Double getValueForDouble(Cell cell) {
        Double value = null;
        if(cell != null) {
            switch(cell.getCellType()) {
                case 0:
                    value = Double.valueOf(cell.getNumericCellValue());
                    break;
                case 1:
                    try {
                        value = Double.valueOf(Double.parseDouble(cell.getStringCellValue().trim()));
                    } catch (Exception var3) {
                        log.error("字符串转数字异常,原始值为：" + cell.getStringCellValue(), var3);
                    }
            }
        }

        return value;
    }

    public static Long getValueForLong(Cell cell) {
        Long value = null;
        if(cell != null) {
            switch(cell.getCellType()) {
                case 0:
                    value = Long.valueOf((long)cell.getNumericCellValue());
                    break;
                case 1:
                    try {
                        value = Long.valueOf(Long.parseLong(cell.getStringCellValue().trim()));
                    } catch (Exception var3) {
                        log.error("字符串转数字异常,原始值为：" + cell.getStringCellValue(), var3);
                    }
            }
        }

        return value;
    }

    public static HSSFWorkbook export(List values, Class clazz, String proName) {
        return ExcelExporter.export(values, clazz, proName);
    }

    public static HSSFWorkbook export(ExportModel model) {
        return ExcelExporter.export(model);
    }

    public static HSSFWorkbook export(Map<String, List> sheetValueMap) {
        return ExcelExporter.export(sheetValueMap);
    }

    public static HSSFWorkbook export(List values, Class clazz, String proName, Integer sizePerSheet) {
        WorkbookUtil.MAX_SIZE = sizePerSheet.intValue();
        return ExcelExporter.export(values, clazz, proName);
    }

    public static <T> List<T> parseExcelToList(Workbook workbook, Class<T> clazz, String proName) {
        return ExcelImport.parseExcelToList(workbook, clazz, proName);
    }

    public static <T> List<T> parseExcelToList(HSSFWorkbook workbook, Class<T> clazz, String proName, Integer stratIndex, EndRow endRow) {
        return ExcelImport.parseExcelToList(workbook, clazz, proName, stratIndex, endRow);
    }







}

