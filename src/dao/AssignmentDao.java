package dao;


import models.Assignment;
import templates.ICrud;
import util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;

public class AssignmentDao extends ICrud<Assignment> {
    EntityManager em = null;

    public AssignmentDao() {
        if(em == null || !em.isOpen())
            em = JPAUtil.getEntityManager();
    }

    /**
     * Return Entity by id
     * @param id
     * @return Assignment.class Entity
     */
    public Assignment getById(long id) {
        Assignment obj = null;
        try{
            if(em == null || !em.isOpen())
                em = JPAUtil.getEntityManager();
            obj = em.find(Assignment.class, id);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
        return obj;
    }

    /**
     * Return all object from database
     * @return ArrayList of Assignment.class objects
     */
    public ArrayList<Assignment> getAll(){
        ArrayList<Assignment> list = null;
        try {
            if(em == null || !em.isOpen())
                em = JPAUtil.getEntityManager();
            Query query = em.createQuery("from Assignment");
            list = (ArrayList<Assignment>) query.getResultList();
        }
        catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
        return list;
    }

}
