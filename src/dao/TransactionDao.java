package dao;


import models.Transactions;
import models.Users;
import templates.ICrud;
import util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.ArrayList;

public class TransactionDao extends ICrud<Transactions> {
    EntityManager em = null;
    public TransactionDao() {
    }

    /**
     * Return Transactions Entity object by id
     * @param id
     * @return Transactions.class object with id = id
     */
    public Transactions getById(long id) {
        Transactions obj = null;
        try{
            if(em == null || !em.isOpen())
                em = JPAUtil.getEntityManager();
            obj = em.find(Transactions.class, id);
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
     * Return all Transactions for user
     * @param user
     * @param count max return objects count
     * @return ArrayList of Transactions.class objects
     */
    public ArrayList<Transactions> getWith(Users user, int count){
        ArrayList<Transactions> list = null;
        try {
            if(em == null || !em.isOpen())
                em = JPAUtil.getEntityManager();
            Query query = em.createQuery("from Transactions where sender = :param or receiver = :param order by id desc")
                    .setParameter("param", user)
                    .setMaxResults(count);
            list = (ArrayList<Transactions>) query.getResultList();
        }
        catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
        return list;
    }

    /**
     * Return all Transactions from database
     * @return ArrayList of Transactions.class objects
     */
    public ArrayList<Transactions> getAll(){
        ArrayList<Transactions> list = null;
        try {
            if(em == null || !em.isOpen())
                em = JPAUtil.getEntityManager();
            Query query = em.createQuery("from Transactions");
            list = (ArrayList<Transactions>) query.getResultList();
        }
        catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
        return list;
    }

    /**
     * Send coins from one user to another
     * @param from sender
     * @param to reciver
     * @param count amount
     * @param message message to reciver
     * @return boolean value with transaction result
     */
    public boolean sendCoins(Users from, Users to, Long count, String message){
        UserDao ud = new UserDao();
        EntityTransaction transaction = null;
        try {
            if(em == null || !em.isOpen())
                em = JPAUtil.getEntityManager();
            transaction = em.getTransaction();
            transaction.begin();

            Transactions tr = new Transactions(from, to, count, message);
            from.setCoins(from.getCoins() - count);
            to.setCoins(to.getCoins() + count);
            ud.update(from);
            ud.update(to);
            add(tr);

            transaction.commit();
        }
        catch (Exception e) {
            if(transaction != null)
                transaction.rollback();
            e.printStackTrace();
            return false;
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
        return true;
    }

    /**
     * Getting coins
     * @param user who buy
     * @param count amount
     * @return boolean value with transaction result
     */
    public boolean buyCoins(Users user, Long count){
        UserDao ud = new UserDao();
        EntityTransaction transaction = null;
        String message = "Buying coins";
        try {
            if(em == null || !em.isOpen())
                em = JPAUtil.getEntityManager();
            transaction = em.getTransaction();
            transaction.begin();

            Transactions tr = new Transactions(null, user, count, message);
            user.setCoins(user.getCoins() + count);
            ud.update(user);
            add(tr);
            transaction.commit();
        }
        catch (Exception e) {
            if(transaction != null)
                transaction.rollback();
            e.printStackTrace();
            return false;
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
        return true;
    }

    /**
     * Receive coins from system
     * @param user who receive
     * @param count amount
     * @return boolean value with transaction result
     */
    public boolean receiveCoins(Users user, Long count){
        UserDao ud = new UserDao();
        EntityTransaction transaction = null;
        String message = "Receive coins";
        try {
            if(em == null || !em.isOpen())
                em = JPAUtil.getEntityManager();
            transaction = em.getTransaction();
            transaction.begin();

            Transactions tr = new Transactions(user, null, count, message);
            user.setCoins(user.getCoins() - count);
            ud.update(user);
            add(tr);
            transaction.commit();
        }
        catch (Exception e) {
            if(transaction != null)
                transaction.rollback();
            e.printStackTrace();
            return false;
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
        return true;
    }
}
