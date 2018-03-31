package dao;


import models.Rent;
import templates.ICrud;
import util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;


/**
 * Util class for Rent Entity
 */

public class RentDao extends ICrud<Rent> {

    EntityManager em = null;

    public RentDao() {
    }

    /**
     *  Return Rent Entity of id
     * @param id rent id
     * @return Entity object of Rent.class
     */

    public Rent getById(long id) {
        Rent rent = null;
        try {
            if (em == null || !em.isOpen())
                em = JPAUtil.getEntityManager();
            rent = em.find(Rent.class, id);
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

    public ArrayList<Rent> getAll() {
        ArrayList<Rent> rent = null;
        try {
            if (em == null || !em.isOpen())
                em = JPAUtil.getEntityManager();
            Query query = em.createQuery("from Rent");
            rent = (ArrayList<Rent>) query.getResultList();
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
     * @return Entity object of Rent.class
     */
    public Rent getByclientid(long Client_ID) {
        List rents;
        Rent user = null;
        try {
            if (em == null || !em.isOpen())
                em = JPAUtil.getEntityManager();
           Query query = em.createQuery("from Rent where Client_ID = :param").setParameter("param", Client_ID);
         //  Query query = em.createQuery("from Users where name = :param").setParameter("param", Client_ID);
            if (query == null)
                return null;
            rents = query.getResultList();
            if (rents != null && rents.size() > 0)
                user = (Rent) rents.get(0);
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


