package test;

import github.com.excel.annotation.Export;
import github.com.excel.annotation.Import;
import github.com.excel.support.ExcelType;

/**
 * Created by IntelliJ IDEA ^_^
 * Author : renhongqiang
 * Date: 2017/7/1 12:46
 * Email: renhongqiang1397@gmail.com
 */
public class Student2 {
    @Import(index = "1")
    @Export(description = "id", index = "0")
    private int id;

    @Import(index = "2",type = ExcelType.String)
    @Export(description = "名称", index = "1")
    private String name;

    @Import(index = "3",type = ExcelType.String)
    @Export(description = "性别", index = "2")
    private int sex;

    @Import(index = "4",type = ExcelType.Date)
    @Export(description = "生日", index = "3", type = ExcelType.String)
    private String birthday;

    @Import(index = "5",type = ExcelType.String)
    @Export(description = "公司名称", index = "4")
    private String company;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getCompany() {
        return company;
    }

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
