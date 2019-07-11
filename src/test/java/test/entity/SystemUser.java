package test.entity;


import github.com.excel.annotation.Export;
import github.com.excel.annotation.Import;
import lombok.Data;

@Data
public class SystemUser {
    @Import(index = "0")
    @Export(description = "姓名",index = "0")
    private String name;
    @Import(index = "1")
    @Export(description = "帐号",index = "1")
    private String email;

    @Import(index = "2")
    @Export(description = "工号",index = "2")
    private String employeeCode;

    @Import(index = "3")
    @Export(description = "权限",index = "3")
    private String security;

    @Import(index = "4")
    @Export(description = "部门",index = "4")
    private String department;

    @Import(index = "5")
    @Export(description = "岗位",index = "5")
    private String job;

    @Import(index = "6")
    @Export(description = "状态",index = "6")
    private String status;

}
