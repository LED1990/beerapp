package com.microservices.beer.services.impl;

import com.microservices.beer.model.dtos.CustomerDto;

import java.util.Optional;

public interface CustomerService {

    Optional<CustomerDto> getCustomerById(Long id);

    Optional<CustomerDto> addNewCustomer(CustomerDto customerDto);

    void deleteCustomer(Long id);

    Optional<CustomerDto> updateCustomer(CustomerDto customerDto);
}
