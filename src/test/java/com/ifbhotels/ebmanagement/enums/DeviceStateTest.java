package com.ifbhotels.ebmanagement.enums;

import junit.framework.TestCase;

public class DeviceStateTest extends TestCase {

    public void testAllStates() {
        final DeviceState onState = DeviceState.ON;
        final DeviceState offState = DeviceState.OFF;
        assertEquals("ON", onState.toString());
        assertEquals("OFF", offState.toString());
    }
}