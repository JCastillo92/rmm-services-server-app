package com.alex.rmmservicesserverapp.repository;

import com.alex.rmmservicesserverapp.model.CustomerService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerServiceRepository extends JpaRepository<CustomerService, Integer>, CustomerServiceRepositoryCustom {



}
