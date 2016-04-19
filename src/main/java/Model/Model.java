package Model;

import javax.persistence.*;

/**
 * Created by Гога on 16.04.2016.
 */
@MappedSuperclass
public abstract class Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    public Model() {
    }

    public int getId() {
        return id;
    }

    public String getClassName() {
        return this.getClass().toString().substring(this.getClass().toString().indexOf(".") + 1);
    }

    public String[] getDataToStringArr(){
        String[] data = new String[1];
        data[0] = String.valueOf(id);
        return data;
    }
}
