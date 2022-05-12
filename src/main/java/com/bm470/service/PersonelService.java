package com.bm470.service;

import com.bm470.dao.MainDAO;
import com.bm470.dao.PersonelDAO;
import com.bm470.model.Gorev;
import com.bm470.model.Personel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly =true,rollbackFor = Exception.class)
public class PersonelService {
    @Autowired
    private PersonelDAO personelDAO;

    @Autowired
    private MainDAO mainDAO;

    public List<Personel> personelListele() {
        List<Personel> personelList = personelDAO.personelListele();
        return personelList;
    }
    @Transactional(readOnly = false)
    public Boolean personelKaydet(Long personelId,String personelAd, String personelSoyad, long personelTc, long personelMaas, String isBaslangicTarihi, Gorev gorev,String personelCinsiyet) {
        Date personelIsBaslangicTarihi = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            personelIsBaslangicTarihi = sdf.parse(isBaslangicTarihi);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Personel personel = null;
        if(personelId != null) {
            personel = personelLoad(personelId);
        }
        else {
            personel = new Personel();
        }
        personel.setPersonelAd(personelAd);
        personel.setPersonelSoyad(personelSoyad);
        personel.setPersonelTc(personelTc);
        personel.setPersonelMaas(personelMaas);
        personel.setPersonelCinsiyet(personelCinsiyet);
        personel.setIsBaslangicTarihi(personelIsBaslangicTarihi);
        personel.setGorev(gorev);
        Boolean exist = mainDAO.saveOrUpdateObject(personel);
        return exist;
    }

    public Personel personelLoad(long  personelId) {
        Personel personel =  (Personel) mainDAO.loadObject(Personel.class,personelId);
        return personel;
    }

    @Transactional(readOnly = false)
    public Boolean personelSil(long personelId) {
        Personel personel = personelLoad(personelId);
        Boolean exist = mainDAO.removeObject(personel);
        return exist;
    }
}
