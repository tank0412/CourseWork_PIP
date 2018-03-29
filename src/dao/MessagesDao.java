package dao;


import models.Chats;
import models.Messages;
import templates.ICrud;
import util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;

public class MessagesDao extends ICrud<Messages> {

    EntityManager em = null;

    public MessagesDao() {
    }

    /**
     * Return object of Messages Entity by id
     * @param id
     * @return Messages.class object
     */
    public Messages getById(long id) {
        Messages obj = null;
        try{
            if(em == null || !em.isOpen())
                em = JPAUtil.getEntityManager();
            obj = em.find(Messages.class, id);
        }
        catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeEntityManager();
        }
        return obj;
    }

    /**
     * Return all Messages from database
     * @return ArrayList of Messages.class objects
     */
    public ArrayList<Messages> getAll(){
        ArrayList<Messages> list = null;
        try {
            if(em == null || !em.isOpen())
                em = JPAUtil.getEntityManager();
            javax.persistence.Query query = em.createQuery("from Messages");
            list = (ArrayList<Messages>) query.getResultList();
        }
        catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeEntityManager();
        }
        return list;
    }

    /**
     * Return Messages from chat
     * @param chat
     * @return ArrayList of Messages.class objects
     */
    public ArrayList<Messages> getAllFrom(Chats chat){
        ArrayList<Messages> list = null;
        try {
            if(em == null || !em.isOpen())
                em = JPAUtil.getEntityManager();
            Query query = em.createQuery("from Messages where chat = :param").setParameter("param", chat);
            list = (ArrayList<Messages>) query.getResultList();
        }
        catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeEntityManager();
        }
        return list;
    }
}
