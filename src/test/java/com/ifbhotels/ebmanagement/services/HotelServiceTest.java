package com.ifbhotels.ebmanagement.services;

import com.ifbhotels.ebmanagement.enums.DeviceState;
import com.ifbhotels.ebmanagement.exceptions.ConsumptionLimitExceededException;
import com.ifbhotels.ebmanagement.mocks.HotelServiceMock;
import com.ifbhotels.ebmanagement.mocks.ModelMocks;
import com.ifbhotels.ebmanagement.models.structures.Corridor;
import com.ifbhotels.ebmanagement.models.structures.Floor;
import junit.framework.TestCase;

public class HotelServiceTest extends TestCase {

    private HotelService hotelService;

    @Override
    protected void setUp() throws Exception {
        hotelService = new HotelServiceMock();
    }

    public void testHotelFloors () {
        assertEquals(2, hotelService.getHotel().getFloorList().size());
    }

    public void testIsConsumptionWithinLimits() {
        assertTrue(hotelService.isConsumptionWithinLimits());
    }

    public void testLight () throws ConsumptionLimitExceededException {
        hotelService.turnOnLight(ModelMocks.movement);
        assertTrue(areAllLightsTurnedOn());
        hotelService.turnOffLight(ModelMocks.movement);
        assertTrue(areAllLightsTurnedOff());
    }

    public void testAC () throws ConsumptionLimitExceededException {
        hotelService.turnOnAC(ModelMocks.movement);
        assertTrue(areAllACsTurnedOn());
        hotelService.turnOffAC(ModelMocks.movement);
        assertTrue(areAllACsTurnedOff());
    }

    private boolean areAllLightsTurnedOff() {
        for (Floor floor : hotelService.getHotel().getFloorList()) {
            for (Corridor corridor : floor.getCorridorList()) {
                if (corridor.getLight().getDeviceState() != DeviceState.OFF) return false;
            }
        }
        return true;
    }

    private boolean areAllLightsTurnedOn() {
        for (Floor floor : hotelService.getHotel().getFloorList()) {
            for (Corridor corridor : floor.getCorridorList()) {
                if (corridor.getLight().getDeviceState() != DeviceState.ON) return false;
            }
        }
        return true;
    }

    private boolean areAllACsTurnedOff() {
        for (Floor floor : hotelService.getHotel().getFloorList()) {
            for (Corridor corridor : floor.getCorridorList()) {
                if (corridor.getAC().getDeviceState() != DeviceState.OFF) return false;
            }
        }
        return true;
    }

    private boolean areAllACsTurnedOn() {
        for (Floor floor : hotelService.getHotel().getFloorList()) {
            for (Corridor corridor : floor.getCorridorList()) {
                if (corridor.getAC().getDeviceState() != DeviceState.ON) return false;
            }
        }
        return true;
    }
}