package com.aoyia.machineshop.service;

import com.aoyia.machineshop.domain.Manufacturer;
import com.aoyia.machineshop.exception.BadRequestException;
import com.aoyia.machineshop.repository.ManufacturerRepository;
import com.aoyia.machineshop.repository.StatusRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
@Log4j2
public class ManufacturerService {
    private final ManufacturerRepository manufacturerRepository;
    private final StatusRepository statusRepository;

    public Manufacturer find(Long id){
        return this.manufacturerRepository.findById(id)
            .orElseThrow(() -> new BadRequestException("Manufacturer not found!"));
    }

    public Page<Manufacturer> find(Pageable pageable, Manufacturer manufacturer, String searchType){
        if (manufacturer == null || searchType == null){
            return this.manufacturerRepository.findAll(pageable);
        }
        if (!searchType.equals("or") && !searchType.equals("and")){
            throw new RuntimeException("Invalid query method. The available methods are 'and' or 'or'.");
        }

        Specification<Manufacturer> myQuery = Specification.where(null);
        if (manufacturer.getId() != null){
            myQuery = searchType.equals("or") ? myQuery.or((ManufacturerRepository.hasId(manufacturer))) : myQuery.and((ManufacturerRepository.hasId(manufacturer)));
        }
        if (manufacturer.getName() != null){
            myQuery = searchType.equals("or") ? myQuery.or((ManufacturerRepository.hasName(manufacturer))) : myQuery.and((ManufacturerRepository.hasName(manufacturer)));
        }
        if (manufacturer.getStatus() != null && manufacturer.getStatus().getId() != null){
            myQuery = searchType.equals("or") ? myQuery.or(ManufacturerRepository.hasStatus(manufacturer)) : myQuery.and(ManufacturerRepository.hasStatus(manufacturer));
        }

        return this.manufacturerRepository.findAll(myQuery, pageable);
    }

    public Page<Manufacturer> find(Pageable pageable, ArrayList<Manufacturer> manufacturerList, String searchType){
        if (manufacturerList == null || manufacturerList.isEmpty() || searchType == null){
            return this.manufacturerRepository.findAll(pageable);
        }
        if (!searchType.equals("or") && !searchType.equals("and")){
            throw new RuntimeException("Invalid query method. The available methods are 'and' or 'or'.");
        }

        Specification<Manufacturer> myQuery = Specification.where(null);
        for (Manufacturer manufacturer : manufacturerList){
            Specification<Manufacturer> mySubQuery = Specification.where(null);
            if (manufacturer.getId() != null){
                mySubQuery = mySubQuery.and(ManufacturerRepository.hasId(manufacturer));
            }
            if (manufacturer.getName() != null){
                mySubQuery = mySubQuery.and(ManufacturerRepository.hasName(manufacturer));
            }
            if (manufacturer.getStatus() != null && manufacturer.getStatus().getId() != null){
                mySubQuery = mySubQuery.and(ManufacturerRepository.hasStatus(manufacturer));
            }
            myQuery = searchType.equals("or") ? myQuery.or(mySubQuery) : myQuery.and(mySubQuery);
        }

        return this.manufacturerRepository.findAll(myQuery, pageable);
    }

    public Manufacturer create(Manufacturer manufacturer){
        if (manufacturer == null){
            throw new RuntimeException("Null manufacturer object error!");
        }
        if (manufacturer.getStatus() == null ||
            !this.statusRepository.existsById(manufacturer.getStatus().getId())
        ){
            throw new RuntimeException("Invalid status object error!");
        }
        return this.manufacturerRepository.save(manufacturer);
    }

    public void delete(Long id){
        Manufacturer manufacturer = this.find(id);
        this.manufacturerRepository.delete(manufacturer);
    }

    public Manufacturer replace(Manufacturer manufacturer){
        if (manufacturer == null){
            throw new RuntimeException("Null manufacturer object error!");
        }
        if (this.manufacturerRepository.existsById(manufacturer.getId())){
            this.delete(manufacturer.getId());
            return this.create(manufacturer);
        }
        throw new RuntimeException("Manufacturer not found!");
    }
}