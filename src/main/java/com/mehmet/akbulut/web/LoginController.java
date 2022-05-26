package com.mehmet.akbulut.web;


import com.mehmet.akbulut.model.Aday;
import com.mehmet.akbulut.model.InsanKaynaklari;
import com.mehmet.akbulut.service.AdayService;
import com.mehmet.akbulut.service.InsanKaynaklariService;
import com.mehmet.akbulut.util.HashUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
     private AdayService adayService;
    @Autowired
    private InsanKaynaklariService insanKaynaklariService;
    @GetMapping(value = "/")
    public String login(HttpServletRequest request,HttpServletResponse response) throws Exception{
        HttpSession session = request.getSession();
        Object insanKaynaklari = session.getAttribute("insanKaynaklari");
        if(insanKaynaklari != null) {
            response.sendRedirect(request.getContextPath() + "/personelListele");
        }
        return "login";

    }

    @GetMapping(value = {"/aday/login","/aday","/aday/"})
    public String adayLogin(HttpServletRequest request,HttpServletResponse response) throws Exception{
        HttpSession session = request.getSession();
        Object aday = session.getAttribute("aday");
        if(aday != null) {
            response.sendRedirect(request.getContextPath() + "/aday/adayIslemleri");
        }
        return "adayLogin";
    }
    @PostMapping(value = "/aday/loginKontrol")
    public @ResponseBody  String adayLoginKontrol(@RequestParam("email") String email, @RequestParam("password") String password, HttpServletRequest req, HttpServletResponse res) throws Exception{
        JSONObject jsonObject = new JSONObject();
        /**
         * İstek parametrelerinin boş kontrolü
         */
        if (email.equals("") || password.equals("")) {
            jsonObject.put("success",true);
            jsonObject.put("exist",false);
            jsonObject.put("icon","error");
            jsonObject.put("title","Zorunlu alanları doldurunuz");
            return jsonObject.toString();
        }
        HashUtil hashUtil = new HashUtil(password);
        String hashPassword = hashUtil.md5();

        Aday aday = adayService.loginKontrol(email,hashPassword);
        Boolean exists = false;
        if(aday != null) {
            exists = true;
        }

        if(exists){
            HttpSession session = req.getSession();
            session.setAttribute("aday",aday);
            session.setAttribute("adsoyad",aday.getAdSoyad());
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

    @GetMapping("/logout")
    public void insankaynaklariLogout (HttpServletRequest request,HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
       session.invalidate();
        response.sendRedirect(request.getContextPath());
    }

    @PostMapping(value = "/loginKontrol")
    public @ResponseBody  String loginKontrol(@RequestParam("email") String email, @RequestParam("password") String password, HttpServletRequest req, HttpServletResponse res) throws Exception{
        JSONObject jsonObject = new JSONObject();
        /**
         * İstek parametrelerinin boş kontrolü
         */
        if (email.equals("") || password.equals("")) {
            jsonObject.put("success",true);
            jsonObject.put("exist",false);
            jsonObject.put("icon","error");
            jsonObject.put("title","Zorunlu alanları doldurunuz");
            return jsonObject.toString();
        }
        HashUtil hashUtil = new HashUtil(password);
        String hashPassword = hashUtil.md5();



        InsanKaynaklari insanKaynaklari = insanKaynaklariService.loginKontrol(email,hashPassword);
        Boolean exists = false;
        if(insanKaynaklari != null) {
            exists = true;
        }


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
