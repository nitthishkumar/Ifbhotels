package com.ifbhotels.ebmanagement.services.storage;

import com.ifbhotels.ebmanagement.exceptions.ConsumptionLimitExceededException;
import com.ifbhotels.ebmanagement.mocks.ModelMocks;
import junit.framework.TestCase;

import java.util.Objects;

import static com.ifbhotels.ebmanagement.enums.StorageServiceType.SIMPLE_STORAGE_TYPE;

public class SimpleStorageServiceTest extends TestCase {

    private StorageService storageService;

    public void setUp() throws Exception {
        super.setUp();
        storageService = StorageServiceFactory.getStorageService(SIMPLE_STORAGE_TYPE);
        Objects.requireNonNull(storageService).setHotel(ModelMocks.hotel);
    }

    public void testGetInstance() {
        assertEquals(storageService, StorageServiceFactory.getStorageService(SIMPLE_STORAGE_TYPE));
    }

    public void testGetFloor() {
        assertEquals(storageService.getFloor(11), ModelMocks.getFloor());
    }

    public void testGetMainCorridor() {
        assertEquals(ModelMocks.getMainCorridor(),
                storageService.getMainCorridor(ModelMocks.floor, ModelMocks.getMainCorridor().getId()));
    }

    public void testGetSubCorridor() {
        assertEquals(ModelMocks.getSubCorridor(),
                storageService.getSubCorridor(ModelMocks.floor, ModelMocks.getSubCorridor().getId()));
    }

    public void testIsConsumptionWithinLimits() {
        assertTrue(storageService.isConsumptionWithinLimits());
        storageService.setTotalPowerConsumed(Integer.MAX_VALUE);
        assertFalse(storageService.isConsumptionWithinLimits());
    }

    public void testAddConsumptionCost() throws ConsumptionLimitExceededException {
        assertEquals(0, storageService.getTotalPowerConsumed());
        storageService.addConsumptionCost(9);
        assertEquals(9, storageService.getTotalPowerConsumed());
    }

    public void testReduceConsumptionCost() throws ConsumptionLimitExceededException {
        storageService.reduceConsumptionCost(storageService.getTotalPowerConsumed());
        storageService.addConsumptionCost(9);
        storageService.reduceConsumptionCost(3);
        assertEquals(6, storageService.getTotalPowerConsumed());
    }

    public void testMaxConsumptionAllowed() {
        assertEquals(50, storageService.maxConsumptionAllowed());
    }

    public void testTotalMainCorridors() {
        assertEquals(2, storageService.totalMainCorridors());
    }

    public void testTotalSubCorridors() {
        assertEquals(2, storageService.totalSubCorridors());
    }

    public void testGetTotalPowerConsumed() {
        assertEquals(19, storageService.getTotalPowerConsumed());
        storageService.setTotalPowerConsumed(49);
        assertEquals(49, storageService.getTotalPowerConsumed());
    }

    public void testGetHotel() {
        assertEquals(ModelMocks.hotel, storageService.getHotel());
    }

    public void testSetTotalPowerConsumed() {
        storageService.setTotalPowerConsumed(19);
        assertEquals(19, storageService.getTotalPowerConsumed());
    }

    public void testSetHotel() {
        assertNotNull(storageService.getHotel());
        storageService.setHotel(null);
        assertNull(storageService.getHotel());
    }
}