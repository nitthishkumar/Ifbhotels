package com.ifbhotels.ebmanagement.models.structures;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

public class Hotel {

    @NonNull @Getter @Setter
    private List<Floor> floorList;

    public Hotel (final List<Floor> floorList) {
        this.floorList = floorList;
    }

    @Override
    public String toString() {
        StringBuilder hotelString = new StringBuilder();
        floorList.forEach(hotelString::append);
        return hotelString.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hotel hotel = (Hotel) o;
        return floorList.equals(hotel.floorList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(floorList);
    }
}
