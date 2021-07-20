package com.aoyia.machineshop.mapper;

import com.aoyia.machineshop.domain.Product;
import com.aoyia.machineshop.request.ProductFindRequestBody;
import com.aoyia.machineshop.request.ProductIdRequestBody;
import com.aoyia.machineshop.request.ProductCreateRequestBody;
import com.aoyia.machineshop.request.ProductReplaceRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;

@Mapper(componentModel = "spring")
public abstract class ProductMapper {
    public static final ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);
    public abstract Product toProduct(ProductIdRequestBody productIdRequestBody);
    public abstract Product toProduct(ProductCreateRequestBody productCreateRequestBody);
    public abstract Product toProduct(ProductReplaceRequestBody productReplaceRequestBody);
    public abstract Product toProduct(ProductFindRequestBody productFindRequestBody);
    public abstract ArrayList<Product> toProduct(ArrayList<ProductFindRequestBody> productFindRequestBodyArrayList);
}