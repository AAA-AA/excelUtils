package github.com.excel.entity;

import github.com.excel.annotation.Export;
import github.com.excel.annotation.Import;

public class CoffeUser {

    private String employeeCode;
    private String name;
    private String workCity;
    private String firstLevDep;
    private String secondLevDep;
    private String thirdLevDep;
    private String fourthLevDep;
    private String fifthLevDep;
    private String sixthLevDep;
    private String employeeType;
    private String job;//岗位

    @Export(description = "人员编码",index ="0")
    public String getEmployeeCode() {
        return employeeCode;
    }

    @Import(index = "0")
    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    @Export(description = "姓名",index ="1")
    public String getName() {
        return name;
    }
    @Import(index = "1")
    public void setName(String name) {
        this.name = name;
    }

    @Export(description = "工作城市",index ="2")
    public String getWorkCity() {
        return workCity;
    }

    @Import(index = "2")
    public void setWorkCity(String workCity) {
        this.workCity = workCity;
    }

    @Export(description = "一级部门",index ="3")
    public String getFirstLevDep() {
        return firstLevDep;
    }
    @Import(index = "3")
    public void setFirstLevDep(String firstLevDep) {
        this.firstLevDep = firstLevDep;
    }

    @Export(description = "二级部门",index ="4")
    public String getSecondLevDep() {
        return secondLevDep;
    }

    @Import(index = "4")
    public void setSecondLevDep(String secondLevDep) {
        this.secondLevDep = secondLevDep;
    }

    @Export(description = "三级部门",index ="5")
    public String getThirdLevDep() {
        return thirdLevDep;
    }

    @Import(index = "5")
    public void setThirdLevDep(String thirdLevDep) {
        this.thirdLevDep = thirdLevDep;
    }

    @Export(description = "四级部门",index ="6")
    public String getFourthLevDep() {
        return fourthLevDep;
    }

    @Import(index = "6")
    public void setFourthLevDep(String fourthLevDep) {
        this.fourthLevDep = fourthLevDep;
    }

    @Export(description = "五级部门",index ="7")
    public String getFifthLevDep() {
        return fifthLevDep;
    }

    @Import(index = "7")
    public void setFifthLevDep(String fifthLevDep) {
        this.fifthLevDep = fifthLevDep;
    }

    @Export(description = "六级部门",index ="8")
    public String getSixthLevDep() {
        return sixthLevDep;
    }

    @Import(index = "8")
    public void setSixthLevDep(String sixthLevDep) {
        this.sixthLevDep = sixthLevDep;
    }

    @Export(description = "人员类别",index ="9")
    public String getEmployeeType() {
        return employeeType;
    }

    @Import(index = "9")
    public void setEmployeeType(String employeeType) {
        this.employeeType = employeeType;
    }

    @Export(description = "岗位",index ="10")
    public String getJob() {
        return job;
    }

    @Import(index = "10")
    public void setJob(String job) {
        this.job = job;
    }

    @Override
    public boolean equals(Object obj) {
        return getEmployeeCode().equals(employeeCode);
    }

    @Override
    public int hashCode() {
        return employeeCode.hashCode();
    }
}
