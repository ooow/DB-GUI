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
}
