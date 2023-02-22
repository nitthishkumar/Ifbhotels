package com.ifbhotels.ebmanagement.models.electricaldevices;

import com.ifbhotels.ebmanagement.enums.DeviceState;
import com.ifbhotels.ebmanagement.mocks.ModelMocks;
import junit.framework.TestCase;

public class ElectricalDeviceTest extends TestCase {

    private ElectricalDevice electricalDevice;

    @Override
    protected void setUp() throws Exception {
        electricalDevice = ModelMocks.getElectricalDevice();
    }

    public void testDeviceState () {
        assertEquals(DeviceState.ON, electricalDevice.getDeviceState());
        electricalDevice.setDeviceState(DeviceState.OFF);;
        assertEquals(DeviceState.OFF, electricalDevice.getDeviceState());
    }

    public void testConsumptionCost () {
        assertEquals(1, electricalDevice.getConsumptionCost());
        electricalDevice.setConsumptionCost(2);
        assertEquals(2, electricalDevice.getConsumptionCost());

    }

}