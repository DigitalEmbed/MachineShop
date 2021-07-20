package com.aoyia.machineshop.request;

import lombok.Data;

@Data
public class ManufacturerPostRequestBody {
    private String name;
    private StatusIdRequestBody status;
}
