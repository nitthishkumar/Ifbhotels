package com.ifbhotels.ebmanagement.models.electricaldevices;

import com.ifbhotels.ebmanagement.enums.DeviceState;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.Objects;

public class AC extends ElectricalDevice {

    public AC (ACBuilder builder) {
        super(builder.id);
        setConsumptionCost(builder.getConsumptionCost());
        setDeviceState(builder.getDeviceState());

    }

    public AC (int id,
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
        AC other = (AC) o;
        return this.getId() == other.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return String.format("AC %d: %s", getId(), getDeviceState() );
    }

    public static class ACBuilder {

        @NonNull @Getter
        private int id;
        @NonNull @Getter
        private DeviceState deviceState;
        @NonNull @Getter
        private int consumptionCost;

        public ACBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public ACBuilder setDeviceState(DeviceState deviceState) {
            this.deviceState = deviceState;
            return this;
        }

        public ACBuilder setConsumptionCost(int consumptionCost) {
            this.consumptionCost = consumptionCost;
            return this;
        }

        public AC build () {
            return new AC(this);
        }

    }
}
