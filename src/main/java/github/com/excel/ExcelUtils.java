package github.com.excel;

import github.com.excel.core.ExcelImport;
import github.com.excel.support.EndRow;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

public class ExcelUtils {
    public static void doExport(HttpServletResponse response, List data, String fileName, Class clazz, String propName) {
        try {
            Workbook wb = github.com.excel.utils.ExcelUtils.export(data, clazz, propName);
            response.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + "\"; filename*=UTF-8''" + URLEncoder.encode(fileName, "UTF-8"));
            response.setContentType("application/msexcel");
            wb.write(response.getOutputStream());
        } catch (IOException e) {
        }
    }

    public static void doExport(HttpServletResponse response, List data, String fileName, Class clazz) {
        try {
            Workbook wb = github.com.excel.utils.ExcelUtils.export(data, clazz, null);
            response.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + "\"; filename*=UTF-8''" + URLEncoder.encode(fileName, "UTF-8"));
            response.setContentType("application/msexcel");
            wb.write(response.getOutputStream());
        } catch (IOException e) {

        }
    }

    public static void doExport(HttpServletResponse response, String fileName, Map<String, List> sheetValueMap) {
        try {
            Workbook wb = github.com.excel.utils.ExcelUtils.export(sheetValueMap);
            response.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + "\"; filename*=UTF-8''" + URLEncoder.encode(fileName, "UTF-8"));
            response.setContentType("application/msexcel");
            wb.write(response.getOutputStream());
        } catch (IOException e) {

        }
    }

    public static void doExport(HttpServletResponse response, Workbook wb, String fileName) {
        try {
            response.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + "\"; filename*=UTF-8''" + URLEncoder.encode(fileName, "UTF-8"));
            response.setContentType("application/msexcel");
            wb.write(response.getOutputStream());
        } catch (IOException e) {

        }
    }
    public static void doExport(String filePath, String fileName, Map<String, List> sheetValueMap) {
        Workbook wb = github.com.excel.utils.ExcelUtils.export(sheetValueMap);
        try {
            File file = new File(String.format("%s/%s", filePath, fileName));
            OutputStream os = new FileOutputStream(file);
            wb.write(os);
        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        }
    }

    public static void doExport(String filePath, List data, String fileName, Class clazz) {
        Workbook wb = github.com.excel.utils.ExcelUtils.export(data, clazz, null);
        try {
            File file = new File(String.format("%s/%s", filePath, fileName));
            if (!file.exists()) {
                file.createNewFile();
            }
            OutputStream os = new FileOutputStream(file);
            wb.write(os);
        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        }
    }


    public static <T> List<T> parseExcelToList(Workbook workbook, Class<T> clazz, String proName, Integer skipNum) {
        return ExcelImport.parseExcelToList(workbook, clazz, proName, skipNum);
    }

    public static <T> List<T> parseExcelToList(Workbook workbook, Class<T> clazz, String proName) {
        return ExcelImport.parseExcelToList(workbook, clazz, proName);
    }

    public static <T> List<T> parseExcelToList(Workbook workbook, Class<T> clazz, String proName, Integer startIndex, EndRow endRow) {
        return ExcelImport.parseExcelToList(workbook, clazz,proName,startIndex,endRow);
    }






}
