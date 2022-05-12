package com.bm470.service;


import com.bm470.dao.InsanKaynaklariDAO;
import com.bm470.dao.MainDAO;
import com.bm470.model.InsanKaynaklari;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly =true,rollbackFor = Exception.class)
public class InsanKaynaklariService {
    @Autowired
    private InsanKaynaklariDAO insanKaynaklariDAO;
    @Autowired
    private MainDAO mainDAO;
    public InsanKaynaklari loginKontrol(String email, String sifre){
        InsanKaynaklari insanKaynaklari = insanKaynaklariDAO.loginKontrol(email,sifre);
        return  insanKaynaklari;
    }

    public List<InsanKaynaklari> insanKaynaklariListele() {
        List<InsanKaynaklari> insanKaynaklariList = insanKaynaklariDAO.insanKaynaklariListele();
        return insanKaynaklariList;
    }
    @Transactional(readOnly = false)
    public Boolean insanKaynaklariKaydet(String isim, String soyIsim, String email, String password) {
        InsanKaynaklari insanKaynaklari = new InsanKaynaklari();
        insanKaynaklari.setIkAd(isim);
        insanKaynaklari.setIkSoyad(soyIsim);
        insanKaynaklari.setIkEposta(email);
        insanKaynaklari.setIkSifre(password);
       Boolean exist = mainDAO.saveOrUpdateObject(insanKaynaklari);

       return exist;
    }
    @Transactional(readOnly = false)
    public Boolean insanKaynaklariSil(long id) {
        InsanKaynaklari insanKaynaklari = (InsanKaynaklari) mainDAO.loadObject(InsanKaynaklari.class,id);
        return mainDAO.removeObject(insanKaynaklari);
    }

    public InsanKaynaklari insanKaynaklariLoad(long id) {
        InsanKaynaklari insanKaynaklari = (InsanKaynaklari) mainDAO.loadObject(InsanKaynaklari.class,id);
        return insanKaynaklari;
    }
    @Transactional(readOnly = false)
    public Boolean insanKaynaklariUpdate(long id, String isim, String soyisim, String email, String password) {
        InsanKaynaklari insanKaynaklari = insanKaynaklariLoad(id);
        insanKaynaklari.setIkAd(isim);
        insanKaynaklari.setIkSoyad(soyisim);
        insanKaynaklari.setIkEposta(email);
        insanKaynaklari.setIkSifre(password);
        Boolean exist = mainDAO.saveOrUpdateObject(insanKaynaklari);
        return exist;
    }
}
