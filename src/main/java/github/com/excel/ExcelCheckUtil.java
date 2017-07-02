package github.com.excel;

import github.com.excel.utils.ExcelUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.util.List;

/**
 * Created by IntelliJ IDEA ^_^
 * Author : renhongqiang
 * Date: 2017/7/2 00:18
 * Email: renhongqiang1397@gmail.com
 */
public class ExcelCheckUtil {
    public static Workbook create(InputStream in) throws IOException, InvalidFormatException {
        if (!in.markSupported()) {
            in = new PushbackInputStream(in, 8);
        }
        if (POIFSFileSystem.hasPOIFSHeader(in)) {
            return new HSSFWorkbook(in);
        }
        if (POIXMLDocument.hasOOXMLHeader(in)) {
            return new XSSFWorkbook(OPCPackage.open(in));
        }
        throw new IllegalArgumentException("你的excel版本目前poi解析不了");

    }

    public static boolean isEndRow(Row row, List<Integer> methods) {
        if (row == null) {
            return true;
        } else {
            int nullNum = 0;

            for (int i = 0; i < methods.size(); ++i) {
                if (row.getCell(i) == null || StringUtils.isBlank(ExcelUtils.getValueForString(row.getCell(i)))) {
                    ++nullNum;
                }
            }

            if (methods.size() == nullNum) {
                return true;
            } else {
                return false;
            }
        }
    }
}
