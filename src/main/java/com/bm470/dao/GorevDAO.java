package com.bm470.dao;

import com.bm470.model.Departman;
import com.bm470.model.Gorev;
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
public class GorevDAO {
    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession () {
        return sessionFactory.getCurrentSession();
    }
    public List<Gorev> gorevListele() {
        Session currentSession = getCurrentSession();
        CriteriaBuilder criteriaBuilder = currentSession.getCriteriaBuilder();
        CriteriaQuery<Gorev> criteriaQuery = criteriaBuilder.createQuery(Gorev.class);
        Root<Gorev> root = criteriaQuery.from(Gorev.class);

        criteriaQuery.select(root);
        Query<Gorev> query = currentSession.createQuery(criteriaQuery);
        List<Gorev> gorevList = query.getResultList();
        return gorevList;
    }

    public List<Gorev> gorevGetir(long departmanId){
        Session currentSession = getCurrentSession();
        CriteriaBuilder criteriaBuilder = currentSession.getCriteriaBuilder();
        CriteriaQuery<Gorev> criteriaQuery = criteriaBuilder.createQuery(Gorev.class);

        Root<Gorev> gorevRoot = criteriaQuery.from(Gorev.class);


        Predicate predicateDepartman = criteriaBuilder.equal(gorevRoot.get("departman").get("departmanId"),departmanId);

        criteriaQuery.select(gorevRoot).where(predicateDepartman);
        Query<Gorev> query = currentSession.createQuery(criteriaQuery);
        List<Gorev> gorevList = query.getResultList();
        return gorevList;
    }
}
