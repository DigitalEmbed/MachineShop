package com.aoyia.machineshop.controller;

import com.aoyia.machineshop.domain.Machine;
import com.aoyia.machineshop.mapper.MachineMapper;
import com.aoyia.machineshop.request.MachinePostRequestBody;
import com.aoyia.machineshop.request.MachinePutRequestBody;
import com.aoyia.machineshop.service.MachineService;
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
@RequestMapping("machine")
@RequiredArgsConstructor
@Log4j2
public class MachineController {
    private final MachineService machineService;

    @GetMapping(path = "listAll")
    public ResponseEntity<Page<Machine>> listAll(Pageable pageable){
        return new ResponseEntity<>(machineService.find(pageable, (Machine) null, null), HttpStatus.OK);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<Machine> find(@PathVariable Long id){
        return new ResponseEntity<>(machineService.find(id), HttpStatus.OK);
    }

    @PostMapping(path = "listFind/{searchType}")
    public ResponseEntity<Page<Machine>> find(
            Pageable pageable,
            @RequestBody @Valid ArrayList<MachinePutRequestBody> machinePutRequestBodyArrayList,
            @PathVariable String searchType
    ){
        ArrayList<Machine> machineArrayList = MachineMapper.INSTANCE.toCigaretteDisplay(machinePutRequestBodyArrayList);
        return new ResponseEntity<>(machineService.find(pageable, machineArrayList, searchType), HttpStatus.OK);
    }

    @PostMapping(path = "find/{searchType}")
    public ResponseEntity<Page<Machine>> find(
            Pageable pageable,
            @RequestBody @Valid MachinePutRequestBody machinePutRequestBody,
            @PathVariable String searchType
    ){
        Machine machine = MachineMapper.INSTANCE.toCigaretteDisplay(machinePutRequestBody);
        return new ResponseEntity<>(machineService.find(pageable, machine, searchType), HttpStatus.OK);
    }

    @PostMapping(path = "create")
    public ResponseEntity<Machine> create(
            @RequestBody @Valid MachinePostRequestBody machinePostRequestBody
    ){
        Machine machine = MachineMapper.INSTANCE.toCigaretteDisplay(machinePostRequestBody);
        return new ResponseEntity<>(machineService.create(machine), HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<Machine> replace(
        @RequestBody @Valid MachinePutRequestBody machinePutRequestBody
    ){
        Machine machine = MachineMapper.INSTANCE.toCigaretteDisplay(machinePutRequestBody);
        return new ResponseEntity<>(machineService.replace(machine), HttpStatus.OK);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        machineService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
