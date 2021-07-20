package com.aoyia.machineshop.repository;

import com.aoyia.machineshop.domain.Status;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long>, JpaSpecificationExecutor<Status> {
    /*
        @Query(value = "select status from Status status where status.name like %:name%")
        Page<Status> findAll(Pageable pageable, @Param("name") String name);
    */

    @Query(value =
        "select " +
            "status from Status status " +
        "where " +
            "status.excluded = :excluded "
    )
    Page<Status> findAll(
            Pageable pageable,
            @Param("excluded") Boolean excluded
    );

    @NotNull
    @Contract(pure = true)
    static Specification<Status> hasId(Status status){
        return (statusRoot, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(statusRoot.get("id"), status.getId());
    }

    @NotNull
    @Contract(pure = true)
    static Specification<Status> hasName(Status status){
        return (statusRoot, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(statusRoot.get("name"), status.getName());
    }

    @NotNull
    @Contract(pure = true)
    static Specification<Status> hasExcluded(Status status){
        return (statusRoot, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(statusRoot.get("excluded"), status.getExcluded());
    }

    @NotNull
    @Contract(pure = true)
    static Specification<Status> hasModified(Status status){
        return (statusRoot, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(statusRoot.get("modified"), status.getModified());
    }
}