package com.ifbhotels.ebmanagement.services;

import com.ifbhotels.ebmanagement.constants.Constants;
import com.ifbhotels.ebmanagement.enums.DeviceState;
import com.ifbhotels.ebmanagement.exceptions.ConsumptionLimitExceededException;
import com.ifbhotels.ebmanagement.models.data.Movement;
import com.ifbhotels.ebmanagement.models.electricaldevices.AC;
import com.ifbhotels.ebmanagement.models.electricaldevices.ElectricalDevice;
import com.ifbhotels.ebmanagement.models.structures.*;
import com.ifbhotels.ebmanagement.services.storage.StorageService;
import com.ifbhotels.ebmanagement.services.storage.StorageServiceFactory;

import java.util.ArrayList;
import java.util.List;

public class HotelService {

    private StorageService storageService;

    private final ElectricalService electricalService;

    public HotelService() {
        storageService = StorageServiceFactory.getStorageService(Constants.SIMPLE_STORAGE_TYPE);
        electricalService = new ElectricalService();
    }

    public Hotel createHotel (int floors,
                             int mainCorridorPerFloor,
                             int subCorridorPerFloor) {
        storageService.setHotel(new Hotel(createFloors(floors,
                mainCorridorPerFloor,
                subCorridorPerFloor) ) );
        return storageService.getHotel();
    }

    public Hotel getHotel () {
        return storageService.getHotel();
    }

    private List<Floor> createFloors(int floorCount,
                                     int mainCorridorPerFloor,
                                     int subCorridorPerFloor) {
        List<Floor> floorList = new ArrayList<>();

        for (int i = 0; i < floorCount; i++) {
            floorList.add(createFloor(i + 1,
                    mainCorridorPerFloor,
                    subCorridorPerFloor));
        }
        return floorList;
    }

    private Floor createFloor(int id,
                              int mainCorridorPerFloor,
                              int subCorridorPerFloor) {
        return new Floor(id, createCorridors(mainCorridorPerFloor, subCorridorPerFloor));
    }

    private List<Corridor> createCorridors(int mainCorridors, int subCorridors) {
        List<Corridor> corridorList = new ArrayList<>(mainCorridors + subCorridors);
        corridorList.addAll(createMainCorridors(mainCorridors));
        corridorList.addAll(createSubCorridors(subCorridors));
        return corridorList;
    }

    private List<MainCorridor> createMainCorridors(int mainCorridors) {
        List<MainCorridor> mainCorridorList = new ArrayList<>(mainCorridors);
        for (int i = 0; i < mainCorridors; i++)
            mainCorridorList.add(createMainCorridor(i + 1));
        return mainCorridorList;
    }

    private List<SubCorridor> createSubCorridors(int subCorridors) {
        List<SubCorridor> subCorridorList = new ArrayList<>(subCorridors);
        for (int i = 0; i < subCorridors; i++)
            subCorridorList.add(createSubCorridor(i + 1, i + 1));
        return subCorridorList;
    }

    private MainCorridor createMainCorridor(int corridorId) {
        return new MainCorridor(corridorId,
                electricalService.getLight(1, DeviceState.OFF),
                electricalService.getAC(1, DeviceState.ON) );
    }

    private SubCorridor createSubCorridor(int corridorId, int lightId) {
        return new SubCorridor(corridorId,
                electricalService.getLight(lightId, DeviceState.OFF),
                electricalService.getAC(1, DeviceState.ON) );
    }

    public void turnOnAC (Movement movement)
            throws ConsumptionLimitExceededException {
        electricalService.turnOnAC(movement.getCorridor().getAC());
    }

    public void turnOnLight (Movement movement)
            throws ConsumptionLimitExceededException {
        electricalService.turnOnLight(movement.getCorridor().getLight());
    }

    public void turnOffAC (Movement movement) {
        electricalService.turnOffAC(movement.getCorridor().getAC());
    }

    public void turnOffLight (Movement movement) {
        electricalService.turnOffLight(movement.getCorridor().getLight());
    }

    public Floor getFloor(int floorId) {
        return storageService.getFloor(floorId);
    }

    public MainCorridor getMainCorridor (Floor floor, int mainCorridorId) {
        return storageService.getMainCorridor(floor, mainCorridorId);
    }

    public SubCorridor getSubCorridor (Floor floor, int subCorridorId) {
        return storageService.getSubCorridor(floor,subCorridorId);
    }

    public boolean isConsumptionWithinLimits() {
        return storageService.isConsumptionWithinLimits();
    }

    public int getConsumptionCost() {
        return storageService.getTotalPowerConsumed();
    }

    public int reduceConsumptionCostFor (ElectricalDevice device) {
        device.setDeviceState(DeviceState.OFF);
        if (device instanceof AC)
            storageService.reduceConsumptionCost(Constants.AC_CONSUMPTION);
        else
            storageService.reduceConsumptionCost(Constants.LIGHT_CONSUMPTION);
        return storageService.getTotalPowerConsumed();
    }
}
