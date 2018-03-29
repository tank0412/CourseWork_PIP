package dao;

import models.Requestexec;
import templates.ICrud;
import util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;

public class RequestexecDao extends ICrud<Requestexec> {
    EntityManager em = null;

    /**
     * Return record about request by id
     * @param id
     * @return Requestexec.class record
     */
    public Requestexec getById(long id) {
        Requestexec obj = null;
        try{
            if(em == null || !em.isOpen())
                em = JPAUtil.getEntityManager();
            obj = em.find(Requestexec.class, id);
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
     * Return all records for order with order.id = id
     * @param id
     * @return ArrayList od Requestexec.class recors for order
     */
    public ArrayList<Requestexec> getByTaskId(Long id){
        ArrayList<Requestexec> list = null;
        try {
            if(em == null || !em.isOpen())
                em = JPAUtil.getEntityManager();
            Query query = em.createQuery("from Requestexec where orderid = :param")
                    .setParameter("param", id);
            list = (ArrayList<Requestexec>) query.getResultList();
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
}
