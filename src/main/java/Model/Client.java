package Model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Гога on 16.04.2016.
 */
@Entity
@Table
public class Client extends Model {
    @Column
    private String name;
    @Column
    private String sex;
    @Column
    private String age;
    @ManyToOne
    @JoinColumn(name = "trainer_id")
    private Trainer trainer;
    @ManyToMany
    @JoinTable(name = "client_section",
            joinColumns = {@JoinColumn(name = "client_id")},
            inverseJoinColumns = {@JoinColumn(name = "section_id")})
    private Set<Section> sections = new HashSet<Section>();

    @ManyToOne
    @JoinColumn(name = "gym_id")
    private Gym gym;

    @OneToOne
    @PrimaryKeyJoinColumn
    private Subscription subscription;

    public Client() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }


    public void setSections(Set<Section> sections) {
        this.sections = sections;
    }

    public void setGym(Gym gym) {
        this.gym = gym;
    }

    public Set<Section> getSections() {
        return sections;
    }


    public Gym getGym() {
        return gym;
    }
}
