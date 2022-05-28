package com.mehmet.akbulut.dao;

import com.mehmet.akbulut.model.Aday;
import com.mehmet.akbulut.model.AdayIsTecrube;
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
public class AdayIsTecrubeDAO {
    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession () {
        return sessionFactory.getCurrentSession();
    }


    public List<AdayIsTecrube> adayIsTecrubeListele(Aday aday) {

        Session currentSession = getCurrentSession();
        CriteriaBuilder criteriaBuilder = currentSession.getCriteriaBuilder();
        CriteriaQuery<AdayIsTecrube> criteriaQuery = criteriaBuilder.createQuery(AdayIsTecrube.class);
        Root<AdayIsTecrube> root = criteriaQuery.from(AdayIsTecrube.class);
        Predicate predicate = criteriaBuilder.equal(root.get("aday").get("adayId"),aday.getAdayId());
        criteriaQuery.select(root).where(predicate);
        Query<AdayIsTecrube> query = currentSession.createQuery(criteriaQuery);
        List<AdayIsTecrube> adayIsTecrubeList = query.getResultList();
        return adayIsTecrubeList;
    }
}
