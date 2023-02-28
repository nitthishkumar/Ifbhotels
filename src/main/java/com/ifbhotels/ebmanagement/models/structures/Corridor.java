package com.ifbhotels.ebmanagement.models.structures;

import com.ifbhotels.ebmanagement.models.electricaldevices.AC;
import com.ifbhotels.ebmanagement.models.electricaldevices.Light;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

public abstract class Corridor {

    @NonNull @Getter
    private final int id;

    @NonNull @Getter @Setter
    private Light light;

    @NonNull @Getter @Setter
    private AC aC;

    protected Corridor(int id) {
        this.id = id;
    }
}
