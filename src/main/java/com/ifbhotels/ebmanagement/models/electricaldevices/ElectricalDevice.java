package com.ifbhotels.ebmanagement.models.electricaldevices;

import com.ifbhotels.ebmanagement.enums.DeviceState;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

public abstract class ElectricalDevice {

    @NonNull @Getter
    private final int id;

    @NonNull @Getter @Setter
    private DeviceState deviceState;

    @NonNull @Getter @Setter
    private int consumptionCost;

    protected ElectricalDevice(int id) {
        this.id = id;
    }
}
