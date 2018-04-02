package dao;


import models.Rent_prolong;
import templates.ICrud;
import util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;


/**
 * Util class for Rent Entity
 */

public class Rent_prolongDao extends ICrud<Rent_prolong> {

    EntityManager em = null;

    public Rent_prolongDao() {
    }

    /**
     *  Return Rent_prolongate Entity of id
     * @param id rent id
     * @return Entity object of Rent_prolongate.class
     */

    public Rent_prolong getById(long id) {
        Rent_prolong rent = null;
        try {
            if (em == null || !em.isOpen())
                em = JPAUtil.getEntityManager();
            rent = em.find(Rent_prolong.class, id);
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
     * Return all Rent from database
     * @return ArrayList with Entitys of Rent.class
     */

    public ArrayList<Rent_prolong> getAll() {
        ArrayList<Rent_prolong> rent = null;
        try {
            if (em == null || !em.isOpen())
                em = JPAUtil.getEntityManager();
            Query query = em.createQuery("from Rent_prolong");
            rent = (ArrayList<Rent_prolong>) query.getResultList();
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
     * Get rent with client_id
     * @param Client_ID of client_id
     * @return Entity object of Rent_prolong.class
     */
    public  ArrayList<Rent_prolong> getByclientid(long Client_ID) {
        ArrayList<Rent_prolong> rents = null;
        Rent_prolong user = null;
        try {
            if (em == null || !em.isOpen())
                em = JPAUtil.getEntityManager();
           Query query = em.createQuery("from Rent_prolong where client_ID = :param").setParameter("param", Client_ID);
         //  Query query = em.createQuery("from Users where name = :param").setParameter("param", Client_ID);
            if (query == null)
                return null;
            rents = ( ArrayList<Rent_prolong> )query.getResultList();
            if (rents != null && rents.size() > 0)
                user = (Rent_prolong) rents.get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
        return rents;
    }


    }


