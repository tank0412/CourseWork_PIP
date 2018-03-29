package templates;

import util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;


public class ICrud<T> {
    EntityManager em = null;
    /**
     * Save record in database
     * @param entity Entity
     */
    public void add(T entity) {
        if(em == null || !em.isOpen())
            em = JPAUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(entity);
        transaction.commit();
        closeEntityManager();
    }
    /**
     * Update record in database
     * @param entity Entity
     */
    public void update(T entity) {
        if(em == null || !em.isOpen())
            em = JPAUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.merge(entity);
        transaction.commit();
        closeEntityManager();
    }
    public void getUpdate(T entity) {
        if(em == null || !em.isOpen())
            em = JPAUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.refresh(entity);
        transaction.commit();
        closeEntityManager();
    }
    /**
     * Delete record from
     * @param entity Entity
     */
    public void delete(T entity) {
        if(em == null || !em.isOpen())
            em = JPAUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.remove(entity);
        transaction.commit();
        closeEntityManager();
    }

    public void closeEntityManager(){
        if (em != null && em.isOpen()) {
            em.close();
        }
    }
}
