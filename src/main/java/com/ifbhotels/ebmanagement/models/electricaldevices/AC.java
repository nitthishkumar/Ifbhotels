package com.ifbhotels.ebmanagement.models.electricaldevices;

import com.ifbhotels.ebmanagement.enums.DeviceState;
import com.ifbhotels.ebmanagement.models.structures.Hotel;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.Objects;

public class AC implements ElectricalDevice {

    @NonNull @Getter
    private final int id;

    @NonNull @Getter @Setter
    private DeviceState deviceState;

    @NonNull @Getter @Setter
    private int consumptionCost;

    public AC (ACBuilder builder) {
        this.id = builder.id;
        this.deviceState = builder.deviceState;
        this.consumptionCost = builder.consumptionCost;
    }

    public AC (int id,
               DeviceState deviceState,
               int consumptionCost) {
        this.id = id;
        this.consumptionCost = consumptionCost;
        this.deviceState = deviceState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AC ac = (AC) o;
        return id == ac.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.format("AC %d: %s", id, deviceState );
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
