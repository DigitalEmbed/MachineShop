package com.aoyia.machineshop.controller;

import com.aoyia.machineshop.domain.Manufacturer;
import com.aoyia.machineshop.mapper.ManufacturerMapper;
import com.aoyia.machineshop.request.ManufacturerPostRequestBody;
import com.aoyia.machineshop.request.ManufacturerPutRequestBody;
import com.aoyia.machineshop.service.ManufacturerService;
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
@RequestMapping("manufacturer")
@RequiredArgsConstructor
@Log4j2
public class ManufacturerController {
    private final ManufacturerService manufacturerService;

    @GetMapping(path = "listAll")
    public ResponseEntity<Page<Manufacturer>> listAll(Pageable pageable){
        return new ResponseEntity<>(manufacturerService.find(pageable, (Manufacturer) null, null), HttpStatus.OK);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<Manufacturer> find(@PathVariable Long id){
        return new ResponseEntity<>(manufacturerService.find(id), HttpStatus.OK);
    }

    @PostMapping(path = "find/{searchType}")
    public ResponseEntity<Page<Manufacturer>> find(
        Pageable pageable,
        @RequestBody @Valid ManufacturerPutRequestBody manufacturerPutRequestBody,
        @PathVariable String searchType
    ){
        Manufacturer manufacturer = ManufacturerMapper.INSTANCE.toManufacturer(manufacturerPutRequestBody);
        return new ResponseEntity<>(manufacturerService.find(pageable, manufacturer, searchType), HttpStatus.OK);
    }

    @PostMapping(path = "listFind/{searchType}")
    public ResponseEntity<Page<Manufacturer>> find(
            Pageable pageable,
            @RequestBody @Valid ArrayList<ManufacturerPutRequestBody> manufacturerPutRequestBodyArrayList,
            @PathVariable String searchType
    ){
        ArrayList<Manufacturer> manufacturerArrayList = ManufacturerMapper.INSTANCE.toManufacturer(manufacturerPutRequestBodyArrayList);
        return new ResponseEntity<>(manufacturerService.find(pageable, manufacturerArrayList, searchType), HttpStatus.OK);
    }

    @PostMapping(path = "create")
    public ResponseEntity<Manufacturer> create(
        @RequestBody @Valid ManufacturerPostRequestBody manufacturerPostRequestBody
    ){
        Manufacturer manufacturer = ManufacturerMapper.INSTANCE.toManufacturer(manufacturerPostRequestBody);
        return new ResponseEntity<>(manufacturerService.create(manufacturer), HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<Manufacturer> replace(
        @RequestBody @Valid ManufacturerPutRequestBody manufacturerPutRequestBody
    ){
        Manufacturer manufacturer = ManufacturerMapper.INSTANCE.toManufacturer(manufacturerPutRequestBody);
        return new ResponseEntity<>(manufacturerService.replace(manufacturer), HttpStatus.OK);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        manufacturerService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
