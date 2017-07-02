package github.com.excel.core;

import github.com.excel.ExcelCheckUtil;
import github.com.excel.annotation.Import;
import github.com.excel.exception.ExcelParseException;
import github.com.excel.support.EndRow;
import github.com.excel.support.ExcelType;
import github.com.excel.support.SetMethodInfo;
import github.com.excel.utils.PropertiesUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by IntelliJ IDEA ^_^
 * Author : renhongqiang
 * Date: 2017/7/1 12:29
 * Email: renhongqiang1397@gmail.com
 */
public class ExcelImport {
    private static final Log log = LogFactory.getLog(ExcelImport.class);

    public ExcelImport() {
    }

    public static <T> List<T> parseExcelToList(Workbook workbook, Class<T> clazz, String proName, Integer skipNum) {
        if (workbook != null && clazz != null) {
            new ArrayList();
            final List methods = getSetMethods(clazz, proName);
            if (methods != null && methods.size() != 0) {
                List excelRows = getExcelRows(workbook, Integer.valueOf(1 + skipNum.intValue()), methods);
                return generatorList(excelRows, methods, clazz);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public static <T> List<T> parseExcelToList(Workbook workbook, Class<T> clazz, String proName) {
        if (workbook != null && clazz != null) {
            final List methods = getSetMethods(clazz, proName);
            if (methods != null && methods.size() != 0) {
                List excelRows = getExcelRows(workbook, Integer.valueOf(1), methods);
                return generatorList(excelRows, methods, clazz);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public static <T> List<T> parseExcelToList(Workbook workbook, Class<T> clazz, String proName, Integer startIndex, EndRow endRow) {
        if (endRow == null) {
            return parseExcelToList(workbook, clazz, proName);
        } else if (workbook != null && clazz != null) {
            new ArrayList();
            List methods = getSetMethods(clazz, proName);
            if (methods != null && methods.size() != 0) {
                List excelRows = getExcelRows(workbook, startIndex, methods);
                return generatorList(excelRows, methods, clazz);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    private static <T> List<T> generatorList(List<Row> rows, List<SetMethodInfo> methods, Class<T> clazz) {
        if (CollectionUtils.isNotEmpty(methods) && CollectionUtils.isNotEmpty(rows)) {
            ArrayList resultList = new ArrayList();

            for (Integer i = Integer.valueOf(0); i.intValue() < rows.size(); i = Integer.valueOf(i.intValue() + 1)) {
                Row row = rows.get(i.intValue());

                try {
                    Object e = clazz.newInstance();

                    for (Integer j = Integer.valueOf(0); j.intValue() < methods.size(); j = Integer.valueOf(j.intValue() + 1)) {
                        SetMethodInfo setMethod = (SetMethodInfo) methods.get(j.intValue());
                        if (setMethod != null) {
                            try {
                                setMethod.setValue(e, row.getCell(j.intValue()), Integer.valueOf(row.getRowNum()));
                            } catch (Exception var10) {
                                log.error("行【" + (i.intValue() + 1) + "】列【" + (j.intValue() + 1) + "】设值失败！", var10);
                                throw new ExcelParseException("行【" + (i.intValue() + 1) + "】列【" + (j.intValue() + 1) + "】数据格式错误!");
                            }
                        }
                    }
                    resultList.add(e);
                } catch (Exception var11) {
                    log.error("初始化对象失败!", var11);
                    throw new ExcelParseException("初始化对象失败!" + var11.getMessage(), var11);
                }
            }

            return resultList;
        } else {
            return null;
        }
    }

    private static List<Row> getExcelRows(Workbook workbook, Integer startIndex, List<Integer> methods) {
        ArrayList resultList = new ArrayList();
        Sheet sheet = workbook.getSheetAt(0);
        if (sheet != null) {
            int rowIndex = startIndex.intValue();

            while (!ExcelCheckUtil.isEndRow(sheet.getRow(rowIndex), methods)) {
                Row row = sheet.getRow(rowIndex++);
                resultList.add(row);
            }
        }
        return resultList;
    }

    public static List<SetMethodInfo> getSetMethods(Class clazz, String proName) {
        ArrayList methodInfos = new ArrayList();
        List cofigerlist = PropertiesUtil.getInPropertyAsList(proName);
        Method[] methods = clazz.getMethods();
        HashMap methodMap = new HashMap();
        for (int i = 0; i < methods.length; ++i) {
            Method index = methods[i];
            if (index.isAnnotationPresent(Import.class)) {
                Import m = (Import) index.getAnnotation(Import.class);
                if (StringUtils.isNotBlank(m.index())) {
                    Integer index1 = Integer.valueOf(Integer.parseInt(m.index()));
                    ExcelType excelType = m.type();
                    if (methodMap.containsKey(index1)) {
                        throw new ExcelParseException("index【" + index1 + "】重复");
                    }

                    SetMethodInfo methodInfo = new SetMethodInfo();
                    methodInfo.setMethod(index);
                    methodInfo.setExcelType(excelType);
                    methodMap.put(index1, methodInfo);
                }
            }
        }

        if (CollectionUtils.isNotEmpty(cofigerlist)) {
            Iterator var12 = cofigerlist.iterator();

            while (var12.hasNext()) {
                Integer var13 = (Integer) var12.next();
                SetMethodInfo var14 = (SetMethodInfo) methodMap.get(var13);
                methodInfos.add(var14);
            }
        }
        return methodInfos;
    }


}
