package github.com.excel.entity;


import github.com.excel.annotation.Export;
import github.com.excel.annotation.Import;

public class SystemUser {
    private String name;
    private String email;
    private String employeeCode;
    private String security;
    private String department;
    private String job;
    private String status;

    @Export(description = "姓名",index = "0")
    public String getName() {
        return name;
    }
    @Import(index = "0")
    public void setName(String name) {
        this.name = name;
    }

    @Export(description = "帐号",index = "1")
    public String getEmail() {
        return email;
    }
    @Import(index = "1")
    public void setEmail(String email) {
        this.email = email;
    }

    @Export(description = "工号",index = "2")
    public String getEmployeeCode() {
        return employeeCode;
    }

    @Import(index = "2")
    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    @Export(description = "权限",index = "3")
    public String getSecurity() {
        return security;
    }

    @Import(index = "3")
    public void setSecurity(String security) {
        this.security = security;
    }
    @Export(description = "部门",index = "4")
    public String getDepartment() {
        return department;
    }

    @Import(index = "4")
    public void setDepartment(String department) {
        this.department = department;
    }

    @Export(description = "岗位",index = "5")
    public String getJob() {
        return job;
    }
    @Import(index = "5")
    public void setJob(String job) {
        this.job = job;
    }
    @Export(description = "状态",index = "6")
    public String getStatus() {
        return status;
    }

    @Import(index = "6")
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "SystemUser{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", employeeCode='" + employeeCode + '\'' +
                ", security='" + security + '\'' +
                ", department='" + department + '\'' +
                ", job='" + job + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
