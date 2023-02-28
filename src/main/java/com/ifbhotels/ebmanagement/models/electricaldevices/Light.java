package com.ifbhotels.ebmanagement.models.electricaldevices;

import com.ifbhotels.ebmanagement.enums.DeviceState;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.Objects;

public class Light extends ElectricalDevice {

    public Light (Light.LightBuilder builder) {
        super(builder.id);
        setConsumptionCost(builder.getConsumptionCost());
        setDeviceState(builder.getDeviceState());

    }

    public Light (int id,
               DeviceState deviceState,
               int consumptionCost) {
        super(id);
        setConsumptionCost(consumptionCost);
        setDeviceState(deviceState);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Light other = (Light) o;
        return this.getId() == other.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return String.format("Light %d: %s", getId(), getDeviceState() );
    }

    public static class LightBuilder {

        @NonNull @Getter @Setter
        private int id;
        @NonNull @Getter @Setter
        private DeviceState deviceState;
        @NonNull @Getter @Setter
        private int consumptionCost;


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
