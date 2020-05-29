package com.microservices.beer.mappers;

import com.microservices.beer.model.Customer;
import com.microservices.beer.model.dtos.CustomerDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomerMapperTest {

    CustomerMapper mapper = CustomerMapper.INSTANCE;

    @Test
    public void testCustomerToDto() {
        //given
        Customer customer = new Customer();
        customer.setName("name");

        //when
        CustomerDto customerDto = mapper.customerToDto(customer);

        //then
        assertEquals(customerDto.getId(), customer.getId());
        assertEquals(customerDto.getName(), customer.getName());
    }

}