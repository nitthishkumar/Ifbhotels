package com.ifbhotels.ebmanagement.constants;

import com.ifbhotels.ebmanagement.enums.DeviceState;
import com.ifbhotels.ebmanagement.services.storage.SimpleStorageService;

public class Constants {

    private Constants() {}

    public static final int LIGHT_CONSUMPTION = 5;

    public static final int AC_CONSUMPTION = 10;

    public static final DeviceState AC_DEFAULT_STATE = DeviceState.ON;

    public static final int MAIN_CORRIDOR_ALLOWANCE = 15;

    public static final int SUB_CORRIDOR_ALLOWANCE = 10;

    public static final String SIMPLE_STORAGE_TYPE = "SimpleStorageService";

}
