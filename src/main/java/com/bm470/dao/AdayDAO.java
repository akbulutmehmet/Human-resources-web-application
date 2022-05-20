package com.bm470.dao;

import com.bm470.model.Aday;
import com.bm470.model.InsanKaynaklari;
import com.bm470.model.Personel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class AdayDAO {
    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession () {
        return sessionFactory.getCurrentSession();
    }

    public Aday loginKontrol(String email, String sifre) {
        Session currentSession = getCurrentSession();
        CriteriaBuilder criteriaBuilder = currentSession.getCriteriaBuilder();
        CriteriaQuery<Aday> criteriaQuery = criteriaBuilder.createQuery(Aday.class);
        Root<Aday> root = criteriaQuery.from(Aday.class);
        Predicate predicateAdayEposta = criteriaBuilder.equal((root.get("adayEposta")),email);
        Predicate predicateAdaySifre = criteriaBuilder.equal((root.get("adaySifre")),sifre);

        criteriaQuery.select(root).where(criteriaBuilder.and(predicateAdaySifre,predicateAdaySifre));

        Query<Aday> query = currentSession.createQuery(criteriaQuery);
        Aday aday = null;
        try {
            aday = query.getSingleResult();
        }
        catch (Exception e) {
            return aday;
        }
        return aday;
    }

    public List<Long> adayTcListele() {
        Session currentSession = getCurrentSession();
        CriteriaBuilder criteriaBuilder = currentSession.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        Root<Aday> root = criteriaQuery.from(Aday.class);

        criteriaQuery.select(root.get("adayTc"));
        Query<Long> query = currentSession.createQuery(criteriaQuery);
        List<Long> adayTcList = query.getResultList();
        return adayTcList;
    }
}