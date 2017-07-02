import github.com.excel.ExcelCheckUtil;
import github.com.excel.ExportUtil;
import github.com.excel.core.ExcelImport;
import test.Student;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA ^_^
 * Author : renhongqiang
 * Date: 2017/7/1 12:34
 * Email: renhongqiang1397@gmail.com
 */
public class ExcelTest {

    public static void main(String[] args) {
        String path = "/Users/hongqiangren./Downloads/study";
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
        ExportUtil.doExport(path,list,filename,Student.class);

        File file = new File("/Users/hongqiangren./Downloads/study/demo.xls");
        InputStream is = null;
        try {
            is = new FileInputStream(file);
            Workbook workBook = ExcelCheckUtil.create(is);
            List<Student> students = ExcelImport.parseExcelToList(workBook, Student.class, "demo");
            System.out.println(students.get(0).toString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
    }
}
