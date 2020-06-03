package com.microservices.beer.services.impl;

import com.microservices.beer.model.dtos.BeerDto;
import com.microservices.beer.model.dtos.CustomerDto;
import org.springframework.data.domain.PageImpl;

import java.util.Optional;

public interface BeerService {

    Optional<BeerDto> getBeerById(Long id);

    Optional<BeerDto> addNewBeer(BeerDto beerDto);

    void deleteBeer(Long id);

    Optional<BeerDto> updateBeer(BeerDto beerDto);

//    Optional<PageImpl<BeerDto>> getBeerPage(); //todo later
}
