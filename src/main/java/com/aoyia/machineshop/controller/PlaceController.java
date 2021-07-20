package com.aoyia.machineshop.controller;

import com.aoyia.machineshop.domain.Place;
import com.aoyia.machineshop.mapper.PlaceMapper;
import com.aoyia.machineshop.request.*;
import com.aoyia.machineshop.service.PlaceService;
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
@RequestMapping("place")
@RequiredArgsConstructor
@Log4j2
public class PlaceController {
    private final PlaceService placeService;

    @GetMapping(path = "list")
    public ResponseEntity<Page<Place>> list(
        Pageable pageable,
        @RequestParam(required = false) Boolean excluded
    ){
        return new ResponseEntity<>(placeService.find(pageable, (Place) null, null), HttpStatus.OK);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<Place> find(@PathVariable Long id){
        return new ResponseEntity<>(placeService.find(id), HttpStatus.OK);
    }

    @PostMapping(path = "find/{searchType}")
    public ResponseEntity<Page<Place>> find(
        Pageable pageable,
        @RequestBody @Valid PlacePutRequestBody placePutRequestBody,
        @PathVariable String searchType
    ){
        Place place = PlaceMapper.INSTANCE.toPlace(placePutRequestBody);
        return new ResponseEntity<>(placeService.find(pageable, place, searchType), HttpStatus.OK);
    }

    @PostMapping(path = "listFind/{searchType}")
    public ResponseEntity<Page<Place>> find(
        Pageable pageable,
        @RequestBody @Valid ArrayList<PlacePutRequestBody> placePutRequestBodyArrayList,
        @PathVariable String searchType
    ){
        ArrayList<Place> placeArrayList = PlaceMapper.INSTANCE.toPlace(placePutRequestBodyArrayList);
        return new ResponseEntity<>(placeService.find(pageable, placeArrayList, searchType), HttpStatus.OK);
    }

    @PostMapping(path = "create")
    public ResponseEntity<Place> create(
        @RequestBody @Valid PlacePostRequestBody placePostRequestBody
    ){
        Place place = PlaceMapper.INSTANCE.toPlace(placePostRequestBody);
        return new ResponseEntity<>(placeService.create(place), HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<Place> replace(
        @RequestBody @Valid PlacePutRequestBody placePutRequestBody
    ){
        Place place = PlaceMapper.INSTANCE.toPlace(placePutRequestBody);
        return new ResponseEntity<>(placeService.replace(place), HttpStatus.OK);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        placeService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}