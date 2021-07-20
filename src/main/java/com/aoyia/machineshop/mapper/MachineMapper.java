package com.aoyia.machineshop.mapper;

import com.aoyia.machineshop.domain.Machine;
import com.aoyia.machineshop.request.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;

@Mapper(componentModel = "spring")
public abstract class MachineMapper {
    public static final MachineMapper INSTANCE = Mappers.getMapper(MachineMapper.class);
    public abstract Machine toCigaretteDisplay(MachinePostRequestBody machinePostRequestBody);
    public abstract Machine toCigaretteDisplay(MachinePutRequestBody machinePutRequestBody);
    public abstract Machine toCigaretteDisplay(MachineIdRequestBody machineIdRequestBody);
    public abstract ArrayList<Machine> toCigaretteDisplay(ArrayList<MachinePutRequestBody> machinePutRequestBodyArrayList);
}
