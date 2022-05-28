package com.mehmet.akbulut.web;

import com.mehmet.akbulut.model.Aday;
import com.mehmet.akbulut.model.AdayIsTecrube;
import com.mehmet.akbulut.service.AdayIsTecrubeService;
import com.mehmet.akbulut.service.AdayService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class AdayIsTecrubeController {
    @Autowired
    private AdayIsTecrubeService adayIsTecrubeService;

    @Autowired
    private AdayService adayService;
    @GetMapping("/aday/adayIsTecrubeListele")
    public String adayIsTecrubeListele (Model model, HttpServletRequest request, HttpServletResponse response) {
        model.addAttribute("title","İş Tecrübeleriniz");
        Aday aday = (Aday) request.getSession().getAttribute("aday");

        List<AdayIsTecrube> adayIsTecrubeList = adayIsTecrubeService.adayIsTecrubeListele(aday);
        model.addAttribute("adayIsTecrubeList",adayIsTecrubeList);
        return "adayIsTecrubeListele";
    }


    @GetMapping("/aday/adayIsTecrubeGuncelle/{id}")
    public String adayIsTecrubeGuncelle (@PathVariable("id") Long adayIsTecrubeId, Model model, HttpServletRequest request, HttpServletResponse response) throws NoHandlerFoundException {
        model.addAttribute("title","İş Tecrübesi Güncelle");
        Aday aday = (Aday) request.getSession().getAttribute("aday");
        Long adayId = aday.getAdayId();
        AdayIsTecrube adayIsTecrube = adayIsTecrubeService.adayIsTecrubeLoad(adayIsTecrubeId);
        if(!adayIsTecrube.getAday().getAdayId().equals(adayId)) {
            throw new NoHandlerFoundException(request.getMethod(),request.getRequestURI(),null);
        }
        model.addAttribute("adayIsTecrube",adayIsTecrube);
        return "adayIsTecrubeGuncelle";
    }
    @GetMapping("/aday/adayIsTecrubesiEkle")
    public String adayIsTecrubesiEkle (Model model) {
        model.addAttribute("title","İş Tecrübesi Ekle");
        return "adayIsTecrubesiEkle";
    }
    @PostMapping("/aday/adayIsTecrubesiSil")
    public @ResponseBody String adayIsTecrubesiSil  (@RequestParam("adayIsTecrubeId") Long adayIsTecrubeId,HttpServletRequest request) throws NoHandlerFoundException {
        Aday aday = (Aday) request.getSession().getAttribute("aday");
        Long adayId = aday.getAdayId();
        AdayIsTecrube  adayIsTecrube = adayIsTecrubeService.adayIsTecrubeLoad(adayIsTecrubeId);
        if(!adayIsTecrube.getAday().getAdayId().equals(adayId)) {
            throw new NoHandlerFoundException(request.getMethod(),request.getRequestURI(),null);
        }
        Boolean exist = adayIsTecrubeService.adayIsTecrubesiSil(adayIsTecrubeId);
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
    @PostMapping("/aday/adayIsTecrubesiKaydet")
    public @ResponseBody String adayIsTecrubesiKaydet (@RequestParam(value = "adayIsTecrubeId",required = false) Long adayIsTecrubeId,
                                                       @RequestParam("isyeriAdi") String isyeriAdi,
                                                       @RequestParam("gorevAdi") String gorevAdi,
                                                       @RequestParam("isBaslangicTarihi") String isBaslangicTarihi,
                                                       @RequestParam(value = "isBitisTarihi",required = false) String isBitisTarihi,
                                                       @RequestParam(value = "gorevliAdi",required = false) String gorevliAdi,
                                                       @RequestParam(value = "gorevliUnvan",required = false) String gorevliUnvan,
                                                       @RequestParam(value = "gorevliTelefon",required = false) Long gorevliTelefon,
                                                       HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        Boolean exist = false;
        if(isyeriAdi.equals("") ||
                gorevAdi.equals("") ||
                isBaslangicTarihi.equals("")

        ) {
            jsonObject.put("success",true);
            jsonObject.put("exist",exist);
            jsonObject.put("icon","error");
            jsonObject.put("title","Zorunlu alanları doldurunuz");
            return jsonObject.toString();
        }
        Aday aday =(Aday) request.getSession().getAttribute("aday");
        exist = adayIsTecrubeService.adayKaydet(adayIsTecrubeId,isyeriAdi,gorevAdi,isBaslangicTarihi,isBitisTarihi,gorevliAdi,gorevliUnvan,gorevliTelefon,aday.getAdayId());
        if(exist) {
            jsonObject.put("icon","success");
            jsonObject.put("title","İş Tecrübesi ekleme işlemi başarılı");
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
