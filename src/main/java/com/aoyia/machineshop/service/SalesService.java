package com.aoyia.machineshop.service;

import com.aoyia.machineshop.domain.*;
import com.aoyia.machineshop.exception.BadRequestException;
import com.aoyia.machineshop.repository.SalesRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;

@Service
@RequiredArgsConstructor
@Log4j2
public class SalesService {
    private final SalesRepository salesRepository;
    private final EntityManager entityManager;

    public Sales findById(Long id){
        return salesRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Sale not found!"));
    }

    public Page<Sales> findAll(Pageable pageable){
        return salesRepository.findAll(pageable);
    }

    public Page<Sales> findByManufacturer(Pageable pageable, Manufacturer manufacturer){
        return salesRepository.findByManufacturer(pageable, manufacturer);
    }

    public Page<Sales> findByProduct(Pageable pageable, Product product){
        return salesRepository.findByProduct(pageable, product);
    }

    public Page<Sales> findByStatus(Pageable pageable, Status status){
        return salesRepository.findByStatus(pageable, status);
    }

    public Page<Sales> findByPlace(Pageable pageable, Place place){
        return salesRepository.findByPlace(pageable, place);
    }

}
