package com.ifbhotels.ebmanagement.constants;

import junit.framework.TestCase;

public class ConstantsTest extends TestCase {

    public void testAllConstants () {
        assertEquals(5, Constants.LIGHT_CONSUMPTION);
        assertEquals(10, Constants.AC_CONSUMPTION);
    }

}