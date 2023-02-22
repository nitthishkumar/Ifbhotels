package com.ifbhotels.ebmanagement.mocks;

import com.ifbhotels.ebmanagement.enums.DeviceState;
import com.ifbhotels.ebmanagement.exceptions.ConsumptionLimitExceededException;
import com.ifbhotels.ebmanagement.models.data.Movement;
import com.ifbhotels.ebmanagement.models.structures.Hotel;
import com.ifbhotels.ebmanagement.services.HotelService;

import java.util.Arrays;
import java.util.Collections;

public class HotelServiceMock extends HotelService {

    private Hotel hotel;

    public HotelServiceMock() {
        hotel =  new Hotel(Arrays.asList(ModelMocks.getFloor(), ModelMocks.getFloor()));
    }

    @Override
    public Hotel createHotel (int floors,
                              int mainCorridorPerFloor,
                              int subCorridorPerFloor) {
        hotel = new Hotel(Arrays.asList(ModelMocks.getFloor(), ModelMocks.getFloor()));
        return hotel;
    }

    @Override
    public Hotel getHotel () {
        return hotel;
    }

    @Override
    public boolean isConsumptionWithinLimits() {
        return true;
    }

    public void turnOnAC (Movement movement)
            throws ConsumptionLimitExceededException {
        hotel.getFloorList().forEach(floor -> {
            floor.getCorridorList().forEach(corridor -> {
                corridor.getAC().setDeviceState(DeviceState.ON);
            });
        });
    }

    public void turnOnLight (Movement movement)
            throws ConsumptionLimitExceededException {
        hotel.getFloorList().forEach(floor -> {
            floor.getCorridorList().forEach(corridor -> {
                corridor.getLight().setDeviceState(DeviceState.ON);
            });
        });
    }

    public void turnOffAC (Movement movement) {
        hotel.getFloorList().forEach(floor -> {
            floor.getCorridorList().forEach(corridor -> {
                corridor.getAC().setDeviceState(DeviceState.OFF);
            });
        });
    }

    public void turnOffLight (Movement movement) {
        hotel.getFloorList().forEach(floor -> {
            floor.getCorridorList().forEach(corridor -> {
                corridor.getLight().setDeviceState(DeviceState.OFF);
            });
        });
    }



}
