package com.aoyia.machineshop.service;

import com.aoyia.machineshop.domain.Machine;
import com.aoyia.machineshop.exception.BadRequestException;
import com.aoyia.machineshop.repository.MachineRepository;
import com.aoyia.machineshop.repository.StatusRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
@Log4j2
public class MachineService {
    private final MachineRepository machineRepository;
    private final StatusRepository statusRepository;

    public Machine find(Long id){
        return this.machineRepository.findById(id)
            .orElseThrow(() -> new BadRequestException("Cigarette display not found!"));
    }

    public Page<Machine> find(Pageable pageable, Machine machine, String searchType){
        if (machine == null || searchType == null){
            return this.machineRepository.findAll(pageable);
        }
        if (!searchType.equals("or") && !searchType.equals("and")){
            throw new RuntimeException("Invalid query method. The available methods are 'and' or 'or'.");
        }

        Specification<Machine> myQuery = Specification.where(null);
        if (machine.getId() != null){
            myQuery = searchType.equals("or") ? myQuery.or(MachineRepository.hasId(machine)) : myQuery.and(MachineRepository.hasId(machine));
        }
        if (machine.getSerial() != null){
            myQuery = searchType.equals("or") ? myQuery.or(MachineRepository.hasSerial(machine)) : myQuery.and(MachineRepository.hasSerial(machine));
        }
        if (machine.getStatus() != null && machine.getStatus().getId() != null){
            myQuery = searchType.equals("or") ? myQuery.or(MachineRepository.hasStatus(machine)) : myQuery.and(MachineRepository.hasStatus(machine));
        }

        return this.machineRepository.findAll(myQuery, pageable);
    }

    public Page<Machine> find(Pageable pageable, ArrayList<Machine> machineList, String searchType){
        if (machineList == null || machineList.isEmpty() || searchType == null){
            return this.machineRepository.findAll(pageable);
        }
        if (!searchType.equals("or") && !searchType.equals("and")){
            throw new RuntimeException("Invalid query method. The available methods are 'and' or 'or'.");
        }

        Specification<Machine> myQuery = Specification.where(null);
        for (Machine machine : machineList){
            Specification<Machine> mySubQuery = Specification.where(null);
            if (machine.getId() != null){
                mySubQuery = mySubQuery.and(MachineRepository.hasId(machine));
            }
            if (machine.getSerial() != null){
                mySubQuery = mySubQuery.and(MachineRepository.hasSerial(machine));
            }
            if (machine.getStatus() != null && machine.getStatus().getId() != null){
                mySubQuery = mySubQuery.and(MachineRepository.hasStatus(machine));
            }
            myQuery = searchType.equals("or") ? myQuery.or(mySubQuery) : myQuery.and(mySubQuery);
        };

        return this.machineRepository.findAll(myQuery, pageable);
    }

    public Machine create(Machine machine){
        if (machine == null){
            throw new RuntimeException("Null cigarette display object error!");
        }
        if (machine.getStatus() == null ||
            !this.statusRepository.existsById(machine.getStatus().getId())
        ){
            throw new RuntimeException("Invalid status object error!");
        }
        return this.machineRepository.save(machine);
    }

    public void delete(Long id){
        Machine machine = this.find(id);
        this.machineRepository.delete(machine);
    }

    public Machine replace(Machine machine) {
        if (machine == null) {
            throw new RuntimeException("Null cigarette display object error!");
        }
        if (this.machineRepository.existsById(machine.getId())) {
            this.delete(machine.getId());
            return this.create(machine);
        }
        throw new RuntimeException("Cigarette display not found!");
    }
}
