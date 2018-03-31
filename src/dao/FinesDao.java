package dao;


import models.Fines;
import templates.ICrud;
import util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;


/**
 * Util class for Rent Entity
 */

public class FinesDao extends ICrud<Fines> {

    EntityManager em = null;

    public FinesDao() {
    }

    /**
     *  Return Rent Entity of id
     * @param id rent id
     * @return Entity object of Rent.class
     */

    public Fines getById(long id) {
        Fines fines = null;
        try {
            if (em == null || !em.isOpen())
                em = JPAUtil.getEntityManager();
            fines = em.find(Fines.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
        return fines;
    }

    /**
     * Return all Rent from database
     * @return ArrayList with Entitys of Rent.class
     */

    public ArrayList<Fines> getAll() {
        ArrayList<Fines> rent = null;
        try {
            if (em == null || !em.isOpen())
                em = JPAUtil.getEntityManager();
            Query query = em.createQuery("from Rent");
            rent = (ArrayList<Fines>) query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
        return rent;
    }

    /**
     * Get Fines with client_id
     * @param Client_ID of client_id
     * @return Entity object of Fines.class
     */
    public Fines getByclientid(long Client_ID) {
        List rents;
        Fines user = null;
        try {
            if (em == null || !em.isOpen())
                em = JPAUtil.getEntityManager();
           Query query = em.createQuery("from Fines where client_ID = :param").setParameter("param", Client_ID);
         //  Query query = em.createQuery("from Users where name = :param").setParameter("param", Client_ID);
            if (query == null)
                return null;
            rents = query.getResultList();
            if (rents != null && rents.size() > 0)
                user = (Fines) rents.get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
        return user;
    }


    }


