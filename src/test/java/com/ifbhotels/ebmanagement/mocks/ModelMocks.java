package com.ifbhotels.ebmanagement.mocks;

import com.ifbhotels.ebmanagement.enums.DeviceState;
import com.ifbhotels.ebmanagement.models.data.Movement;
import com.ifbhotels.ebmanagement.models.electricaldevices.AC;
import com.ifbhotels.ebmanagement.models.electricaldevices.ElectricalDevice;
import com.ifbhotels.ebmanagement.models.electricaldevices.Light;
import com.ifbhotels.ebmanagement.models.structures.Corridor;
import com.ifbhotels.ebmanagement.models.structures.Floor;
import com.ifbhotels.ebmanagement.models.structures.MainCorridor;
import com.ifbhotels.ebmanagement.models.structures.SubCorridor;
import lombok.Getter;

import java.util.Arrays;

import static com.ifbhotels.ebmanagement.constants.ElectricalUnitConstants.AC_CONSUMPTION;
import static com.ifbhotels.ebmanagement.constants.ElectricalUnitConstants.LIGHT_CONSUMPTION;

public class ModelMocks {

    @Getter
    public static final ElectricalDevice electricalDevice = new ElectricalDevice(1) {

        private DeviceState deviceState = DeviceState.ON;
        private int consumptionCost = 1;

        @Override
        public DeviceState getDeviceState() {
            return deviceState;
        }

        @Override
        public void setDeviceState(DeviceState deviceState) {
            this.deviceState = deviceState;
        }

        @Override
        public int getConsumptionCost() {
            return this.consumptionCost;
        }

        @Override
        public void setConsumptionCost(int _consumptionCost) {
            this.consumptionCost = _consumptionCost;
        }
    };

    @Getter
    public static final Light light = new Light(1, DeviceState.ON, LIGHT_CONSUMPTION);

    @Getter
    public static final AC aC = new AC(1, DeviceState.ON, AC_CONSUMPTION);

    @Getter
    public static final MainCorridor mainCorridor = new MainCorridor(11, light, aC);

    @Getter
    public static final SubCorridor subCorridor = new SubCorridor(10, light, aC);

    @Getter
    public static final Corridor corridor = mainCorridor;

    @Getter
    public static Floor floor = new Floor(11, Arrays.asList(mainCorridor, subCorridor));

    @Getter
    public static Movement movement = new Movement(floor, corridor);
}
