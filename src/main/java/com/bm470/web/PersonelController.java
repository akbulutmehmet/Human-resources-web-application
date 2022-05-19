package com.bm470.web;

import com.bm470.model.Departman;
import com.bm470.model.Gorev;
import com.bm470.model.Personel;
import com.bm470.service.DepartmanService;
import com.bm470.service.GorevService;
import com.bm470.service.PersonelService;
import com.bm470.util.TcCheck;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class PersonelController {
    @Autowired
    private PersonelService personelService;

    @Autowired
    private GorevService gorevService;

    @Autowired
    private DepartmanService departmanService;

    @PostMapping(value = "/personelTcKontrol")
    public @ResponseBody String personelTcKontrol (@RequestParam("personelTc") Long personelTc) {
        JSONObject jsonObject = new JSONObject();
        TcCheck tcCheck = new TcCheck();
        tcCheck.setTcNo(personelTc);
        Boolean exist = false;
        if(tcCheck.tcCheck()) {
            exist = personelService.personelTcKontrol(personelTc);
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
    @GetMapping(value = "/personelListele")
    public String personelListele(Model model){
        model.addAttribute("title","Personel İşlemleri");
       List<Personel> personelList = personelService.personelListele();
        model.addAttribute("personelList",personelList);
        return "personelListele";
    }

    @PostMapping(value = "/personelKaydet")
    public @ResponseBody String personelKaydet (
            @RequestParam(value = "personelId",required = false) Long personelId,
            @RequestParam("personelAd") String personelAd,
            @RequestParam("personelSoyad") String personelSoyad,
            @RequestParam("personelTc") Long personelTc,
            @RequestParam("personelCinsiyet") Long personelCinsiyet,
            @RequestParam("personelMaas") Long personelMaas,
            @RequestParam("isBaslangicTarihi") String isBaslangicTarihi,
            @RequestParam("personelGorevId") Long personelGorevId
    )   {
        JSONObject jsonObject = new JSONObject();
        /**
         * İstekten gelen parametlerin değerlerinin boş kontrolü
         */
        if(personelAd.equals("") ||
           personelSoyad.equals("") ||
           personelTc.equals(0L) ||
            personelCinsiyet.equals("") ||
            personelMaas.equals(0L) ||
            isBaslangicTarihi.equals("") ||
            personelGorevId.equals(0L)

        ) {
            jsonObject.put("success",true);
            jsonObject.put("exist",false);
            jsonObject.put("icon","error");
            jsonObject.put("title","Zorunlu alanları doldurunuz");
            return jsonObject.toString();
        }

        Gorev gorev = gorevService.gorevLoad(personelGorevId);
        TcCheck tcCheck = new TcCheck();
        tcCheck.setTcNo(personelTc);
        Boolean exist=false;
        if(tcCheck.tcCheck()) {
             exist = personelService.personelKaydet(personelId,personelAd,personelSoyad,personelTc,personelMaas,isBaslangicTarihi,gorev,personelCinsiyet);
        }
        if(exist) {
            jsonObject.put("icon","success");
            jsonObject.put("title","Personel Ekleme işlemi başarılı");
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
    @PostMapping(value = "/personelGetir")
    public @ResponseBody String personelGetir (@RequestParam("gorevId") long gorevId) {
        Gorev gorev = gorevService.gorevLoad(gorevId);
        JSONObject jsonObject = new JSONObject();
        List<Personel> personelList = personelService.personelGetir(gorevId);

        String personeller = "";
        for (Personel personel : personelList) {
            personeller += "<option value='" + personel.getPersonelId() + "'>" + personel.getPersonelAdSoyad() + "</option>";
        }
        jsonObject.put("personeller",personeller);
        return jsonObject.toString();
    }
    @PostMapping(value = "/personelData")
    public @ResponseBody String loadPersonel (@RequestParam("draw") int draw,
                                              @RequestParam("start") int start,
                                              @RequestParam("length") int length,
                                              @RequestParam(value = "search[value]" ,required = false) String dbQuery,
                                              @RequestParam(value = "order[0][column]",required = false) Integer  column,
                                              @RequestParam(value = "order[0][dir]",required = false) String order,
                                              HttpServletRequest request,
                                              HttpServletResponse response)
    {
        String columnName = null;
        if(column != null) {
            columnName = request.getParameter("columns[" + column + "][data]");
        }
        Long totalCount = personelService.getTotalCount(dbQuery);
        JSONArray jsonArray = personelService.loadPersonel(start,length,dbQuery,columnName,order);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("draw",draw);
        jsonObject.put("recordsTotal",totalCount);
        jsonObject.put("recordsFiltered",totalCount);
        jsonObject.put("data",jsonArray);
        jsonObject.put("columns",columnName);
        return jsonObject.toString();
    }
}
