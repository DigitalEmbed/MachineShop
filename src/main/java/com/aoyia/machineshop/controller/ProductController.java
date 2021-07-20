package com.aoyia.machineshop.controller;

import com.aoyia.machineshop.domain.Product;
import com.aoyia.machineshop.mapper.ProductMapper;
import com.aoyia.machineshop.request.ProductCreateRequestBody;
import com.aoyia.machineshop.request.ProductFindRequestBody;
import com.aoyia.machineshop.request.ProductReplaceRequestBody;
import com.aoyia.machineshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("product")
@RequiredArgsConstructor
@Log4j2
public class ProductController {
    private final ProductService productService;

    @GetMapping(path = "list")
    public ResponseEntity<Page<Product>> list(
        Pageable pageable,
        @RequestParam(required = false) Boolean excluded
    ){
        return new ResponseEntity<>(productService.find(pageable, excluded), HttpStatus.OK);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<Product> find(@PathVariable Long id){
        return new ResponseEntity<>(productService.find(id), HttpStatus.OK);
    }

    @PostMapping(path = "find/{searchType}")
    public ResponseEntity<Page<Product>> find(
        Pageable pageable,
        @RequestBody @Valid ProductFindRequestBody productFindRequestBody,
        @PathVariable String searchType
    ){
        Product product = ProductMapper.INSTANCE.toProduct(productFindRequestBody);
        return new ResponseEntity<>(this.productService.find(pageable, product, searchType), HttpStatus.OK);
    }

    @PostMapping(path = "listFind/{searchType}")
    public ResponseEntity<Page<Product>> find(
    Pageable pageable,
    @RequestBody @Valid ArrayList<ProductFindRequestBody> productFindRequestBody,
    @PathVariable String searchType
    ){
        ArrayList<Product> productArrayList = ProductMapper.INSTANCE.toProduct(productFindRequestBody);
        return new ResponseEntity<>(productService.listFind(pageable, productArrayList, searchType), HttpStatus.OK);
    }

    @PostMapping(path = "create")
    public ResponseEntity<Product> create(
        @RequestBody @Valid ProductCreateRequestBody productCreateRequestBody
    ){
        Product product = ProductMapper.INSTANCE.toProduct(productCreateRequestBody);
        return new ResponseEntity<>(this.productService.create(product), HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<Product> replace(
        @RequestBody @Valid ProductReplaceRequestBody productReplaceRequestBody
    ){
        Product product = ProductMapper.INSTANCE.toProduct(productReplaceRequestBody);
        return new ResponseEntity<>(this.productService.replace(product), HttpStatus.OK);
    }

    @PutMapping(path = "restore/{id}")
    public ResponseEntity<Product> restore(@PathVariable Long id){
        return new ResponseEntity<>(this.productService.restore(id), HttpStatus.OK);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        this.productService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
