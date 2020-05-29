package com.microservices.beer.services;

import com.microservices.beer.exceptions.NotFoundException;
import com.microservices.beer.mappers.CustomerMapper;
import com.microservices.beer.model.Customer;
import com.microservices.beer.model.dtos.CustomerDto;
import com.microservices.beer.repository.CustomerRepository;
import com.microservices.beer.services.impl.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerMapper mapper = CustomerMapper.INSTANCE;

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Optional<CustomerDto> getCustomerById(Long id) {

        Optional<Customer> customer = customerRepository.findById(id);

        return Optional.ofNullable(customer.map(mapper::customerToDto).orElseThrow(() -> new NotFoundException("customer not found")));
    }

    @Override
    public Optional<CustomerDto> addNewCustomer(CustomerDto customerDto) {

        Customer customer = customerRepository.save(mapper.dtoToCustomer(customerDto));

        return Optional.of(mapper.customerToDto(customer));
    }
}
