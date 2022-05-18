package com.bm470.dao;

import com.bm470.model.Departman;
import com.bm470.model.Gorev;
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
public class PersonelDAO {
    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession () {
        return sessionFactory.getCurrentSession();
    }

    public List<Personel> personelListele() {
        Session currentSession = getCurrentSession();
        CriteriaBuilder criteriaBuilder = currentSession.getCriteriaBuilder();
        CriteriaQuery<Personel> criteriaQuery = criteriaBuilder.createQuery(Personel.class);
        Root<Personel> root = criteriaQuery.from(Personel.class);

        criteriaQuery.select(root);
        Query<Personel> query = currentSession.createQuery(criteriaQuery);
        List<Personel> personelList = query.getResultList();
        return personelList;
    }

    public List<Personel> personelGetir(long gorevId) {
        Session currentSession = getCurrentSession();
        CriteriaBuilder criteriaBuilder = currentSession.getCriteriaBuilder();
        CriteriaQuery<Personel> criteriaQuery = criteriaBuilder.createQuery(Personel.class);

        Root<Personel> personelRoot = criteriaQuery.from(Personel.class);


        Predicate predicateGorev = criteriaBuilder.equal(personelRoot.get("gorev").get("gorevId"),gorevId);

        criteriaQuery.select(personelRoot).where(predicateGorev);
        Query<Personel> query = currentSession.createQuery(criteriaQuery);
        List<Personel> personelList = query.getResultList();
        return personelList;
    }


    public List<Long> personelTcListele() {
        Session currentSession = getCurrentSession();
        CriteriaBuilder criteriaBuilder = currentSession.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        Root<Personel> root = criteriaQuery.from(Personel.class);

        criteriaQuery.select(root.get("personelTc"));
        Query<Long> query = currentSession.createQuery(criteriaQuery);
        List<Long> personelTcList = query.getResultList();
        return personelTcList;
    }
}
