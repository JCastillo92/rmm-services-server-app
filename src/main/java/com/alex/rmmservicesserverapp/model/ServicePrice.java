package com.alex.rmmservicesserverapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import javax.xml.ws.Service;

@Entity
@Table(name="serviceprice")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class ServicePrice {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name="serviceid")
    @JsonIgnore
    private ServiceEntity service;

    @ManyToOne
    @JoinColumn(name="devicetypeid")
    private DeviceTypes deviceType;

    @Column
    private double value;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DeviceTypes getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(DeviceTypes deviceType) {
        this.deviceType = deviceType;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }


    public ServiceEntity getService() {
        return service;
    }


    public void setService(ServiceEntity service) {
        this.service = service;
    }

    public ServicePrice() {
    }

    public ServicePrice(ServiceEntity service, DeviceTypes deviceType, double value) {
        this.service = service;
        this.deviceType = deviceType;
        this.value = value;
    }


}
