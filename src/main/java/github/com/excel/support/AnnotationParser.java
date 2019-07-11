package github.com.excel.support;

import github.com.excel.annotation.Export;
import github.com.excel.core.ExportExcel;
import github.com.excel.exception.ExportParamErrorTypeException;
import github.com.excel.model.CellModel;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA ^_^
 * Author : renhongqiang
 * Date: 2017/7/1 10:20
 * Email: renhongqiang1397@gmail.com
 */
public class AnnotationParser {
    private AnnotationParser() {
    }

    public static ExportExcel parseAnnotations(Class clazz, List<Integer> indexes) {
        ExportExcel excel = new ExportExcel();
        HashMap methodIndexMap = new HashMap();
        HashMap map = new HashMap();
        Field[] fields = clazz.getDeclaredFields();
        for (int i = 0; i < fields.length; ++i) {
            Field f = fields[i];
            if (f.isAnnotationPresent(Export.class)) {
                Export e = f.getAnnotation(Export.class);
                int index = checkIndex(clazz, f, e);
                if (null == indexes || indexes.contains(Integer.valueOf(index))) {
                    CellModel cell = new CellModel();
                    methodIndexMap.put(f.getName(), Integer.valueOf(index));
                    cell.setFieldName(f.getName());
                    String description = "";
                    if (null != e.description() && !e.description().equals("")) {
                        description = e.description();
                    }
                    cell.setDescription(description);
                    if (null != e.cellWidth()) {
                        try {
                            Integer e2 = Integer.valueOf(Integer.parseInt(e.cellWidth()));
                            if (e2.intValue() < 1) {
                                throw new ExportParamErrorTypeException(String.format("[%s.%s]Export.cellWidth属性值必须是正整数", clazz.toString(), f.getName()));
                            }

                            cell.setCellWidth(e2);
                        } catch (Exception var16) {
                            throw new ExportParamErrorTypeException(String.format("[%s.%s]Export.cellWidth属性值必须是正整数", clazz.toString(), f.getName()));
                        }
                    }

                    if (e.isLink().equals(Export.ISLINK.YES)) {
                        cell.setIsLink(Boolean.valueOf(true));
                    } else {
                        cell.setIsLink(Boolean.valueOf(false));
                    }

                    map.put(Integer.valueOf(index), cell);
                }
            }
        }
        excel.setCellMap(map);
        excel.setMethodIndexMap(methodIndexMap);
        return excel;
    }

    public static Map<Integer, String> parseMethod(Class clazz) {
        HashMap map = new HashMap();
        Field[] ms = clazz.getDeclaredFields();
        Field[] var3 = ms;
        int var4 = ms.length;

        for (int var5 = 0; var5 < var4; ++var5) {
            Field m = var3[var5];
            if (m.isAnnotationPresent(Export.class)) {
                Annotation an = m.getAnnotation(Export.class);
                Export e = (Export) an;
                int index = checkIndex(clazz, m, e);
                map.put(Integer.valueOf(index), m.getName());
            }
        }

        return map;
    }

    public static Map<Integer, String> parseDescription(Class clazz) {
        HashMap map = new HashMap();
        Field[] ms = clazz.getDeclaredFields();
        Field[] var3 = ms;
        int var4 = ms.length;

        for (int var5 = 0; var5 < var4; ++var5) {
            Field m = var3[var5];
            if (m.isAnnotationPresent(Export.class)) {
                Annotation an = m.getAnnotation(Export.class);
                Export e = (Export) an;
                String methodName = "";
                if (null != e.description() && !e.description().equals("")) {
                    methodName = e.description();
                } else if (m.getName().startsWith("get")) {
                    methodName = m.getName().replaceFirst("get", "");
                }

                int index = checkIndex(clazz, m, e);
                map.put(Integer.valueOf(index), methodName);
            }
        }

        return map;
    }

    private static int checkIndex(Class clazz, Field m, Export e) {
        boolean index = false;
        if (null != e.index()) {
            try {
                int index1 = Integer.parseInt(e.index());
                if (index1 < 0) {
                    throw new ExportParamErrorTypeException("[" + clazz.toString() + "." + m.getName() + "]Export.index属性值必须是正整数");
                } else {
                    return index1;
                }
            } catch (Exception var5) {
                throw new ExportParamErrorTypeException("[" + clazz.toString() + "." + m.getName() + "]Export.index属性值必须是正整数");
            }
        } else {
            throw new ExportParamErrorTypeException("[" + clazz.toString() + "." + m.getName() + "]Export.index属性未赋值");
        }
    }
}
