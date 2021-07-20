package com.aoyia.machineshop.request;

import lombok.Data;

@Data
public class PlacePostRequestBody {
    private String ip;
    private StatusIdRequestBody status;
}
