import github.com.excel.ExcelCheckUtil;
import github.com.excel.ExcelUtils;
import github.com.excel.core.ExcelImport;
import test.Student;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.*;
import java.util.*;

/**
 * Created by IntelliJ IDEA ^_^
 * Author : renhongqiang
 * Date: 2017/7/1 12:34
 * Email: renhongqiang1397@gmail.com
 */
public class ExcelTest {

    public static void main(String[] args) {
        String path = "/Users/hongqiangren./Downloads/study";//According to your system path
        String filename = "demo1.xls";
        List<Student> list = new ArrayList<>();
        for (int i = 1;i <= 5;i++) {
            Student stu = new Student();
            stu.setId(i);
            stu.setBirthday(new Date());
            stu.setName("lala"+i);
            stu.setCompany(RandomStringUtils.randomAlphabetic(4));
            list.add(stu);
        }
        ExcelUtils.doExport(path,list,filename,Student.class);
       /* Map<String,List> valueMapList = new HashMap<>();
        valueMapList.put("sheet1",list);
        valueMapList.put("sheet2",list);
        ExcelUtils.doExport(path,filename,valueMapList);*/



       File file = new File("/Users/hongqiangren./Downloads/study/demo1.xls");//According to your system path
        InputStream is = null;
        try {
            is = new FileInputStream(file);
            Workbook workBook = ExcelCheckUtil.create(is);
            List<Student> students = ExcelImport.parseExcelToList(workBook, Student.class, "demo");//map to the file of excel_map.properties
            System.out.println(students.get(0).toString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
    }
}
