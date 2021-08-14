package com.alex.rmmservicesserverapp.repository;

import com.alex.rmmservicesserverapp.model.ServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceEntityRepository extends JpaRepository<ServiceEntity, Integer> {

}
