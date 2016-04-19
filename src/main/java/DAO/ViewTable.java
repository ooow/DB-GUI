package DAO;

import GUI.*;
import Model.*;
import hibernate.HibernateConfig;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by Гога on 18.04.2016.
 */
public class ViewTable {
    public JTableItem getTable(Model classs) {
        JTableItem jTableItem = null;
        SessionFactory sessionFactory = HibernateConfig.getSessionFactory();
        Session session = sessionFactory.openSession();
        try {
            Field[] fields = classs.getClass().getDeclaredFields();
            String[] colums = new String[fields.length];
            for (int i = 0; i < fields.length; i++) {
                String field = fields[i].toString();
                colums[i] = field.substring(field.lastIndexOf(".") + 1);
            }
            Criteria criteria = session.createCriteria(classs.getClass());
            List<Model> models = criteria.list();
            String[][] data = new String[models.size()][];
            for (int i = 0; i < models.size(); i++) {
                data[i] = models.get(i).getDataToStringArr();
            }
            jTableItem = new JTableItem(colums, data);

        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return jTableItem;
    }

    public Association[] getTablesName() {
        Association[] associations = {new Association("Клиенты", Client.class),
                new Association("Тренера", Trainer.class),
                new Association("Секции", Section.class),
                new Association("Абонименты", Subscription.class),
                new Association("Спортзалы", Gym.class)};
        return associations;
    }
}
