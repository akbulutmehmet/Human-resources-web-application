package com.mehmet.akbulut.service;

import com.mehmet.akbulut.dao.AdayEgitimDAO;
import com.mehmet.akbulut.dao.MainDAO;
import com.mehmet.akbulut.model.Aday;
import com.mehmet.akbulut.model.AdayEgitim;
import com.mehmet.akbulut.model.AdayIsTecrube;
import com.mehmet.akbulut.util.DateConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly =true,rollbackFor = Exception.class)
public class AdayEgitimService {
    @Autowired
    private AdayEgitimDAO adayEgitimDAO;

    @Autowired
    private MainDAO mainDAO;

    @Autowired
    private AdayService adayService;

    @Transactional(readOnly = false)
    public Boolean adayEgitimKaydet(Long adayEgitimId, String egitimTuru, String okulAdi, String bolumAdi, String baslangicTarihi, String bitisTarihi, String fileName,Long adayId) {
        AdayEgitim adayEgitim = null;
        if(adayEgitimId != null) {
            adayEgitim = adayEgitimLoad(adayEgitimId);
        }
        else {
            adayEgitim = new AdayEgitim();
        }
        Aday aday = adayService.adayLoad(adayId);
        DateConvert dateConvert = new DateConvert(baslangicTarihi);
        Date baslangicTarihidate = dateConvert.getDate();
     //   Date bitisTarihiDate = null;
        if(!bitisTarihi.equals("")) {
            dateConvert =  new DateConvert(bitisTarihi);
          Date  bitisTarihiDate = dateConvert.getDate();
            adayEgitim.setBitisTarihi(bitisTarihiDate);
        }
        adayEgitim.setBolumAdi(bolumAdi);
        adayEgitim.setOkulAdi(okulAdi);
        adayEgitim.setEgitimTuru(egitimTuru);
        adayEgitim.setBaslangicTarihi(baslangicTarihidate);
        if(!fileName.equals("")) {
            adayEgitim.setFileName(fileName);
        }
        adayEgitim.setAday(aday);
        Boolean exist = mainDAO.saveOrUpdateObject(adayEgitim);
        return exist;
    }

    public AdayEgitim adayEgitimLoad(Long adayEgitimId) {
        AdayEgitim adayEgitim =  (AdayEgitim) mainDAO.loadObject(AdayEgitim.class,adayEgitimId);
        return adayEgitim;
    }
    @Transactional(readOnly = false)
    public Boolean adayEgitimSil(Long adayEgitimId) {
        AdayEgitim adayEgitim = adayEgitimLoad(adayEgitimId);
        Boolean exist = mainDAO.removeObject(adayEgitim);
        return exist;
    }

    public List<AdayEgitim> adayEgitimListele(Aday aday) {
        List<AdayEgitim> adayEgitimList = adayEgitimDAO.adayIsTecrubeListele(aday);
        return adayEgitimList;
    }
}
