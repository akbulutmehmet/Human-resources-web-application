package com.bm470.dao;

import com.bm470.model.Departman;
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
import java.util.List;
@Repository
public class DepartmanDAO {
    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession () {
        return sessionFactory.getCurrentSession();
    }


    public List<Departman> departmanListele() {
        Session currentSession = getCurrentSession();
        CriteriaBuilder criteriaBuilder = currentSession.getCriteriaBuilder();
        CriteriaQuery<Departman> criteriaQuery = criteriaBuilder.createQuery(Departman.class);
        Root<Departman> root = criteriaQuery.from(Departman.class);

        criteriaQuery.select(root);
        Query<Departman> query = currentSession.createQuery(criteriaQuery);
        List<Departman> departmanList = query.getResultList();
        return departmanList;
    }
}
