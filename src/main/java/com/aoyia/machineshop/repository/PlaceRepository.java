package com.aoyia.machineshop.repository;

import com.aoyia.machineshop.domain.Place;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long>, JpaSpecificationExecutor<Place> {
    Page<Place> findAll(Specification specification, Pageable pageable);

    @NotNull
    @Contract(pure = true)
    static Specification<Place> hasId(Place place){
        return (placeRepositoryRoot, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(placeRepositoryRoot.get("id"), place.getId());
    }

    @NotNull
    @Contract(pure = true)
    static Specification<Place> hasHost(Place place){
        return (placeRepositoryRoot, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(placeRepositoryRoot.get("host"), place.getHost());
    }

    @NotNull
    @Contract(pure = true)
    static Specification<Place> hasIp(Place place){
        return (placeRepositoryRoot, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(placeRepositoryRoot.get("ip"), place.getIp());
    }

    @NotNull
    @Contract(pure = true)
    static Specification<Place> hasRdns(Place place){
        return (placeRepositoryRoot, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(placeRepositoryRoot.get("rdns"), place.getRdns());
    }

    @NotNull
    @Contract(pure = true)
    static Specification<Place> hasAsn(Place place){
        return (placeRepositoryRoot, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(placeRepositoryRoot.get("asn"), place.getAsn());
    }

    @NotNull
    @Contract(pure = true)
    static Specification<Place> hasIsp(Place place){
        return (placeRepositoryRoot, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(placeRepositoryRoot.get("isp"), place.getIsp());
    }

    @NotNull
    @Contract(pure = true)
    static Specification<Place> hasCountryName(Place place){
        return (placeRepositoryRoot, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(placeRepositoryRoot.get("country_name"), place.getCountryName());
    }

    @NotNull
    @Contract(pure = true)
    static Specification<Place> hasCountryCode(Place place){
        return (placeRepositoryRoot, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(placeRepositoryRoot.get("country_code"), place.getCountryCode());
    }

    @NotNull
    @Contract(pure = true)
    static Specification<Place> hasRegionName(Place place){
        return (placeRepositoryRoot, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(placeRepositoryRoot.get("region_name"), place.getRegionName());
    }

    @NotNull
    @Contract(pure = true)
    static Specification<Place> hasRegionCode(Place place){
        return (placeRepositoryRoot, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(placeRepositoryRoot.get("region_code"), place.getRegionCode());
    }

    @NotNull
    @Contract(pure = true)
    static Specification<Place> hasCity(Place place){
        return (placeRepositoryRoot, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(placeRepositoryRoot.get("city"), place.getCity());
    }

    @NotNull
    @Contract(pure = true)
    static Specification<Place> hasPostalCode(Place place){
        return (placeRepositoryRoot, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(placeRepositoryRoot.get("postal_code"), place.getPostalCode());
    }

    @NotNull
    @Contract(pure = true)
    static Specification<Place> hasContinentName(Place place){
        return (placeRepositoryRoot, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(placeRepositoryRoot.get("continent_name"), place.getContinentName());
    }

    @NotNull
    @Contract(pure = true)
    static Specification<Place> hasContinentCode(Place place){
        return (placeRepositoryRoot, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(placeRepositoryRoot.get("continent_code"), place.getContinentCode());
    }

    @NotNull
    @Contract(pure = true)
    static Specification<Place> hasLatitude(Place place){
        return (placeRepositoryRoot, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(placeRepositoryRoot.get("latitude"), place.getLatitude());
    }

    @NotNull
    @Contract(pure = true)
    static Specification<Place> hasLongitude(Place place){
        return (placeRepositoryRoot, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(placeRepositoryRoot.get("longitude"), place.getLongitude());
    }

    @NotNull
    @Contract(pure = true)
    static Specification<Place> hasMetroCode(Place place){
        return (placeRepositoryRoot, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(placeRepositoryRoot.get("metro_code"), place.getMetroCode());
    }

    @NotNull
    @Contract(pure = true)
    static Specification<Place> hasTimeZone(Place place){
        return (placeRepositoryRoot, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(placeRepositoryRoot.get("time_zone"), place.getTimeZone());
    }

    @NotNull
    @Contract(pure = true)
    static Specification<Place> hasDateTime(Place place){
        return (placeRepositoryRoot, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(placeRepositoryRoot.get("date_time"), place.getDateTime());
    }

    @NotNull
    @Contract(pure = true)
    static Specification<Place> hasStatus(Place place){
        return (placeRepositoryRoot, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(placeRepositoryRoot.get("status"), place.getStatus());
    }
}