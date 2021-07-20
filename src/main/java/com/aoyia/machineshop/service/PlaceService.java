package com.aoyia.machineshop.service;

import com.aoyia.machineshop.domain.Place;
import com.aoyia.machineshop.domain.Status;
import com.aoyia.machineshop.exception.BadRequestException;
import com.aoyia.machineshop.mapper.PlaceMapper;
import com.aoyia.machineshop.repository.PlaceRepository;
import com.aoyia.machineshop.repository.StatusRepository;
import com.aoyia.machineshop.request.PlaceRestTemplateResponseBody;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;

@Service
@RequiredArgsConstructor
@Log4j2
public class PlaceService {
    private final PlaceRepository placeRepository;
    private final StatusRepository statusRepository;

    private Place getRequestLocalization(Place place){
        Long id = place.getId();
        Status status = place.getStatus();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("User-Agent", "keycdn-tools:https://www.domod.com.br");

        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        ResponseEntity<PlaceRestTemplateResponseBody> externalRequestResponse = new RestTemplate().exchange(
        "https://tools.keycdn.com/geo.json?host=" + place.getIp(),
            HttpMethod.GET,
            entity,
            PlaceRestTemplateResponseBody.class
        );

        if( externalRequestResponse.getStatusCode() == HttpStatus.OK
            && externalRequestResponse.getBody() != null
            && externalRequestResponse.getBody().getData() != null)
        {
            place = PlaceMapper.INSTANCE.toPlace(externalRequestResponse.getBody().getData().getGeo());
            place.setId(id);
            place.setStatus(status);
            return place;
        }
        throw new RuntimeException("Error getting geolocation!");
    }

    public Place find(Long id){
        return placeRepository.findById(id)
            .orElseThrow(() -> new BadRequestException("Place not found!"));
    }

    public Page<Place> find(Pageable pageable, Place place, String searchType){
        if (place == null || searchType == null){
            return this.placeRepository.findAll(pageable);
        }
        if (!searchType.equals("or") && !searchType.equals("and")){
            throw new RuntimeException("Invalid query method. The available methods are 'and' or 'or'.");
        }

        Specification<Place> myQuery = Specification.where(null);
        if (place.getId() != null){
            myQuery = searchType.equals("or") ? myQuery.or(PlaceRepository.hasId(place)) : myQuery.and(PlaceRepository.hasId(place));
        }
        if (place.getHost() != null){
            myQuery = searchType.equals("or") ? myQuery.or(PlaceRepository.hasHost(place)) : myQuery.and(PlaceRepository.hasHost(place));
        }
        if (place.getIp() != null){
            myQuery = searchType.equals("or") ? myQuery.or(PlaceRepository.hasIp(place)) : myQuery.and(PlaceRepository.hasIp(place));
        }
        if (place.getRdns() != null){
            myQuery = searchType.equals("or") ? myQuery.or(PlaceRepository.hasRdns(place)) : myQuery.and(PlaceRepository.hasRdns(place));
        }
        if (place.getAsn() != null){
            myQuery = searchType.equals("or") ? myQuery.or(PlaceRepository.hasAsn(place)) : myQuery.and(PlaceRepository.hasAsn(place));
        }
        if (place.getIsp() != null){
            myQuery = searchType.equals("or") ? myQuery.or(PlaceRepository.hasIsp(place)) : myQuery.and(PlaceRepository.hasIsp(place));
        }
        if (place.getCountryName() != null){
            myQuery = searchType.equals("or") ? myQuery.or(PlaceRepository.hasCountryName(place)) : myQuery.and(PlaceRepository.hasCountryName(place));
        }
        if (place.getCountryCode() != null){
            myQuery = searchType.equals("or") ? myQuery.or(PlaceRepository.hasCountryCode(place)) : myQuery.and(PlaceRepository.hasCountryCode(place));
        }
        if (place.getRegionName() != null){
            myQuery = searchType.equals("or") ? myQuery.or(PlaceRepository.hasRegionName(place)) : myQuery.and(PlaceRepository.hasRegionName(place));
        }
        if (place.getRegionCode() != null){
            myQuery = searchType.equals("or") ? myQuery.or(PlaceRepository.hasRegionCode(place)) : myQuery.and(PlaceRepository.hasRegionCode(place));
        }
        if (place.getCity() != null){
            myQuery = searchType.equals("or") ? myQuery.or(PlaceRepository.hasCity(place)) : myQuery.and(PlaceRepository.hasCity(place));
        }
        if (place.getPostalCode() != null){
            myQuery = searchType.equals("or") ? myQuery.or(PlaceRepository.hasPostalCode(place)) : myQuery.and(PlaceRepository.hasPostalCode(place));
        }
        if (place.getContinentName() != null){
            myQuery = searchType.equals("or") ? myQuery.or(PlaceRepository.hasRegionName(place)) : myQuery.and(PlaceRepository.hasRegionName(place));
        }
        if (place.getContinentCode() != null){
            myQuery = searchType.equals("or") ? myQuery.or(PlaceRepository.hasRegionCode(place)) : myQuery.and(PlaceRepository.hasRegionCode(place));
        }
        if (place.getLatitude() != null){
            myQuery = searchType.equals("or") ? myQuery.or(PlaceRepository.hasLatitude(place)) : myQuery.and(PlaceRepository.hasLatitude(place));
        }
        if (place.getLongitude() != null){
            myQuery = searchType.equals("or") ? myQuery.or(PlaceRepository.hasLongitude(place)) : myQuery.and(PlaceRepository.hasLongitude(place));
        }
        if (place.getMetroCode() != null){
            myQuery = searchType.equals("or") ? myQuery.or(PlaceRepository.hasMetroCode(place)) : myQuery.and(PlaceRepository.hasMetroCode(place));
        }
        if (place.getTimeZone() != null){
            myQuery = searchType.equals("or") ? myQuery.or(PlaceRepository.hasTimeZone(place)) : myQuery.and(PlaceRepository.hasTimeZone(place));
        }
        if (place.getDateTime() != null){
            myQuery = searchType.equals("or") ? myQuery.or(PlaceRepository.hasDateTime(place)) : myQuery.and(PlaceRepository.hasDateTime(place));
        }
        if (place.getStatus() != null && place.getStatus().getId() != null){
            myQuery = searchType.equals("or") ? myQuery.or(PlaceRepository.hasStatus(place)) : myQuery.and(PlaceRepository.hasStatus(place));
        }

        return this.placeRepository.findAll(myQuery, pageable);
    }

    public Page<Place> find(Pageable pageable, ArrayList<Place> placeList, String searchType){
        if (placeList == null || placeList.isEmpty() || searchType == null){
            return this.placeRepository.findAll(pageable);
        }
        if (!searchType.equals("or") && !searchType.equals("and")){
            throw new RuntimeException("Invalid query method. The available methods are 'and' or 'or'.");
        }

        Specification<Place> myQuery = Specification.where(null);
        for (Place place : placeList){
            Specification<Place> mySubQuery = Specification.where(null);
            if (place.getId() != null){
                mySubQuery = mySubQuery.and(PlaceRepository.hasId(place));
            }
            if (place.getHost() != null){
                mySubQuery = mySubQuery.and(PlaceRepository.hasHost(place));
            }
            if (place.getIp() != null){
                mySubQuery = mySubQuery.and(PlaceRepository.hasIp(place));
            }
            if (place.getRdns() != null){
                mySubQuery = mySubQuery.and(PlaceRepository.hasRdns(place));
            }
            if (place.getAsn() != null){
                mySubQuery = mySubQuery.and(PlaceRepository.hasAsn(place));
            }
            if (place.getIsp() != null){
                mySubQuery = mySubQuery.and(PlaceRepository.hasIsp(place));
            }
            if (place.getCountryName() != null){
                mySubQuery = mySubQuery.and(PlaceRepository.hasCountryName(place));
            }
            if (place.getCountryCode() != null){
                mySubQuery = mySubQuery.and(PlaceRepository.hasCountryCode(place));
            }
            if (place.getRegionName() != null){
                mySubQuery = mySubQuery.and(PlaceRepository.hasRegionName(place));
            }
            if (place.getRegionCode() != null){
                mySubQuery = mySubQuery.and(PlaceRepository.hasRegionCode(place));
            }
            if (place.getCity() != null){
                mySubQuery = mySubQuery.and(PlaceRepository.hasCity(place));
            }
            if (place.getPostalCode() != null){
                mySubQuery = mySubQuery.and(PlaceRepository.hasPostalCode(place));
            }
            if (place.getContinentName() != null){
                mySubQuery = mySubQuery.and(PlaceRepository.hasRegionName(place));
            }
            if (place.getContinentCode() != null){
                mySubQuery = mySubQuery.and(PlaceRepository.hasRegionCode(place));
            }
            if (place.getLatitude() != null){
                mySubQuery = mySubQuery.and(PlaceRepository.hasLatitude(place));
            }
            if (place.getLongitude() != null){
                mySubQuery = mySubQuery.and(PlaceRepository.hasLongitude(place));
            }
            if (place.getMetroCode() != null){
                mySubQuery = mySubQuery.and(PlaceRepository.hasMetroCode(place));
            }
            if (place.getTimeZone() != null){
                mySubQuery = mySubQuery.and(PlaceRepository.hasTimeZone(place));
            }
            if (place.getDateTime() != null){
                mySubQuery = mySubQuery.and(PlaceRepository.hasDateTime(place));
            }
            if (place.getStatus() != null && place.getStatus().getId() != null){
                mySubQuery = mySubQuery.and(PlaceRepository.hasStatus(place));
            }
            myQuery = searchType.equals("or") ? myQuery.or(mySubQuery) : myQuery.and(mySubQuery);
        }

        return this.placeRepository.findAll(myQuery, pageable);
    }

    public Place create(Place place){
        if (place == null) {
            throw new RuntimeException("Invalid place object!");
        }
        if (place.getStatus() == null
            || place.getStatus().getId() == null
            || !this.statusRepository.existsById(place.getStatus().getId())
        ){
            throw new RuntimeException("Invalid status object!");
        }
        if (place.getIp() == null) {
            throw new RuntimeException("Invalid client ip!");
        }
        place = this.getRequestLocalization(place);
        return this.placeRepository.save(place);
    }

    public void delete(Long id){
        Place place = this.find(id);
        this.placeRepository.delete(place);
    }

    public Place replace(Place place) {
        if (place == null
            || place.getId() == null
            || !this.placeRepository.existsById(place.getId())
        ){
            throw new RuntimeException("Invalid place object!");
        }
        if (place.getStatus() == null
            || place.getStatus().getId() == null
            || !this.statusRepository.existsById(place.getStatus().getId())
        ){
            throw new RuntimeException("Invalid status object!");
        }
        if (place.getIp() == null) {
            throw new RuntimeException("Invalid client ip!");
        }
        this.delete(place.getId());
        return this.placeRepository.save(place);
    }
}
