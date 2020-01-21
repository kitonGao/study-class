package com.study.core.xss;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

public class XssFilter implements Filter{

    FilterConfig filterConfig = null;

    private List<String> urlExclusion = null;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void destroy() {
        this.filterConfig = null;
    }


    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain filterChain) throws IOException, ServletException {

       HttpServletRequest httpServletRequest = (HttpServletRequest)request;
        String servletPath = httpServletRequest.getServletPath();
        if (urlExclusion != null && urlExclusion.contains(servletPath)) {
            filterChain.doFilter(request, response);
        } else {
            filterChain.doFilter(new XssHttpServletRequestWrapper((HttpServletRequest)request), response);
        }
    }

    public List<String> getUrlExclusion() {
        return urlExclusion;
    }

    public void setUrlExclusion(List<String> urlExclusion) {
        this.urlExclusion = urlExclusion;
    }
}
