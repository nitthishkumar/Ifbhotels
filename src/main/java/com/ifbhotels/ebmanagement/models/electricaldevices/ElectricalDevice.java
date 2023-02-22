package com.ifbhotels.ebmanagement.models.electricaldevices;

import com.ifbhotels.ebmanagement.enums.DeviceState;

public interface ElectricalDevice {

    DeviceState getDeviceState();

    void setDeviceState (DeviceState deviceState);

    int getConsumptionCost ();

    void setConsumptionCost (int consumptionCost);

}
