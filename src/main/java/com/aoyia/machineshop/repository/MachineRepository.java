package com.aoyia.machineshop.repository;

import com.aoyia.machineshop.domain.Machine;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MachineRepository extends JpaRepository<Machine, Long>, JpaSpecificationExecutor<Machine> {
    Page<Machine> findAll(Specification specification, Pageable pageable);

    @NotNull
    @Contract(pure = true)
    static Specification<Machine> hasId(Machine machine){
        return (cigaretteDisplayRepositoryRoot, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(cigaretteDisplayRepositoryRoot.get("id"), machine.getId());
    }

    @NotNull
    @Contract(pure = true)
    static Specification<Machine> hasSerial(Machine machine){
        return (cigaretteDisplayRepositoryRoot, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(cigaretteDisplayRepositoryRoot.get("serial"), machine.getSerial());
    }

    @NotNull
    @Contract(pure = true)
    static Specification<Machine> hasStatus(Machine machine){
        return (cigaretteDisplayRepositoryRoot, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(cigaretteDisplayRepositoryRoot.get("status"), machine.getStatus().getId());
    }
}
