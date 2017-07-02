package test;

import github.com.excel.annotation.Export;
import github.com.excel.annotation.Import;
import github.com.excel.support.ExcelType;

import java.util.Date;

/**
 * Created by IntelliJ IDEA ^_^
 * Author : renhongqiang
 * Date: 2017/7/1 12:46
 * Email: renhongqiang1397@gmail.com
 */
public class Student {

    private int id;

    private String name;

    private int sex;

    private Date birthday;

    private String company;

    @Export(description = "id", index = "0")
    public int getId() {
        return id;
    }

    @Import(index = "1")
    public void setId(int id) {
        this.id = id;
    }

    @Export(description = "名称", index = "1")
    public String getName() {
        return name;
    }

    @Import(index = "2",type = ExcelType.String)
    public void setName(String name) {
        this.name = name;
    }

    @Export(description = "性别", index = "2")
    public int getSex() {
        return sex;
    }

    @Import(index = "3",type = ExcelType.String)
    public void setSex(int sex) {
        this.sex = sex;
    }

    @Export(description = "生日", index = "3", type = ExcelType.Date)
    public Date getBirthday() {
        return birthday;
    }

    @Import(index = "4",type = ExcelType.Date)
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Export(description = "公司名称", index = "4")
    public String getCompany() {
        return company;
    }

    @Import(index = "5",type = ExcelType.String)
    public void setCompany(String company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                ", birthday=" + birthday +
                ", company='" + company + '\'' +
                '}';
    }
}
