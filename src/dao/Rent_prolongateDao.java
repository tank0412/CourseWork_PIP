package dao;


import models.Rent_prolongate;
import org.primefaces.context.RequestContext;
import templates.ICrud;
import util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Util class for Rent Entity
 */

public class Rent_prolongateDao extends ICrud<Rent_prolongate> {

    EntityManager em = null;

    public Rent_prolongateDao() {
    }

    /**
     *  Return Rent_prolongate Entity of id
     * @param id rent id
     * @return Entity object of Rent_prolongate.class
     */

    public Rent_prolongate getById(long id) {
        Rent_prolongate rent = null;
        try {
            if (em == null || !em.isOpen())
                em = JPAUtil.getEntityManager();
            rent = em.find(Rent_prolongate.class, id);
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

    public ArrayList<Rent_prolongate> getAll() {
        ArrayList<Rent_prolongate> rent = null;
        try {
            if (em == null || !em.isOpen())
                em = JPAUtil.getEntityManager();
            Query query = em.createQuery("from Rent_prolongate");
            rent = (ArrayList<Rent_prolongate>) query.getResultList();
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
    public  ArrayList<Rent_prolongate> getByclientid(long Client_ID) {
        ArrayList<Rent_prolongate> rents = null;
        Rent_prolongate user = null;
        try {
            if (em == null || !em.isOpen())
                em = JPAUtil.getEntityManager();
           Query query = em.createQuery("from Rent_prolongate where Client_ID = :param").setParameter("param", Client_ID);
         //  Query query = em.createQuery("from Users where name = :param").setParameter("param", Client_ID);
            if (query == null)
                return null;
            rents = ( ArrayList<Rent_prolongate> )query.getResultList();
            if (rents != null && rents.size() > 0)
                user = (Rent_prolongate) rents.get(0);
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


