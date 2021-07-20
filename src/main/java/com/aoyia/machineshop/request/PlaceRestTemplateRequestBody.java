package com.aoyia.machineshop.request;

import lombok.Data;

@Data
public class PlaceRestTemplateRequestBody {
    private String host;
    private String ip;
    private String rdns;
    private Long asn;
    private String isp;
    private String country_name;
    private String country_code;
    private String region_name;
    private String region_code;
    private String city;
    private String postal_code;
    private String continent_name;
    private String continent_code;
    private Double latitude;
    private Double longitude;
    private String metro_code;
    private String timezone;
    private String datetime;
}
