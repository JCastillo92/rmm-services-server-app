package com.alex.rmmservicesserverapp.repository;

import com.alex.rmmservicesserverapp.model.Customer;
import com.alex.rmmservicesserverapp.model.CustomerService;
import com.alex.rmmservicesserverapp.model.ServiceEntity;

import javax.xml.ws.Service;
import java.util.List;

public interface CustomerServiceRepositoryCustom {

    List<CustomerService> getCustomerServicesByCustomerAndService(int customerId, int serviceId);

}
