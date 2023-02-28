package com.ifbhotels.ebmanagement.models.structures;

import com.ifbhotels.ebmanagement.constants.Constants;
import com.ifbhotels.ebmanagement.enums.DeviceState;
import com.ifbhotels.ebmanagement.models.electricaldevices.AC;
import com.ifbhotels.ebmanagement.models.electricaldevices.Light;
import junit.framework.TestCase;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;


public class CorridorTest extends TestCase {

    private Corridor corridorInstance;

    @Override
    protected void setUp() throws Exception {
        corridorInstance = new Corridor(121) {
            @NonNull @Getter @Setter
            private int id;
            @NonNull @Getter @Setter
            private Light light;
            @NonNull @Getter @Setter
            private AC AC;
        };
    }

    public void testCorridorLight () {
        corridorInstance.setLight(new Light(1, DeviceState.ON, Constants.LIGHT_CONSUMPTION));
        assertEquals(DeviceState.ON, corridorInstance.getLight().getDeviceState());
        assertEquals(Constants.LIGHT_CONSUMPTION, corridorInstance.getLight().getConsumptionCost());
    }

    public void testCorridorAC () {
        corridorInstance.setAC(new AC(1, DeviceState.ON, Constants.AC_CONSUMPTION));
        assertEquals(DeviceState.ON, corridorInstance.getAC().getDeviceState());
        assertEquals(Constants.AC_CONSUMPTION, corridorInstance.getAC().getConsumptionCost());
    }

    
}