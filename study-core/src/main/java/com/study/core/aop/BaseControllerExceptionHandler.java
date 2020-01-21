package com.study.core.aop;

import com.study.core.base.tips.ErrorTip;
import com.study.core.exception.StudyException;
import com.study.core.exception.StudyExceptionEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 全局异常拦截器(拦截所有的控制器)(带有@RequestMapping注解的方法上都会拦截)
 */
public class BaseControllerExceptionHandler {

    private Logger LOGGER = LoggerFactory.getLogger(BaseControllerExceptionHandler.class);


    /**
     * 拦截业务异常
     * @param e
     * @return
     */
    @ExceptionHandler(StudyException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorTip notFound(StudyException e) {
        LOGGER.error("业务异常:", e.getMessage());
        return new ErrorTip(e.getCode(), e.getMessage());
    }


    /**
     * 拦截未知的运行时异常
     * @param e
     * @return
     */
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorTip notFound(RuntimeException e) {
        LOGGER.error("运行时异常：", e);
        return new ErrorTip(StudyExceptionEnum.SERVER_ERROR.getCode(), StudyExceptionEnum.SERVER_ERROR.getMessage());
    }

}
