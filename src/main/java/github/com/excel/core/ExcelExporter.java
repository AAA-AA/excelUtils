package github.com.excel.core;

import github.com.excel.model.ExportModel;
import github.com.excel.utils.ExportUtils;
import github.com.excel.utils.PropertiesUtil;
import github.com.excel.utils.WorkbookUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA ^_^
 * Author : renhongqiang
 * Date: 2017/7/1 12:21
 * Email: renhongqiang1397@gmail.com
 */
public class ExcelExporter {
    public ExcelExporter() {
    }

    public static HSSFWorkbook export(ExportModel model) {
        List indexes = PropertiesUtil.getPropertyAsList(model.getConfigName());
        return export(model.getDataList(), indexes, model.getCellRangeAddress(), model.getHeadContent(), model.getClazz());
    }

    public static HSSFWorkbook export(List values, Class clazz, String proName) {
        List indexes = PropertiesUtil.getPropertyAsList(proName);
        return export(values, clazz, indexes);
    }

    public static HSSFWorkbook export(List values, Class clazz, List<Integer> indexes) {
        ExportExcel ee = ExportUtils.parseBean(values, clazz, indexes);
        HSSFWorkbook hwb = WorkbookUtil.createWorkBook(ee);
        return hwb;
    }

    private static HSSFWorkbook export(List dataList, List<Integer> indexes, CellRangeAddress cellRangeAddress, String headContent, Class clazz) {
        ExportExcel ee = ExportUtils.parseBean(dataList, clazz, indexes);
        HSSFWorkbook hwb = WorkbookUtil.createWorkBook(ee, cellRangeAddress, headContent);
        return hwb;
    }

    public static HSSFWorkbook export(List values, Class clazz) {
        ExportExcel ee = ExportUtils.parseBean(values, clazz, (List)null);
        HSSFWorkbook hwb = WorkbookUtil.createWorkBook(ee);
        return hwb;
    }

    public static HSSFWorkbook export(Map<String, List> sheetValueMap) {
        HSSFWorkbook hwb = null;
        if(sheetValueMap != null && sheetValueMap.size() > 0) {
            ArrayList eel = new ArrayList();
            sheetValueMap.forEach((k, v) -> {
                ExportExcel ee = ExportUtils.parseBean(v, (Class)null, (List)null);
                ee.setSheetName(k);
                eel.add(ee);
            });
            hwb = WorkbookUtil.createWorkBook(eel);
        }

        return hwb;
    }



}
