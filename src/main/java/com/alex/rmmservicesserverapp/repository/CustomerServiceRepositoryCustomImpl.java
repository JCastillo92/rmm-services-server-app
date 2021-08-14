package com.alex.rmmservicesserverapp.repository;

import com.alex.rmmservicesserverapp.model.Customer;
import com.alex.rmmservicesserverapp.model.CustomerService;
import com.alex.rmmservicesserverapp.model.ServiceEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public class CustomerServiceRepositoryCustomImpl implements CustomerServiceRepositoryCustom {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<CustomerService> getCustomerServicesByCustomerAndService(int customerId, int serviceId) {
        Query query = entityManager.createNativeQuery
                ("Select cs.* from CustomerService as cs where cs.customerid = ? and cs.serviceid = ?", CustomerService.class);

        query.setParameter(1, customerId );
        query.setParameter(2,serviceId);
        return query.getResultList();

    }
}
