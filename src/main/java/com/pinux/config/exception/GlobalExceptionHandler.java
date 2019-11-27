package com.pinux.config.exception;

import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;

import java.sql.SQLException;

/**
 * * ┏┓　　　┏┓
 * ┏┛┻━━━┛┻┓
 * ┃　　　　　　　┃
 * ┃　　　━　　　┃
 * ┃　┳┛　┗┳　┃
 * ┃　　　　　　　┃
 * ┃　　　┻　　　┃
 * ┃　　　　　　　┃
 * ┗━┓　　　┏━┛
 * 　　┃　　　┃神兽保佑
 * 　　┃　　　┃代码无BUG！
 * 　　┃　　　┗━━━┓
 * 　　┃　　　　　　　┣┓
 * 　　┃　　　　　　　┏┛
 * 　　┗┓┓┏━┳┓┏┛
 * 　　　┃┫┫　┃┫┫
 * 　　　┗┻┛　┗┻┛
 *
 * @ClassName GlobalExceptionHandler
 * @Description TODO
 * @Author pinux
 * @Date 2019/11/4 10:14
 * @Version 1.0
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    Logger log = LoggerFactory.getLogger(this.getClass());

    @ResponseBody
    @ExceptionHandler(SQLException.class)
    public String handSql(Exception e) {
        log.error("***sql执行异常***", e);
        return "sql执行异常："+e.getMessage();
    }


    @ExceptionHandler(MultipartException.class)
    @ResponseBody
    public String handleUpload(MultipartException e) {
        log.error("***文件异常***", e);
        return  "文件异常:"+e.getMessage();
    }


    @ExceptionHandler(Exception.class)
    @ResponseBody
    public String defaultErrorHandler(Exception ex) throws Exception {
        log.error("***错误异常***", ex);
        return "错误异常"+ ex.getMessage();
    }

    @ExceptionHandler(value = AuthorizationException.class)
    public String handleAuthorizationException() {
        return "403";
    }
}
