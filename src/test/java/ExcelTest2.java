import github.com.excel.ExcelCheckUtil;
import github.com.excel.ExcelUtils;
import github.com.excel.core.ExcelImport;
import github.com.excel.entity.CoffeUser;
import github.com.excel.entity.SystemUser;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import test.Student;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA ^_^
 * Author : renhongqiang
 * Date: 2017/7/1 12:34
 * Email: renhongqiang1397@gmail.com
 */
public class ExcelTest2 {

    public static void main(String[] args) {
        String path = "/Users/hongqiangren./Downloads/excel_transfer";//According to your system path
        String original = "system.xls";

        String target = "target.xls";

        String finalTagetExcel = "CRM各功能模块.xls";

        File file = new File("/Users/hongqiangren./Downloads/excel_transfer/CRM.xls");//According to your system path

        File file2 = new File("/Users/hongqiangren./Downloads/excel_transfer/target.xls");


        InputStream is = null;
        InputStream is2 = null;
        try {
            is = new FileInputStream(file);
            Workbook workBook = ExcelCheckUtil.create(is);
            List<SystemUser> users = ExcelImport.parseExcelToList(workBook, SystemUser.class, "sys");//map to the file of excel_map.properties
            System.out.println(users.get(0).toString());
            is.close();

            Map<String, CoffeUser> coffeUserMap = getStringCoffeUserMap(file2);

            users.forEach(e -> {
                CoffeUser coffeUser = coffeUserMap.get(e.getEmployeeCode());
                if (coffeUser == null) {
                    e.setJob("#N/A");
                    e.setStatus("停用");
                }else {
                    e.setJob(coffeUser.getJob());
                    e.setStatus("在职");
                }
            });

            ExcelUtils.doExport(path,users,finalTagetExcel,SystemUser.class);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
    }

    private static Map<String, CoffeUser> getStringCoffeUserMap(File file2) throws IOException, InvalidFormatException {
        InputStream is2;
        is2 = new FileInputStream(file2);
        Workbook workBook2 = ExcelCheckUtil.create(is2);

        List<CoffeUser> coffeUsers = ExcelImport.parseExcelToList(workBook2, CoffeUser.class, "coffee");
        Set<String> set = new HashSet<>();
        for (CoffeUser user:coffeUsers) {
            set.add(user.getEmployeeCode());
        }
        is2.close();


        return coffeUsers.stream().distinct().collect(Collectors.toMap(CoffeUser::getEmployeeCode, CoffeUser -> CoffeUser));
    }
}
