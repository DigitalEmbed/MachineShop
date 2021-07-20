package com.aoyia.machineshop.repository;

import com.aoyia.machineshop.domain.Manufacturer;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ManufacturerRepository extends JpaRepository <Manufacturer, Long>, JpaSpecificationExecutor<Manufacturer> {
    Page<Manufacturer> findAll(Specification specification, Pageable pageable);

    @NotNull
    @Contract(pure = true)
    static Specification<Manufacturer> hasId(Manufacturer manufacturer){
        return (manufacturerRepositoryRoot, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(manufacturerRepositoryRoot.get("id"), manufacturer.getId());
    }

    @NotNull
    @Contract(pure = true)
    static Specification<Manufacturer> hasName(Manufacturer manufacturer){
        return (manufacturerRepositoryRoot, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(manufacturerRepositoryRoot.get("name"), manufacturer.getName());
    }

    @NotNull
    @Contract(pure = true)
    static Specification<Manufacturer> hasStatus(Manufacturer manufacturer){
        return (manufacturerRepositoryRoot, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(manufacturerRepositoryRoot.get("status"), manufacturer.getStatus().getId());
    }
}
