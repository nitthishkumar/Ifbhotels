package com.ifbhotels.ebmanagement.services;

import com.ifbhotels.ebmanagement.enums.DeviceState;
import com.ifbhotels.ebmanagement.enums.StorageServiceType;
import com.ifbhotels.ebmanagement.exceptions.ConsumptionLimitExceededException;
import com.ifbhotels.ebmanagement.mocks.ModelMocks;
import com.ifbhotels.ebmanagement.services.storage.StorageService;
import com.ifbhotels.ebmanagement.services.storage.StorageServiceFactory;
import junit.framework.TestCase;

public class ElectricalServiceTest extends TestCase {

    private ElectricalService electricalService;
    private StorageService storageService;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        electricalService = new ElectricalService();
        storageService = StorageServiceFactory.getStorageService(StorageServiceType.SIMPLE_STORAGE_TYPE);
        storageService.setHotel(ModelMocks.hotel);
    }

    public void testGetLight() {
        assertEquals(ModelMocks.getLight(),
                electricalService.getLight(ModelMocks.getLight().getId(), ModelMocks.getLight().getDeviceState()));
    }

    public void testGetAC() {
        assertEquals(ModelMocks.getAC(),
                electricalService.getAC(ModelMocks.getAC().getId(), ModelMocks.getAC().getDeviceState()));
    }

    public void testTurnOnAC() throws ConsumptionLimitExceededException {
        electricalService.turnOnAC(ModelMocks.aC);
        assertEquals(ModelMocks.getAC().getDeviceState(), DeviceState.ON);
    }

    public void testTurnOffAC() {
        electricalService.turnOffAC(ModelMocks.aC);
        assertEquals(ModelMocks.getAC().getDeviceState(), DeviceState.OFF);
    }

    public void testTurnOnLight() throws ConsumptionLimitExceededException {
        electricalService.turnOnLight(ModelMocks.light);
        assertEquals(ModelMocks.getLight().getDeviceState(), DeviceState.ON);
    }

    public void testTurnOffLight() {
        electricalService.turnOffLight(ModelMocks.light);
        assertEquals(ModelMocks.getLight().getDeviceState(), DeviceState.OFF);
    }
}