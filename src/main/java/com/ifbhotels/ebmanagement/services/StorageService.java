package com.ifbhotels.ebmanagement.services;

import com.ifbhotels.ebmanagement.constants.ElectricalUnitConstants;
import com.ifbhotels.ebmanagement.exceptions.ConsumptionLimitExceededException;
import com.ifbhotels.ebmanagement.models.structures.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

public class StorageService {

    private static StorageService instance;

    @Getter @Setter
    private int totalPowerConsumed;

    @NonNull @Getter @Setter
    private Hotel hotel;

    private StorageService() {}

    public static StorageService getInstance() {
        if (instance == null) instance = new StorageService();
        return instance;
    }

    public Floor getFloor (int floorId) {
        return getHotel().getFloorList().get(floorId);
    }

    public MainCorridor getMainCorridor (Floor floor, int mainCorridorId) {
        for (Corridor corridor : floor.getCorridorList())
            if (corridor instanceof MainCorridor && corridor.getId() == mainCorridorId)
                return (MainCorridor) corridor;
        throw new NullPointerException("Main Corridor with ID " + mainCorridorId + " not found");
    }

    public SubCorridor getSubCorridor (Floor floor, int subCorridorId) {
        for (Corridor corridor : floor.getCorridorList())
            if (corridor instanceof SubCorridor && corridor.getId() == subCorridorId)
                return (SubCorridor) corridor;
        throw new NullPointerException("Sub Corridor with ID " + subCorridorId + " not found");
    }

    public boolean isConsumptionWithinLimits () {
        return totalPowerConsumed <= getMaxConsumptionAllowed();
    }

    public int addConsumptionCost (int consumptionCost)
            throws ConsumptionLimitExceededException {
        totalPowerConsumed += consumptionCost;
        if (totalPowerConsumed + consumptionCost > getMaxConsumptionAllowed())
            throw new ConsumptionLimitExceededException();
        return totalPowerConsumed;
    }

    public int reduceConsumptionCost (int reducedCost) {
        totalPowerConsumed -= reducedCost;
        return totalPowerConsumed;
    }

    private int getMaxConsumptionAllowed() {
        return ( getTotalMainCorridors() * ElectricalUnitConstants.MAIN_CORRIDOR_ALLOWANCE)
                + (getTotalSubCorridors() * ElectricalUnitConstants.SUB_CORRIDOR_ALLOWANCE);
    }

    private final int getTotalMainCorridors () {
        int mainCorridors = 0;
        for (Corridor corridor : hotel.getFloorList().get(0).getCorridorList()) {
            if (corridor instanceof MainCorridor) mainCorridors += 1;
        }
        return mainCorridors * hotel.getFloorList().size();
    }

    private final int getTotalSubCorridors () {
        int subCorridors = 0;
        for (Corridor corridor : hotel.getFloorList().get(0).getCorridorList()) {
            if (corridor instanceof SubCorridor) subCorridors += 1;
        }
        return subCorridors * hotel.getFloorList().size();
    }
}