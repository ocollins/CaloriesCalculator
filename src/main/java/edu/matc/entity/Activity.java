package edu.matc.entity;

/**
 * Created by Calories Calculator team on 2/28/17.
 */
import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "activity", schema = "calories_calculator")
public class Activity {
    @Id
    @Column(name = "id")
    private int id;

    @Basic
    @Column(name = "name")
    private String name;

    @Basic
    @Column(name = "mets")
    private BigDecimal mets;

    /**
     * Instantiates a new Activity.
     */
    public Activity() {

    }

    /**
     * Instantiates a new Activity.
     *
     * @param name the name
     * @param mets the mets
     */
    public Activity(String name, BigDecimal mets) {
        //this.id = id;
        this.name = name;
        this.mets = mets;
    }

    /**
     * Instantiates a new Activity.
     *
     * @param id   the id
     * @param name the name
     * @param mets the mets
     */
    public Activity(int id, String name, BigDecimal mets) {
        this.id = id;
        this.name = name;
        this.mets = mets;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }


    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * Gets mets.
     *
     * @return the mets
     */
    public BigDecimal getMets() {
        return mets;
    }

    /**
     * Sets mets.
     *
     * @param mets the mets
     */
    public void setMets(BigDecimal mets) {
        this.mets = mets;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", mets=" + mets +
                '}';
    }
}

