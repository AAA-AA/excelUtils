package github.com.excel.core;

import github.com.excel.model.CellModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA ^_^
 * Author : renhongqiang
 * Date: 2017/7/1 10:21
 * Email: renhongqiang1397@gmail.com
 */
public class ExportExcel {
    private String sheetName;
    private List<Map<Integer, Object>> valueMapList;
    private List<Integer> indexes;
    private Map<String, Integer> methodIndexMap;
    private Map<Integer, CellModel> cellMap;

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public void setValueMapList(List<Map<Integer, Object>> valueMapList) {
        this.valueMapList = valueMapList;
    }

    public List<Integer> getIndexes() {
        return indexes;
    }

    public void setIndexes(List<Integer> indexes) {
        this.indexes = indexes;
    }

    public Map<String, Integer> getMethodIndexMap() {
        return methodIndexMap;
    }

    public void setMethodIndexMap(Map<String, Integer> methodIndexMap) {
        this.methodIndexMap = methodIndexMap;
    }

    public Map<Integer, CellModel> getCellMap() {
        return cellMap;
    }

    public void setCellMap(Map<Integer, CellModel> cellMap) {
        this.cellMap = cellMap;
    }

    public ExportExcel() {
    }

    public List<Map<Integer, Object>> getValueMapList() {
        if(null == this.valueMapList) {
            this.valueMapList = new ArrayList();
        }

        return this.valueMapList;
    }
}
