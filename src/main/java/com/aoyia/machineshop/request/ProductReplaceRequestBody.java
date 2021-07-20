package com.aoyia.machineshop.request;

import lombok.Data;

@Data
public class ProductReplaceRequestBody {
    private Long id;
    private String name;
    private ManufacturerIdRequestBody manufacturer;
    private StatusIdRequestBody status;
}
