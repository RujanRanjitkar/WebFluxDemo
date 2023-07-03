package com.esewa.webfluxdemo.service;

import com.esewa.webfluxdemo.dao.CustomerDao;
import com.esewa.webfluxdemo.dto.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerDao customerDao;

    public List<Customer> loadAllCustomers(){
        long start = System.currentTimeMillis();
        List<Customer> customers = customerDao.getCustomers();
        long end = System.currentTimeMillis();
        System.out.println("Total execution time : " + (end - start));
        return customers;
    }

    public Flux<Customer> loadAllCustomersStream(){
        long start = System.currentTimeMillis();
        Flux<Customer> customers = customerDao.getCustomersStream();
        long end = System.currentTimeMillis();
        System.out.println("Total execution time : " + (end - start));
        return customers;
    }
}
