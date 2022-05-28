package com.mehmet.akbulut.service;

import com.mehmet.akbulut.dao.AdayDAO;
import com.mehmet.akbulut.dao.MainDAO;
import com.mehmet.akbulut.model.Aday;
import com.mehmet.akbulut.util.DateConvert;
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

    public Aday adayLoad(Long adayId) {
        Aday aday =  (Aday) mainDAO.loadObject(Aday.class,adayId);
        return aday;
    }

    @Transactional(readOnly = false)
    public Boolean adayGuncelle(Long adayId, String adayAd, String adaySoyad, Long adayCinsiyet, String adayEposta, String adaySifre, String adayDogumTarihi) {
        Aday aday = adayLoad(adayId);
        aday.setAdayAd(adayAd);
        aday.setAdayCinsiyet(adayCinsiyet);
        aday.setAdaySoyad(adaySoyad);
        aday.setAdayEposta(adayEposta);
        DateConvert dateConvert = new DateConvert(adayDogumTarihi);
        Date adayDogum = dateConvert.getDate();
        aday.setAdayDogumTarihi(adayDogum);
        if(!aday.getAdaySifre().equals(adaySifre)) {
            aday.setAdaySifre(adaySifre);
        }
        return mainDAO.saveOrUpdateObject(aday);
    }

    public List<Aday> adayListele() {
        List<Aday> adayList = adayDAO.adayListele();
        return adayList;
    }
}
