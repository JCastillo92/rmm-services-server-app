package com.alex.rmmservicesserverapp.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "service")
public class ServiceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private String name;

    @Column
    private String description;

    @OneToMany(mappedBy = "service")
    @JsonIgnore
    private List<ServicePrice> servicePriceList;

    @OneToMany(mappedBy = "service")
    @JsonIgnore
    private List<CustomerService> customerServiceList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ServiceEntity(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public ServiceEntity() {
    }

    public List<ServicePrice> getServicePriceList() {
        return servicePriceList;
    }

    public List<CustomerService> getCustomerServiceList() {
        return customerServiceList;
    }
}
