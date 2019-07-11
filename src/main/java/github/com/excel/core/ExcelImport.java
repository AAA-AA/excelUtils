package github.com.excel.core;

import github.com.excel.ExcelCheckUtil;
import github.com.excel.annotation.Import;
import github.com.excel.exception.ExcelParseException;
import github.com.excel.support.EndRow;
import github.com.excel.support.ExcelType;
import github.com.excel.support.SetMethodInfo;
import github.com.excel.utils.PropertiesUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.lang.reflect.Field;
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
@Slf4j
public class ExcelImport {

    private ExcelImport() {
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
                    Object instance = clazz.newInstance();

                    for (Integer j = Integer.valueOf(0); j.intValue() < methods.size(); j = Integer.valueOf(j.intValue() + 1)) {
                        SetMethodInfo setMethod = (SetMethodInfo) methods.get(j.intValue());
                        if (setMethod != null) {
                            try {
                                setMethod.setValue(instance, row.getCell(j.intValue()), Integer.valueOf(row.getRowNum()));
                            } catch (Exception var10) {
                                log.error("行【" + (i.intValue() + 1) + "】列【" + (j.intValue() + 1) + "】设值失败！", var10);
                                throw new ExcelParseException("行【" + (i.intValue() + 1) + "】列【" + (j.intValue() + 1) + "】数据格式错误!");
                            }
                        }
                    }
                    resultList.add(instance);
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
        Field[] fields = clazz.getDeclaredFields();
        HashMap methodMap = new HashMap();
        for (int i = 0; i < fields.length; ++i) {
            Field field = fields[i];
            if (field.isAnnotationPresent(Import.class)) {
                Import m = (Import) field.getAnnotation(Import.class);
                if (StringUtils.isNotBlank(m.index())) {
                    Integer index = Integer.valueOf(Integer.parseInt(m.index()));
                    ExcelType excelType = m.type();
                    if (methodMap.containsKey(index)) {
                        throw new ExcelParseException("index【" + index + "】重复");
                    }

                    SetMethodInfo methodInfo = new SetMethodInfo();
                    methodInfo.setField(field);
                    methodInfo.setExcelType(excelType);
                    methodMap.put(index, methodInfo);
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
