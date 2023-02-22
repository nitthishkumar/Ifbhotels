package com.ifbhotels.ebmanagement.models.structures;

import com.ifbhotels.ebmanagement.models.electricaldevices.AC;
import com.ifbhotels.ebmanagement.models.electricaldevices.Light;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.Objects;

public class MainCorridor implements Corridor {

    @NonNull @Getter
    private final int id;

    @NonNull @Getter @Setter
    private Light light;

    @NonNull @Getter @Setter
    private AC aC;

    public MainCorridor (int id, Light light, AC aC) {
        this.id = id;
        setLight(light);
        setAC(aC);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MainCorridor that = (MainCorridor) o;
        return id == that.id && light.equals(that.light) && aC.equals(that.aC);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, light, aC);
    }

    @Override
    public String toString() {
        return "Main corridor " + getId() + " " + getLight() + " " + getAC();
    }
}
