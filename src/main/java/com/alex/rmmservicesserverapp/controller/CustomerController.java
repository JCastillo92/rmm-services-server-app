package com.alex.rmmservicesserverapp.controller;

import com.alex.rmmservicesserverapp.dto.Bill;
import com.alex.rmmservicesserverapp.dto.Detail;
import com.alex.rmmservicesserverapp.model.*;
import com.alex.rmmservicesserverapp.repository.CustomerRepository;
import com.alex.rmmservicesserverapp.repository.CustomerServiceRepository;
import com.alex.rmmservicesserverapp.repository.DeviceRepository;
import com.alex.rmmservicesserverapp.repository.ServiceEntityRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class CustomerController {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    DeviceRepository deviceRepository;

    @Autowired
    ServiceEntityRepository serviceEntityRepository;

    @Autowired
    CustomerServiceRepository customerServiceRepository;

    @Operation(summary = "Get all customers with their devices")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Customers found"),
            @ApiResponse(responseCode = "204", description = "There are no customers")
    })
    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getAllCustomers(){

        List<Customer> customerList = new ArrayList<>();
        customerRepository.findAll().forEach(customerList::add);

        if (customerList.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(customerList,HttpStatus.OK);

    }

    @Operation(summary = "Get devices from specific user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Devices found"),
            @ApiResponse(responseCode = "404", description = "Customer not found"),
            @ApiResponse(responseCode = "204", description = "No device was found for user")
    })
    @GetMapping("/customer/{id}/devices")
    public ResponseEntity<List<Device>> getDevicesByUserId(@PathVariable int id){

        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        Customer customer = null;
        List<Device> deviceList=new ArrayList<>();
        if (optionalCustomer.isPresent()){
            customer = optionalCustomer.get();
            deviceList = customer.getDevicesList();
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if (deviceList.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(deviceList,HttpStatus.OK);

    }

    @Operation(summary = "Add a device for a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Devices created"),
            @ApiResponse(responseCode = "404", description = "Customer not found"),
            @ApiResponse(responseCode = "500", description = "Device could not be created")
    })
    @PostMapping("/customer/{id}/device")
    public ResponseEntity<Device> addUserDevice(@PathVariable int id, @RequestBody Device device){

        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        Customer customer = null;
        if (optionalCustomer.isPresent()){
            customer = optionalCustomer.get();
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        device.setCustomer(customer);
        boolean created=false;
        try{
            deviceRepository.save(device);
            created=true;
        }catch (Exception e){
            e.printStackTrace();
        }

        if(created)
            return new ResponseEntity<>(device,HttpStatus.CREATED);
        else
            return new ResponseEntity<>(device,HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @Operation(summary = "Update a user's device")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Devices updated"),
            @ApiResponse(responseCode = "404", description = "Customer or device not found"),
            @ApiResponse(responseCode = "500", description = "Device could not be updated")
    })
    @PutMapping("/customer/{id}/device/{deviceId}")
    public ResponseEntity<Device> updateUserDevice(@PathVariable int id,@PathVariable int deviceId, @RequestBody Device device){

        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        Customer customer = null;
        Device deviceUpdated = null;
        boolean updated=false;
        String errorMessage="";
        if (optionalCustomer.isPresent()){
            customer = optionalCustomer.get();
            Optional<Device> deviceOptional = deviceRepository.findById(deviceId);

            if(deviceOptional.isPresent()){
                deviceUpdated = deviceOptional.get();
                deviceUpdated.setDeviceType(device.getDeviceType());
                deviceUpdated.setSerial(device.getSerial());
                deviceUpdated.setSystemName(device.getSystemName());
                // I do not include customer, cause customer should not be able to be changed
                try{
                    deviceRepository.save(deviceUpdated);
                    updated=true;
                }catch (Exception e){
                    updated=false;
                    errorMessage=e.getMessage();
                }


            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if(updated)
            return new ResponseEntity<>(deviceUpdated,HttpStatus.OK);
        else
            return new ResponseEntity<>(deviceUpdated,HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @Operation(summary = "Delete a user's device")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Device deleted"),
            @ApiResponse(responseCode = "404", description = "Customer or device not found"),
            @ApiResponse(responseCode = "500", description = "Device could not be deleted")
    })
    @DeleteMapping("/customer/{id}/device/{deviceId}")
    public ResponseEntity<Device> deleteUserDevice(@PathVariable int id,@PathVariable int deviceId){

        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        Customer customer = null;
        Device device = null;
        boolean deleted=false;
        String errorMessage="";
        if (optionalCustomer.isPresent()){
            customer = optionalCustomer.get();
            Optional<Device> deviceOptional = deviceRepository.findById(deviceId);

            if(deviceOptional.isPresent()){
                device = deviceOptional.get();
                try{
                    deviceRepository.delete(device);
                    deleted=true;
                }catch (Exception e){
                    deleted=false;
                    errorMessage=e.getMessage();
                }

            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if(deleted)
            return new ResponseEntity<>(device,HttpStatus.OK);
        else
            return new ResponseEntity<>(device,HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @Operation(summary = "Get services from specific user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Services found"),
            @ApiResponse(responseCode = "404", description = "Customer not found"),
            @ApiResponse(responseCode = "204", description = "No services for specific user")
    })
    @GetMapping("/customer/{id}/services")
    public ResponseEntity<List<ServiceEntity>> getAllServicesByUserId(@PathVariable int id){

        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        Customer customer = null;
        List<ServiceEntity> serviceEntityList= new ArrayList<>();
        if (optionalCustomer.isPresent()){
            customer = optionalCustomer.get();

            //
            List<CustomerService> customerServiceList = customer.getCustomerServiceList();
            for(int i=0;i<customerServiceList.size();i++){
                CustomerService customerService = null;
                customerService = customerServiceList.get(i);
                ServiceEntity service = new ServiceEntity();
                int serviceId = customerService.getService().getId();
                service = serviceEntityRepository.findById(serviceId).get();
                ServiceEntity unproxiedServiceEntity = (ServiceEntity)Hibernate.unproxy(service);
                serviceEntityList.add(unproxiedServiceEntity);
            }
            //

            /*
            customer.getCustomerServiceList().forEach(
                    x ->serviceEntityList.add(x.getService())
            );*/
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if (serviceEntityList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(serviceEntityList,HttpStatus.OK);
        }
    }

    @Operation(summary = "Add a service for a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Service created"),
            @ApiResponse(responseCode = "404", description = "Customer or device not found"),
            @ApiResponse(responseCode = "412", description = "Service can not be asigned more than once")
    })
    @PostMapping("/customer/{id}/service/{serviceId}")
    public ResponseEntity<CustomerService> addServiceByUserId(@PathVariable int id, @PathVariable int serviceId ){

        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        Optional<ServiceEntity> serviceEntityOptional = serviceEntityRepository.findById(serviceId);
        Customer customer = null;
        ServiceEntity service = null;
        if (optionalCustomer.isPresent() && serviceEntityOptional.isPresent()){
            customer = optionalCustomer.get();
            service = serviceEntityOptional.get();

            //Validate if service is already subscribed
            List<CustomerService> customerServiceList = new ArrayList<>();
            customerServiceList = customerServiceRepository.getCustomerServicesByCustomerAndService(customer.getId(),service.getId());

            if(customerServiceList.isEmpty()){
                CustomerService customerService = new CustomerService(customer,service);
                customerServiceRepository.save(customerService);
                return new ResponseEntity<>(customerService,HttpStatus.CREATED);
            }else{
                return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
            }

        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @Operation(summary = "Delete a user's service")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Service deleted"),
            @ApiResponse(responseCode = "404", description = "Customer or service not found"),
            @ApiResponse(responseCode = "500", description = "Service could not be deleted")
    })
    @DeleteMapping("/customer/{id}/service/{serviceId}")
    public ResponseEntity<CustomerService> deleteServiceByUserId(@PathVariable int id, @PathVariable int serviceId ){

        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        Optional<ServiceEntity> serviceEntityOptional = serviceEntityRepository.findById(serviceId);
        Customer customer = null;
        ServiceEntity service = null;
        CustomerService cs=null;
        boolean deleted = false;
        String errorMessage ="";
        if (optionalCustomer.isPresent() && serviceEntityOptional.isPresent()){
            customer = optionalCustomer.get();
            service = serviceEntityOptional.get();

            //Validate if service is already subscribed
            List<CustomerService> customerServiceList = new ArrayList<>();
            customerServiceList = customerServiceRepository.getCustomerServicesByCustomerAndService(customer.getId(),service.getId());
            try{
                cs = customerServiceList.get(0);
                customerServiceRepository.delete(cs);
                deleted=true;
            }catch (Exception e){
                errorMessage = e.getMessage();
                deleted=false;
            }
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if(deleted)
            return new ResponseEntity<>(cs,HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @Operation(summary = "Get bill from user's service and devices")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bill calculated"),
            @ApiResponse(responseCode = "404", description = "Customer not found"),
            @ApiResponse(responseCode = "500", description = "Bill could not be calculated")
    })
    @GetMapping("/customer/{id}/bill")
    public ResponseEntity<Bill> getBillByUserId(@PathVariable int id ){

        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        Customer customer = null;
        double devicesCost=0;
        Map<String, Double> billDetails = new HashMap<>();
        if(optionalCustomer.isPresent()){

            customer = optionalCustomer.get();

            // Finding Devices $$$$
            List<Device> deviceList= customer.getDevicesList();
            for(Device device:deviceList){
                double deviceValue = device.getDeviceType().getValue();
                devicesCost = devicesCost+deviceValue;
            }
            billDetails.put("Devices",devicesCost);

            //Iterate over services
            List<CustomerService> customerServiceList = customer.getCustomerServiceList();
            for (CustomerService cs: customerServiceList){
                double serviceCost=0;
                ServiceEntity service = cs.getService();
                List<ServicePrice> servicePriceList = service.getServicePriceList();
                for(ServicePrice sp: servicePriceList){
                    int servicePriceTypeId = sp.getDeviceType().getId();
                    for(Device device:deviceList){
                        int deviceTypeId = device.getDeviceType().getId();
                        if(deviceTypeId == servicePriceTypeId){
                            serviceCost=serviceCost+sp.getValue();
                        }
                    }
                }
                billDetails.put(service.getName(),serviceCost);
            }

            List<Detail> detailList = new ArrayList<>();
            billDetails.forEach((k, v) -> {
                detailList.add(new Detail(k,v));
            });
            double total = billDetails.values().stream().mapToDouble(Double::valueOf).sum();

            Bill bill = new Bill();
            bill.setCustomerName(customer.getName());
            bill.setTotalValue(total);
            bill.setDetailList(detailList);

            return new ResponseEntity<>(bill,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

}
