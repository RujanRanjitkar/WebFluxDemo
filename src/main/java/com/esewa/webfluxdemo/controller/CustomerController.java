package com.esewa.webfluxdemo.controller;

import com.esewa.webfluxdemo.dto.Customer;
import com.esewa.webfluxdemo.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.awt.*;
import java.util.List;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping()
    public List<Customer> getAllCustomers(){
        return customerService.loadAllCustomers();
    }

    @GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE) //in java 20 "produces = MediaType.TEXT_EVENT_STREAM_VALUE" media type nahaleni kaam garxa
    public Flux<Customer> getAllCustomersStreams(){
        return customerService.loadAllCustomersStream();
    }
}
