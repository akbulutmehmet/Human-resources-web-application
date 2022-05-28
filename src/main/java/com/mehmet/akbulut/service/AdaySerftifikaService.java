package com.mehmet.akbulut.service;

import com.mehmet.akbulut.dao.AdaySerftifikaDAO;
import com.mehmet.akbulut.dao.MainDAO;
import com.mehmet.akbulut.model.Aday;
import com.mehmet.akbulut.model.AdayEgitim;
import com.mehmet.akbulut.model.AdaySerftifika;
import com.mehmet.akbulut.util.DateConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly =true,rollbackFor = Exception.class)
public class AdaySerftifikaService {
    @Autowired
    private AdaySerftifikaDAO adaySerftifikaDAO;

    @Autowired
    private MainDAO mainDAO;

    @Autowired
    private AdayService adayService;

    @Transactional(readOnly = false)
    public Boolean adaySerftifikaKaydet(Long adaySerftifikaId, String adaySerftikaAd, String adaySerftikaUrl, String adaySerftifikaTarihi,  String fileName,Long adayId) {
        AdaySerftifika adaySerftifika = null;
        if(adaySerftifikaId != null) {
            adaySerftifika = adaySerftifikaLoad(adaySerftifikaId);
        }
        else {
            adaySerftifika = new AdaySerftifika();
        }
        Aday aday = adayService.adayLoad(adayId);
        DateConvert dateConvert = new DateConvert(adaySerftifikaTarihi);
        Date baslangicTarihidate = dateConvert.getDate();
        adaySerftifika.setAdaySerftifikaAd(adaySerftikaAd);
        adaySerftifika.setAdaySerftifikaUrl(adaySerftikaUrl);
        adaySerftifika.setAdaySerftifikaTarihi(baslangicTarihidate);
        if(!fileName.equals("")) {
            adaySerftifika.setFileName(fileName);
        }
        adaySerftifika.setAday(aday);
        Boolean exist = mainDAO.saveOrUpdateObject(adaySerftifika);
        return exist;
    }

    public AdaySerftifika adaySerftifikaLoad(Long adaySerftifikaId) {
        AdaySerftifika AdaySerftifika =  (AdaySerftifika) mainDAO.loadObject(AdaySerftifika.class,adaySerftifikaId);
        return AdaySerftifika;
    }
    @Transactional(readOnly = false)
    public Boolean adaySerftifikaSil(Long adaySerftifikaId) {
        AdaySerftifika adaySerftifika = adaySerftifikaLoad(adaySerftifikaId);
        Boolean exist = mainDAO.removeObject(adaySerftifika);
        return exist;
    }

    public List<AdaySerftifika> adaySerftikaListele(Aday aday) {
        List<AdaySerftifika> adaySerftifikaList = adaySerftifikaDAO.adaySerftifikaListele(aday);
        return adaySerftifikaList;
    }
}
