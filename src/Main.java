import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static Connection con;
    private static Statement st1;
    private static ResultSet rs;

    public static void main(String[] args) {
        System.out.println("Hello World!");
        dbGUI gui = new dbGUI();
       /* PreparedStatement st = con.prepareStatement("INSERT into ORG (OID,TITLE) values (?,?)");
        for (int i = 3; i < 100; i++) {
            st.setInt(1, i);
            st.setString(2, "HTC");
            st.addBatch();
        }
        st.executeBatch();*/


      /*  Statement st1 = con.createStatement();
        ResultSet rs = st1.executeQuery("use GYM select * from Clients");
        while (rs.next()) {
            System.out.println(rs.getString(1) + "\t" + rs.getString(2) + "\t\t" + rs.getString(3) + "\t" + rs.getString(4));
        }*/
    }

    public static void run(String log, String pass) throws SQLException {
        log = "usergt";
        pass = "zxcxz1234";
        con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433", log, pass);
        st1 = con.createStatement();
        rs = st1.executeQuery("use GYM select name from sys.objects where type = 'U'");
        String[] tables = setToArray(rs);
        MainMenu menu = new MainMenu(tables);
        System.out.println(log);
        System.out.println(pass);
    }

    public static String[] setToArray(ResultSet rs) throws SQLException {
        List<String> temp = new ArrayList<>();
        while (rs.next()) {
            temp.add(rs.getString(1));
        }
        return temp.toArray(new String[temp.size()]);
    }

    public static String[] getColumn(String tableName) throws SQLException {
        rs = st1.executeQuery("SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = '" + tableName + "'");
        return setToArray(rs);
    }

    public static String[][] getData(String tableName, int columnCount) throws SQLException {
        rs = st1.executeQuery("use GYM SELECT * FROM " + tableName);
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
