package com.ifbhotels.ebmanagement.models.electricaldevices;

import com.ifbhotels.ebmanagement.enums.DeviceState;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.Objects;

public class Light implements ElectricalDevice {

    @NonNull @Getter
    private final int id;

    @NonNull @Getter @Setter
    private DeviceState deviceState;

    @NonNull @Getter @Setter
    private int consumptionCost;

    public Light (final int id, final DeviceState deviceState, final int consumptionCost) {
        this.id = id;
        this.deviceState = deviceState;
        this.consumptionCost = consumptionCost;
    }

    public Light (LightBuilder lightBuilder) {
        this.id = lightBuilder.getId();
        this.consumptionCost = lightBuilder.getConsumptionCost();
        this.deviceState = lightBuilder.getDeviceState();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Light light = (Light) o;
        return id == light.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.format("Light %d: %s", id, deviceState );
    }

    public static class LightBuilder {

        @NonNull @Getter
        private int id;
        @NonNull @Getter
        private DeviceState deviceState;
        @NonNull @Getter
        private int consumptionCost;

        public LightBuilder(){}

        public LightBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public LightBuilder setDeviceState(DeviceState deviceState) {
            this.deviceState = deviceState;
            return this;
        }

        public LightBuilder setConsumptionCost(int consumptionCost) {
            this.consumptionCost = consumptionCost;
            return this;
        }

        public Light build () {
            return new Light(this);
        }

    }

}
