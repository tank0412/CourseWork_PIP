package util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
    private static EntityManagerFactory mFactory = buildFactory();

    protected static EntityManagerFactory buildFactory() {
        try {
            mFactory = Persistence.createEntityManagerFactory("PostDB");
        }
        catch (Exception e) {
            throw new ExceptionInInitializerError("Initial EntityManagerFactory failed" + e);
        }
        return mFactory;
    }


    public static EntityManagerFactory getEntityManagerFactory() {
        return mFactory;
    }
    public static EntityManager getEntityManager() {
        return mFactory.createEntityManager();
    }


}
