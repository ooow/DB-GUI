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

    public Subscription(){super();}

    @Override
    public String[] getDataToStringArr() {
        String[] data = new String[3];
        data[0] = String.valueOf(getId());
        data[1] = String.valueOf(Price);
        data[2] = String.valueOf(Period);
        return data;
    }

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

}
