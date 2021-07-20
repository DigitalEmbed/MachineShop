package com.aoyia.machineshop.mapper;

import com.aoyia.machineshop.domain.Manufacturer;
import com.aoyia.machineshop.request.ManufacturerIdRequestBody;
import com.aoyia.machineshop.request.ManufacturerPostRequestBody;
import com.aoyia.machineshop.request.ManufacturerPutRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;

@Mapper(componentModel = "spring")
public abstract class ManufacturerMapper {
    public static final ManufacturerMapper INSTANCE = Mappers.getMapper(ManufacturerMapper.class);
    public abstract Manufacturer toManufacturer(ManufacturerPostRequestBody manufacturerPostRequestBody);
    public abstract Manufacturer toManufacturer(ManufacturerPutRequestBody manufacturerPutRequestBody);
    public abstract Manufacturer toManufacturer(ManufacturerIdRequestBody manufacturerIdRequestBody);
    public abstract ArrayList<Manufacturer> toManufacturer(ArrayList<ManufacturerPutRequestBody> manufacturerPutRequestBodyArrayList);
}
