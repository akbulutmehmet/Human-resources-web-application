package com.bm470.web;

import com.bm470.model.Departman;
import com.bm470.service.DepartmanService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class DepartmanController {

    @Autowired
    private DepartmanService departmanService;
    @GetMapping(value = "/departmanListele")
    public String departmanListele (Model model) {
        model.addAttribute("title","Departman İşlemleri");
        List<Departman> departmanList = departmanService.departmanListele();
        model.addAttribute("departmanList",departmanList);
        return "departmanListele";
    }
    @GetMapping(value = "/departmanEkle")
    public String departmanEkle (Model model) {
        model.addAttribute("title","Departman Ekle");

        return "departmanEkle";
    }
    @PostMapping(value = "/departmanKaydet")
    public @ResponseBody String departmanKaydet (@RequestParam ("departmanAdi") String departmanAdi) {
        JSONObject jsonObject = new JSONObject();
        /**
         * İstek parametrelerinin boş kontrolü
         */
        if(departmanAdi.equals("")) {
            jsonObject.put("success",true);
            jsonObject.put("exist",false);
            jsonObject.put("icon","error");
            jsonObject.put("title","Zorunlu alanları doldurunuz");
            return jsonObject.toString();
        }
        Boolean exist = departmanService.departmanKaydet(departmanAdi);
        if(exist) {
            jsonObject.put("icon","success");
            jsonObject.put("title","Departman Başarıyla Kaydedildi");
        }
        else {
            jsonObject.put("icon","error");
            jsonObject.put("title","Hata");
        }
        jsonObject.put("success",true);
        jsonObject.put("exist",exist);

        return jsonObject.toString();
    }
    @GetMapping(value = "/departmanGuncelle/{id}")
    public String departmanGuncelle (Model model, @PathVariable("id") long id) {
        model.addAttribute("title","Departman Güncelle");
        Departman departman = departmanService.departmanLoad(id);
        model.addAttribute("departman",departman);
        return "departmanGuncelle";
    }
    @PostMapping(value = "/departmanUpdate")
    public @ResponseBody String departmanUpdate (@RequestParam("departmanId") Long departmanId,
                                                 @RequestParam("departmanAdi") String departmanAdi) {
        JSONObject jsonObject = new JSONObject();
        /**
         * İstek parametrelerinin boş kontrolü
         */
        if(departmanAdi.equals("") || departmanId.equals("")) {
            jsonObject.put("success",true);
            jsonObject.put("exist",false);
            jsonObject.put("icon","error");
            jsonObject.put("title","Zorunlu alanları doldurunuz");
            return jsonObject.toString();
        }
        Boolean exist = departmanService.departmanUpdate(departmanId,departmanAdi);

        if(exist) {
            jsonObject.put("icon","success");
            jsonObject.put("title","Departman Başarıyla Güncellendi");
        }
        else {
            jsonObject.put("icon","error");
            jsonObject.put("title","Hata");
        }
        jsonObject.put("success",true);
        jsonObject.put("exist",exist);

        return jsonObject.toString();
    }
    @PostMapping(value = "/departmanSil")
    public @ResponseBody String departmanSil (@RequestParam("id") long id) {
        Boolean exist = departmanService.departmanSil(id);
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
