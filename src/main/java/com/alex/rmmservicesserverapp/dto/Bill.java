package com.alex.rmmservicesserverapp.dto;

import java.util.List;

public class Bill {

    private String customerName;
    private double totalValue;
    private List<Detail> detailList;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(double totalValue) {
        this.totalValue = totalValue;
    }

    public List<Detail> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<Detail> detailList) {
        this.detailList = detailList;
    }

    public Bill() {
    }

    public Bill(String customerName, double totalValue, List<Detail> detailList) {
        this.customerName = customerName;
        this.totalValue = totalValue;
        this.detailList = detailList;
    }
}
