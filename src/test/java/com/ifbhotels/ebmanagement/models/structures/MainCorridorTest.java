package com.ifbhotels.ebmanagement.models.structures;

import com.ifbhotels.ebmanagement.constants.Constants;
import com.ifbhotels.ebmanagement.enums.DeviceState;
import com.ifbhotels.ebmanagement.models.electricaldevices.AC;
import com.ifbhotels.ebmanagement.models.electricaldevices.Light;
import junit.framework.TestCase;

import static org.mockito.Mockito.mock;

public class MainCorridorTest extends TestCase {

    private MainCorridor mainCorridor;

    @Override
    protected void setUp() throws Exception {
        mainCorridor = new MainCorridor(1, mock(Light.class), mock(AC.class) );
    }

    public void testMainCorridorLight () {
        mainCorridor.setLight(new Light(1, DeviceState.ON, Constants.LIGHT_CONSUMPTION));
        assertEquals(DeviceState.ON, mainCorridor.getLight().getDeviceState());
        assertEquals(Constants.LIGHT_CONSUMPTION, mainCorridor.getLight().getConsumptionCost());
    }

    public void testMainCorridorAC () {
        mainCorridor.setAC(new AC(1, DeviceState.ON, Constants.AC_CONSUMPTION));
        assertEquals(DeviceState.ON, mainCorridor.getAC().getDeviceState());
        assertEquals(Constants.AC_CONSUMPTION, mainCorridor.getAC().getConsumptionCost());
    }
}