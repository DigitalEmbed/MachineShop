package com.aoyia.machineshop.request;

import lombok.Data;

@Data
public class MachinePutRequestBody {
    private Long id;
    private String serial;
    private StatusIdRequestBody status;
}
