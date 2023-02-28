package com.ifbhotels.ebmanagement.controllers;

import com.ifbhotels.ebmanagement.enums.DeviceState;
import com.ifbhotels.ebmanagement.enums.StorageServiceType;
import com.ifbhotels.ebmanagement.mocks.ModelMocks;
import com.ifbhotels.ebmanagement.models.structures.Hotel;
import com.ifbhotels.ebmanagement.services.storage.StorageService;
import com.ifbhotels.ebmanagement.services.storage.StorageServiceFactory;
import junit.framework.TestCase;

import java.util.stream.Collectors;

public class HotelControllerTest extends TestCase {

    private HotelController hotelController;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        hotelController = new HotelController();
        StorageService storageService =
                StorageServiceFactory.getStorageService(StorageServiceType.SIMPLE_STORAGE_TYPE);
        storageService.setHotel(ModelMocks.hotel);
    }

    public void testCreateHotel() {
        hotelController.createHotel(ModelMocks.getHotel().getFloorList().size(), 1, 1);
        assertNotNull(hotelController.getHotel());
    }

    public void testGetHotel() {
        Hotel hotel = hotelController.createHotel(ModelMocks.getHotel().getFloorList().size(), 1, 1);
        assertEquals(hotel, hotelController.getHotel());
    }

    public void testTurnOnAC() {
        hotelController.turnOnAC(ModelMocks.movement);
        assertEquals(DeviceState.ON, ModelMocks.getAC().getDeviceState());

    }

    public void testTurnOnLight() {
        hotelController.turnOnLight(ModelMocks.movement);
        assertEquals(DeviceState.ON, ModelMocks.getLight().getDeviceState());
    }

    public void testTurnOffLight() {
        hotelController.turnOffLight(ModelMocks.movement);
        assertEquals(DeviceState.OFF, ModelMocks.getLight().getDeviceState());
    }

    public void testTurnOffAC() {
        hotelController.turnOffAC(ModelMocks.movement);
        assertEquals(DeviceState.OFF, ModelMocks.getAC().getDeviceState());
    }

    public void testGetFloor() {
        assertEquals(ModelMocks.floor, hotelController.getFloor(ModelMocks.getFloor().getId()) );
    }

    public void testGetMainCorridor() {
        assertEquals(ModelMocks.mainCorridor,
                hotelController.getMainCorridor(ModelMocks.floor, ModelMocks.mainCorridor.getId()) );
    }

    public void testGetSubCorridor() {
        assertEquals(ModelMocks.subCorridor,
                hotelController.getSubCorridor(ModelMocks.floor, ModelMocks.subCorridor.getId()) );
    }

    public void testIdleFloors() {
        assertEquals(hotelController.idleFloors(ModelMocks.movement), ModelMocks
                .hotel
                .getFloorList()
                .stream()
                .filter(floor -> !floor.equals(ModelMocks.movement.getFloor()))
                .collect(Collectors.toList()) );
    }

    public void testControlPowerConsumption() {
        hotelController.controlPowerConsumption(ModelMocks.movement);
        assertEquals(0, hotelController.getTotalPowerConsumed());
    }

    public void testControlPowerConsumptionOnFloor() {
        hotelController.controlPowerConsumptionOnFloor(ModelMocks.floor);
        assertEquals(0, hotelController.getTotalPowerConsumed());
    }

    public void testControlPowerConsumptionOnCorridor() {
        hotelController.controlPowerConsumptionOnCorridor(ModelMocks.corridor);
        assertEquals(0, hotelController.getTotalPowerConsumed());
    }

    public void testGetTotalPowerConsumed() {
        assertEquals(0, hotelController.getTotalPowerConsumed());
    }
}