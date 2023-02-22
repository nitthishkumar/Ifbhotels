package com.ifbhotels.ebmanagement.models.data;

import com.ifbhotels.ebmanagement.models.structures.Corridor;
import com.ifbhotels.ebmanagement.models.structures.Floor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.Objects;

public class Movement {

    @NonNull @Getter @Setter
    private Floor floor;

    @NonNull @Getter @Setter
    private Corridor corridor;

    @Override
    public String toString() {
        return "Movement in Floor " + floor.getId() +
                ", " + corridor.getClass().getSimpleName() + " " + getCorridor().getId();
    }

    // TODO: 22/02/23 move to builder pattern 
    public Movement (Floor floor, Corridor corridor) {
        this.floor = floor;
        this.corridor = corridor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movement movement = (Movement) o;
        return floor.equals(movement.floor) && corridor.equals(movement.corridor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(floor, corridor);
    }
}
