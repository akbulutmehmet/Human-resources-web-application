package com.mehmet.akbulut.service;

import com.mehmet.akbulut.dao.IzinliPersonelDAO;
import com.mehmet.akbulut.dao.MainDAO;
import com.mehmet.akbulut.model.IzinliPersonel;
import com.mehmet.akbulut.model.Personel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
            Long izinliGunSayisi = izinliPersonel.getIzinBitisTarihi().getTime() - izinliPersonel.getIzinBaslangicTarihi().getTime();
            izinliGunSayisi = TimeUnit.DAYS.convert(izinliGunSayisi,TimeUnit.MILLISECONDS);
            personel.setPersonelIzinHakki(personel.getPersonelIzinHakki() + izinliGunSayisi);
            mainDAO.saveOrUpdateObject(personel);
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

        izinliPersonel.setPersonel(personel);
        izinliPersonel.setIzinBaslangicTarihi(personelIzinBaslangicTarihi);
        izinliPersonel.setIzinBitisTarihi(personelIzinBitisTarihi);
        Long gunSayisi = TimeUnit.DAYS.convert(izinliGunSayisi,TimeUnit.MILLISECONDS);
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
