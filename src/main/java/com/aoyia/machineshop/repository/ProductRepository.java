package com.aoyia.machineshop.repository;

import com.aoyia.machineshop.domain.Product;
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
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
    @Query(value =
        "select " +
            "product from Product product " +
        "where " +
            "product.excluded = :excluded "
    )
    Page<Product> findAll(
        Pageable pageable,
        @Param("excluded") Boolean excluded
    );

    @NotNull
    @Contract(pure = true)
    static Specification<Product> hasId(Product product){
        return (productRepositoryRoot, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(productRepositoryRoot.get("id"), product.getId());
    }

    @NotNull
    @Contract(pure = true)
    static Specification<Product> hasName(Product product){
        return (productRepositoryRoot, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(productRepositoryRoot.get("name"), product.getName());
    }

    @NotNull
    @Contract(pure = true)
    static Specification<Product> hasManufacturer(Product product){
        return (productRepositoryRoot, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(productRepositoryRoot.get("manufacturer"), product.getManufacturer().getId());
    }

    @NotNull
    @Contract(pure = true)
    static Specification<Product> hasStatus(Product product){
        return (productRepositoryRoot, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(productRepositoryRoot.get("status"), product.getStatus().getId());
    }

    @NotNull
    @Contract(pure = true)
    static Specification<Product> hasExcluded(Product product){
        return (statusRoot, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(statusRoot.get("excluded"), product.getExcluded());
    }

    @NotNull
    @Contract(pure = true)
    static Specification<Product> hasModified(Product product){
        return (statusRoot, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(statusRoot.get("modified"), product.getModified());
    }
}
