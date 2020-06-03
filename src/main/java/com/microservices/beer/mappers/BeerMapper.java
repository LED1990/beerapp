package com.microservices.beer.mappers;

import com.microservices.beer.model.Beer;
import com.microservices.beer.model.dtos.BeerDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BeerMapper {

    BeerMapper INSTANCE = Mappers.getMapper(BeerMapper.class);

    BeerDto beerToDto(Beer beer);

    Beer dtoToBeer(BeerDto beerDto);
}
