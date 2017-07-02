package simple_example;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.formula.functions.T;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA ^_^
 * Author : renhongqiang
 * Date: 2017/7/2 10:42
 * Email: renhongqiang1397@gmail.com
 */
public class ExportExcelUtils {
    public static void exportExcel(HSSFWorkbook workbook, int sheetNum,
                                   String sheetTitle, String[] headers, List<List<String>> result,
                                   OutputStream out) throws Exception {
        // 生成一个表格
        HSSFSheet sheet = workbook.createSheet();
        HSSFCellStyle style = handleExcelStyle(workbook,sheet,sheetNum,sheetTitle);
        writeCell(headers, result, sheet, style);

    }

    private static void writeCell(String[] headers, List<List<String>> result, HSSFSheet sheet, HSSFCellStyle style) {
        // 产生表格标题行
        HSSFRow row = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            HSSFCell cell = row.createCell((short) i);
            cell.setCellStyle(style);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text.toString());
        }
        // 遍历集合数据，产生数据行
        if (result != null) {
            int index = 1;
            for (List<String> m : result) {
                row = sheet.createRow(index);
                int cellIndex = 0;
                for (String str : m) {
                    HSSFCell cell = row.createCell((short) cellIndex);
                    cell.setCellValue(str.toString());
                    cellIndex++;
                }
                index++;
            }
        }
    }

    private static void writeCell(String[] headers, List<List<String>> result, HSSFSheet sheet, HSSFCellStyle style, List<HSSFRow> rowList) {
        // 产生表格标题列
        for (int i = 0; i < headers.length; i++) {
            HSSFRow row = sheet.createRow(i);
            HSSFCell cell = row.createCell((short) 0);
            cell.setCellStyle(style);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text.toString());
            rowList.add(row);
        }
        // 遍历集合数据，产生数据列
        if(result != null) {
            int columnIndex = 1;
            for(List<String> data:result) {
                for(int i = 0;i < rowList.size();i++) {
                    HSSFCell cell = rowList.get(i).createCell(columnIndex);
                    cell.setCellValue(data.get(i));
                }
                columnIndex++;
            }
        }
    }


    public static void exportExcel2(int sheetNum,
                                    String sheetTitle, String[] headers, List<List<String>> result,String filePath) throws Exception {
        HSSFWorkbook workbook = new HSSFWorkbook();
        OutputStream out = new FileOutputStream(filePath);
        // 生成一个表格
        HSSFSheet sheet = workbook.createSheet();
        //设置样式
        HSSFCellStyle style = handleExcelStyle(workbook,sheet,sheetNum,sheetTitle);
        List<HSSFRow> rowList = new ArrayList<>();
        writeCell(headers, result, sheet, style, rowList);
        //关闭连接
        workbook.write(out);
        out.close();

    }

    public static void exportExcel(String sheetTitle, String[] headers, List<T> result) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.cloneSheet(0);
        handleExcelStyle(workbook,sheet,0,sheetTitle);


    }

    /**
     * 设置Excel表格样式
     * @param workbook
     * @param sheet
     */
    private static HSSFCellStyle handleExcelStyle(HSSFWorkbook workbook, HSSFSheet sheet, Integer sheetNum,String sheetTitle) {
        // 设置表格默认列宽度为20个字节
        sheet.setDefaultColumnWidth((short) 6);

        workbook.setSheetName(sheetNum,sheetTitle);
        // 生成一个样式
        HSSFCellStyle style = workbook.createCellStyle();
        // 设置这些样式
        style.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // 生成一个字体
        HSSFFont font = workbook.createFont();
        font.setColor(HSSFColor.BLACK.index);
        font.setFontHeightInPoints((short) 12);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 把字体应用到当前的样式
        style.setFont(font);
        // 指定当单元格内容显示不下时自动换行
        style.setWrapText(true);

        return style;
    }
}
