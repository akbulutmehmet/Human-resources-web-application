package com.bm470.dao;

import com.bm470.model.InsanKaynaklari;
import com.bm470.model.IzinliPersonel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class IzinliPersonelDAO {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession () {
        return sessionFactory.getCurrentSession();
    }


    public List<IzinliPersonel> izinliPersonelListele() {
        Session currentSession = getCurrentSession();
        CriteriaBuilder criteriaBuilder = currentSession.getCriteriaBuilder();
        CriteriaQuery<IzinliPersonel> criteriaQuery = criteriaBuilder.createQuery(IzinliPersonel.class);
        Root<IzinliPersonel> root = criteriaQuery.from(IzinliPersonel.class);

        criteriaQuery.select(root);
        Query<IzinliPersonel> query = currentSession.createQuery(criteriaQuery);
        List<IzinliPersonel> izinliPersonelList = query.getResultList();
        return izinliPersonelList;
    }
}
