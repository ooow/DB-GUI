package Model;

import javax.persistence.*;

/**
 * Created by Гога on 16.04.2016.
 */
@Entity
@Table
public class Subscription extends Model{
    @Column
    private int Price;
    @Column
    private int Period;
    @OneToOne(mappedBy = "subscription")
    private Client client;

    public Subscription(){super();}

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public int getPeriod() {
        return Period;
    }

    public void setPeriod(int period) {
        Period = period;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
