package dao;


import models.Fines;
import templates.ICrud;
import util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;


/**
 * Util class for Fines Entity
 */

public class FinesDao extends ICrud<Fines> {

    EntityManager em = null;

    public FinesDao() {
    }

    /**
     *  Return Fines Entity of id
     * @param id Fines id
     * @return Entity object of Fines.class
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
     * Return all Fines from database
     * @return ArrayList with Entitys of Fines.class
     */

    public ArrayList<Fines> getAll() {
        ArrayList<Fines> fine = null;
        try {
            if (em == null || !em.isOpen())
                em = JPAUtil.getEntityManager();
            Query query = em.createQuery("from Fines ");
            fine = (ArrayList<Fines>) query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
        return fine;
    }

    /**
     * Get Fines with client_id
     * @param Client_ID of client_id
     * @return Entity object of Fines.class
     */
    public  ArrayList<Fines> getByclientid(long Client_ID) {
        ArrayList<Fines> fines = null;
        Fines user = null;
        try {
            if (em == null || !em.isOpen())
                em = JPAUtil.getEntityManager();
           Query query = em.createQuery("from Fines where client_ID = :param").setParameter("param", Client_ID);
         //  Query query = em.createQuery("from Users where name = :param").setParameter("param", Client_ID);
            if (query == null)
                return null;
            fines =(ArrayList<Fines>) query.getResultList();
            if (fines != null && fines.size() > 0)
                user = (Fines) fines.get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
        return fines;
    }


    }


