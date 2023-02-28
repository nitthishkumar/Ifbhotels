package com.ifbhotels.ebmanagement.services;

import com.ifbhotels.ebmanagement.constants.Constants;
import com.ifbhotels.ebmanagement.enums.DeviceState;
import com.ifbhotels.ebmanagement.enums.StorageServiceType;
import com.ifbhotels.ebmanagement.exceptions.ConsumptionLimitExceededException;
import com.ifbhotels.ebmanagement.mocks.ModelMocks;
import com.ifbhotels.ebmanagement.models.structures.Hotel;
import com.ifbhotels.ebmanagement.services.storage.StorageService;
import com.ifbhotels.ebmanagement.services.storage.StorageServiceFactory;
import junit.framework.TestCase;

public class HotelServiceTest extends TestCase {

    private StorageService storageService;

    private HotelService hotelService;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        storageService = StorageServiceFactory.getStorageService(StorageServiceType.SIMPLE_STORAGE_TYPE);
        hotelService = new HotelService();
        storageService.setHotel(ModelMocks.hotel);
    }

    public void testCreateHotel() {
        Hotel createdHotel = hotelService.createHotel(ModelMocks.getHotel().getFloorList().size(), 1, 1);
        assertEquals(createdHotel, storageService.getHotel());
    }

    public void testGetHotel() {
        assertEquals(hotelService.getHotel(), ModelMocks.hotel);
    }

    public void testTurnOnAC() throws ConsumptionLimitExceededException {
        hotelService.turnOnAC(ModelMocks.movement);
        assertEquals(ModelMocks.getAC().getDeviceState(), DeviceState.ON);
    }

    public void testTurnOnLight() throws ConsumptionLimitExceededException {
        hotelService.turnOnLight(ModelMocks.movement);
        assertEquals(ModelMocks.getLight().getDeviceState(), DeviceState.ON);

    }

    public void testTurnOffAC() {
        hotelService.turnOffAC(ModelMocks.movement);
        assertEquals(ModelMocks.getAC().getDeviceState(), DeviceState.OFF);
    }

    public void testTurnOffLight() {
        hotelService.turnOffLight(ModelMocks.movement);
        assertEquals(ModelMocks.getLight().getDeviceState(), DeviceState.OFF);
    }

    public void testGetFloor() {
        assertEquals(ModelMocks.getFloor(), hotelService.getFloor(ModelMocks.floor.getId()) );
    }

    public void testGetMainCorridor() {
        assertEquals(ModelMocks.getMainCorridor(),
                hotelService.getMainCorridor(ModelMocks.floor, ModelMocks.mainCorridor.getId()));
    }

    public void testGetSubCorridor() {
        assertEquals(ModelMocks.getSubCorridor(),
                hotelService.getSubCorridor(ModelMocks.floor, ModelMocks.subCorridor.getId()));
    }

    public void testIsConsumptionWithinLimits() {
        assertTrue(hotelService.isConsumptionWithinLimits());
    }

    public void testGetConsumptionCost() throws ConsumptionLimitExceededException {
        assertEquals(0, hotelService.getConsumptionCost());
        testTurnOnAC();
        assertEquals(Constants.AC_CONSUMPTION, hotelService.getConsumptionCost());
    }

    public void testReduceConsumptionCostFor() {
        assertEquals(0, hotelService.getConsumptionCost());
        hotelService.reduceConsumptionCostFor(ModelMocks.aC);
        assertEquals(0, hotelService.getConsumptionCost());
    }
}