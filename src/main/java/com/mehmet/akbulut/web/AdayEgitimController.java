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
import java.io.IOException;
import java.util.List;

@Controller
public class AdayEgitimController {
    @Autowired
    private AdayEgitimService adayEgitimService;

    @Autowired
    private AdayService adayService;

    private final String upload_path="C:/files/diplomalar/";
    @GetMapping("/aday/adayEgitimListele")
    public String adayEgitimListele (Model model, HttpServletRequest request, HttpServletResponse response) {
        model.addAttribute("title","Eğitim Bilgileriniz");
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
    public void adayEgitimKaydet (@RequestParam(value = "adayEgitimId",required = false) Long adayEgitimId,
                                                       @RequestParam("okulAdi") String okulAdi,
                                                       @RequestParam("egitimTuru") String egitimTuru,
                                                       @RequestParam(value = "bolumAdi",required = false) String bolumAdi,
                                                       @RequestParam("baslangicTarihi") String baslangicTarihi,
                                                       @RequestParam(value = "bitisTarihi",required = false) String bitisTarihi,
                                                       @RequestParam(value = "diplomaFile",required = false) CommonsMultipartFile diplomaFile,
                                                       HttpServletRequest request,HttpServletResponse response) throws NoHandlerFoundException, IOException {
        Boolean exist = false;
        if(okulAdi.equals("") ||
                egitimTuru.equals("") ||
                baslangicTarihi.equals("")

        ) {
            throw new NoHandlerFoundException(request.getMethod(),request.getRequestURI(),null);
        }
        Aday aday =(Aday) request.getSession().getAttribute("aday");
        String fileName="";
        if(!diplomaFile.isEmpty() || diplomaFile.getSize()>0L) {
            BufferedOutputStream bufferedOutputStream = null;
            String path = request.getServletContext().getRealPath(upload_path);
            String originalFilename = diplomaFile.getOriginalFilename();
            byte [] bytes = diplomaFile.getBytes();
            try {
                bufferedOutputStream = new BufferedOutputStream(new
                        FileOutputStream(
                        new File(upload_path + File.separator + originalFilename)
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
            response.sendRedirect(request.getServletContext().getContextPath() + "/aday/adayEgitimListele");
        }
        else {
            throw new NoHandlerFoundException(request.getMethod(),request.getRequestURI(),null);

        }

    }
}
