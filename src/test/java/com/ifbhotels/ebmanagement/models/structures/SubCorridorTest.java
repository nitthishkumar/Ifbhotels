package com.ifbhotels.ebmanagement.models.structures;

import com.ifbhotels.ebmanagement.constants.ElectricalUnitConstants;
import com.ifbhotels.ebmanagement.enums.DeviceState;
import com.ifbhotels.ebmanagement.models.electricaldevices.AC;
import com.ifbhotels.ebmanagement.models.electricaldevices.Light;
import junit.framework.TestCase;

import static org.mockito.Mockito.mock;

public class SubCorridorTest extends TestCase {

    private SubCorridor subCorridor;

    @Override
    protected void setUp() throws Exception {
        subCorridor = new SubCorridor(1, mock(Light.class), mock(AC.class) );
    }

    public void testSubCorridorLight () {
        subCorridor.setLight(new Light(1, DeviceState.ON, ElectricalUnitConstants.LIGHT_CONSUMPTION));
        assertEquals(DeviceState.ON, subCorridor.getLight().getDeviceState());
        assertEquals(ElectricalUnitConstants.LIGHT_CONSUMPTION, subCorridor.getLight().getConsumptionCost());
    }

    public void testSubCorridorAC () {
        subCorridor.setAC(new AC(1, DeviceState.ON, ElectricalUnitConstants.AC_CONSUMPTION));
        assertEquals(DeviceState.ON, subCorridor.getAC().getDeviceState());
        assertEquals(ElectricalUnitConstants.AC_CONSUMPTION, subCorridor.getAC().getConsumptionCost());
    }

}