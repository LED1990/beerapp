package com.microservices.beer.controllers;

import com.microservices.beer.model.dtos.BeerDto;
import com.microservices.beer.services.impl.BeerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/beer")
public class BeerController {

    private final BeerService beerService;

    @Autowired
    public BeerController(BeerService beerService) {
        this.beerService = beerService;
    }

    @GetMapping("{id}")
    public ResponseEntity<BeerDto> getBeerById(@PathVariable Long id) {
        Optional<BeerDto> result = beerService.getBeerById(id);
        return result.map(beerDto -> new ResponseEntity<>(beerDto, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<BeerDto> addNewBeer(@RequestBody BeerDto beerDto) {
        Optional<BeerDto> result = beerService.addNewBeer(beerDto);
        return result.map(resultDto -> new ResponseEntity<>(resultDto, HttpStatus.CREATED)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBeerById(@PathVariable Long id) {
        beerService.deleteBeer(id);
    }

    @PutMapping
    public ResponseEntity<BeerDto> updateBeer(@RequestBody BeerDto beerDto) {
        Optional<BeerDto> result = beerService.updateBeer(beerDto);
        return result.map(resultDto -> new ResponseEntity<>(resultDto, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
