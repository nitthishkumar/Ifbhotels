package com.ifbhotels.ebmanagement.models.electricaldevices;

import com.ifbhotels.ebmanagement.constants.Constants;
import com.ifbhotels.ebmanagement.enums.DeviceState;
import junit.framework.TestCase;

public class LightTest extends TestCase {

    private Light lightInstance;

    @Override
    protected void setUp() throws Exception {
        lightInstance = new Light(1, DeviceState.OFF, Constants.LIGHT_CONSUMPTION);
    }

    public void testId() {
        lightInstance.setDeviceState(DeviceState.ON);
        assertEquals(1, lightInstance.getId());
    }

    public void testLightStates () {
        assertEquals(DeviceState.OFF, lightInstance.getDeviceState());
        lightInstance.setDeviceState(DeviceState.ON);
        assertEquals(DeviceState.ON, lightInstance.getDeviceState());

    }

    public void testLightConsumption () {
        assertEquals(Constants.LIGHT_CONSUMPTION, lightInstance.getConsumptionCost());
    }

    public void testToString() {
        assertEquals("Light 1: OFF", lightInstance.toString());
    }

}