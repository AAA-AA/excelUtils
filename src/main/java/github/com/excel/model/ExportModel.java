package github.com.excel.model;

import org.apache.poi.ss.util.CellRangeAddress;

import java.util.List;

/**
 * Created by IntelliJ IDEA ^_^
 * Author : renhongqiang
 * Date: 2017/7/1 10:25
 * Email: renhongqiang1397@gmail.com
 */
public class ExportModel<T> {
    private List<T> dataList;
    private String sheetName;
    private String configName;
    private String headContent;
    private Class clazz;
    private CellRangeAddress cellRangeAddress;

    public ExportModel() {
    }

    public List<T> getDataList() {
        return this.dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }

    public String getSheetName() {
        return this.sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public String getHeadContent() {
        return this.headContent;
    }

    public void setHeadContent(String headContent) {
        this.headContent = headContent;
    }

    public CellRangeAddress getCellRangeAddress() {
        return this.cellRangeAddress;
    }

    public void setCellRangeAddress(CellRangeAddress cellRangeAddress) {
        this.cellRangeAddress = cellRangeAddress;
    }

    public String getConfigName() {
        return this.configName;
    }

    public void setConfigName(String configName) {
        this.configName = configName;
    }

    public Class getClazz() {
        return this.clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }
}
