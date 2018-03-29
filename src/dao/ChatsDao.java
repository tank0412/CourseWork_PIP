package dao;

import models.Chats;
import templates.ICrud;
import util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;

public class ChatsDao extends ICrud<Chats> {
    EntityManager em = null;

    public ChatsDao() {
        if(em == null || !em.isOpen())
            em = JPAUtil.getEntityManager();
    }

    /**
     * Return object of Chats Entity by id
     * @param id
     * @return Chats.class object
     */
    public Chats getById(long id) {
        Chats obj = null;
        try{
            if(em == null || !em.isOpen())
                em = JPAUtil.getEntityManager();
            obj = em.find(Chats.class, id);
        }
        catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeEntityManager();
        }
        return obj;
    }

    /**
     * Return all chats from database
     * @return ArrayList of Chats.class objects
     */
    public ArrayList<Chats> getAll(){
        ArrayList<Chats> list = null;
        try {
            if(em == null || !em.isOpen())
                em = JPAUtil.getEntityManager();
            Query query = em.createQuery("from Chats");
            list = (ArrayList<Chats>) query.getResultList();
        }
        catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeEntityManager();
        }
        return list;
    }
}
