package DAO;

import java.sql.SQLException;

import static app.Main.getStatement;

/**
 * Created by Гога on 21.04.2016.
 */
public class DML {

    public static void update(String tableName, String columName, String update, String[] items, String[] colums) throws SQLException {
        String sql = "UPDATE " + tableName + " SET " + columName + " = " + "'" + update + "'" + " WHERE ";
        for (int i = 0; i < colums.length; i++) {
            if (!items[i].equals(columName)) {
                sql += items[i] + " = " + "'" + colums[i] + "'";
                if (colums.length - i > 1)
                    sql += " and ";
            }
        }
        System.out.println(sql);
        getStatement().executeUpdate(sql);
    }

    public static void delete(String tableName, String[] items, String[] colums) throws SQLException {
        String sql = "DELETE FROM " + tableName + " WHERE " +
                items[0] + " = " + "'" + colums[0] + "'" ;
        System.out.println(sql);
        getStatement().executeUpdate(sql);
    }

    public static void insert(String tableName, String[] colums) throws SQLException {
        String sql = "INSERT INTO " + tableName + " VALUES (";
        for (int i = 0; i < colums.length; i++) {
            sql += "'" + colums[i] + "'";
            if (colums.length - i > 1)
                sql += ", ";
            else sql += ")";
        }
        System.out.println(sql);
        getStatement().executeUpdate(sql);
    }
}
