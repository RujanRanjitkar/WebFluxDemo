package com.esewa.webfluxdemo.handler;

import com.esewa.webfluxdemo.dao.CustomerDao;
import com.esewa.webfluxdemo.dto.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CustomerHandler {
    private final CustomerDao customerDao;

    public Mono<ServerResponse> loadCustomers(ServerRequest request){
        Flux<Customer> customerList = customerDao.getCustomerList();
        return ServerResponse.ok().body(customerList, Customer.class);
    }

    public Mono<ServerResponse> findCustomer(ServerRequest request) {
        int customerId = Integer.valueOf(request.pathVariable("input"));
//        customerDao.getCustomerList().filter(c->c.getId() == customerId).take(1).single();
        Mono<Customer> customerMono = customerDao.getCustomerList().filter(c -> c.getId() == customerId).next();
        return ServerResponse.ok().body(customerMono, Customer.class);
    }

    public Mono<ServerResponse> saveCustomer(ServerRequest request){
        Mono<Customer> customerMono = request.bodyToMono(Customer.class);
        Mono<String> saveResponse = customerMono.map(dto -> dto.getId() + ":" + dto.getName());
        return ServerResponse.ok().body(saveResponse, String.class);
    }
}
