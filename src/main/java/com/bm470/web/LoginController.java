package com.bm470.web;


import com.bm470.model.InsanKaynaklari;
import com.bm470.service.InsanKaynaklariService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class LoginController {

    @Autowired
     private InsanKaynaklariService loginService;

    @GetMapping(value = "/")
    public String login(HttpServletRequest request,HttpServletResponse response) throws Exception{
        HttpSession session = request.getSession();
        Object admin = session.getAttribute("admin");
        if(admin != null) {
            response.sendRedirect("/personelListele");
        }
        return "login";

    }
    @GetMapping("/logout")
    public void logout (HttpServletRequest request,HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        session.invalidate();
        response.sendRedirect(request.getContextPath());
    }
    @PostMapping(value = "/loginKontrol")
    public @ResponseBody  String loginKontrol(@RequestParam String email, @RequestParam String password, HttpServletRequest req, HttpServletResponse res) throws Exception{

        InsanKaynaklari insanKaynaklari = loginService.loginKontrol(email,password);
        Boolean exists = false;
        if(insanKaynaklari != null) {
            exists = true;
        }

        JSONObject jsonObject = new JSONObject();
        if(exists){
            HttpSession session = req.getSession();
            session.setAttribute("insanKaynaklari",insanKaynaklari);
            session.setAttribute("adsoyad",insanKaynaklari.getAdSoyad());
            jsonObject.put("icon","success");
            jsonObject.put("title","Başarıyla giriş yaptınız");
        }
        else {
            jsonObject.put("icon","error");
            jsonObject.put("title","Hatalı giriş yaptınız");
        }

        jsonObject.put("success", true);
        jsonObject.put("exists", exists);

        return jsonObject.toString();

    }



}
