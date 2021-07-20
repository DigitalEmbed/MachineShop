package com.aoyia.machineshop.controller;

import com.aoyia.machineshop.domain.Status;
import com.aoyia.machineshop.mapper.StatusMapper;
import com.aoyia.machineshop.request.StatusFindRequestBody;
import com.aoyia.machineshop.request.StatusReplaceRequestBody;
import com.aoyia.machineshop.service.StatusService;
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
@RequestMapping("status")
@RequiredArgsConstructor
@Log4j2
public class StatusController {
    private final StatusService statusService;

    @GetMapping(path = "list")
    public ResponseEntity<Page<Status>> list(
        Pageable pageable,
        @RequestParam(required = false) Boolean excluded
    ){
        return new ResponseEntity<>(statusService.find(pageable, excluded), HttpStatus.OK);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<Status> find(@PathVariable Long id) {
        return new ResponseEntity<>(statusService.find(id), HttpStatus.OK);
    }

    @PostMapping(path = "find/{searchType}")
    public ResponseEntity<Page<Status>> find(
        Pageable pageable,
        @RequestBody @Valid StatusFindRequestBody statusFindRequestBody,
        @PathVariable String searchType
    ){
        Status status = StatusMapper.INSTANCE.toStatus(statusFindRequestBody);
        return new ResponseEntity<>(statusService.find(pageable, status, searchType), HttpStatus.OK);
    }

    @PostMapping(path = "listFind/{searchType}")
    public ResponseEntity<Page<Status>> find(
            Pageable pageable,
            @RequestBody @Valid ArrayList<StatusFindRequestBody> statusFindRequestBodyArrayList,
            @PathVariable String searchType
    ){
        ArrayList<Status> statusArrayList = StatusMapper.INSTANCE.toStatus(statusFindRequestBodyArrayList);
        return new ResponseEntity<>(statusService.find(pageable, statusArrayList, searchType), HttpStatus.OK);
    }

    @PostMapping(path = "create")
    public ResponseEntity<Status> create(
        @RequestBody @Valid StatusReplaceRequestBody statusReplaceRequestBody
    ){
        Status status = StatusMapper.INSTANCE.toStatus(statusReplaceRequestBody);
        return new ResponseEntity<>(statusService.create(status), HttpStatus.CREATED);
    }

    @PutMapping(path = "replace")
    public ResponseEntity<Status> replace(@RequestBody @Valid StatusReplaceRequestBody statusReplaceRequestBody){
        Status status = StatusMapper.INSTANCE.toStatus(statusReplaceRequestBody);
        return new ResponseEntity<>(statusService.replace(status), HttpStatus.OK);
    }

    @PutMapping(path = "restore/{id}")
    public ResponseEntity<Status> restore(@PathVariable Long id){
        return new ResponseEntity<>(statusService.restore(id), HttpStatus.OK);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        statusService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}