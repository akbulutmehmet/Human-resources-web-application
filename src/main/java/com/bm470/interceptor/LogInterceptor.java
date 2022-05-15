package com.bm470.interceptor;

import com.bm470.web.PersonelController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.security.Key;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.logging.Handler;

@Component
public class LogInterceptor extends HandlerInterceptorAdapter {

    private static Logger logger;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        setLogger(handlerMethod.getBeanType());
        String log = "Class : " + handlerMethod.getBeanType().getName();
        log += " Metod adi : " ;
        log += method.getName().toString() + " - ";
        log += "Geri donus tipi : " + method.getReturnType().getName() + " - ";
        log += "Request url : " + request.getRequestURL();

            Map<String,String[]> parameterMap = request.getParameterMap();
            Set<String> setKey = parameterMap.keySet();
        Iterator <String> iterator = setKey.iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            log +="- " +  "Paratmere adi : " + key + " - ";
            String [] value = parameterMap.get(key);
            for(int i=0 ; i<value.length;i++) {
                log +="Parametre degeri : " + value[i] + " -  ";
            }
        }
        logger.info(log);



        return super.preHandle(request, response, handler);
    }

    public void setLogger (Class clss) {
        logger = LoggerFactory.getLogger(clss);
    }
}
