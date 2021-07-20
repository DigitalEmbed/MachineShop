package com.aoyia.machineshop.request;

public class PlaceRestTemplateResponseBody {
    private String status;
    private String description;
    private PlaceRestTemplateResponseData data;

    public PlaceRestTemplateResponseData getData(){
        return data;
    }
}
