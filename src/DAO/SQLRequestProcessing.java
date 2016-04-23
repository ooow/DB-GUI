package DAO;

import java.sql.SQLException;

import static DAO.Mapping.setToArray;
import static app.Main.getStatement;

/**
 * Created by Гога on 23.04.2016.
 */
public class SQLRequestProcessing {
    public String[] getStatistic() {
        String[] ans = new String[3];
        String sql = "select * from quantityClients";
        String sql1 = "select * from quantityWomans";
        String sql2 = "select * from quantityMan";
        try {
            ans[0] = setToArray(getStatement().executeQuery(sql))[0];
            ans[1] = setToArray(getStatement().executeQuery(sql1))[0];
            ans[2] = setToArray(getStatement().executeQuery(sql2))[0];
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ans;
    }

    public String[] getReport() {
        String[] ans = new String[4];
        String sql = "select * from quantitySubscriptions";
        String sql1 = "select * from statementAccounts";
        String sql2 = "select * from quantityBez";
        String sql3 = "select * from quantityOgr";
        try {
            ans[0] = setToArray(getStatement().executeQuery(sql))[0];
            ans[1] = setToArray(getStatement().executeQuery(sql1))[0];
            ans[2] = setToArray(getStatement().executeQuery(sql2))[0];
            ans[3] = setToArray(getStatement().executeQuery(sql3))[0];
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ans;
    }

    public String[] getSpace() {
        String[] ans = new String[4];
        String sql = "select * from quantity360";
        String sql1 = "select * from quantity180";
        String sql2 = "select * from quantity30";
        try {
            ans[0] = setToArray(getStatement().executeQuery(sql))[0];
            ans[1] = setToArray(getStatement().executeQuery(sql1))[0];
            ans[2] = setToArray(getStatement().executeQuery(sql2))[0];
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ans;
    }
}
