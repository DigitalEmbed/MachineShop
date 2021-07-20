package com.aoyia.machineshop.request;

import lombok.Data;

@Data
public class ManufacturerPutRequestBody {
    private Long id;
    private String name;
    private StatusIdRequestBody status;
}
