package com.microservices.beer.services;

import com.microservices.beer.exceptions.NotFoundException;
import com.microservices.beer.mappers.BeerMapper;
import com.microservices.beer.model.Beer;
import com.microservices.beer.model.dtos.BeerDto;
import com.microservices.beer.repository.BeerRepository;
import com.microservices.beer.services.impl.BeerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BeerServiceImpl implements BeerService {

    private final BeerRepository beerRepository;

    private final BeerMapper mapper = BeerMapper.INSTANCE;

    @Autowired
    public BeerServiceImpl(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }

    @Override
    public Optional<BeerDto> getBeerById(Long id) {
        Optional<Beer> beer = beerRepository.findById(id);
        if (beer.isPresent()) {
            BeerDto beerDto = mapper.beerToDto(beer.get());
            return Optional.of(beerDto);
        }
        throw new NotFoundException("beer not found!");
    }

    @Override
    public Optional<BeerDto> addNewBeer(BeerDto beerDto) {
        Beer result = beerRepository.save(mapper.dtoToBeer(beerDto));
        return Optional.of(mapper.beerToDto(result));
    }

    @Override
    public void deleteBeer(Long id) {
        beerRepository.deleteById(id);
    }

    @Override
    public Optional<BeerDto> updateBeer(BeerDto beerDto) {
        Beer result = beerRepository.save(mapper.dtoToBeer(beerDto));
        return Optional.of(mapper.beerToDto(result));
    }
}
