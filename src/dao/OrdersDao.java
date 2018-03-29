package dao;

import models.Orders;
import models.Users;
import templates.ICrud;
import util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;

public class OrdersDao extends ICrud<Orders> {
    EntityManager em = null;
    public OrdersDao() {
        if(em == null || !em.isOpen())
            em = JPAUtil.getEntityManager();
    }

    /**
     * Return Orders.class object by id
     * @param id
     * @return Orders.class object with id = id
     */
    public Orders getById(long id) {
        Orders obj = null;
        try{
            if(em == null || !em.isOpen())
                em = JPAUtil.getEntityManager();
            obj = em.find(Orders.class, id);
        }
        catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
        return obj;
    }

    /**
     * Return all orders from data base
     * @return ArrayList of Orders.class objects
     */
    public ArrayList<Orders> getAll(){
        ArrayList<Orders> users = null;
        try {
            if(em == null || !em.isOpen())
                em = JPAUtil.getEntityManager();
            Query query = em.createQuery("from Orders order by placementDate desc ");
            users = (ArrayList<Orders>) query.getResultList();
        }
        catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
        return users;
    }

    /**
     * Return all order that opened by user
     * @param user
     * @return ArrayList of Orders.class objects
     */
    public ArrayList<Orders> getOpenOrders(Users user){
        ArrayList<Orders> users = null;
        try {
            if(em == null || !em.isOpen())
                em = JPAUtil.getEntityManager();
            Query query = em.createQuery("from Orders where open = true and executor = :param order by placementDate desc ").setParameter("param", user);
            users = (ArrayList<Orders>) query.getResultList();
        }
        catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
        return users;
    }

    /**
     * Return all open orders
     * @return ArrayList of Orders.class objects
     */
    public ArrayList<Orders> getOpenOrders(){
        ArrayList<Orders> users = null;
        try {
            if(em == null || !em.isOpen())
                em = JPAUtil.getEntityManager();
            Query query = em.createQuery("from Orders where open = true order by placementDate desc ");
            users = (ArrayList<Orders>) query.getResultList();
        }
        catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
        return users;
    }

    /**
     * Return all closed orders were executor is user
     * @param user
     * @return ArrayList of Orders.class objects
     */
    public ArrayList<Orders> getClosedByExecutor(Users user){
        ArrayList<Orders> users = null;
        try {
            if(em == null || !em.isOpen())
                em = JPAUtil.getEntityManager();
            Query query = em.createQuery("from Orders where open = false and executor = :param order by placementDate desc ").setParameter("param", user);
            users = (ArrayList<Orders>) query.getResultList();
        }
        catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
        return users;
    }

    /**
     * Return all orders where customer is user
     * @param user
     * @return ArrayList of Orders.class objects
     */
    public ArrayList<Orders> getByCustomer(Users user){
        ArrayList<Orders> users = null;
        try {
            if(em == null || !em.isOpen())
                em = JPAUtil.getEntityManager();
            Query query = em.createQuery("from Orders where customer = :param order by placementDate desc ").setParameter("param", user);
            users = (ArrayList<Orders>) query.getResultList();
        }
        catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
        return users;
    }
}
