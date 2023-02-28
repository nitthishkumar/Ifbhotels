package com.ifbhotels.ebmanagement.controllers;

import com.ifbhotels.ebmanagement.exceptions.ConsumptionLimitExceededException;
import com.ifbhotels.ebmanagement.models.data.Movement;
import com.ifbhotels.ebmanagement.models.structures.*;
import com.ifbhotels.ebmanagement.services.HotelService;

import java.util.List;
import java.util.stream.Collectors;

public class HotelController {

    private final HotelService hotelService;

    public HotelController() {
        hotelService = new HotelService();
    }

    public Hotel createHotel(int floors,
                              int mainCorridorPerFloor,
                              int subCorridorPerFloor) {
        return hotelService.createHotel(floors,
                mainCorridorPerFloor,
                subCorridorPerFloor);
    }

    public Hotel getHotel() {
        return hotelService.getHotel();
    }

    public void turnOnAC(Movement movement) {
        try {
            hotelService.turnOnAC(movement);
        } catch (ConsumptionLimitExceededException e) {
            System.out.println(e + ". Controlling power consumption");
            controlPowerConsumption(movement);
        }
    }

    public void turnOnLight(Movement movement) {
        try {
            hotelService.turnOnLight(movement);
        } catch (ConsumptionLimitExceededException e) {
            controlPowerConsumption(movement);
            System.out.println(e + ". Controlling power consumption");
        }
    }

    public void turnOffLight(Movement movement) {
        hotelService.turnOffLight(movement);
    }

    public void turnOffAC(Movement movement) {
        hotelService.turnOffAC(movement);
    }

    public Floor getFloor(int floorId) {
        return hotelService.getFloor(floorId);
    }

    public MainCorridor getMainCorridor(Floor floor, int mainCorridorId) {
        return hotelService.getMainCorridor(floor, mainCorridorId);
    }

    public SubCorridor getSubCorridor(Floor floor, int subCorridorId) {
        return hotelService.getSubCorridor(floor, subCorridorId);
    }

    public List<Floor> idleFloors(Movement movement) {
        return getFloorList()
                .stream()
                .filter(floor -> !floor.equals(movement.getFloor()))
                .collect(Collectors.toList());
    }

    public void controlPowerConsumption(Movement movement) {
        idleFloors(movement)
                .forEach(this::controlPowerConsumptionOnFloor);
    }

    private List<Floor> getFloorList() {
        return hotelService.getHotel().getFloorList();
    }

    public void controlPowerConsumptionOnFloor(Floor floor) {
        floor.getCorridorList()
                .forEach(this::controlPowerConsumptionOnCorridor);
    }

    public void controlPowerConsumptionOnCorridor(Corridor corridor) {
        hotelService.reduceConsumptionCostFor(corridor.getAC());
        if (hotelService.isConsumptionWithinLimits() ) return;
        hotelService.reduceConsumptionCostFor(corridor.getLight());
    }

    public int getTotalPowerConsumed () {
        return hotelService.getConsumptionCost();
    }

}
