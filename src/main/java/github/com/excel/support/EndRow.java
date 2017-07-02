package github.com.excel.support;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

/**
 * Created by IntelliJ IDEA ^_^
 * Author : renhongqiang
 * Date: 2017/7/1 12:31
 * Email: renhongqiang1397@gmail.com
 */
public interface EndRow {
    boolean isEndRow(Sheet var1, Row var2, Integer var3);
}
