package com.bm470.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class SessionInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        Object admin = session.getAttribute("admin");
        if (admin == null){
            /*
            * Giriş yapılmamış is 404 sayfasını kullanıcıya gösteriyor.
            *
            */
            request.getRequestDispatcher("WEB-INF/view/404.jsp").forward(request,response);
            return false;
        }

        return true;
    }
}
