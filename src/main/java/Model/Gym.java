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
    private String Title;
    @Column
    private String City;

    public Gym(){
        super();
    }

    @Override
    public String[] getDataToStringArr() {
        String[] data = new String[3];
        data[0] = String.valueOf(getId());
        data[1] = Title;
        data[2] = City;
        return data;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        this.Title = title;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        this.City = city;
    }
}
