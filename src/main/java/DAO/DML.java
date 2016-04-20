package DAO;

import Model.Trainer;
import hibernate.HibernateConfig;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * Created by Гога on 18.04.2016.
 */
public class DML {
    public String getIdByName(String name) {
        SessionFactory sessionFactory = HibernateConfig.getSessionFactory();
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(Trainer.class);
        criteria.add(Restrictions.eq("Name", name));
        Trainer trainers = (Trainer) criteria.list().get(0);
        return String.valueOf(trainers.getId());
    }
}
