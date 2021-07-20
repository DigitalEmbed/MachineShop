package com.aoyia.machineshop.request;

import lombok.Data;

@Data
public class MachinePostRequestBody {
    private String serial;
    private StatusIdRequestBody status;
}
