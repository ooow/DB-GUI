package hibernate;

import Model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


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
        Gym gym = new Gym();
        gym.setTitle("Физкульт");
        gym.setCity("Саратов");
        session.save(gym);


        Client client = new Client("Иван", 21, "Муж", gym);
        Client client1 = new Client("Екатерина", 24, "Жен", gym);
        Client client2 = new Client("Роман", 19, "Муж", gym);


        Trainer trainer = new Trainer("Денис", 9, gym);
        Trainer trainer1 = new Trainer("Сергей", 7, gym);
        Trainer trainer2 = new Trainer("Виктория", 5, gym);

        Section section = new Section();
        section.setSportname("Бокс");
        section.setFollowers(15);


        Section section1 = new Section();
        section1.setSportname("Йога");
        section1.setFollowers(20);

        Subscription subscription = new Subscription();
        subscription.setPrice(12900);
        subscription.setPeriod(360);
        session.save(subscription);
        client.setSubscription(subscription);

        Subscription subscription1 = new Subscription();
        subscription1.setPrice(6900);
        subscription1.setPeriod(180);
        session.save(subscription1);
        client1.setSubscription(subscription1);

        Subscription subscription2 = new Subscription();
        subscription2.setPrice(6900);
        subscription2.setPeriod(180);
        session.save(subscription2);
        client2.setSubscription(subscription2);

        client.setTrainer(trainer);
        client1.setTrainer(trainer1);
        client2.setTrainer(trainer2);

        client.setSection(section);
        client1.setSection(section1);
        client2.setSection(section);

        trainer.setSection(section);
        trainer1.setSection(section);
        trainer2.setSection(section1);

        session.save(section);
        session.save(section1);

        session.save(trainer);
        session.save(trainer1);
        session.save(trainer2);

        session.save(client);
        session.save(client1);
        session.save(client2);

        session.close();
        sessionFactory.close();
    }
}
