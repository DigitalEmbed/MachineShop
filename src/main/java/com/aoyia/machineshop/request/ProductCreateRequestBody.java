package com.aoyia.machineshop.request;

import lombok.Data;

@Data
public class ProductCreateRequestBody {
    private String name;
    private ManufacturerIdRequestBody manufacturer;
    private StatusIdRequestBody status;
}
