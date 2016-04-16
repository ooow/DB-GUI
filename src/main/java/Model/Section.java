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
    private String sportname;
    @Column
    private int followers;
    @ManyToMany(mappedBy = "sections")
    private Set<Trainer> trainers = new HashSet<Trainer>();

    @ManyToMany(mappedBy = "sections")
    private Set<Client> clients = new HashSet<Client>();

    public Section() {
        super();
    }

    public String getSportname() {
        return sportname;
    }


    public void setSportname(String sportname) {
        this.sportname = sportname;
    }

    public int getFollowers() {
        return followers;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
    }
}
