package hibernate;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * Created by Гога on 16.04.2016.
 */
public class TestHibernate {
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateConfig.getSessionFactory();
        Session session = sessionFactory.openSession();
       /* Criteria criteria = session.createCriteria(Client.class);
        criteria.add(Restrictions.eq("gid", 1));
        List<Client> clients = criteria.list();
        for (int i = 0; i < clients.size(); i++) {
            clients.get(i).toString();
        }*/
        session.close();
        sessionFactory.close();
    }
}
