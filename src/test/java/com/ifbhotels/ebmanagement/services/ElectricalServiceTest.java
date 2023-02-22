package com.ifbhotels.ebmanagement.services;

import com.ifbhotels.ebmanagement.enums.DeviceState;
import com.ifbhotels.ebmanagement.exceptions.ConsumptionLimitExceededException;
import com.ifbhotels.ebmanagement.mocks.ElectricalServiceMock;
import com.ifbhotels.ebmanagement.mocks.ModelMocks;
import com.ifbhotels.ebmanagement.models.electricaldevices.AC;
import com.ifbhotels.ebmanagement.models.electricaldevices.Light;
import junit.framework.TestCase;

public class ElectricalServiceTest extends TestCase {
    
    private ElectricalService electricalService;

    @Override
    protected void setUp() throws Exception {
        electricalService = new ElectricalServiceMock();
    }


    public void testAC() throws ConsumptionLimitExceededException {
        AC ac = ModelMocks.getAC();
        electricalService.turnOnAC(ac);
        assertEquals(DeviceState.ON, ac.getDeviceState());
        electricalService.turnOffAC(ac);
        assertEquals(DeviceState.OFF, ac.getDeviceState());
    }

    public void testLight() throws ConsumptionLimitExceededException {
        Light light = ModelMocks.getLight();
        electricalService.turnOnLight(light);
        assertEquals(DeviceState.ON, light.getDeviceState());
        electricalService.turnOffLight(light);
        assertEquals(DeviceState.OFF, light.getDeviceState());
    }

}