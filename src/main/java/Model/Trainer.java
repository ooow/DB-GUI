package Model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Гога on 16.04.2016.
 */
@Entity
@Table
public class Trainer extends Model {
    @Column
    private String Name;
    @Column
    private int Experience;
    @ManyToOne
    @JoinColumn(name = "section_id")
    private Section Section;
    @ManyToOne
    @JoinColumn(name = "gym_id")
    private Gym Gym;

    public Trainer() {
        super();
    }

    public Trainer(String name, int experience, Gym gym) {
        this.Name = name;
        this.Experience = experience;
        this.Gym = gym;
    }

    @Override
    public String[] getDataToStringArr() {
        String[] data = new String[5];
        data[0] = String.valueOf(getId());
        data[1] = Name;
        data[2] = String.valueOf(Experience);
        data[3] = Section.getSportname();
        data[4] = Gym.getTitle();
        return data;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public int getExperience() {
        return Experience;
    }

    public void setExperience(int experience) {
        this.Experience = experience;
    }

    public Section getSection() {
        return Section;
    }

    public void setSection(Section section) {
        this.Section = section;
    }

    public void setGym(Gym gym) {
        this.Gym = gym;
    }

    public Gym getGym() {
        return Gym;
    }
}
