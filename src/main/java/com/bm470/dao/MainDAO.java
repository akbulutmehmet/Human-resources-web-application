package com.bm470.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public class MainDAO {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession(){
        return  sessionFactory.getCurrentSession();
    }
    public Object loadObject(Class clas , Serializable id){
        return getCurrentSession().get(clas,id);
    }

    public boolean saveOrUpdateObject(Object object){
        boolean success = false;
        try {
            Serializable s = getCurrentSession().save(object);
            success = true;
        }catch (Exception e){
            e.printStackTrace();
            success = false;
        }
        return success ;
    }
    public boolean removeObject(Object object) {
        boolean success = true;
        try {
            getCurrentSession().remove(object);
        } catch (Exception e) {
            e.printStackTrace();
            success = false;
        }
        return success;
    }



}