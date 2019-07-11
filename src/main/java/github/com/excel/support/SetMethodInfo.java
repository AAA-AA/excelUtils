package github.com.excel.support;

import github.com.excel.exception.ExcelParseException;
import github.com.excel.utils.ExcelUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.time.DateUtils;
import org.apache.poi.ss.usermodel.Cell;

import java.lang.reflect.Field;
import java.text.ParseException;

/**
 * Created by IntelliJ IDEA ^_^
 * Author : renhongqiang
 * Date: 2017/7/1 12:30
 * Email: renhongqiang1397@gmail.com
 */
@Slf4j
public class SetMethodInfo {
    private Field field;
    private Class parameterType;
    private ExcelType excelType;
    public static final String EXCEL_TYPE_STRING = "String";
    public static final String EXCEL_TYPE_DATE = "Date";

    public SetMethodInfo() {
    }

    public void setValue(Object instance, Cell cell, Integer rowNum) {
        Object value = this.getCellValue(cell);
        this.field.setAccessible(true);

        try {
            if (value == null) {
                if (this.field.getName().equals("setRowNum")) {
                    this.field.setInt(instance, rowNum);
                }
            } else if (parameterType == Integer.TYPE) {
                this.field.setInt(instance, Integer.valueOf((String) value));
            } else if (parameterType == Short.TYPE) {
                this.field.setShort(instance, Short.valueOf((String) value));
            } else if (parameterType == Float.TYPE) {
                this.field.setFloat(instance, Float.valueOf((String) value));
            } else if (parameterType == Double.TYPE) {
                this.field.setDouble(instance, Double.valueOf((String) value));
            } else if (parameterType == Long.TYPE) {
                this.field.setLong(instance, Long.valueOf((String) value));
            } else if (parameterType == Byte.TYPE) {
                this.field.setByte(instance, Byte.valueOf((String) value));
            } else {
                this.field.set(instance, value);
            }

        } catch (Exception var8) {
            log.error("设值失败！", var8);
            throw new ExcelParseException("设值失败！", var8);
        }

    }

    private Object getCellValue(Cell cell) {
        if (cell != null) {
            if (cell.getCellType() == 2) {
                return String.valueOf(cell.getNumericCellValue());
            }

            if (this.excelType == ExcelType.String) {
                return ExcelUtils.getValueForString(cell);
            }

            if (this.excelType == ExcelType.Date) {
                String[] patterns = {"yyyy-MM-dd HH:mm:ss"};
                try {
                    return DateUtils.parseDate(cell.getStringCellValue(), patterns);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

    private Object getNullValue() {
        return this.parameterType != Integer.TYPE && this.parameterType != Byte.TYPE && this.parameterType != Short.TYPE ? (this.parameterType == Long.TYPE ? Long.valueOf(0L) : (this.parameterType == Float.TYPE ? Float.valueOf(0.0F) : (this.parameterType == Double.TYPE ? Double.valueOf(0.0D) : (this.parameterType == Boolean.TYPE ? Boolean.valueOf(false) : null)))) : Integer.valueOf(0);
    }


    public Field getField() {
        return this.field;
    }

    public void setField(Field field) {
        this.parameterType = field.getType();
        this.field = field;
    }

    public ExcelType getExcelType() {
        return this.excelType;
    }

    public void setExcelType(ExcelType excelType) {
        this.excelType = excelType;
    }
}
