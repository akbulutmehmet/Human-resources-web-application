package com.bm470.dao;

import com.bm470.model.Departman;
import com.bm470.model.Gorev;
import com.bm470.model.Personel;
import net.sf.json.JSONArray;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;
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

    public Long getTotalCount (String dbQuery) {
        Session currentSession = getCurrentSession();
        CriteriaBuilder criteriaBuilder = currentSession.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        Root<Personel> root = criteriaQuery.from(Personel.class);

        criteriaQuery.select(criteriaBuilder.countDistinct(root));
        if(dbQuery != null && !dbQuery.equals("")) {
            if(StringUtils.isNumeric(dbQuery)) {
                Predicate predicateTc = criteriaBuilder.equal(root.get("personelTc"),Long.valueOf(dbQuery));
                Predicate predicateId = criteriaBuilder.equal(root.get("personelId"),Long.valueOf(dbQuery));
                criteriaQuery.where(criteriaBuilder.or(predicateId,predicateTc));
            }
            else {
                Predicate predicateAd = criteriaBuilder.like(root.get("personelAd"),"%" + dbQuery + "%");
                Predicate predicateSoyad = criteriaBuilder.like(root.get("personelSoyad"),"%" + dbQuery + "%");
                criteriaQuery.where(criteriaBuilder.or(predicateAd,predicateSoyad));
            }
        }
        Query<Long> query = currentSession.createQuery(criteriaQuery);
        return  query.getSingleResult();
    }

    public List<Personel> loadPersonel(int start, int length, String dbQuery,String columnName,String order) {
        Session currentSession = getCurrentSession();
        CriteriaBuilder criteriaBuilder = currentSession.getCriteriaBuilder();
        CriteriaQuery<Personel> criteriaQuery = criteriaBuilder.createQuery(Personel.class);
        Root<Personel> root = criteriaQuery.from(Personel.class);
        criteriaQuery.select(root);
        if(dbQuery != null && !dbQuery.equals("")) {
            if(StringUtils.isNumeric(dbQuery)) {
                Predicate predicateTc = criteriaBuilder.equal(root.get("personelTc"),Long.valueOf(dbQuery));
                Predicate predicateId = criteriaBuilder.equal(root.get("personelId"),Long.valueOf(dbQuery));
                criteriaQuery.where(criteriaBuilder.or(predicateId,predicateTc));
            }
            else {
                Predicate predicateAd = criteriaBuilder.like(root.get("personelAd"),"%" + dbQuery + "%");
                Predicate predicateSoyad = criteriaBuilder.like(root.get("personelSoyad"),"%" + dbQuery + "%");
                criteriaQuery.where(criteriaBuilder.or(predicateAd,predicateSoyad));
            }
        }
        if(columnName!=null && !columnName.equals("") && order!=null && !order.equals("")) {
            if(order.equals("asc")) {
                if(columnName.equals("departman")) {
                    criteriaQuery.orderBy(criteriaBuilder.asc(root.get("gorev").get("departman").get("departmanAdi")));
                }
                else if (columnName.equals("gorev")) {
                    criteriaQuery.orderBy(criteriaBuilder.asc(root.get("gorev").get("gorevAdi")));
                }
                else {
                    criteriaQuery.orderBy(criteriaBuilder.asc(root.get(columnName)));
                }
            }
            else if (order.equals("desc")) {
                if(columnName.equals("departman")) {
                    criteriaQuery.orderBy(criteriaBuilder.desc(root.get("gorev").get("departman").get("departmanAdi")));
                }
                else if (columnName.equals("gorev")) {
                    criteriaQuery.orderBy(criteriaBuilder.desc(root.get("gorev").get("gorevAdi")));
                }
                else {
                    criteriaQuery.orderBy(criteriaBuilder.desc(root.get(columnName)));
                }
            }

        }

        Query<Personel> query = currentSession.createQuery(criteriaQuery);
        query.setFirstResult(start);
        query.setMaxResults(length);
        List<Personel> personelList = query.getResultList();
        return personelList;
    }
}
