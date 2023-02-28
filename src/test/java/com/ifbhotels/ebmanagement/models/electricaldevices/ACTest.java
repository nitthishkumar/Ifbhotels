package com.ifbhotels.ebmanagement.models.electricaldevices;

import com.ifbhotels.ebmanagement.constants.Constants;
import com.ifbhotels.ebmanagement.enums.DeviceState;
import junit.framework.TestCase;

public class ACTest extends TestCase {

    private AC acInstance;

    @Override
    protected void setUp() throws Exception {
        acInstance = new AC(11, DeviceState.OFF, Constants.AC_CONSUMPTION);
    }

    public void testId() {
        acInstance.setDeviceState(DeviceState.ON);
        assertEquals(11, acInstance.getId());
    }

    public void testACStates () {
        assertEquals(DeviceState.OFF, acInstance.getDeviceState());
        acInstance.setDeviceState(DeviceState.ON);
        assertEquals(DeviceState.ON, acInstance.getDeviceState());

    }

    public void testACConsumption () {
        assertEquals(Constants.AC_CONSUMPTION, acInstance.getConsumptionCost());
    }

    public void testToString() {
        assertEquals("AC 11: OFF", acInstance.toString());
    }

}