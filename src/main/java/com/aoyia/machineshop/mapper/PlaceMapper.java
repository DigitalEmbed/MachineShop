package com.aoyia.machineshop.mapper;

import com.aoyia.machineshop.domain.Place;
import com.aoyia.machineshop.request.PlacePostRequestBody;
import com.aoyia.machineshop.request.PlacePutRequestBody;
import com.aoyia.machineshop.request.PlaceRestTemplateRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;

@Mapper(componentModel = "spring")
public abstract class PlaceMapper {
    public static final PlaceMapper INSTANCE = Mappers.getMapper(PlaceMapper.class);
    public abstract Place toPlace(PlacePostRequestBody placePostRequestBody);
    public abstract Place toPlace(PlacePutRequestBody placePutRequestBody);
    public abstract ArrayList<Place> toPlace(ArrayList<PlacePutRequestBody> placePutRequestBody);
    public Place toPlace(PlaceRestTemplateRequestBody placeRestTemplateRequestBody){
        return Place.builder()
            .ip(placeRestTemplateRequestBody.getIp())
            .rdns(placeRestTemplateRequestBody.getRdns())
            .asn(placeRestTemplateRequestBody.getAsn())
            .isp(placeRestTemplateRequestBody.getIsp())
            .countryName(placeRestTemplateRequestBody.getCountry_name())
            .countryCode(placeRestTemplateRequestBody.getCountry_code())
            .regionName(placeRestTemplateRequestBody.getRegion_name())
            .regionCode(placeRestTemplateRequestBody.getRegion_code())
            .city(placeRestTemplateRequestBody.getCity())
            .postalCode(placeRestTemplateRequestBody.getPostal_code())
            .continentName(placeRestTemplateRequestBody.getContinent_name())
            .continentCode(placeRestTemplateRequestBody.getContinent_code())
            .latitude(placeRestTemplateRequestBody.getLatitude())
            .longitude(placeRestTemplateRequestBody.getLongitude())
            .metroCode(placeRestTemplateRequestBody.getMetro_code())
            .timeZone(placeRestTemplateRequestBody.getTimezone())
            .dateTime(placeRestTemplateRequestBody.getDatetime())
            .host(placeRestTemplateRequestBody.getHost())
        .build();
    }
}
