package com.alex.rmmservicesserverapp.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "devicetypes")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class DeviceTypes {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private String name;

    @Column
    private double value;

    @OneToMany(mappedBy = "deviceType")
    private List<Device> devicesList;

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

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public DeviceTypes(String name, double value) {
        this.name = name;
        this.value = value;
    }

    public DeviceTypes() {
    }

    @Override
    public String toString() {
        return "DeviceTypes{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", value=" + value +
                '}';
    }

    /*public List<Device> getDevicesList() {
        return devicesList;
    }*/
}
