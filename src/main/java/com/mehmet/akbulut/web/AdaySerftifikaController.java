package com.mehmet.akbulut.web;

import com.mehmet.akbulut.model.Aday;
import com.mehmet.akbulut.model.AdayEgitim;
import com.mehmet.akbulut.model.AdaySerftifika;
import com.mehmet.akbulut.service.AdayEgitimService;
import com.mehmet.akbulut.service.AdaySerftifikaService;
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
public class AdaySerftifikaController {
    @Autowired
    private AdaySerftifikaService adaySerftifikaService;

    @Autowired
    private AdayService adayService;

    private final String upload_path="C:/files/serftifikalar/";
    @GetMapping("/aday/adaySerftifikaListele")
    public String adaySerftifikaListele (Model model, HttpServletRequest request, HttpServletResponse response) {
        model.addAttribute("title","Serftifika Bilgileriniz");
        Aday aday = (Aday) request.getSession().getAttribute("aday");

        List<AdaySerftifika> adaySerftifikaList = adaySerftifikaService.adaySerftikaListele(aday);
        model.addAttribute("adaySerftifikaList",adaySerftifikaList);
        return "adaySerftifikaListele";
    }


    @GetMapping("/aday/adaySertifikaGuncelle/{id}")
    public String adaySerftifikaGuncelle (@PathVariable("id") Long adaySerftifikaId, Model model, HttpServletRequest request, HttpServletResponse response) throws NoHandlerFoundException {
        model.addAttribute("title","Serftifika  Güncelle");
        Aday aday = (Aday) request.getSession().getAttribute("aday");
        Long adayId = aday.getAdayId();
        AdaySerftifika adaySerftifika = adaySerftifikaService.adaySerftifikaLoad(adaySerftifikaId);
        if(!adaySerftifika.getAday().getAdayId().equals(adayId)) {
            throw new NoHandlerFoundException(request.getMethod(),request.getRequestURI(),null);
        }
        model.addAttribute("adaySerftifika",adaySerftifika);
        return "adaySerftifikaGuncelle";
    }
    @GetMapping("/aday/adaySerftifikaEkle")
    public String adayEgitimEkle (Model model) {
        model.addAttribute("title","Serftifika Ekle");
        return "adaySerftifikaEkle";
    }
    @PostMapping("/aday/adaySerftifikaSil")
    public @ResponseBody String adaySerftifikaSil  (@RequestParam("adaySerftifikaId") Long adaySerftifikaId,HttpServletRequest request) throws NoHandlerFoundException {
        Aday aday = (Aday) request.getSession().getAttribute("aday");
        Long adayId = aday.getAdayId();
        AdaySerftifika  adaySerftifika = adaySerftifikaService.adaySerftifikaLoad(adaySerftifikaId);
        if(!adaySerftifika.getAday().getAdayId().equals(adayId)) {
            throw new NoHandlerFoundException(request.getMethod(),request.getRequestURI(),null);
        }
        Boolean exist = adaySerftifikaService.adaySerftifikaSil(adaySerftifikaId);
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
    @PostMapping("/aday/adaySerftifikaKaydet")
    public void adaySerftifikaKaydet (@RequestParam(value = "adaySertifikaId",required = false) Long adaySertifikaId,
                                                       @RequestParam("adaySerftifikaAd") String adaySerftifikaAd,
                                                       @RequestParam("adaySerftifikaUrl") String adaySerftifikaUrl,
                                                       @RequestParam("adaySerftifikaTarih") String adaySerftifikaTarih,
                                                       @RequestParam(value = "serftifikaFile",required = false) CommonsMultipartFile serftifikaFile,
                                                       HttpServletRequest request,HttpServletResponse response) throws NoHandlerFoundException, IOException {
        Boolean exist = false;
        if(adaySerftifikaAd.equals("") ||
                adaySerftifikaUrl.equals("") ||
                adaySerftifikaTarih.equals("")

        ) {
            throw new NoHandlerFoundException(request.getMethod(),request.getRequestURI(),null);
        }
        Aday aday =(Aday) request.getSession().getAttribute("aday");
        String fileName="";
        if(!serftifikaFile.isEmpty() || serftifikaFile.getSize()>0L) {
            BufferedOutputStream bufferedOutputStream = null;
            String path = request.getServletContext().getRealPath(upload_path);
            String originalFilename = serftifikaFile.getOriginalFilename();
            byte [] bytes = serftifikaFile.getBytes();
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

        exist = adaySerftifikaService.adaySerftifikaKaydet(adaySertifikaId,adaySerftifikaAd,adaySerftifikaUrl,adaySerftifikaTarih,fileName,aday.getAdayId());
        if(exist) {
            response.sendRedirect(request.getServletContext().getContextPath() + "/aday/adaySerftifikaListele");
        }
        else {
            throw new NoHandlerFoundException(request.getMethod(),request.getRequestURI(),null);

        }

    }
}
