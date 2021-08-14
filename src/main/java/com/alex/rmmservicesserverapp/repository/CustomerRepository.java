package com.alex.rmmservicesserverapp.repository;

import com.alex.rmmservicesserverapp.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {



}
