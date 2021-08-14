package com.alex.rmmservicesserverapp.controller;

import com.alex.rmmservicesserverapp.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeviceController {

    @Autowired
    DeviceRepository deviceRepository;



}
