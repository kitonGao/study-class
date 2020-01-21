package com.study.core.config;

import com.study.core.base.controller.StudyErrorView;
import com.study.core.exception.StudyException;
import com.study.core.exception.StudyExceptionEnum;
import com.study.core.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.support.ConfigurableWebBindingInitializer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import java.util.Date;
import java.util.regex.Pattern;


@Configuration
public class DefaultWebConfig extends WebMvcConfigurationSupport{

    @Autowired
    private RequestMappingHandlerAdapter handlerAdapter;


    @Bean("error")
    public StudyErrorView error(){
        return new StudyErrorView();
    }


    public void addConversionConfig(){
        ConfigurableWebBindingInitializer initializer = (ConfigurableWebBindingInitializer) handlerAdapter.getWebBindingInitializer();
        GenericConversionService genericConversionService =  (GenericConversionService)initializer.getConversionService();
        genericConversionService.addConverter(new StringToDateConverter());
    }


    public class StringToDateConverter implements Converter<String, Date> {

        @Nullable
        @Override
        public Date convert(String source) {
            String patternDate = "\\d{4}-\\d{1,2}-\\d{1,2}";
            String patternTimeMinutes = "\\d{4}-\\d{1,2}-\\d{1,2} \\d{1,2}:\\d{1,2}";
            String patternTimeSeconds = "\\d{4}-\\d{1,2}-\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}";

            boolean dateFlag = Pattern.matches(patternDate, source);
            boolean timeMinutesFlag = Pattern.matches(patternTimeMinutes, source);
            boolean timeSecondsFlag = Pattern.matches(patternTimeSeconds, source);

            if (dateFlag) {
                return DateUtil.parseDate(source);
            } else if (timeMinutesFlag) {
                return DateUtil.parseTimeMinutes(source);
            } else if (timeSecondsFlag) {
                return DateUtil.parseTime(source);
            } else {
                throw new StudyException(StudyExceptionEnum.INVLIDE_DATE_STRING);
            }
        }
    }




}
