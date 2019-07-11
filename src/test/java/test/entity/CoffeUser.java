package test.entity;

import github.com.excel.annotation.Export;
import github.com.excel.annotation.Import;
import lombok.Data;

@Data
public class CoffeUser {
    @Import(index = "0")
    @Export(description = "人员编码",index ="0")
    private String employeeCode;
    @Import(index = "1")
    @Export(description = "姓名",index ="1")
    private String name;
    @Import(index = "2")
    @Export(description = "工作城市",index ="2")
    private String workCity;

    @Import(index = "3")
    @Export(description = "一级部门",index ="3")
    private String firstLevDep;

    @Import(index = "4")
    @Export(description = "二级部门",index ="4")
    private String secondLevDep;

    @Import(index = "5")
    @Export(description = "三级部门",index ="5")
    private String thirdLevDep;

    @Import(index= "6")
    @Export(description = "四级部门",index ="6")
    private String fourthLevDep;

    @Import(index = "7")
    @Export(description = "五级部门",index ="7")
    private String fifthLevDep;

    @Import(index = "8")
    @Export(description = "六级部门",index ="8")
    private String sixthLevDep;

    @Import(index = "9")
    @Export(description = "人员类别",index ="9")
    private String employeeType;

    @Import(index = "10")
    @Export(description = "岗位",index ="10")
    private String job;//岗位
}
