package com.bm470.service;

import com.bm470.dao.IzinliPersonelDAO;
import com.bm470.dao.MainDAO;
import com.bm470.model.InsanKaynaklari;
import com.bm470.model.IzinliPersonel;
import com.bm470.model.Personel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly =true,rollbackFor = Exception.class)
public class IzinliPersonelService {
    @Autowired
    private IzinliPersonelDAO izinliPersonelDAO;

    @Autowired
    private MainDAO mainDAO;



    public List<IzinliPersonel> izinliPersonelListele() {
        List<IzinliPersonel> izinliPersonelList = izinliPersonelDAO.izinliPersonelListele();
        return izinliPersonelList;
    }

    @Transactional(readOnly = false)
    public Boolean izinliPersonelKaydet(Long izinliPersonelId, String izinBaslangicTarihi, String izinBitisTarihi,Long personelId) {

        Personel personel = null;
        if(personelId != null){
            personel = (Personel) mainDAO.loadObject(Personel.class,personelId);
        }
        else{
            personel = new Personel();
        }
        IzinliPersonel izinliPersonel = null;
        if(izinliPersonelId != null) {
            izinliPersonel = izinliPersonelLoad(izinliPersonelId);
        }
        else {
            izinliPersonel = new IzinliPersonel();
        }

        Date personelIzinBaslangicTarihi = null;
        Date personelIzinBitisTarihi = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            personelIzinBaslangicTarihi = sdf.parse(izinBaslangicTarihi);
            personelIzinBitisTarihi = sdf.parse(izinBitisTarihi);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Long izinliGunSayisi = personelIzinBitisTarihi.getTime() - personelIzinBaslangicTarihi.getTime();
        Date izinliDate = new Date(izinliGunSayisi);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(izinliDate);

        izinliPersonel.setPersonel(personel);
        izinliPersonel.setIzinBaslangicTarihi(personelIzinBaslangicTarihi);
        izinliPersonel.setIzinBitisTarihi(personelIzinBitisTarihi);
        Long gunSayisi = Long.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
        Boolean exist = false;
        if(gunSayisi<=personel.getPersonelIzinHakki()) {
            personel.setPersonelIzinHakki(personel.getPersonelIzinHakki()-gunSayisi);
            mainDAO.saveOrUpdateObject(personel);
           exist  = mainDAO.saveOrUpdateObject(izinliPersonel);
        }

        return exist;

    }
    public IzinliPersonel izinliPersonelLoad(long  izinliPersonelId) {
        IzinliPersonel izinliPersonel =  (IzinliPersonel) mainDAO.loadObject(IzinliPersonel.class,izinliPersonelId);
        return izinliPersonel;
    }

    @Transactional(readOnly = false)
    public Boolean izinliPersonelSil(long izinliPersonelId) {
        IzinliPersonel izinliPersonel = izinliPersonelLoad(izinliPersonelId);
        Boolean exist = mainDAO.removeObject(izinliPersonel);
        return exist;
    }
}
