package com.aoyia.machineshop.request;

import lombok.Data;

@Data
public class PlacePutRequestBody {
    private Long id;
    private String host;
    private String ip;
    private String rdns;
    private Long asn;
    private String isp;
    private String countryName;
    private String countryCode;
    private String regionName;
    private String regionCode;
    private String city;
    private String postalCode;
    private String continentName;
    private String continentCode;
    private Double latitude;
    private Double longitude;
    private String metroCode;
    private String timeZone;
    private String dateTime;
    private StatusIdRequestBody status;
}
