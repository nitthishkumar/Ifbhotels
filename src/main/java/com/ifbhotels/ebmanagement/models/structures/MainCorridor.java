package com.ifbhotels.ebmanagement.models.structures;

import com.ifbhotels.ebmanagement.models.electricaldevices.AC;
import com.ifbhotels.ebmanagement.models.electricaldevices.Light;

import java.util.Objects;

public class MainCorridor extends Corridor {

    public MainCorridor (int id, Light light, AC aC) {
        super(id);
        setLight(light);
        setAC(aC);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MainCorridor other = (MainCorridor) o;
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
