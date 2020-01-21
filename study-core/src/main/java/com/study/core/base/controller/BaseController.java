package com.study.core.base.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 */
public class BaseController {

    protected static String SUCCESS = "SUCCESS";

    protected static String ERROR = "ERROR";

    protected static String REDIRECT = "redirect";

    protected static String FORWARD = "forward";



    protected HttpServletRequest getHttpServletRequest(){
        return null;
    }

    protected HttpServletResponse getHttpServletResponse(){
        return null;
    }

    protected HttpSession getSession(){
        return null;
    }

    protected HttpSession getSession(Boolean flag){
        return null;
    }

    protected String getPara(String name) {
        return null;
    }

    protected void setAttr(String name, Object value) {

    }


    protected Integer getSystemInvokCount(){
        return  null;
    }




}
