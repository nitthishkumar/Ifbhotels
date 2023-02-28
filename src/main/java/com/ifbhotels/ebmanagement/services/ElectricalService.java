package com.ifbhotels.ebmanagement.services;

import com.ifbhotels.ebmanagement.constants.Constants;
import com.ifbhotels.ebmanagement.enums.DeviceState;
import com.ifbhotels.ebmanagement.exceptions.ConsumptionLimitExceededException;
import com.ifbhotels.ebmanagement.models.electricaldevices.AC;
import com.ifbhotels.ebmanagement.models.electricaldevices.Light;
import com.ifbhotels.ebmanagement.services.storage.StorageService;
import com.ifbhotels.ebmanagement.services.storage.StorageServiceFactory;

import static com.ifbhotels.ebmanagement.enums.StorageServiceType.SIMPLE_STORAGE_TYPE;

public class ElectricalService {

    private final StorageService storageService;

    public ElectricalService() {
        storageService = StorageServiceFactory.getStorageService(SIMPLE_STORAGE_TYPE);
    }

    public Light getLight(int lightId,
                           DeviceState deviceState) {
        return new Light.LightBuilder()
                .setId(lightId)
                .setDeviceState(deviceState)
                .setConsumptionCost(Constants.LIGHT_CONSUMPTION)
                .build();
    }

    public AC getAC(int acID,
                     DeviceState deviceState) {
        return new AC.ACBuilder()
                .setId(acID)
                .setDeviceState(deviceState)
                .setConsumptionCost(Constants.AC_CONSUMPTION)
                .build();
    }

    public void turnOnAC(AC ac)
            throws ConsumptionLimitExceededException {
        storageService.addConsumptionCost(Constants.AC_CONSUMPTION);
        ac.setDeviceState(DeviceState.ON);
    }

    public void turnOffAC(AC ac) {
        ac.setDeviceState(DeviceState.OFF);
    }

    public void turnOnLight(Light light)
            throws ConsumptionLimitExceededException {
        storageService.addConsumptionCost(Constants.LIGHT_CONSUMPTION);
        light.setDeviceState(DeviceState.ON);
    }

    public void turnOffLight(Light light) {
        light.setDeviceState(DeviceState.OFF);
    }
}
