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
    private String name;
    @Column
    private int Experience;
    @ManyToMany
    @JoinTable(name = "trainer_section",
            joinColumns = {@JoinColumn(name = "trainer_id")},
            inverseJoinColumns = {@JoinColumn(name = "section_id")})
    private Set<Section> sections = new HashSet<Section>();
    @ManyToOne
    @JoinColumn(name = "gym_id")
    private Gym gym;

    public Trainer() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getExperience() {
        return Experience;
    }

    public void setExperience(int experience) {
        Experience = experience;
    }

    public Set<Section> getSections() {
        return sections;
    }

    public void setSections(Set<Section> sections) {
        this.sections = sections;
    }

    public void setGym(Gym gym) {
        this.gym = gym;
    }

    public Gym getGym() {
        return gym;
    }
}
