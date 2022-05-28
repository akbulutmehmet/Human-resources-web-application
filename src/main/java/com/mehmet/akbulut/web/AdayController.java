package com.mehmet.akbulut.web;

import com.mehmet.akbulut.model.Aday;
import com.mehmet.akbulut.service.AdayService;
import com.mehmet.akbulut.util.HashUtil;
import com.mehmet.akbulut.util.TcCheck;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class AdayController {
    @Autowired
    private AdayService adayService;
    @PostMapping("/aday/adayGuncelle")
    public @ResponseBody String AdayGuncelle (@RequestParam(value = "adayId") Long adayId,
                                              @RequestParam("adayAd") String adayAd,
                                              @RequestParam("adaySoyad") String adaySoyad,
                                              @RequestParam("adayEposta") String adayEposta,
                                              @RequestParam(value = "adaySifre",required = false) String adaySifre,
                                              @RequestParam("adayCinsiyet") Long adayCinsiyet,
                                              @RequestParam("adayDogumTarihi") String adayDogumTarihi,
                                              HttpServletRequest request) {
            JSONObject jsonObject = new JSONObject();
        Boolean exist=false;
        if(adayAd.equals("") || adaySoyad.equals("") || adayEposta.equals("")
                || adayCinsiyet.equals(0L)
        ) {
            jsonObject.put("success",true);
            jsonObject.put("exist",exist);
            jsonObject.put("icon","error");
            jsonObject.put("title","Zorunlu alanları doldurunuz");
            return jsonObject.toString();
        }
        Aday aday = (Aday) request.getSession().getAttribute("aday");
        if(!adaySifre.equals("")) {
            HashUtil hashUtil = new HashUtil(adaySifre);
            adaySifre = hashUtil.md5();
        }
        else {
            adaySifre = aday.getAdaySifre();
        }
        exist = adayService.adayGuncelle(adayId,adayAd,adaySoyad,adayCinsiyet,adayEposta,adaySifre,adayDogumTarihi);
        if(exist) {
            request.getSession().setAttribute("aday",adayService.adayLoad(adayId));
            jsonObject.put("icon","success");
            jsonObject.put("title","Güncellendi");
        }
        else {
            jsonObject.put("icon","error");
            jsonObject.put("title","Hata");
        }
        jsonObject.put("success",true);
        jsonObject.put("exist",exist);
        return jsonObject.toString();
    }

    @GetMapping("/aday/adayIslemleri")
    public String adayIslemleri (Model model, HttpServletRequest request, HttpServletResponse response) {
        model.addAttribute("title","Kişisel Bilgileriniz");
        Aday aday = (Aday) request.getSession().getAttribute("aday");
        model.addAttribute("aday",aday);
        return "adayIslemleri";
    }
    @GetMapping("/aday/register")
    public String adayRegisterPage(Model model) {
        model.addAttribute("title","Aday Üye OL");
        return  "adayRegister";
    }

    @PostMapping("/aday/adayRegister")
    public @ResponseBody String adayRegister (@RequestParam(value = "adayId",required = false) Long adayId,
                                              @RequestParam("adayAd") String adayAd,
                                              @RequestParam("adaySoyad") String adaySoyad,
                                              @RequestParam("adayEposta") String adayEposta,
                                              @RequestParam("adaySifre") String adaySifre,
                                              @RequestParam("adayCinsiyet") Long adayCinsiyet,
                                              @RequestParam("adayTc") Long adayTc,
                                              @RequestParam("adayDogumTarihi") String adayDogumTarihi) {
        JSONObject jsonObject = new JSONObject();
        Boolean exist=false;
        if(adayAd.equals("") || adaySoyad.equals("") || adayEposta.equals("") || adaySifre.equals("")
        || adayCinsiyet.equals(0L) || adayTc.equals(0L)
        ) {
            jsonObject.put("success",true);
            jsonObject.put("exist",exist);
            jsonObject.put("icon","error");
            jsonObject.put("title","Zorunlu alanları doldurunuz");
            return jsonObject.toString();
        }

        TcCheck tcCheck = new TcCheck();
        tcCheck.setTcNo(adayTc);
        HashUtil hashUtil = new HashUtil(adaySifre);
        adaySifre = hashUtil.md5();
        if(tcCheck.tcCheck()) {
            exist = adayService.adayKaydet(adayId,adayAd,adaySoyad,adayTc,adayCinsiyet,adayEposta,adaySifre,adayDogumTarihi);
        }
        if(exist) {
            jsonObject.put("icon","success");
            jsonObject.put("title","Üyelik Başarıyla Oluşturuldu. Lütfen Giriş Yapınız");
        }
        else {
            jsonObject.put("icon","error");
            jsonObject.put("title","Hata");
        }
        jsonObject.put("success",true);
        jsonObject.put("exist",exist);
        return jsonObject.toString();
    }


    @PostMapping(value = "/aday/adayTcKontrol")
    public @ResponseBody String adayTcKontrol (@RequestParam("adayTc") Long adayTc) {
        JSONObject jsonObject = new JSONObject();
        TcCheck tcCheck = new TcCheck();
        tcCheck.setTcNo(adayTc);
        Boolean exist = false;
        if(tcCheck.tcCheck()) {
            exist = adayService.adayTcKontrol(adayTc);
        }
        if(!exist) {
            jsonObject.put("icon","error");
            jsonObject.put("title","Hatalı Tc girdiniz!");
            return jsonObject.toString();
        }
        jsonObject.put("exist",exist);
        jsonObject.put("icon","success");
        jsonObject.put("title","Girdiğiniz TC Doğru");
        return jsonObject.toString();
    }
}
