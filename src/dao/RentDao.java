package dao;


import models.Rent;
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
    public  ArrayList<Rent> getByclientid(long Client_ID) {
        ArrayList<Rent> rents = null;
        Rent user = null;
        try {
            if (em == null || !em.isOpen())
                em = JPAUtil.getEntityManager();
           Query query = em.createQuery("from Rent where client_ID = :param").setParameter("param", Client_ID);
         //  Query query = em.createQuery("from Users where name = :param").setParameter("param", Client_ID);
            if (query == null)
                return null;
            rents = ( ArrayList<Rent> )query.getResultList();
            if (rents != null && rents.size() > 0)
                user = (Rent) rents.get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
        return rents;
    }
    public void prolongateRent(Long rentid, Date newDate_end_rent, Long newPrice_of_rent) {
        List rents;
        Rent rent = null;
        if (em == null || !em.isOpen())
            em = JPAUtil.getEntityManager();
        Query query = em.createNativeQuery("update rent set price_of_rent = :newprice,date_end_rent = :newdate  where id = :rentid" );
        query.setParameter("rentid", rentid);
        query.setParameter("newdate", newDate_end_rent);
        query.setParameter("newprice", newPrice_of_rent);
        List<Rent> result = (List<Rent>)query.getResultList();
        //Query query = em.createQuery("from Users where id = 3L" );
        if (result.size() == 0) { // Не работает TODO: Пофиксить вывод успешности/неуспешности запроса
            RequestContext.getCurrentInstance().execute("alert('Make admin unsuccessful');");
            return;
        }
        else
            RequestContext.getCurrentInstance().execute("alert('Make admin successful');");
        return;
    }
    public void prolongateRent2(Long rentid) {
        List rents;
        Rent rent = null;
        if (em == null || !em.isOpen())
            em = JPAUtil.getEntityManager();
        Query query = em.createNativeQuery("update rent set isprolongated = TRUE where id = :rentid" ).setParameter("rentid", rentid);

        List<Rent> result = (List<Rent>)query.getResultList();
        //Query query = em.createQuery("from Users where id = 3L" );
        if (result.size() == 0) { // Не работает TODO: Пофиксить вывод успешности/неуспешности запроса
            RequestContext.getCurrentInstance().execute("alert('Make prolongateRent2 unsuccessful');");
            return;
        }
        else
            RequestContext.getCurrentInstance().execute("alert('Make prolongateRent2 successful');");
        return;
    }
    public void confirmRent(Long rentid) {
        List rents;
        Rent rent = null;
        if (em == null || !em.isOpen())
            em = JPAUtil.getEntityManager();
        Query query = em.createNativeQuery("update rent set isconfirmed = TRUE where id = :rentid" ).setParameter("rentid", rentid);

        List<Rent> result = (List<Rent>)query.getResultList();
        //Query query = em.createQuery("from Users where id = 3L" );
        if (result.size() == 0) { // Не работает TODO: Пофиксить вывод успешности/неуспешности запроса
            RequestContext.getCurrentInstance().execute("alert('Make confirmRent unsuccessful');");
            return;
        }
        else
            RequestContext.getCurrentInstance().execute("alert('Make confirmRent successful');");
        return;
    }

    public void declineRent(Long rentid) {
        List rents;
        Rent rent = null;
        if (em == null || !em.isOpen())
            em = JPAUtil.getEntityManager();
        Query query = em.createNativeQuery("update rent set isconfirmed = FALSE where id = :rentid" ).setParameter("rentid", rentid);

        List<Rent> result = (List<Rent>)query.getResultList();
        //Query query = em.createQuery("from Users where id = 3L" );
        if (result.size() == 0) { // Не работает TODO: Пофиксить вывод успешности/неуспешности запроса
            RequestContext.getCurrentInstance().execute("alert('Make declineRent unsuccessful');");
            return;
        }
        else
            RequestContext.getCurrentInstance().execute("alert('Make declineRent successful');");
        return;
    }


    }


