package com.aoyia.machineshop.repository;

import com.aoyia.machineshop.domain.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesRepository extends JpaRepository<Sales, Long> {

    Page<Sales> findByManufacturer(Pageable pageable, Manufacturer manufacturer);
    Page<Sales> findByProduct(Pageable pageable, Product product);
    Page<Sales> findByStatus(Pageable pageable, Status status);
    Page<Sales> findByPlace(Pageable pageable, Place place);
    //Page<Sales> find(Pageable pageable, Manufacturer manufacturer, Product product, Status status, Place place);

    /*
    @Query(
            value =
            "select " +
                "sales " +
            "from " +
                "Sales sales " +
            "where " +
                "sales.manufacturer = :manufacturer_id and " +
                "sales.product = :product_id and " +
                "sales.status = :status_id and " +
                "sales.place = :place_id"
    )
    Page<Sales> find(
            Pageable pageable,
            @Param("manufacturer_id") Long manufacturerId,
            @Param("product_id") Long productId,
            @Param("place_id") Long placeId,
            @Param("status_id") Long statusId
    );
    */
}
