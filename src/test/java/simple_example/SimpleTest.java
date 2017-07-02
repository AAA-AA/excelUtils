package simple_example;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA ^_^
 * Author : renhongqiang
 * Date: 2017/7/2 10:44
 * Email: renhongqiang1397@gmail.com
 */
public class SimpleTest {

    public static void main(String[] args) {

        try {
            String filePath = "/Users/hongqiangren./Downloads/study/test.xls";/*定义路径*/
            List<List<String>> data = new ArrayList<List<String>>();
            for (int i = 1; i <= 30; i++) {
                List rowData = new ArrayList();
                rowData.add(String.valueOf(i));
                if(i % 2 == 0) {
                    rowData.add("0");
                    rowData.add("0");
                    rowData.add("0");
                    rowData.add("0");
                    rowData.add("0");
                    rowData.add("0");
                    rowData.add("0");
                }else {
                    rowData.add("1");
                    rowData.add("1");
                    rowData.add("1");
                    rowData.add("1");
                    rowData.add("1");
                    rowData.add("1");
                    rowData.add("1");
                }
                data.add(rowData);
            }
            String[] headers = { "项目", "1、通道是否畅通无阻" ,"2、警示标志齐全、完好",
                    "3、上下开启、动作正常","4、疏齿板无破损或无异物夹入","5、扶手带无破损及驰速适合",
                    "6、整机运转正常、无噪音","7、整机外观整洁无损坏"};

            String sheetTitle = "上海";

            ExportExcelUtils.exportExcel2(0, sheetTitle, headers, data, filePath);
          /*  eeu.exportExcel(workbook, 1, "深圳", headers, data, out);
            eeu.exportExcel(workbook, 2, "广州", headers, data, out);*/
            //原理就是将所有的数据一起写入，然后再关闭输入流。
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
