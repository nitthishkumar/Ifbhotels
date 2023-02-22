package com.ifbhotels.ebmanagement.services;

import com.ifbhotels.ebmanagement.constants.ElectricalUnitConstants;
import com.ifbhotels.ebmanagement.enums.DeviceState;
import com.ifbhotels.ebmanagement.exceptions.ConsumptionLimitExceededException;
import com.ifbhotels.ebmanagement.models.electricaldevices.AC;
import com.ifbhotels.ebmanagement.models.electricaldevices.Light;

public class ElectricalService {

    private final StorageService storageService = StorageService.getInstance();

    public Light getLight (int lightId,
                           DeviceState deviceState) {
        return new Light.LightBuilder()
                .setId(lightId)
                .setDeviceState(deviceState)
                .setConsumptionCost(ElectricalUnitConstants.LIGHT_CONSUMPTION)
                .build();
    }

    public AC getAC (int acID,
                     DeviceState deviceState) {
        return new AC.ACBuilder()
                .setId(acID)
                .setDeviceState(deviceState)
                .setConsumptionCost(ElectricalUnitConstants.AC_CONSUMPTION)
                .build();
    }

    public void turnOnAC (AC ac)
            throws ConsumptionLimitExceededException {
        storageService.addConsumptionCost(ElectricalUnitConstants.AC_CONSUMPTION);
        ac.setDeviceState(DeviceState.ON);
    }

    public void turnOffAC (AC ac) {
        ac.setDeviceState(DeviceState.OFF);
    }

    public void turnOnLight (Light light)
            throws ConsumptionLimitExceededException {
        storageService.addConsumptionCost(ElectricalUnitConstants.LIGHT_CONSUMPTION);
        light.setDeviceState(DeviceState.ON);
    }

    public void turnOffLight (Light light) {
        light.setDeviceState(DeviceState.OFF);
    }
}
