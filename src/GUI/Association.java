package GUI;

/**
 * Created by Гога on 21.04.2016.
 */
public class Association {
    private String tableName;
    private String rusName;

    public Association() {
    }

    public Association(String tableName, String rusName) {
        this.tableName = tableName;
        this.rusName = rusName;
    }

    public Association[] getItems() {
        Association[] associations = {new Association("Clients", "Клиенты"),
                new Association("Trainers", "Тренера"),
                new Association("Sections", "Секции"),
                new Association("GYM", "Спортзалы"),
                new Association("SportsEquipment", "Спортивные оборудования"),
                new Association("Hall", "Залы"),
                new Association("Timetable", "Рассписание"),
                new Association("PriceList", "Прйс лист"),
                new Association("Subscription", "Абонементы"),
                new Association("Cloakroom", "Раздевалка"),
                new Association("Connects", "Связь Клиент-Секция")
        };
        return associations;
    }

    public String getTableNameByRusName(String rusName) {
        Association[] associations = new Association().getItems();
        for (int i = 0; i < associations.length; i++) {
            if (associations[i].getRusName().equals(rusName))
                return associations[i].getTableName();
        }
        return "";
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getRusName() {
        return rusName;
    }

    public void setRusName(String rusName) {
        this.rusName = rusName;
    }
}
