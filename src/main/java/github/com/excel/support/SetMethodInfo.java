package github.com.excel.support;

import github.com.excel.exception.ExcelParseException;
import github.com.excel.utils.ExcelUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.ss.usermodel.Cell;

import java.lang.reflect.Method;
import java.text.ParseException;

/**
 * Created by IntelliJ IDEA ^_^
 * Author : renhongqiang
 * Date: 2017/7/1 12:30
 * Email: renhongqiang1397@gmail.com
 */
public class SetMethodInfo {
    private static final Log log = LogFactory.getLog(SetMethodInfo.class);
    private Method method;
    private Class parameterType;
    private ExcelType excelType;
    public static final String EXCEL_TYPE_STRING = "String";
    public static final String EXCEL_TYPE_DATE = "Date";

    public SetMethodInfo() {
    }

    public void setValue(Object obj, Cell cell, Integer rowNum) {
        Object value = this.getCellValue(cell);
        if(value == null) {
            try {
                if(this.method.getName().equals("setRowNum")) {
                    this.method.invoke(obj, new Object[]{rowNum});
                } else {
                    this.method.invoke(obj, new Object[]{this.getNullValue()});
                }
            } catch (Exception var8) {
                log.error("设值失败！", var8);
                throw new ExcelParseException("设值失败！", var8);
            }
        } else if(value.getClass() == this.parameterType) {
            try {
                this.method.invoke(obj, new Object[]{value});
            } catch (Exception var7) {
                log.error("设值失败！", var7);
                throw new ExcelParseException("设值失败！", var7);
            }
        } else {
            try {
                this.method.invoke(obj, new Object[]{this.getValue(value.toString())});
            } catch (Exception var6) {
                log.error("设值失败！", var6);
                throw new ExcelParseException("设值失败！方法【" + this.method.getName() + "】,值【" + value + "】", var6);
            }
        }

    }

    private Object getCellValue(Cell cell) {
        if(cell != null) {
            if(cell.getCellType() == 2) {
                return String.valueOf(cell.getNumericCellValue());
            }

            if(this.excelType == ExcelType.String) {
                return ExcelUtils.getValueForString(cell);
            }

            if(this.excelType == ExcelType.Date) {
                String[] patterns = {"yyyy-MM-dd HH:mm:ss"};
                try {
                    return DateUtils.parseDate(cell.getStringCellValue(),patterns);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

    private Object getNullValue() {
        return this.parameterType != Integer.TYPE && this.parameterType != Byte.TYPE && this.parameterType != Short.TYPE?(this.parameterType == Long.TYPE?Long.valueOf(0L):(this.parameterType == Float.TYPE?Float.valueOf(0.0F):(this.parameterType == Double.TYPE?Double.valueOf(0.0D):(this.parameterType == Boolean.TYPE?Boolean.valueOf(false):null)))):Integer.valueOf(0);
    }

    private Object getValue(String value) {
        if(this.parameterType != Integer.TYPE && this.parameterType != Integer.class) {
            if(this.parameterType != Long.TYPE && this.parameterType != Long.class) {
                if(this.parameterType != Double.class && this.parameterType != Double.TYPE) {
                    if(this.parameterType != Float.TYPE && this.parameterType != Float.class) {
                        if(this.parameterType != Byte.TYPE && this.parameterType != Byte.class) {
                            if(this.parameterType != Short.TYPE && this.parameterType != Short.class) {
                                return value;
                            } else {
                                if(value.indexOf(".") != -1) {
                                    value = value.substring(0, value.indexOf("."));
                                }

                                return new Short(value);
                            }
                        } else {
                            if(value.indexOf(".") != -1) {
                                value = value.substring(0, value.indexOf("."));
                            }

                            return new Byte(value);
                        }
                    } else {
                        return new Float(value);
                    }
                } else {
                    return new Double(value);
                }
            } else {
                if(value.indexOf(".") != -1) {
                    value = value.substring(0, value.indexOf("."));
                }

                return new Long(value);
            }
        } else {
            if(value.indexOf(".") != -1) {
                value = value.substring(0, value.indexOf("."));
            }

            return new Integer(value);
        }
    }

    public Method getMethod() {
        return this.method;
    }

    public void setMethod(Method method) {
        this.method = method;
        Class[] types = method.getParameterTypes();
        if(types.length != 1) {
            throw new ExcelParseException("方法不符合set方法规范");
        } else {
            this.parameterType = types[0];
        }
    }

    public ExcelType getExcelType() {
        return this.excelType;
    }

    public void setExcelType(ExcelType excelType) {
        this.excelType = excelType;
    }
}
