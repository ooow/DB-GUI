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
            String[] colums = new String[fields.length + 1];
            colums[0] = "id";
            for (int i = 0; i < fields.length; i++) {
                String field = fields[i].toString();
                colums[i + 1] = field.substring(field.lastIndexOf(".") + 1);
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
        Association[] associations = {new Association("Клиенты", new Client()),
                new Association("Тренера", new Trainer()),
                new Association("Секции", new Section()),
                new Association("Абонементы", new Subscription()),
                new Association("Спортзалы", new Gym())};
        return associations;
    }
}
