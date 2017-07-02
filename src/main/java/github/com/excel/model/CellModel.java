package github.com.excel.model;

/**
 * Created by IntelliJ IDEA ^_^
 * Author : renhongqiang
 * Date: 2017/7/1 10:23
 * Email: renhongqiang1397@gmail.com
 */
public class CellModel {
    private Integer cellWidth;
    private Boolean isLink;
    private String methodName;
    private String description;

    public CellModel() {
    }

    public String getMethodName() {
        return this.methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Integer getCellWidth() {
        return this.cellWidth;
    }

    public void setCellWidth(Integer cellWidth) {
        this.cellWidth = cellWidth;
    }

    public Boolean getIsLink() {
        return this.isLink;
    }

    public void setIsLink(Boolean isLink) {
        this.isLink = isLink;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
