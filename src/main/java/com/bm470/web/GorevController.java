package com.bm470.web;

import com.bm470.model.Departman;
import com.bm470.model.Gorev;
import com.bm470.service.DepartmanService;
import com.bm470.service.GorevService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class GorevController {
    @Autowired
    private GorevService gorevService;

    @Autowired
    private DepartmanService departmanService;

    @GetMapping(value = "/gorevListele")
    public String gorevListele (Model model) {
        model.addAttribute("title","Görev İşlemleri");
        List<Gorev> gorevList = gorevService.gorevListele();
        model.addAttribute("gorevList",gorevList);
        return "gorevListele";
    }
    @GetMapping(value = "/gorevEkle")
    public String gorevEkle (Model model) {
        model.addAttribute("title","Görev Ekle");
        List<Departman> departmanList = departmanService.departmanListele();
        model.addAttribute("departmanList",departmanList);
        return "gorevEkle";
    }
    @PostMapping(value = "/gorevKaydet")
    public @ResponseBody String gorevKaydet (@RequestParam("departmanId") Long departmanId,
                                             @RequestParam("gorevAdi") String gorevAdi) {
        JSONObject jsonObject = new JSONObject();
        if(departmanId.equals(0L) || gorevAdi.equals("")) {
            jsonObject.put("success",true);
            jsonObject.put("exist",false);
            jsonObject.put("icon","error");
            jsonObject.put("title","Zorunlu alanları doldurunuz");
            return jsonObject.toString();
        }
        Departman departman = departmanService.departmanLoad(departmanId);
        Boolean exist = gorevService.gorevKaydet(departman,gorevAdi);

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
    @GetMapping(value = "/gorevGuncelle/{id}")
    public String gorevGuncelle (Model model, @PathVariable("id") long gorevId) {
        model.addAttribute("title","Görev Güncelle");
        Gorev gorev = gorevService.gorevLoad(gorevId);
        List<Departman> departmanList = departmanService.departmanListele();
        model.addAttribute("gorev",gorev);
        model.addAttribute("departmanList",departmanList);
        return "gorevGuncelle";
    }

    @PostMapping(value = "/gorevUpdate")
    public @ResponseBody String gorevUpdate (@RequestParam("departmanId") Long departmanId,
                                                 @RequestParam("gorevId") Long gorevId,
                                             @RequestParam("gorevAdi") String gorevAdi) {
        JSONObject jsonObject = new JSONObject();
        if(departmanId.equals(0L) || gorevAdi.equals("") || gorevId.equals(0L)) {
            jsonObject.put("success",true);
            jsonObject.put("exist",false);
            jsonObject.put("icon","error");
            jsonObject.put("title","Zorunlu alanları doldurunuz");
            return jsonObject.toString();
        }
        Departman departman = departmanService.departmanLoad(departmanId);

        Boolean exist = gorevService.gorevUpdate(departman,gorevAdi,gorevId);
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
    @PostMapping(value = "/gorevSil")
    public @ResponseBody String gorevSil (@RequestParam("id") long id) {
        Boolean exist = gorevService.gorevSil(id);
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
    @PostMapping(value = "/gorevGetir")
    public @ResponseBody String gorevGetir (@RequestParam("departmanId") long departmanId) {
      //  Departman departman = departmanService.departmanLoad(departmanId);
        JSONObject jsonObject = new JSONObject();
        List<Gorev> gorevList = gorevService.gorevGetir(departmanId);

        String gorevler = "";
        for (Gorev gorev : gorevList) {
            gorevler += "<option value='" + gorev.getGorevId() + "'>" + gorev.getGorevAdi() + "</option>";
        }
        gorevler += "<option value='0' selected>" + "GÖREV SEÇİMİ YAPINIZ" + "</option>";
        jsonObject.put("gorevler",gorevler);
        return jsonObject.toString();
    }
}
