package com.ifbhotels.ebmanagement.models.structures;

import com.ifbhotels.ebmanagement.models.electricaldevices.AC;
import com.ifbhotels.ebmanagement.models.electricaldevices.Light;

import java.util.Objects;

public class SubCorridor extends Corridor {

    public SubCorridor (int id, Light light, AC aC) {
        super(id);
        setLight(light);
        setAC(aC);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubCorridor other = (SubCorridor) o;
        return this.getId() == other.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Main corridor " + getId() + " " + getLight() + " " + getAC();
    }
}