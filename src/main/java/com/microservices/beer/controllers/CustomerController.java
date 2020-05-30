package com.microservices.beer.controllers;

import com.microservices.beer.model.dtos.CustomerDto;
import com.microservices.beer.services.impl.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable Long id) {
        Optional<CustomerDto> result = customerService.getCustomerById(id);
        if (result.isPresent()) {
            result.get().add(linkTo(methodOn(CustomerController.class).getCustomerById(result.get().getId())).withSelfRel());
            return ResponseEntity.ok(result.get());
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> addNewCustomer(@RequestBody CustomerDto customerDto) {
        Optional<CustomerDto> result = customerService.addNewCustomer(customerDto);
        if (result.isPresent()) {
            result.get().add(linkTo(methodOn(CustomerController.class).getCustomerById(result.get().getId())).withSelfRel());
            return new ResponseEntity<>(result.get(), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
    }

    @PutMapping
    public ResponseEntity<?> updateCustomer(@RequestBody CustomerDto customerDto) {
        Optional<CustomerDto> result = customerService.updateCustomer(customerDto);
        if (result.isPresent()) {
            result.get().add(linkTo(methodOn(CustomerController.class).getCustomerById(result.get().getId())).withSelfRel());
            return new ResponseEntity<>(result.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
