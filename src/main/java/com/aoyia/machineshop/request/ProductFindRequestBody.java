package com.aoyia.machineshop.request;

import lombok.Data;

@Data
public class ProductFindRequestBody {
    private Long id;
    private String name;
    private ManufacturerIdRequestBody manufacturer;
    private StatusIdRequestBody status;
    private Boolean excluded;
    private Boolean modified;
}
