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
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
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
        Personel personel = null;
        if(personelId != null) {
            personel = personelLoad(personelId);
        }
        else {
            personel = new Personel();
        }

        Date personelIsBaslangicTarihi = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            personelIsBaslangicTarihi = sdf.parse(isBaslangicTarihi);
        } catch (ParseException e) {
            e.printStackTrace();
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

    public List<Personel> personelGetir(long gorevId) {

        List<Personel> personelList = personelDAO.personelGetir(gorevId);
        return personelList;
    }

    public void izinGuncelle() {
        List<Personel> personelList = personelListele();
        Iterator<Personel> personelIterator = personelList.iterator();
        while (personelIterator.hasNext()) {
            Personel personel = personelIterator.next();
            if(personel.getIsBaslangicTarihi() == null) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(personel.getIsBaslangicTarihi());
                calendar.add(Calendar.DATE,365);
                Date izinTarihi = calendar.getTime();
                if(izinTarihi.after(personel.getIsBaslangicTarihi())) {
                    personel.setPersonelIzinHakki(20L);
                    mainDAO.saveOrUpdateObject(personel);
                }
            }
        }
    }
}
