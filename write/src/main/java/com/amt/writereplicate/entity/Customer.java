package com.amt.writereplicate.entity;

import com.amt.writereplicate.enums.MarketingPreference;

import javax.persistence.*;

@Entity
@Table(name="customer")
public class Customer {

    @Column(name="id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name="name")
    private String name;

    @Column(name="marketing_preference")
    @Enumerated(value = EnumType.STRING)
    private MarketingPreference marketingPreference;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MarketingPreference getMarketingPreference() {
        return marketingPreference;
    }

    public void setMarketingPreference(MarketingPreference marketingPreference) {
        this.marketingPreference = marketingPreference;
    }

    public Customer(String name, MarketingPreference marketingPreference) {
        this.name = name;
        this.marketingPreference = marketingPreference;
    }

    public Customer() {
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", marketingPreference=" + marketingPreference +
                '}';
    }
}
