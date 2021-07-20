package com.aoyia.machineshop.mapper;

import com.aoyia.machineshop.domain.Status;
import com.aoyia.machineshop.request.StatusCreateRequestBody;
import com.aoyia.machineshop.request.StatusFindRequestBody;
import com.aoyia.machineshop.request.StatusIdRequestBody;
import com.aoyia.machineshop.request.StatusReplaceRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;

@Mapper(componentModel = "spring")
public abstract class StatusMapper {
    public static final StatusMapper INSTANCE = Mappers.getMapper(StatusMapper.class);
    public abstract Status toStatus(StatusCreateRequestBody statusCreateRequestBody);
    public abstract Status toStatus(StatusReplaceRequestBody statusReplaceRequestBody);
    public abstract Status toStatus(StatusIdRequestBody statusIdRequestBody);
    public abstract Status toStatus(StatusFindRequestBody statusFindRequestBody);
    public abstract ArrayList<Status> toStatus(ArrayList<StatusFindRequestBody> statusFindRequestBody);
}