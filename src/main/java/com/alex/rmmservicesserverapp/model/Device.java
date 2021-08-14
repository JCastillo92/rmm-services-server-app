package com.alex.rmmservicesserverapp.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

@Entity
@Table(name = "device")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "systemname")
    private String systemName;

    @Column
    private String serial;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="customerid")
    @JsonIgnore
    private Customer customer;

    @ManyToOne
    @JoinColumn(name="typeid")
    private DeviceTypes deviceType;

    /*public Customer getCustomer() {
        return customer;
    }*/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public DeviceTypes getDeviceType() {
        return deviceType;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setDeviceType(DeviceTypes deviceType) {
        this.deviceType = deviceType;
    }

    public Device(String systemName, String serial) {
        this.systemName = systemName;
        this.serial = serial;
    }

    public Device() {
    }

    @Override
    public String toString() {
        return "Device{" +
                "id=" + id +
                ", systemName='" + systemName + '\'' +
                ", serial='" + serial + '\'' +
                '}';
    }

    public Device(String systemName, String serial, Customer customer, DeviceTypes deviceType) {
        this.systemName = systemName;
        this.serial = serial;
        this.customer = customer;
        this.deviceType = deviceType;
    }
}
