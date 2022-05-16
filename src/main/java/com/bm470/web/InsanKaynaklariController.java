package com.bm470.web;

import com.bm470.model.InsanKaynaklari;
import com.bm470.service.InsanKaynaklariService;
import com.bm470.util.HashUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class InsanKaynaklariController {
    @Autowired
    private InsanKaynaklariService insanKaynaklariService;

    @GetMapping(value = "/insanKaynaklariListele")
    public String insanKaynaklariListele(Model model) {
        model.addAttribute("title","İnsan Kaynakları İşlemleri");
        List<InsanKaynaklari> insanKaynaklariList = insanKaynaklariService.insanKaynaklariListele();
        model.addAttribute("insanKaynaklariList",insanKaynaklariList);
        return "insanKaynaklariListele";
    }
    @GetMapping(value = "/insanKaynaklariEkle")
    public String insanKaynaklariEkle(Model model) {
        model.addAttribute("title","İnsan Kaynakları Ekle");
        return "insanKaynaklariEkle";
    }
    @PostMapping(value = "/insanKaynaklariKaydet")
    public @ResponseBody String insanKaynaklariKaydet (@RequestParam("isim") String isim,@RequestParam("soyisim") String soyIsim,
                                                       @RequestParam("email") String email,@RequestParam("password") String password) {
        JSONObject jsonObject = new JSONObject();
        if(isim.equals("") || soyIsim.equals("") || email.equals("") || password.equals("")) {
            jsonObject.put("success",true);
            jsonObject.put("exist",false);
            jsonObject.put("icon","error");
            jsonObject.put("title","Zorunlu alanları doldurunuz");
            return jsonObject.toString();
        }
        HashUtil hashUtil = new HashUtil(password);
        String hashPassword = hashUtil.md5();
        Boolean exist = insanKaynaklariService.insanKaynaklariKaydet(isim,soyIsim,email,hashPassword);
        if(exist) {
            jsonObject.put("icon","success");
            jsonObject.put("title","Ekleme işlemi başarılı");
        }
        else {
            jsonObject.put("icon","error");
            jsonObject.put("title","Hata");
        }
        jsonObject.put("success",true);
        jsonObject.put("exist",exist);
        return  jsonObject.toString();
    }
    @GetMapping(value = "/insanKaynaklariGuncelle/{id}")
    public String insanKaynaklariGuncelle(Model model,@PathVariable("id") long id) {
        model.addAttribute("title","İnsan Kaynakları Güncelle");
        InsanKaynaklari insanKaynaklari = insanKaynaklariService.insanKaynaklariLoad(id);
        model.addAttribute("insanKaynaklari",insanKaynaklari);
        return "insanKaynaklariGuncelle";
    }
    @PostMapping(value = "/insanKaynaklariUpdate")
    public @ResponseBody String insanKaynaklariUpdate (@RequestParam("id") Long id,
                                                       @RequestParam("isim") String isim,
                                                       @RequestParam("soyisim") String soyisim,
                                                       @RequestParam("email") String email,
                                                       @RequestParam("password") String password) {
        JSONObject jsonObject = new JSONObject();
        if(id.equals(0L)||isim.equals("") || soyisim.equals("") || email.equals("") || password.equals("")) {
            jsonObject.put("success",true);
            jsonObject.put("exist",false);
            jsonObject.put("icon","error");
            jsonObject.put("title","Zorunlu alanları doldurunuz");
            return jsonObject.toString();
        }
        InsanKaynaklari insanKaynaklari = insanKaynaklariService.insanKaynaklariLoad(id);
        HashUtil hashUtil;
        String hashPassword;
        if(!password.equals(insanKaynaklari.getIkSifre())) {
            hashUtil = new HashUtil(password);
             hashPassword = hashUtil.md5();
        }
        else  {
            hashPassword = password;
        }

        Boolean exist = insanKaynaklariService.insanKaynaklariUpdate(id,isim,soyisim,email,hashPassword);

        if(exist) {
            jsonObject.put("icon","success");
            jsonObject.put("title","Başarı ile güncellendi");
        }
        else {
            jsonObject.put("icon","error");
            jsonObject.put("title","Hata");
        }
        jsonObject.put("success",true);
        jsonObject.put("exist",exist);
        return jsonObject.toString();
    }
    @PostMapping(value = "/insanKaynaklariSil")
    public @ResponseBody String insanKaynaklariSil (@RequestParam("id") long id) {
        Boolean exist = insanKaynaklariService.insanKaynaklariSil(id);
        JSONObject jsonObject = new JSONObject();
        if(exist) {
            jsonObject.put("icon","success");
            jsonObject.put("title","Silme işlemi başarılı");
        }
        else {
            jsonObject.put("icon","error");
            jsonObject.put("title","Hata");
        }
        jsonObject.put("success",true);
        jsonObject.put("exist",exist);
        return jsonObject.toString();
    }
}
