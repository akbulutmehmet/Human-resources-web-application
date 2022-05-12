package com.bm470.service;

import com.bm470.dao.GorevDAO;
import com.bm470.dao.MainDAO;
import com.bm470.model.Departman;
import com.bm470.model.Gorev;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly =true,rollbackFor = Exception.class)
public class GorevService {
    @Autowired
    private MainDAO mainDAO;
    @Autowired
    private GorevDAO gorevDAO;

    public List<Gorev> gorevListele() {
        List<Gorev> gorevList = gorevDAO.gorevListele();
        return gorevList;
    }
    @Transactional(readOnly = false)
    public Boolean gorevKaydet(Departman departman, String gorevAdi) {
        Gorev gorev = new Gorev();
        gorev.setDepartman(departman);
        gorev.setGorevAdi(gorevAdi);

        Boolean exist = mainDAO.saveOrUpdateObject(gorev);
        return  exist;
    }

    public Gorev gorevLoad(long gorevId) {
        Gorev gorev = (Gorev) mainDAO.loadObject(Gorev.class,gorevId);
        return gorev;
    }
    @Transactional(readOnly = false)
    public Boolean gorevUpdate(Departman departman, String gorevAdi,long gorevId) {
        Gorev gorev = gorevLoad(gorevId);
        gorev.setGorevAdi(gorevAdi);
        gorev.setDepartman(departman);
        Boolean exist = mainDAO.saveOrUpdateObject(gorev);
        return exist;
    }
    @Transactional(readOnly = false)
    public Boolean gorevSil(long id) {
        Gorev gorev = gorevLoad(id);
        Boolean exist = mainDAO.removeObject(gorev);
        return exist;
    }

    public List<Gorev> gorevGetir(long departmanId) {
        List<Gorev> gorevList = gorevDAO.gorevGetir(departmanId);
        return gorevList ;
    }
}
