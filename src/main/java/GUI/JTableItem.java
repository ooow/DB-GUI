package GUI;

/**
 * Created by Гога on 18.04.2016.
 */
public class JTableItem {
    private String[] columNames;
    private String[][] items;

    public JTableItem(String[] columNames, String[][] items) {
        this.columNames = columNames;
        this.items = items;
    }

    public String[] getColumNames() {
        return columNames;
    }

    public void setColumNames(String[] columNames) {
        this.columNames = columNames;
    }

    public String[][] getItems() {
        return items;
    }

    public void setItems(String[][] items) {
        this.items = items;
    }
}
