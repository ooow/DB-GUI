package Model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Гога on 16.04.2016.
 */
@Entity
@Table
public class Section extends Model {
    @Column
    private String Sportname;
    @Column
    private int Followers;

    public Section() {
        super();
    }

    @Override
    public String[] getDataToStringArr() {
        String[] data = new String[3];
        data[0] = String.valueOf(getId());
        data[1] = Sportname;
        data[2] = String.valueOf(Followers);
        return data;
    }

    public String getSportname() {
        return Sportname;
    }

    public void setSportname(String sportname) {
        this.Sportname = sportname;
    }

    public int getFollowers() {
        return Followers;
    }

    public void setFollowers(int followers) {
        this.Followers = followers;
    }

}
