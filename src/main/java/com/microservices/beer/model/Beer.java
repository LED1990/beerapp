package com.microservices.beer.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data //getters setters equals hashcode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Beer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Version
    private Integer version;
    @CreationTimestamp
    private LocalDate createDate;
    @UpdateTimestamp
    private LocalDate lastModifiedDate;
    private String beerName;
    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private BeerStyleEnum beerStyleEnum;
    private BigDecimal price;
}
