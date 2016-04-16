package Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Гога on 16.04.2016.
 */
@Entity
@Table
public class Gym extends Model{
    @Column
    private String title;
    @Column
    private String city;

    public Gym(){
        super();
    }
}
