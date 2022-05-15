package com.bm470.dao;

import com.bm470.model.InsanKaynaklari;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

@Repository
public class InsanKaynaklariDAO {
    @Autowired
    private SessionFactory sessionFactory;

     private Session getCurrentSession () {
         return sessionFactory.getCurrentSession();
     }
    public InsanKaynaklari loginKontrol(String email, String sifre)  {
        Session currentSession = getCurrentSession();
        CriteriaBuilder criteriaBuilder = currentSession.getCriteriaBuilder();
        CriteriaQuery<InsanKaynaklari> criteriaQuery = criteriaBuilder.createQuery(InsanKaynaklari.class);
        Root<InsanKaynaklari> root = criteriaQuery.from(InsanKaynaklari.class);
        Predicate predicateIkEposta = criteriaBuilder.equal((root.get("ikEposta")),email);
        Predicate predicateIkSifre = criteriaBuilder.equal((root.get("ikSifre")),sifre);

        criteriaQuery.select(root).where(criteriaBuilder.and(predicateIkEposta,predicateIkSifre));

        Query<InsanKaynaklari> query = currentSession.createQuery(criteriaQuery);
        InsanKaynaklari insanKaynaklari = null;
        try {
            insanKaynaklari = query.getSingleResult();
        }
        catch (Exception e) {
            return insanKaynaklari;
        }
        return insanKaynaklari;
    }

    public List<InsanKaynaklari> insanKaynaklariListele() {
        Session currentSession = getCurrentSession();
        CriteriaBuilder criteriaBuilder = currentSession.getCriteriaBuilder();
        CriteriaQuery<InsanKaynaklari> criteriaQuery = criteriaBuilder.createQuery(InsanKaynaklari.class);
        Root<InsanKaynaklari> root = criteriaQuery.from(InsanKaynaklari.class);

        criteriaQuery.select(root);
        Query<InsanKaynaklari> query = currentSession.createQuery(criteriaQuery);
        List<InsanKaynaklari> insanKaynaklariList = query.getResultList();
         return insanKaynaklariList;
    }


}
