package com.ifbhotels.ebmanagement.models.structures;

import com.ifbhotels.ebmanagement.constants.Constants;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Floor {

    @NonNull @Getter @Setter
    private final int id;

    @NonNull @Getter @Setter
    private List<Corridor> corridorList;

    // TODO: 22/02/23 move to builder pattern 
    public Floor (int id, List<Corridor> corridorList) {
        this.id = id;
        this.corridorList = corridorList;
    }

    public Floor (FloorBuilder builder) {
        this.id = builder.id;
        this.corridorList = builder.corridorList;
    }

    @Override
    public String toString() {
        StringBuilder floorAsString = new StringBuilder();
        floorAsString.append("\t\tFloor ")
                .append(id)
                .append("\n");

        corridorList.forEach(corridor -> floorAsString
                .append(corridor)
                .append("\n"));

        return floorAsString.toString();
    }

    public List<MainCorridor> getMainCorridorsList() {
        List<MainCorridor> mainCorridorList = new ArrayList<>();
        corridorList.stream()
                .filter(Constants.IS_MAIN_CORRIDOR)
                .forEach(corridor -> mainCorridorList.add((MainCorridor) corridor) );

        return mainCorridorList;
    }

    public List<SubCorridor> getSubCorridorsList() {
        List<SubCorridor> subCorridorList = new ArrayList<>();
        corridorList.stream()
                .filter(Constants.IS_MAIN_CORRIDOR.negate())
                .forEach(corridor -> subCorridorList.add((SubCorridor) corridor));

        return subCorridorList;

    }

    public static class FloorBuilder {

        @NonNull @Getter
        private int id;

        @NonNull @Getter
        private List<Corridor> corridorList;

        public FloorBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public FloorBuilder setCorridorList(List<Corridor> corridorList) {
            this.corridorList = corridorList;
            return this;
        }

        public Floor build () {
            return new Floor(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Floor floor = (Floor) o;
        return id == floor.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
