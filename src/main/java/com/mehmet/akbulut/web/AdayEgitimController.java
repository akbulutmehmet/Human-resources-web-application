package com.mehmet.akbulut.web;

import com.mehmet.akbulut.model.Aday;
import com.mehmet.akbulut.model.AdayEgitim;
import com.mehmet.akbulut.model.AdayIsTecrube;
import com.mehmet.akbulut.service.AdayEgitimService;
import com.mehmet.akbulut.service.AdayService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

@Controller
public class AdayEgitimController {
    @Autowired
    private AdayEgitimService adayEgitimService;

    @Autowired
    private AdayService adayService;

    private final String upload_path="/resources/diplomalar/";
    @GetMapping("/aday/adayEgitimListele")
    public String adayEgitimListele (Model model, HttpServletRequest request, HttpServletResponse response) {
        model.addAttribute("title","İş Tecrübeleriniz");
        Aday aday = (Aday) request.getSession().getAttribute("aday");

        List<AdayEgitim> adayEgitimList = adayEgitimService.adayEgitimListele(aday);
        model.addAttribute("adayEgitimList",adayEgitimList);
        return "adayEgitimListele";
    }


    @GetMapping("/aday/adayEgitimGuncelle/{id}")
    public String adayEgitimGuncelle (@PathVariable("id") Long adayEgitimId, Model model, HttpServletRequest request, HttpServletResponse response) throws NoHandlerFoundException {
        model.addAttribute("title","Eğitim  Güncelle");
        Aday aday = (Aday) request.getSession().getAttribute("aday");
        Long adayId = aday.getAdayId();
        AdayEgitim adayEgitim = adayEgitimService.adayEgitimLoad(adayEgitimId);
        if(!adayEgitim.getAday().getAdayId().equals(adayId)) {
            throw new NoHandlerFoundException(request.getMethod(),request.getRequestURI(),null);
        }
        model.addAttribute("adayEgitim",adayEgitim);
        return "adayEgitimGuncelle";
    }
    @GetMapping("/aday/adayEgitimEkle")
    public String adayEgitimEkle (Model model) {
        model.addAttribute("title","Eğitim Ekle");
        return "adayEgitimEkle";
    }
    @PostMapping("/aday/adayEgitimSil")
    public @ResponseBody String adayEgitimSil  (@RequestParam("adayEgitimId") Long adayEgitimId,HttpServletRequest request) throws NoHandlerFoundException {
        Aday aday = (Aday) request.getSession().getAttribute("aday");
        Long adayId = aday.getAdayId();
        AdayEgitim  adayEgitim = adayEgitimService.adayEgitimLoad(adayEgitimId);
        if(!adayEgitim.getAday().getAdayId().equals(adayId)) {
            throw new NoHandlerFoundException(request.getMethod(),request.getRequestURI(),null);
        }
        Boolean exist = adayEgitimService.adayEgitimSil(adayEgitimId);
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
    @PostMapping("/aday/adayEgitimKaydet")
    public @ResponseBody String adayEgitimKaydet (@RequestParam(value = "adayEgitimId",required = false) Long adayEgitimId,
                                                       @RequestParam("okulAdi") String okulAdi,
                                                       @RequestParam("egitimTuru") String egitimTuru,
                                                       @RequestParam(value = "bolumAdi",required = false) String bolumAdi,
                                                       @RequestParam("baslangicTarihi") String baslangicTarihi,
                                                       @RequestParam(value = "bitisTarihi",required = false) String bitisTarihi,
                                                       @RequestParam(value = "diplomaFile",required = false) CommonsMultipartFile diplomaFile,
                                                       HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        Boolean exist = false;
        if(okulAdi.equals("") ||
                egitimTuru.equals("") ||
                baslangicTarihi.equals("")

        ) {
            jsonObject.put("success",true);
            jsonObject.put("exist",exist);
            jsonObject.put("icon","error");
            jsonObject.put("title","Zorunlu alanları doldurunuz");
            return jsonObject.toString();
        }
        Aday aday =(Aday) request.getSession().getAttribute("aday");
        String fileName="";
        if(diplomaFile.getSize()>0) {
            BufferedOutputStream bufferedOutputStream = null;
            String path = request.getServletContext().getRealPath(upload_path);
            String originalFilename = diplomaFile.getOriginalFilename();
            byte [] bytes = diplomaFile.getBytes();
            try {
                bufferedOutputStream = new BufferedOutputStream(new
                        FileOutputStream(
                        new File(path + File.separator + originalFilename)
                )
                );
                bufferedOutputStream.write(bytes);
                bufferedOutputStream.flush();
                bufferedOutputStream.close();
                fileName = originalFilename;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        exist = adayEgitimService.adayEgitimKaydet(adayEgitimId,egitimTuru,okulAdi,bolumAdi,baslangicTarihi,bitisTarihi,fileName,aday.getAdayId());
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
