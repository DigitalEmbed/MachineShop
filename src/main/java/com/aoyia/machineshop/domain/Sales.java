package com.aoyia.machineshop.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Sales {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Manufacturer manufacturer;

    @ManyToOne
    private Product product;

    @ManyToOne
    private Place place;

    @ManyToOne
    private Status status;

    private Boolean excluded;
    private Boolean modified;
}
