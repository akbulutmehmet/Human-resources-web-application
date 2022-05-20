package com.bm470.service;

import com.bm470.dao.AdayDAO;
import com.bm470.dao.MainDAO;
import com.bm470.model.Aday;
import com.bm470.model.InsanKaynaklari;
import com.bm470.model.Personel;
import com.bm470.util.DateConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly =true,rollbackFor = Exception.class)
public class AdayService {
    @Autowired
    private AdayDAO adayDAO;

    @Autowired
    private MainDAO mainDAO;

    public Aday loginKontrol(String email, String sifre){
        Aday aday = adayDAO.loginKontrol(email,sifre);
        return  aday;
    }

    public Boolean adayTcKontrol(Long adayTc) {
        List<Long> adayTcList = adayDAO.adayTcListele();
        Iterator<Long> iteratorAdayTc = adayTcList.iterator();
        Boolean exist = true;
        while (iteratorAdayTc.hasNext()) {
            Long tc = iteratorAdayTc.next();
            if(adayTc.equals(tc)) {
                exist = false;
                break;
            }
        }
        return exist;
    }
    @Transactional(readOnly = false)
    public Boolean adayKaydet(Long adayId, String adayAd, String adaySoyad, Long adayTc, Long adayCinsiyet, String adayEposta, String adaySifre, String adayDogumTarihi) {
        Aday aday = null;
        if(adayId != null) {
            aday = adayLoad(adayId);
        }
        else {
            aday = new Aday();
        }
        DateConvert dateConvert = new DateConvert(adayDogumTarihi);
        Date adayDogum = dateConvert.getDate();
        aday.setAdayAd(adayAd);
        aday.setAdaySoyad(adaySoyad);
        aday.setAdayTc(adayTc);
        aday.setAdayCinsiyet(adayCinsiyet);
        aday.setAdayEposta(adayEposta);
        aday.setAdaySifre(adaySifre);
        aday.setAdayDogumTarihi(adayDogum);
        Boolean exist = mainDAO.saveOrUpdateObject(aday);
        return exist;
    }

    private Aday adayLoad(Long adayId) {
        Aday aday =  (Aday) mainDAO.loadObject(Aday.class,adayId);
        return aday;
    }
}
