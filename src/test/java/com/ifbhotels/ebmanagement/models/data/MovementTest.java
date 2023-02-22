package com.ifbhotels.ebmanagement.models.data;

import com.ifbhotels.ebmanagement.models.electricaldevices.AC;
import com.ifbhotels.ebmanagement.models.electricaldevices.Light;
import com.ifbhotels.ebmanagement.models.structures.Corridor;
import com.ifbhotels.ebmanagement.models.structures.Floor;
import com.ifbhotels.ebmanagement.models.structures.SubCorridor;
import junit.framework.TestCase;

import java.util.ArrayList;

import static org.mockito.Mockito.mock;

public class MovementTest extends TestCase {

    private Movement movement;

    @Override
    protected void setUp() throws Exception {
        movement = new Movement(new Floor(1,
                new ArrayList<>()),
                new SubCorridor(1,
                        new Light(new Light.LightBuilder()),
                        new AC(new AC.ACBuilder())));
    }

    public void testMovementEntities () {
        assertEquals(1, movement.getFloor().getId());
        assertEquals(1, movement.getCorridor().getId());
    }
}