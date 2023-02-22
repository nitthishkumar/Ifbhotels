package com.ifbhotels.ebmanagement.constants;

import junit.framework.TestCase;

public class ElectricalUnitConstantsTest extends TestCase {

    public void testAllConstants () {
        assertEquals(5, ElectricalUnitConstants.LIGHT_CONSUMPTION);
        assertEquals(10, ElectricalUnitConstants.AC_CONSUMPTION);
    }

}