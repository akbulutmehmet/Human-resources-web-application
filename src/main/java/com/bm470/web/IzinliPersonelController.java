package com.bm470.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class IzinliPersonelController {
    @GetMapping(value = "/izinliPersonelListele")
    public String izinliPersonelListele(Model model){
        model.addAttribute("title","İzinli Personel İşlemleri");

        return "izinliPersonelListele";
    }
    @GetMapping(value = "/izinliPersonelEkle")
    public String izinliPersonelEkle(Model model){
        model.addAttribute("title","İzinli Personel Ekle");

        return "izinliPersonelEkle";
    }
    @GetMapping(value = "/izinliPersonelGuncelle/{id}")
    public String izinliPersonelGuncelle(Model model, @PathVariable("id") long id){
        model.addAttribute("title","İzinli Personel Guncelle");

        return "izinliPersonelGuncelle";
    }
}
