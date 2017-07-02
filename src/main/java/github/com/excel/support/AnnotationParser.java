package github.com.excel.support;

import github.com.excel.annotation.Export;
import github.com.excel.core.ExportExcel;
import github.com.excel.exception.ExportParamErrorTypeException;
import github.com.excel.model.CellModel;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
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
        Method[] ms = clazz.getMethods();
        for (int i = 0; i < ms.length; ++i) {
            Method m = ms[i];
            if (m.isAnnotationPresent(Export.class)) {
                Export e = m.getAnnotation(Export.class);
                int index = checkIndex(clazz, m, e);
                if (null == indexes || indexes.contains(Integer.valueOf(index))) {
                    CellModel cell = new CellModel();
                    methodIndexMap.put(m.getName(), Integer.valueOf(index));
                    cell.setMethodName(m.getName());
                    String description = "";
                    if (null != e.description() && !e.description().equals("")) {
                        description = e.description();
                    } else if (m.getName().startsWith("get")) {
                        description = m.getName().replaceFirst("get", "");
                    }

                    cell.setDescription(description);
                    if (null != e.cellWidth()) {
                        try {
                            Integer e2 = Integer.valueOf(Integer.parseInt(e.cellWidth()));
                            if (e2.intValue() < 1) {
                                throw new ExportParamErrorTypeException(String.format("[%s.%s]Export.cellWidth属性值必须是正整数", clazz.toString(), m.getName()));
                            }

                            cell.setCellWidth(e2);
                        } catch (Exception var16) {
                            throw new ExportParamErrorTypeException(String.format("[%s.%s]Export.cellWidth属性值必须是正整数", clazz.toString(), m.getName()));
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
        Method[] ms = clazz.getDeclaredMethods();
        Method[] var3 = ms;
        int var4 = ms.length;

        for (int var5 = 0; var5 < var4; ++var5) {
            Method m = var3[var5];
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
        Method[] ms = clazz.getDeclaredMethods();
        Method[] var3 = ms;
        int var4 = ms.length;

        for (int var5 = 0; var5 < var4; ++var5) {
            Method m = var3[var5];
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

    private static int checkIndex(Class clazz, Method m, Export e) {
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
