package com.bm470.service;

import com.bm470.dao.MainDAO;
import com.bm470.dao.PersonelDAO;
import com.bm470.model.Gorev;
import com.bm470.model.Personel;
import com.bm470.util.DateConvert;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
    public Boolean personelKaydet(Long personelId,String personelAd, String personelSoyad, long personelTc, long personelMaas, String isBaslangicTarihi, Gorev gorev,Long personelCinsiyet) {
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
    @Transactional(readOnly = false)
    public void izinGuncelle() {
        List<Personel> personelList = personelListele();
        Iterator<Personel> personelIterator = personelList.iterator();
        while (personelIterator.hasNext()) {
            Personel personel = personelIterator.next();
            Date tarih = new Date();
            Long ikiTarihFark = tarih.getTime() - personel.getIsBaslangicTarihi().getTime();
            ikiTarihFark = TimeUnit.DAYS.convert(ikiTarihFark,TimeUnit.MILLISECONDS);
            System.out.println(ikiTarihFark);
            if(ikiTarihFark>=1) {
                if(personel.getPersonelIzinHakki() != null) {
                    personel.setPersonelIzinHakki(personel.getPersonelIzinHakki() + 20L);
                }
                else  {
                    personel.setPersonelIzinHakki(20L);
                }
                mainDAO.saveOrUpdateObject(personel);
            }
        }
    }


    public Boolean personelTcKontrol(Long personelTc) {
        List<Long> personelTcList = personelDAO.personelTcListele();
        Iterator<Long> iteratorPersonelTc = personelTcList.iterator();
        Boolean exist = true;
        while (iteratorPersonelTc.hasNext()) {
            Long tc = iteratorPersonelTc.next();
            if(personelTc.equals(tc)) {
                exist = false;
                break;
            }
        }
        return exist;
    }

    public Long getTotalCount (String dbQuery) {
        Long totalCount = personelDAO.getTotalCount(dbQuery);
        return totalCount;
    }
    public JSONArray loadPersonel (int start,int length,String dbQuery,String columnName,String order) {
        List<Personel> personelList = personelDAO.loadPersonel(start,length,dbQuery,columnName,order);
        JSONArray jsonArray = new JSONArray();
        DateConvert dateConvert = new DateConvert();
        for (int i=0;i<personelList.size();i++) {
            JSONObject jsonObject = new JSONObject();
            Personel personel = personelList.get(i);
            jsonObject.put("personelId",personel.getPersonelId());
            jsonObject.put("personelAd",personel.getPersonelAd());
            jsonObject.put("personelSoyad",personel.getPersonelSoyad());
            jsonObject.put("personelTc",personel.getPersonelTc());
            String cinsiyet = (personel.getPersonelCinsiyet() == 1) ? "ERKEK" : "KADIN";
            jsonObject.put("personelCinsiyet",cinsiyet);
            dateConvert.setDate(personel.getIsBaslangicTarihi());
            jsonObject.put("isBaslangicTarihi",dateConvert.getDateString());
            jsonObject.put("departman",personel.getGorev().getDepartman().getDepartmanAdi());
            jsonObject.put("gorev",personel.getGorev().getGorevAdi());
            String islemler = "";
            islemler += "<a href=\"/ilerijava/personelGuncelle/"+ personel.getPersonelId()
                    + " \"  class=\"btn  btn-info \"  data-id=\"\">GÜNCELLE</a>";
            islemler += "<button type=\"button\" data-id=\""+ personel.getPersonelId()
                    + " \"  class=\"btn  btn-blok btn-danger btnPersonelSil \">SİL</button>";

            jsonObject.put("islemler",islemler);
            jsonArray.add(i,jsonObject);
        }
        return jsonArray;
    }

}
