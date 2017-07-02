package github.com.excel.exception;

/**
 * Created by IntelliJ IDEA ^_^
 * Author : renhongqiang
 * Date: 2017/7/1 10:28
 * Email: renhongqiang1397@gmail.com
 */
public class ExcelParseException extends RuntimeException{

    public ExcelParseException() {
    }

    public ExcelParseException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExcelParseException(String message) {
        super(message);
    }

    public ExcelParseException(Throwable cause) {
        super(cause);
    }
}
