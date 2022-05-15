package com.bm470.interceptor;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.http.HttpResponse;

@Component
public class SessionInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        Object insanKaynaklari = session.getAttribute("insanKaynaklari");
        if (insanKaynaklari == null){
            /*
            * Giriş yapılmamış is 404 sayfasını kullanıcıya gösteriyor.
            *
            */

            /*
            response.setStatus(HttpStatus.NOT_FOUND);
            request.getRequestDispatcher("WEB-INF/view/404.jsp").forward(request,response);
            *

             */

            //Session içinde veri  yoksa 404 sayfasını oluşturmak için exception fırlatır.
            throw new NoHandlerFoundException(request.getMethod(),request.getRequestURI(),null);
            //return false;
        }

        return true;
    }
}
