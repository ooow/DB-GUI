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
    private String Name;
    @Column
    private String Sex;
    @Column
    private int Age;
    @ManyToOne
    @JoinColumn(name = "trainer_id")
    private Trainer Trainer;
    @ManyToOne
    @JoinColumn(name = "section_id")
    private Section Section;

    @ManyToOne
    @JoinColumn(name = "gym_id")
    private Gym Gym;

    @OneToOne
    @JoinColumn(name = "subscription_id")
    private Subscription Subscription;


    public Client() {
        super();
    }

    public Client(String name, int age, String sex, Gym gym) {
        this.Name = name;
        this.Age = age;
        this.Sex = sex;
        this.Gym = gym;
    }

    @Override
    public String[] getDataToStringArr() {
        String[] data = new String[8];
        data[0] = String.valueOf(getId());
        data[1] = Name;
        data[2] = Sex;
        data[3] = String.valueOf(Age);
        data[4] = Trainer.getName();
        data[5] = Section.getSportname();
        data[6] = Gym.getTitle();
        data[7] = String.valueOf(Subscription.getId());
        return data;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getSex() {
        return Sex;
    }

    public void setSex(String sex) {
        this.Sex = sex;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        this.Age = age;
    }

    public Trainer getTrainer() {
        return Trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.Trainer = trainer;
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

    public Subscription getSubscription() {
        return Subscription;
    }

    public void setSubscription(Subscription subscription) {
        this.Subscription = subscription;
    }

}
