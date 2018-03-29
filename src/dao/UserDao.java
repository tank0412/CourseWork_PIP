package dao;


import models.Chats;
import models.Ranks;
import models.Users;
import templates.ICrud;
import util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
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

    /**
     * Add chat in user chatlist
     * @param user add to this user
     * @param chat this chat add to user
     */

    public void addChat(Users user, Chats chat) {
        EntityTransaction transaction = null;

        try {
            if (em == null || !em.isOpen())
                em = JPAUtil.getEntityManager();
            transaction = em.getTransaction();
            UserDao userDao = new UserDao();
            Collection<Chats> chats = (Collection<Chats>) user.getChats();
            chats.add(chat);
            user.setChats(chats);
            userDao.update(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
            e.printStackTrace();
        }finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    /**
     * Remove chat from user chatlist
     * @param user from whose delete chat
     * @param chatId that should delete from chatlist
     */

    public void removeChat(Users user, Long chatId){
        EntityTransaction transaction = null;
        try {
            if (em == null || !em.isOpen())
                em = JPAUtil.getEntityManager();
            transaction = em.getTransaction();
            transaction.begin();
            UserDao userDao = new UserDao();
            Collection<Chats> chats = (Collection<Chats>) user.getChats();
            Chats rchat = null;
            for (Chats c:
                 chats) {
                if(c.getId() == chatId){
                    rchat = c;
                    break;
                }
            }
            chats.remove(rchat);
            user.setChats(chats);
            userDao.update(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
            e.printStackTrace();
        }finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    /**
     * Return List of Users by filtr
     * @param nick filtr
     * @param userid filtr
     * @param ranklvl filtr
     * @return List of Users.class Entity
     */
    public List<Users> getByFilters(String nick, Long userid, Integer ranklvl) {
        List users = null;
        Users user = null;
        Ranks rank = null;
        try {
            if (em == null || !em.isOpen())
                em = JPAUtil.getEntityManager();

            Query query;

            if(nick != null && nick.length() > 0){
                if(userid != null){
                    if(ranklvl != null){
                        query = em.createQuery("from Users where nickname like :nparam and id = :iparam and rank.level = :rparam")
                                .setParameter("nparam", "%"+nick+"%")
                                .setParameter("iparam", userid)
                                .setParameter("rparam", ranklvl);
                    }
                    else
                    {
                        query = em.createQuery("from Users where nickname like :nparam and id = :iparam")
                                .setParameter("nparam", nick)
                                .setParameter("iparam", userid);
                    }
                }
                else
                {
                    if(ranklvl != null){
                        query = em.createQuery("from Users where nickname like :nparam and rank.level = :rparam")
                                .setParameter("nparam", "%"+nick+"%")
                                .setParameter("rparam", ranklvl);
                    }
                    else
                    {
                        query = em.createQuery("from Users where nickname like :nparam")
                                .setParameter("nparam", "%"+nick+"%");
                    }
                }
            }
            else
            {
                if(userid != null){
                    if(ranklvl != null){
                        query = em.createQuery("from Users where id = :iparam and rank.level = :rparam")
                                .setParameter("iparam", userid)
                                .setParameter("rparam", ranklvl);
                    }
                    else
                    {
                        query = em.createQuery("from Users where id = :iparam")
                                .setParameter("iparam", userid);
                    }
                }
                else
                {
                    if(ranklvl != null){
                        query = em.createQuery("from Users where rank.level = :rparam")
                                .setParameter("rparam", ranklvl);
                    }
                    else
                    {
                        query = em.createQuery("from Users");
                    }
                }
            }

            if (query == null)
                return null;
            users = query.getResultList();

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
     * Set level to user
     * @param user
     * @param level
     */
    public void updateLvl(Users user, int level){
        EntityTransaction transaction = null;
        try {
            if (em == null || !em.isOpen())
                em = JPAUtil.getEntityManager();
            transaction = em.getTransaction();
            transaction.begin();
            UserDao userDao = new UserDao();
            Users us = userDao.getById(user.getId());
            int lvl = Math.min(level, 9);
            RanksDao rd = new RanksDao();
            Ranks rank = rd.getByLvl(lvl);
            us.setRank(rank);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
            e.printStackTrace();
        }finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

}
