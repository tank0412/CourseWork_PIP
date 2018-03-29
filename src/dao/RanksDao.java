package dao;


import models.Ranks;
import templates.ICrud;
import util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class RanksDao extends ICrud<Ranks> {
    EntityManager em = null;
    public RanksDao() {
        if(em == null || !em.isOpen())
            em = JPAUtil.getEntityManager();
    }

    /**
     * Return Ranks.class object by id
     * @param id
     * @return Ranks.class object where id = id
     */
    public Ranks getById(long id) {
        Ranks obj = null;
        try{
            if(em == null || !em.isOpen())
                em = JPAUtil.getEntityManager();
            obj = em.find(Ranks.class, id);
        }
        catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
        return obj;
    }

    /**
     * Return all ranks from database
     * @return ArrayList of Ranks.class objects
     */
    public ArrayList<Ranks> getAll(){
        ArrayList<Ranks> list = null;
        try {
            if(em == null || !em.isOpen())
                em = JPAUtil.getEntityManager();
            Query query = em.createQuery("from Ranks");
            list = (ArrayList<Ranks>) query.getResultList();
        }
        catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
        return list;
    }

    /**
     * Return rank by name
     * @param name
     * @return Ranks.class object with name == name
     */
    public Ranks getByName(String name)  {
        List objs;
        Ranks obj = null;
        try {
            if(em == null || !em.isOpen())
                em = JPAUtil.getEntityManager();
            Query query = em.createQuery("from Ranks where name = :param").setParameter("param", name);
            objs = query.getResultList();
            if (objs != null && objs.size() > 0)
                obj = (Ranks) objs.get(0);

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
        return obj;
    }

    /**
     * Return ranks by lvl
     * @param lvl
     * @return Ranks.class object with lvl = level
     */
    public Ranks getByLvl(Integer lvl)  {
        List objs;
        Ranks obj = null;
        try {
            if(em == null || !em.isOpen())
                em = JPAUtil.getEntityManager();
            Query query = em.createQuery("from Ranks where level = :param").setParameter("param", lvl);
            objs = query.getResultList();
            if (objs != null && objs.size() > 0)
                obj = (Ranks) objs.get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
        return obj;
    }
}
