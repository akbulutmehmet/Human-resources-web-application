package com.bm470.web;

import com.bm470.dao.DepartmanDAO;
import com.bm470.model.Departman;
import com.bm470.model.Gorev;
import com.bm470.model.IzinliPersonel;
import com.bm470.model.Personel;
import com.bm470.service.DepartmanService;
import com.bm470.service.GorevService;
import com.bm470.service.IzinliPersonelService;
import com.bm470.service.PersonelService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class IzinliPersonelController {
    @Autowired
    private IzinliPersonelService izinliPersonelService;

    @Autowired
    private DepartmanService departmanService;

    @Autowired
    private PersonelService personelService;

    @GetMapping(value = "/izinliPersonelListele")
    public String izinliPersonelListele(Model model){
        model.addAttribute("title","İzinli Personel İşlemleri");
            List<IzinliPersonel> izinliPersonelList = izinliPersonelService.izinliPersonelListele();
        model.addAttribute("izinliPersonelList",izinliPersonelList);
        return "izinliPersonelListele";
    }
    @GetMapping(value = "/izinliPersonelEkle")
    public String izinliPersonelEkle(Model model){
        model.addAttribute("title","İzinli Personel Ekle");
        List<Departman> departmanList = departmanService.departmanListele();
        model.addAttribute("departmanList",departmanList);
        return "izinliPersonelEkle";
    }
    @GetMapping(value = "/izinliPersonelGuncelle/{izinliPersonelId}")
    public String izinliPersonelGuncelle(Model model, @PathVariable("izinliPersonelId") long izinliPersonelId){
        IzinliPersonel izinliPersonel = izinliPersonelService.izinliPersonelLoad(izinliPersonelId);
        model.addAttribute("izinliPersonel",izinliPersonel);
        model.addAttribute("title","İzinli Personel Guncelle");

        return "izinliPersonelGuncelle";
    }
    @PostMapping(value = "/izinliPersonelKaydet")
    public @ResponseBody
    String izinliPersonelKaydet (
            @RequestParam(value = "izinliPersonelId",required = false) Long izinliPersonelId,
            @RequestParam("izinBaslangicTarihi") String izinBaslangicTarihi,
            @RequestParam("izinBitisTarihi") String izinBitisTarihi,
            @RequestParam("personelId") Long personelId) {

        JSONObject jsonObject = new JSONObject();
        if(izinBaslangicTarihi.equals("") || izinBitisTarihi.equals("") || personelId.equals(0L))
        {
            jsonObject.put("success",true);
            jsonObject.put("exist",false);
            jsonObject.put("icon","error");
            jsonObject.put("title","Zorunlu alanları doldurunuz");
            return jsonObject.toString();
        }
        Personel personel = personelService.personelLoad(personelId);
        if(personel.getPersonelIzinHakki() == null  || personel.getPersonelIzinHakki() <= 0L) {
            jsonObject.put("success",true);
            jsonObject.put("exist",false);
            jsonObject.put("icon","error");
            jsonObject.put("title","Personelin İzin Hakkı Yoktur");
            return jsonObject.toString();
        }
        Boolean exist = izinliPersonelService.izinliPersonelKaydet(izinliPersonelId,izinBaslangicTarihi,izinBitisTarihi,personelId);
        if(exist) {
            jsonObject.put("icon","success");
            jsonObject.put("title","Personel İzni Ekleme işlemi başarılı");
        }
        else {
            jsonObject.put("icon","error");
            jsonObject.put("title","Hata");
        }
        jsonObject.put("success",true);
        jsonObject.put("exist",exist);
        return jsonObject.toString();
    }
    @PostMapping(value = "/izinliPersonelSil")
    public @ResponseBody String izinliPersonelSil (@RequestParam ("izinliPersonelId") long izinliPersonelId){

        Boolean exist = izinliPersonelService.izinliPersonelSil(izinliPersonelId);
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
