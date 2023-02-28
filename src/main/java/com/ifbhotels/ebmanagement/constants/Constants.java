package com.ifbhotels.ebmanagement.constants;

import com.ifbhotels.ebmanagement.enums.DeviceState;
import com.ifbhotels.ebmanagement.models.structures.Corridor;
import com.ifbhotels.ebmanagement.models.structures.MainCorridor;

import java.util.function.Predicate;

public class Constants {

    private Constants() {}

    public static final int LIGHT_CONSUMPTION = 5;

    public static final int AC_CONSUMPTION = 10;

    public static final DeviceState AC_DEFAULT_STATE = DeviceState.ON;

    public static final int MAIN_CORRIDOR_ALLOWANCE = 15;

    public static final int SUB_CORRIDOR_ALLOWANCE = 10;

    public static final Predicate<Corridor> IS_MAIN_CORRIDOR = corridor -> corridor instanceof MainCorridor;

}
