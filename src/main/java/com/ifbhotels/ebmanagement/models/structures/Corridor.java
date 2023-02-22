package com.ifbhotels.ebmanagement.models.structures;

import com.ifbhotels.ebmanagement.models.electricaldevices.AC;
import com.ifbhotels.ebmanagement.models.electricaldevices.Light;

public interface Corridor {

    int getId();

    Light getLight();

    void setLight(Light light);

    AC getAC();

    void setAC(AC ac);

}
