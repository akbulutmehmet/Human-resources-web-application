package com.mehmet.akbulut.dao;

import com.mehmet.akbulut.model.Aday;
import com.mehmet.akbulut.model.AdayEgitim;
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
public class AdayEgitimDAO {
    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession () {
        return sessionFactory.getCurrentSession();
    }


    public List<AdayEgitim> adayIsTecrubeListele(Aday aday) {

        Session currentSession = getCurrentSession();
        CriteriaBuilder criteriaBuilder = currentSession.getCriteriaBuilder();
        CriteriaQuery<AdayEgitim> criteriaQuery = criteriaBuilder.createQuery(AdayEgitim.class);
        Root<AdayEgitim> root = criteriaQuery.from(AdayEgitim.class);
        Predicate predicate = criteriaBuilder.equal(root.get("aday").get("adayId"),aday.getAdayId());
        criteriaQuery.select(root).where(predicate);
        Query<AdayEgitim> query = currentSession.createQuery(criteriaQuery);
        List<AdayEgitim> adayEgitimList = query.getResultList();
        return adayEgitimList;
    }
}
