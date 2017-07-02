package github.com.excel.exception;

/**
 * Created by IntelliJ IDEA ^_^
 * Author : renhongqiang
 * Date: 2017/7/1 10:27
 * Email: renhongqiang1397@gmail.com
 */
public class ExportParamErrorTypeException extends RuntimeException{
    public ExportParamErrorTypeException() {
    }

    public ExportParamErrorTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExportParamErrorTypeException(String message) {
        super(message);
    }

    public ExportParamErrorTypeException(Throwable cause) {
        super(cause);
    }
}
