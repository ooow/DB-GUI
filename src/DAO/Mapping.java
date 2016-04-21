package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static app.Main.getStatement;

/**
 * Created by Гога on 21.04.2016.
 */
public class Mapping {
    public static String[] setToArray(ResultSet rs) throws SQLException {
        List<String> temp = new ArrayList<>();
        while (rs.next()) {
            temp.add(rs.getString(1));
        }
        return temp.toArray(new String[temp.size()]);
    }

    public static String[] getColumns(String tableName) throws SQLException {
        String sql = "SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = '" + tableName + "'";
        return setToArray(getStatement().executeQuery(sql));
    }

    public static String[][] getData(String tableName, int columnCount) throws SQLException {
        ResultSet rs = getStatement().executeQuery("use GYM SELECT * FROM " + tableName);
        List<List<String>> temp = new ArrayList<>();
        while (rs.next()) {
            List<String> temp2 = new ArrayList<>();
            for (int i = 1; i <= columnCount; i++) {
                temp2.add(rs.getString(i));
            }
            temp.add(temp2);
        }
        String[][] data = new String[temp.size()][columnCount];
        for (int i = 0; i < temp.size(); i++) {
            data[i] = temp.get(i).toArray(new String[columnCount]);
        }
        return data;
    }
}
