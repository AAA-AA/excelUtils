package github.com.excel.annotation;

import github.com.excel.support.ExcelType;

import java.lang.annotation.*;

/**
 * Created by IntelliJ IDEA ^_^
 * Author : renhongqiang
 * Date: 2017/7/1 10:06
 * Email: renhongqiang1397@gmail.com
 */


/**
 *export annotation
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface Export {
    String TAG_START = "<";
    String TAG_END = ">";

    /***
     * title
     * @return
     */
    String description();

    /**
     * the location which column located
     * @return
     */
    String index();

    /**
     * the width of cell
     * @return
     */
    String cellWidth() default "5000";

    /**
     * export type
     * @return
     */
    ExcelType type() default ExcelType.String;

    /**
     * when export type is date, if you want to get self-pattern time, you can use it
     * @return
     */
    String pattern() default "yyyy-MM-dd HH:mm:ss";

    Export.ISLINK isLink() default Export.ISLINK.NO;

    public static enum ISLINK {
        YES,
        NO;

        private ISLINK() {
        }
    }
}
