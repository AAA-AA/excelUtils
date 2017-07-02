package github.com.excel.utils;

import github.com.excel.core.ExportExcel;
import github.com.excel.model.CellModel;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA ^_^
 * Author : renhongqiang
 * Date: 2017/7/1 12:22
 * Email: renhongqiang1397@gmail.com
 */
public class WorkbookUtil {
    public static int MAX_SIZE = '\uea60';

    public WorkbookUtil() {
    }

    public static HSSFWorkbook createWorkBook(List<ExportExcel> eel) {
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFCellStyle cellStyle = wb.createCellStyle();

        ExportExcel ee;
        for (Iterator var3 = eel.iterator(); var3.hasNext(); wb = createWorkBookSheet(wb, cellStyle, ee)) {
            ee = (ExportExcel) var3.next();
        }

        return wb;
    }

    public static HSSFWorkbook createWorkBook(ExportExcel ee) {
        if (null == ee) {
            return null;
        } else {
            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFCellStyle cellStyle = wb.createCellStyle();
            return createWorkBookSheet(wb, cellStyle, ee);
        }
    }

    public static HSSFWorkbook createWorkBook(ExportExcel ee, CellRangeAddress cellRangeAddress, String headContent) {
        if (null == ee) {
            return null;
        } else {
            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFCellStyle cellStyle = wb.createCellStyle();
            return createWorkBookSheet(wb, cellStyle, ee, cellRangeAddress, headContent);
        }
    }

    public static HSSFWorkbook createWorkBookSheet(HSSFWorkbook wb, HSSFCellStyle cellStyle, ExportExcel ee, CellRangeAddress cellRangeAddress, String headContent) {
        if (null == ee) {
            return null;
        } else {
            cellStyle.setWrapText(true);
            int marginTop = 0;
            HSSFCellStyle style = null;
            if (cellRangeAddress != null) {
                marginTop = cellRangeAddress.getLastRow() - cellRangeAddress.getFirstRow() + 1;
                style = wb.createCellStyle();
                style.setVerticalAlignment((short) 1);
                style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
                style.setFillPattern((short) 1);
            }

            if (CollectionUtils.isNotEmpty(ee.getValueMapList())) {
                for (int sheet = 0; sheet * MAX_SIZE < ee.getValueMapList().size(); ++sheet) {
                    String sheetName = null;
                    if (StringUtils.isNotBlank(ee.getSheetName())) {
                        sheetName = ee.getSheetName();
                    } else {
                        sheetName = "sheet" + (sheet + 1);
                    }

                    HSSFSheet sheet1 = wb.createSheet(sheetName);
                    if (cellRangeAddress != null) {
                        createSheetHeadContent(sheet1, style, cellRangeAddress, headContent);
                    }

                    createHead(sheet1, ee, wb, marginTop);
                    ++marginTop;
                    createRows(sheet1, ee, wb, sheet, cellStyle, marginTop);
                }
            } else {
                HSSFSheet var10 = wb.createSheet("sheet");
                if (cellRangeAddress != null) {
                    createSheetHeadContent(var10, style, cellRangeAddress, headContent);
                }

                createHead(var10, ee, wb, marginTop);
            }

            return wb;
        }
    }

    private static void createSheetHeadContent(HSSFSheet sheet, HSSFCellStyle style, CellRangeAddress cellRangeAddress, String headContent) {
        if (cellRangeAddress != null) {
            int marginLeft = -1;
            if (cellRangeAddress != null && cellRangeAddress.getFirstColumn() != 0) {
                marginLeft = cellRangeAddress.getFirstColumn();
            }

            ++marginLeft;
            sheet.addMergedRegion(cellRangeAddress);
            HSSFRow row = sheet.createRow(0);
            row.setHeight((short) 700);
            HSSFCell cell = row.createCell(marginLeft);
            cell.setCellValue(headContent);
            cell.setCellStyle(style);
        }

    }

    public static HSSFWorkbook createWorkBookSheet(HSSFWorkbook wb, HSSFCellStyle cellStyle, ExportExcel ee) {
        if (null == ee) {
            return null;
        } else {
            cellStyle.setWrapText(true);
            if (CollectionUtils.isNotEmpty(ee.getValueMapList())) {
                for (int sheet = 0; sheet * MAX_SIZE < ee.getValueMapList().size(); ++sheet) {
                    String sheetName = null;
                    if (StringUtils.isNotBlank(ee.getSheetName())) {
                        sheetName = ee.getSheetName();
                    } else {
                        sheetName = "sheet" + (sheet + 1);
                    }

                    HSSFSheet sheet1 = wb.createSheet(sheetName);
                    createHead(sheet1, ee, wb, 0);
                    createRows(sheet1, ee, wb, sheet, cellStyle, 1);
                }
            } else {
                HSSFSheet var6 = wb.createSheet("sheet");
                createHead(var6, ee, wb, 0);
            }

            return wb;
        }
    }

    private static void createHead(HSSFSheet sheet, ExportExcel ee, HSSFWorkbook wb, int marginTop) {
        if (null != ee && null != ee.getCellMap() && ee.getCellMap().size() > 0) {
            HSSFRow row = sheet.createRow(marginTop);
            int i;
            CellModel c;
            HSSFCell cell;
            if (null != ee.getIndexes() && ee.getIndexes().size() > 0) {
                for (i = 0; i < ee.getIndexes().size(); ++i) {
                    c = ee.getCellMap().get(ee.getIndexes().get(i));
                    if (c != null) {
                        sheet.setColumnWidth(i, c.getCellWidth().intValue());
                        cell = row.createCell(i);
                        cell.setCellValue(c.getDescription());
                    }
                }
            } else {
                for (i = 0; i < ee.getCellMap().size(); ++i) {
                    c = ee.getCellMap().get(Integer.valueOf(i));
                    if (c != null) {
                        sheet.setColumnWidth(i, c.getCellWidth().intValue());
                        cell = row.createCell(i);
                        cell.setCellValue(c.getDescription());
                    }
                }
            }
        }

    }

    private static void createRows(HSSFSheet sheet, ExportExcel ee, HSSFWorkbook wb, int count, HSSFCellStyle cellStyle, int marginTop) {
        if (null != ee && null != ee.getValueMapList() && ee.getValueMapList().size() > 0) {
            int toIndex = ee.getValueMapList().size() < MAX_SIZE * (count + 1) ? ee.getValueMapList().size() : MAX_SIZE * (count + 1);
            List valueMapList = ee.getValueMapList().subList(count * MAX_SIZE, toIndex);
            int i = marginTop;

            for (int valueIndex = 1; i <= valueMapList.size() + marginTop - 1; ++valueIndex) {
                HSSFRow row = sheet.createRow(i);
                createRow(wb, row, (Map) valueMapList.get(valueIndex - 1), ee.getCellMap(), ee.getMethodIndexMap(), ee.getIndexes(), cellStyle);
                ++i;
            }
        }

    }

    private static void createRow(HSSFWorkbook wb, HSSFRow row, Map<Integer, Object> valueMap, Map<Integer, CellModel> cellMap, Map<String, Integer> methodIndexMap, List<Integer> indexes, HSSFCellStyle cellStyle) {
        for (int i = 0; i < valueMap.size(); ++i) {
            HSSFCell cell = row.createCell(i);
            cell.setCellStyle(cellStyle);
            Integer index = null;
            if (null != indexes && indexes.size() > 0) {
                index = (Integer) indexes.get(i);
            }

            if (null == index) {
                index = Integer.valueOf(i);
            }

            if (null != valueMap.get(index)) {
                String value = valueMap.get(index).toString();
                if (null != cellMap.get(index) && null != ((CellModel) cellMap.get(index)).getIsLink() && ((CellModel) cellMap.get(index)).getIsLink().equals(Boolean.valueOf(true))) {
                    cell.setCellFormula(value.toString());
                } else {
                    cell.setCellValue(value.toString());
                }
            }
        }

    }

    private static String getMethodName(String paramName) {
        String methodName = paramName.replace("<", "").replace(">", "");
        String tail = methodName.substring(1);
        String upper = methodName.substring(0, 1).toUpperCase();
        methodName = "get" + upper + tail;
        return methodName;
    }

    public static void main(String[] args) {
        ArrayList a = new ArrayList();
        byte n = 1;
        int var4 = n + 1;
        a.add(Integer.valueOf(n));
        a.add(Integer.valueOf(var4++));
        a.add(Integer.valueOf(var4++));
        a.add(Integer.valueOf(var4++));
        a.add(Integer.valueOf(var4++));
        List var3 = a.subList(0, 1);
        System.out.println(var3);
    }

}
