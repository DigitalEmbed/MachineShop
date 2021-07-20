package com.aoyia.machineshop.request;

import lombok.Data;

@Data
public class StatusFindRequestBody {
    private Long id;
    private String name;
    private Boolean excluded;
    private Boolean modified;
}