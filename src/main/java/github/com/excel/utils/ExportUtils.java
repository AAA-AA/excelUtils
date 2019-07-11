package github.com.excel.utils;

import github.com.excel.annotation.Export;
import github.com.excel.core.ExportExcel;
import github.com.excel.model.CellModel;
import github.com.excel.support.AnnotationParser;
import github.com.excel.support.ExcelType;
import org.apache.commons.lang.time.DateFormatUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by IntelliJ IDEA ^_^
 * Author : renhongqiang
 * Date: 2017/7/1 12:28
 * Email: renhongqiang1397@gmail.com
 */
public class ExportUtils {
    public ExportUtils() {
    }

    public static ExportExcel parseBean(List values, Class clazz, List<Integer> indexes) {
        if(null == clazz && null != values && values.size() > 0) {
            clazz = values.get(0).getClass();
        }

        if(null == clazz) {
            return null;
        } else {
            ExportExcel ee = AnnotationParser.parseAnnotations(clazz, indexes);
            ee.setIndexes(indexes);
            Iterator iterator = values.iterator();
            while(iterator.hasNext()) {
                Object obj = iterator.next();
                parseValueByAnnotation(obj, ee);
            }
            return ee;
        }
    }

    private static void parseValueByAnnotation(Object obj, ExportExcel ee) {
        Map cellMap = ee.getCellMap();
        if(null != cellMap && cellMap.size() > 0) {
            HashMap valueMap = new HashMap();
            //todo
            Iterator iterator = cellMap.entrySet().iterator();
            while(iterator.hasNext()) {
                Map.Entry cell = (Map.Entry)iterator.next();
                try {
                    Field e1 = obj.getClass().getDeclaredField(((CellModel)cell.getValue()).getFieldName());
                    e1.setAccessible(true);
                    Export e = e1.getAnnotation(Export.class);
                    Object value = e1.get(obj);
                    if (e != null) {
                        if (e.type() == ExcelType.Date) {
                            String dateStr = DateFormatUtils.format((Date) value, e.pattern());
                            valueMap.put(cell.getKey(),dateStr);
                        }else {
                            valueMap.put(cell.getKey(), value);
                        }
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e.getMessage());
                }
            }
            ee.getValueMapList().add(valueMap);
        }

    }
}
