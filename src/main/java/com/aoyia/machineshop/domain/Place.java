package com.aoyia.machineshop.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @ManyToOne
    private Status status;

    private Boolean excluded;
    private Boolean modified;
}
