package dao;


import models.Users;
import templates.ICrud;
import util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Util class for User Entity
 */

public class UserDao extends ICrud<Users> {

    EntityManager em = null;

    public UserDao() {
    }

    /**
     *  Return Users Entity of id
     * @param id user id
     * @return Entity object of Users.class
     */

    public Users getById(long id) {
        Users user = null;
        try {
            if (em == null || !em.isOpen())
                em = JPAUtil.getEntityManager();
            user = em.find(Users.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
        return user;
    }

    /**
     * Return all Users from database
     * @return ArrayList with Entitys of Users.class
     */

    public ArrayList<Users> getAll() {
        ArrayList<Users> users = null;
        try {
            if (em == null || !em.isOpen())
                em = JPAUtil.getEntityManager();
            Query query = em.createQuery("from Users");
            users = (ArrayList<Users>) query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
        return users;
    }

    /**
     * Return all Users from database who have not null JID
     * @return ArrayList with Entitys of Users.class
     */

    public ArrayList<Users> getAllWithJID() {
        ArrayList<Users> users = null;
        try {
            if (em == null || !em.isOpen())
                em = JPAUtil.getEntityManager();
            Query query = em.createQuery("from Users where length(jid) > 0");
            users = (ArrayList<Users>) query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
        return users;
    }

    /**
     * Get user with nickname
     * @param nickname of user
     * @return Entity object of Users.class
     */
    public Users getByNickname(String nickname) {
        List users;
        Users user = null;
        try {
            if (em == null || !em.isOpen())
                em = JPAUtil.getEntityManager();
            Query query = em.createQuery("from Users where nickname = :param").setParameter("param", nickname);
            if (query == null)
                return null;
            users = query.getResultList();
            if (users != null && users.size() > 0)
                user = (Users) users.get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
        return user;
    }

    /**
     * Get User by name
     * @param name
     * @return Entity object of Users.class
     * @throws SQLException
     */

    public Users getByName(String name) throws SQLException {
        List users;
        Users user = null;
        try {
            if (em == null || !em.isOpen())
                em = JPAUtil.getEntityManager();
            Query query = em.createQuery("from Users where name = :param").setParameter("param", name);
            if (query == null)
                return null;
            users = query.getResultList();
            if (users != null && users.size() > 0)
                user = (Users) users.get(0);
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


