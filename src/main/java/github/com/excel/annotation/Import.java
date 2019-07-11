package github.com.excel.annotation;

import github.com.excel.support.ExcelType;

import java.lang.annotation.*;

/**
 * Created by IntelliJ IDEA ^_^
 * Author : renhongqiang
 * Date: 2017/7/1 10:16
 * Email: renhongqiang1397@gmail.com
 */

/**
 * the function of following annotation is to import excel
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target({ElementType.FIELD})
public @interface Import {

    String index();

    ExcelType type() default ExcelType.String;

}
