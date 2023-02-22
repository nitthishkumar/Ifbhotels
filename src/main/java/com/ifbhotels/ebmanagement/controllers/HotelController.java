package com.ifbhotels.ebmanagement.controllers;

import com.ifbhotels.ebmanagement.enums.DeviceState;
import com.ifbhotels.ebmanagement.exceptions.ConsumptionLimitExceededException;
import com.ifbhotels.ebmanagement.models.data.Movement;
import com.ifbhotels.ebmanagement.models.electricaldevices.AC;
import com.ifbhotels.ebmanagement.models.structures.*;
import com.ifbhotels.ebmanagement.services.HotelService;

public class HotelController {

    private final HotelService hotelService;

    public HotelController() {
        hotelService = new HotelService();
    }

    public void createHotel (int floors,
                              int mainCorridorPerFloor,
                              int subCorridorPerFloor) {
        hotelService.createHotel(floors,
                mainCorridorPerFloor,
                subCorridorPerFloor);
    }

    public Hotel getHotel() {
        return hotelService.getHotel();
    }

    public void turnOnAC (Movement movement) {
        try {
            hotelService.turnOnAC(movement);
        } catch (ConsumptionLimitExceededException e) {

        }
    }

    public void turnOnLight (Movement movement) {
        try {
            hotelService.turnOnLight(movement);
        } catch (ConsumptionLimitExceededException e) {
        }
    }

    public void turnOffLight (Movement movement) {
        hotelService.turnOffLight(movement);
    }

    public void turnOffAC (Movement movement) {
        hotelService.turnOffAC(movement);
    }

    public Floor getFloor(int floorId) {
        return hotelService.getFloor(floorId);
    }

    public MainCorridor getMainCorridor (Floor floor, int mainCorridorId) {
        return hotelService.getMainCorridor(floor, mainCorridorId);
    }

    public SubCorridor getSubCorridor (Floor floor, int subCorridorId) {
        return hotelService.getSubCorridor(floor,subCorridorId);
    }

    public void controlPowerConsumption (Movement movement) {
        while (!hotelService.isConsumptionWithinLimits()) {
            for (Floor floor : hotelService.getHotel().getFloorList()) {
                if (floor.getId() != movement.getFloor().getId() ) {
                    controlPowerConsumptionOn(floor);
                    if (hotelService.isConsumptionWithinLimits()) return;
                }
            }
        }
    }

    public void controlPowerConsumptionOn (Floor floor) {
        for (Corridor corridor : floor.getCorridorList()) {
            if (corridor instanceof SubCorridor) controlPowerConsumptionOn(corridor);
        }
    }

    public void controlPowerConsumptionOn (Corridor corridor) {
        hotelService.reduceConsumptionCostFor(corridor.getAC());
        if (hotelService.isConsumptionWithinLimits() ) return;
        hotelService.reduceConsumptionCostFor(corridor.getLight());
    }

    public int getTotalPowerConsumed () {
        return hotelService.getConsumptionCost();
    }

}
