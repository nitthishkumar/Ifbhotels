package com.ifbhotels.ebmanagement.models.structures;

import junit.framework.TestCase;

import java.util.ArrayList;

import static org.mockito.Mockito.mock;

public class HotelTest extends TestCase {

    private Hotel hotel;

    @Override
    protected void setUp() throws Exception {
        hotel = new Hotel(new ArrayList<>());
    }

    public void testGetHotelFloors () {
        assertEquals(0, hotel.getFloorList().size());
    }

    public void testAddHotelFloors () {
        hotel.getFloorList().add(mock(Floor.class));
        hotel.getFloorList().add(mock(Floor.class));
        hotel.getFloorList().add(mock(Floor.class));
        assertEquals(3, hotel.getFloorList().size());
    }

    public void testRemoveFloors() {
        hotel.getFloorList().add(mock(Floor.class));
        hotel.getFloorList().add(mock(Floor.class));
        hotel.getFloorList().remove(0);
        assertEquals(1, hotel.getFloorList().size());
    }

}