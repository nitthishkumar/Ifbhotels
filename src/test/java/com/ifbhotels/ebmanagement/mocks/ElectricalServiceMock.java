package com.ifbhotels.ebmanagement.mocks;

import com.ifbhotels.ebmanagement.enums.DeviceState;
import com.ifbhotels.ebmanagement.exceptions.ConsumptionLimitExceededException;
import com.ifbhotels.ebmanagement.models.electricaldevices.AC;
import com.ifbhotels.ebmanagement.models.electricaldevices.Light;
import com.ifbhotels.ebmanagement.services.ElectricalService;

public class ElectricalServiceMock extends ElectricalService {

    public void turnOnAC (AC ac)
            throws ConsumptionLimitExceededException {
        ac.setDeviceState(DeviceState.ON);
    }

    public void turnOffAC (AC ac) {
        ac.setDeviceState(DeviceState.OFF);
    }

    public void turnOnLight (Light light)
            throws ConsumptionLimitExceededException {
        light.setDeviceState(DeviceState.ON);
    }

    public void turnOffLight (Light light) {
        light.setDeviceState(DeviceState.OFF);
    }

}
