package com.ifbhotels.ebmanagement.models.structures;

import com.ifbhotels.ebmanagement.models.electricaldevices.AC;
import junit.framework.TestCase;

import java.util.ArrayList;

import static org.mockito.Mockito.mock;

public class FloorTest extends TestCase {

    private Floor floor;

    @Override
    protected void setUp() throws Exception {
        floor = new Floor(1, new ArrayList<>());
    }

    public void testFloor() {
        assertEquals(1, floor.getId());
        floor.getCorridorList().add(mock(MainCorridor.class));
        floor.getCorridorList().add(mock(SubCorridor.class));
        floor.getCorridorList().add(mock(SubCorridor.class));
        assertEquals(3, floor.getCorridorList().size());
    }
}