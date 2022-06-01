package com.sjzx.exception;

import com.alibaba.fastjson.JSONObject;
import com.sjzx.model.Response;
import com.sjzx.model.enums.ErrorEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 拦截程序错误信息，返回json给前端
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public Response defaultErrorHandler(HttpServletRequest req, Exception e) {
        String errorPosition = "";
        //如果错误堆栈信息存在
        if (e.getStackTrace().length > 0) {
            StackTraceElement element = e.getStackTrace()[0];
            String fileName = element.getFileName() == null ? "未找到错误文件" : element.getFileName();
            int lineNumber = element.getLineNumber();
            errorPosition = fileName + ":" + lineNumber;
        }
        Response response = new Response(e.getMessage(), ErrorEnum.E_500.getErrorCode());

        JSONObject errorObject = new JSONObject();
        errorObject.put("errorLocation", e.toString() + "    错误位置:" + errorPosition);
        response.setData(errorObject);
        log.error("异常", e);
        return response;
    }


    /**
     * GET/POST请求方法错误的拦截器
     * 因为开发时可能比较常见,而且发生在进入controller之前,上面的拦截器拦截不到这个错误
     * 所以定义了这个拦截器
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Response httpRequestMethodHandler() {
        return new Response(ErrorEnum.E_500.getErrorMsg(), ErrorEnum.E_500.getErrorCode());
    }


    //效验框架Validate效验异常
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Response validationError(MethodArgumentNotValidException e) {
        log.info("Validate框架效验异常MethodArgumentNotValidException:{}", e.getMessage());
        return Response.fail(e.getBindingResult().getFieldError().getDefaultMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public Response illegalArgumentExceptionHandler(IllegalArgumentException e){
        log.info("illegalArgumentExceptionHandler:{}", e.getMessage());
        return Response.fail(e.getMessage());
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public Response missingServletRequestParameterExceptionHandler(MissingServletRequestParameterException e){
        log.info("missingServletRequestParameterExceptionHandler:{}", e.getMessage());
        return Response.fail(e.getMessage());
    }

    @ExceptionHandler(ServiceException.class)
    public Response serviceExceptionHandler(ServiceException e){
        log.info("serviceExceptionHandler:{}", e.getMessage());
        return Response.fail(e.getMessage());
    }

}
