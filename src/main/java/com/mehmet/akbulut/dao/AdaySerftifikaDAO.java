package com.mehmet.akbulut.dao;

import com.mehmet.akbulut.model.Aday;
import com.mehmet.akbulut.model.AdayEgitim;
import com.mehmet.akbulut.model.AdaySerftifika;
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
public class AdaySerftifikaDAO {
    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession () {
        return sessionFactory.getCurrentSession();
    }


    public List<AdaySerftifika> adaySerftifikaListele(Aday aday) {

        Session currentSession = getCurrentSession();
        CriteriaBuilder criteriaBuilder = currentSession.getCriteriaBuilder();
        CriteriaQuery<AdaySerftifika> criteriaQuery = criteriaBuilder.createQuery(AdaySerftifika.class);
        Root<AdaySerftifika> root = criteriaQuery.from(AdaySerftifika.class);
        Predicate predicate = criteriaBuilder.equal(root.get("aday").get("adayId"),aday.getAdayId());
        criteriaQuery.select(root).where(predicate);
        Query<AdaySerftifika> query = currentSession.createQuery(criteriaQuery);
        List<AdaySerftifika> adaySerftifikaList = query.getResultList();
        return adaySerftifikaList;
    }
}
