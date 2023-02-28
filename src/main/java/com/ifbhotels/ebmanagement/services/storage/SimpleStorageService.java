package com.ifbhotels.ebmanagement.services.storage;

import com.ifbhotels.ebmanagement.constants.Constants;
import com.ifbhotels.ebmanagement.exceptions.ConsumptionLimitExceededException;
import com.ifbhotels.ebmanagement.models.structures.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

public class SimpleStorageService implements StorageService {

    private static SimpleStorageService instance;

    @Getter @Setter
    private int totalPowerConsumed;

    @NonNull @Getter @Setter
    private Hotel hotel;

    private SimpleStorageService() {}

    static StorageService getInstance() {
        if (instance == null) instance = new SimpleStorageService();
        return instance;
    }

    @Override
    public Floor getFloor(int floorId) {
        return getHotel().getFloorList().get(floorId);
    }

    @Override
    public MainCorridor getMainCorridor (Floor floor, int mainCorridorId) {
        for (Corridor corridor : floor.getCorridorList())
            if (corridor instanceof MainCorridor && corridor.getId() == mainCorridorId)
                return (MainCorridor) corridor;
        throw new NullPointerException("Main Corridor with ID " + mainCorridorId + " not found");
    }

    @Override
    public SubCorridor getSubCorridor (Floor floor, int subCorridorId) {
        for (Corridor corridor : floor.getCorridorList())
            if (corridor instanceof SubCorridor && corridor.getId() == subCorridorId)
                return (SubCorridor) corridor;
        throw new NullPointerException("Sub Corridor with ID " + subCorridorId + " not found");
    }

    @Override
    public boolean isConsumptionWithinLimits () {
        return totalPowerConsumed <= getMaxConsumptionAllowed();
    }

    @Override
    public int addConsumptionCost(int consumptionCost)
            throws ConsumptionLimitExceededException {
        totalPowerConsumed += consumptionCost;
        if (totalPowerConsumed + consumptionCost > getMaxConsumptionAllowed())
            throw new ConsumptionLimitExceededException();
        return totalPowerConsumed;
    }

    @Override
    public int reduceConsumptionCost (int reducedCost) {
        totalPowerConsumed -= reducedCost;
        return totalPowerConsumed;
    }


    public int getMaxConsumptionAllowed() {
        return ( getTotalMainCorridors() * Constants.MAIN_CORRIDOR_ALLOWANCE)
                + (getTotalSubCorridors() * Constants.SUB_CORRIDOR_ALLOWANCE);
    }

    @Override
    public int getTotalMainCorridors () {
        int mainCorridors = 0;
        for (Corridor corridor : hotel.getFloorList().get(0).getCorridorList()) {
            if (corridor instanceof MainCorridor) mainCorridors += 1;
        }
        return mainCorridors * hotel.getFloorList().size();
    }

    @Override
    public int getTotalSubCorridors () {
        int subCorridors = 0;
        for (Corridor corridor : hotel.getFloorList().get(0).getCorridorList()) {
            if (corridor instanceof SubCorridor) subCorridors += 1;
        }
        return subCorridors * hotel.getFloorList().size();
    }
}