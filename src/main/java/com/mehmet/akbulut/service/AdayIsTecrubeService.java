package com.mehmet.akbulut.service;

import com.mehmet.akbulut.dao.AdayIsTecrubeDAO;
import com.mehmet.akbulut.dao.MainDAO;
import com.mehmet.akbulut.model.Aday;
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
public class AdayIsTecrubeService {
    @Autowired
    private AdayIsTecrubeDAO adayIsTecrubeDAO;

    @Autowired
    private MainDAO mainDAO;

    @Autowired
    private AdayService adayService;

    @Transactional(readOnly = false)
    public Boolean adayKaydet(Long adayIsTecrubeId, String isyeriAdi, String gorevAdi, String isBaslangicTarihi, String isBitisTarihi, String gorevliAdi, String gorevliUnvan, Long gorevliTelefon,Long adayId) {
        AdayIsTecrube adayIsTecrube = null;
        if(adayIsTecrubeId != null) {
            adayIsTecrube = adayIsTecrubeLoad(adayIsTecrubeId);
        }
        else {
            adayIsTecrube = new AdayIsTecrube();
        }
        Aday aday = adayService.adayLoad(adayId);
        DateConvert dateConvert = new DateConvert(isBaslangicTarihi);
        Date isBaslangicTarihiDate = dateConvert.getDate();
        Date isBitisTarihiDate = null;
        if(!isBitisTarihi.equals("")) {
            dateConvert =  new DateConvert(isBitisTarihi);
            isBitisTarihiDate = dateConvert.getDate();
        }
        adayIsTecrube.setIsyeriAdi(isyeriAdi);
        adayIsTecrube.setGorevAdi(gorevAdi);
        adayIsTecrube.setIsBaslangicTarihi(isBaslangicTarihiDate);
        adayIsTecrube.setIsBitisTarihi(isBitisTarihiDate);
        adayIsTecrube.setGorevliAdi(gorevliAdi);
        adayIsTecrube.setGorevliUnvan(gorevliUnvan);
        adayIsTecrube.setGorevliTelefon(gorevliTelefon);
        adayIsTecrube.setAday(aday);
        Boolean exist = mainDAO.saveOrUpdateObject(adayIsTecrube);
        return exist;
    }

    public AdayIsTecrube adayIsTecrubeLoad(Long adayIsTecrubeId) {
        AdayIsTecrube adayIsTecrube =  (AdayIsTecrube) mainDAO.loadObject(AdayIsTecrube.class,adayIsTecrubeId);
        return adayIsTecrube;
    }
    @Transactional(readOnly = false)
    public Boolean adayIsTecrubesiSil(Long adayIsTecrubeId) {
        AdayIsTecrube adayIsTecrube = adayIsTecrubeLoad(adayIsTecrubeId);
        Boolean exist = mainDAO.removeObject(adayIsTecrube);
        return exist;
    }

    public List<AdayIsTecrube> adayIsTecrubeListele(Aday aday) {
        List<AdayIsTecrube> adayIsTecrubeList = adayIsTecrubeDAO.adayIsTecrubeListele(aday);
        return adayIsTecrubeList;
    }
}
