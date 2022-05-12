package com.bm470.web;

import com.bm470.model.Departman;
import com.bm470.model.Gorev;
import com.bm470.model.Personel;
import com.bm470.service.DepartmanService;
import com.bm470.service.GorevService;
import com.bm470.service.PersonelService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PersonelController {
    @Autowired
    private PersonelService personelService;

    @Autowired
    private GorevService gorevService;

    @Autowired
    private DepartmanService departmanService;


    @GetMapping(value = "/personelListele")
    public String personelListele(Model model){
        model.addAttribute("title","Personel İşlemleri");
       List<Personel> personelList = personelService.personelListele();
        model.addAttribute("personelList",personelList);
        return "personelListele";
    }
    @PostMapping(value = "/personelKaydet")
    public @ResponseBody String personelKaydet (
            @RequestParam(value = "personelId",required = false) long personelId,
            @RequestParam("personelAd") String personelAd,
            @RequestParam("personelSoyad") String personelSoyad,
            @RequestParam("personelTc") long personelTc,
            @RequestParam("personelCinsiyet") String personelCinsiyet,
            @RequestParam("personelMaas") long personelMaas,
            @RequestParam("isBaslangicTarihi") String isBaslangicTarihi,
            @RequestParam("personelGorevId") long personelGorevId
    ) {
        JSONObject jsonObject = new JSONObject();
        Gorev gorev = gorevService.gorevLoad(personelGorevId);
        Boolean exist = personelService.personelKaydet(personelId,personelAd,personelSoyad,personelTc,personelMaas,isBaslangicTarihi,gorev,personelCinsiyet);
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
    @GetMapping(value = "/personelEkle")
    public String personelEkle (Model model) {
        List<Departman> departmanList =  departmanService.departmanListele();
        model.addAttribute("title","YENİ PERSONEL EKLE");
        model.addAttribute("departmanList",departmanList);
        return "personelEkle";
    }
    @GetMapping(value = "/personelGuncelle/{personelId}")
    public String personelGuncelle (@PathVariable("personelId") long personelId,Model model){
        Personel personel = personelService.personelLoad(personelId);
        model.addAttribute("personel",personel);
        List<Departman> departmanList = departmanService.departmanListele();
        model.addAttribute("departmanList",departmanList);
        return "personelGuncelle";
    }

    @PostMapping(value = "/personelSil")
    public @ResponseBody String personelSil (@RequestParam ("personelId") long personelId){

        Boolean exist = personelService.personelSil(personelId);
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
