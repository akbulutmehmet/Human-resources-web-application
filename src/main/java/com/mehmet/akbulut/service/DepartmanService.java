package com.mehmet.akbulut.service;


import com.mehmet.akbulut.dao.DepartmanDAO;
import com.mehmet.akbulut.dao.MainDAO;
import com.mehmet.akbulut.model.Departman;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly =true,rollbackFor = Exception.class)
public class DepartmanService {
    @Autowired
    private DepartmanDAO departmanDAO;
    @Autowired
    private MainDAO mainDAO;

    public List<Departman> departmanListele() {
        List<Departman> departmanList = departmanDAO.departmanListele();
        return departmanList;
    }
    @Transactional(readOnly = false)
    public Boolean departmanKaydet(String departmanAdi) {
        Departman departman = new Departman();
        departman.setDepartmanAdi(departmanAdi);
        Boolean exist = mainDAO.saveOrUpdateObject(departman);
        return exist;
    }

    public Departman departmanLoad(long id) {
        Departman departman = (Departman) mainDAO.loadObject(Departman.class,id);

        return departman;
    }
    @Transactional(readOnly = false)
    public Boolean departmanUpdate(long departmanId, String departmanAdi) {
        Departman departman = departmanLoad(departmanId);
        departman.setDepartmanAdi(departmanAdi);
        Boolean exist = mainDAO.saveOrUpdateObject(departman);

        return exist;
    }

    @Transactional(readOnly = false)
    public Boolean departmanSil(long id) {
        Departman departman = (Departman) mainDAO.loadObject(Departman.class,id);
        return mainDAO.removeObject(departman);
    }


}
