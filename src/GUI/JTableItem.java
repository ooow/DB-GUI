package GUI;

/**
 * Created by Гога on 21.04.2016.
 */
public class JTableItem {
    private String[] columNames;
    private String[][] data;

    public JTableItem(String[] columNames, String[][] data) {
        this.columNames = columNames;
        this.data = data;
    }

    public String[] getColumNames() {
        return columNames;
    }

    public void setColumNames(String[] columNames) {
        this.columNames = columNames;
    }

    public String[][] getData() {
        return data;
    }

    public void setData(String[][] data) {
        this.data = data;
    }
}
