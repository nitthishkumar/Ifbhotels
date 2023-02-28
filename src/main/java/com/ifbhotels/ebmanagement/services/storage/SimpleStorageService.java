package com.ifbhotels.ebmanagement.services.storage;

import com.ifbhotels.ebmanagement.constants.Constants;
import com.ifbhotels.ebmanagement.exceptions.ConsumptionLimitExceededException;
import com.ifbhotels.ebmanagement.models.structures.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.NoSuchElementException;

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
        for (Floor floor : hotel.getFloorList())
            if(floor.getId() == floorId) return floor;
        throw new NoSuchElementException("Floor with ID : " + floorId + " does not exist");
    }

    @Override
    public MainCorridor getMainCorridor(Floor floor, int mainCorridorId) {
        for (Corridor corridor : floor.getCorridorList())
            if (isMainCorridor(corridor) && corridor.getId() == mainCorridorId)
                return (MainCorridor) corridor;
        throw new NullPointerException("Main Corridor with ID " + mainCorridorId + " not found");
    }

    @Override
    public SubCorridor getSubCorridor(Floor floor, int subCorridorId) {
        for (Corridor corridor : floor.getCorridorList())
            if (isSubCorridor(corridor) && corridor.getId() == subCorridorId)
                return (SubCorridor) corridor;
        throw new NullPointerException("Sub Corridor with ID " + subCorridorId + " not found");
    }

    @Override
    public boolean isConsumptionWithinLimits() {
        return getTotalPowerConsumed() <= maxConsumptionAllowed();
    }

    @Override
    public int addConsumptionCost(int consumptionCost)
            throws ConsumptionLimitExceededException {
        if (getTotalPowerConsumed() + consumptionCost > maxConsumptionAllowed())
            throw new ConsumptionLimitExceededException("Consumption limit exceeded by " +
                    (getTotalPowerConsumed() + consumptionCost - maxConsumptionAllowed()) + " units");
        setTotalPowerConsumed(getTotalPowerConsumed() + consumptionCost);
        return totalPowerConsumed;
    }

    @Override
    public int reduceConsumptionCost(int reducedCost) {
        setTotalPowerConsumed(Math.max(0, getTotalPowerConsumed() - reducedCost) );
        return totalPowerConsumed;
    }

    @Override
    public int maxConsumptionAllowed() {
        return (totalMainCorridors() * Constants.MAIN_CORRIDOR_ALLOWANCE)
                + (totalSubCorridors() * Constants.SUB_CORRIDOR_ALLOWANCE);
    }

    @Override
    public int totalMainCorridors() {
        return mainCorridorsPerFloor() * totalFloors();
    }

    @Override
    public int totalSubCorridors() {
        return subCorridorsPerFloor() * totalFloors();
    }

    private boolean isMainCorridor(Corridor corridor) {
        return Constants.IS_MAIN_CORRIDOR.test(corridor);
    }

    private boolean isSubCorridor(Corridor corridor) {
        return !isMainCorridor(corridor);
    }

    private int mainCorridorsPerFloor() {
        return getFirstFloor().getMainCorridorsList().size();
    }

    private int subCorridorsPerFloor() {
        return getFirstFloor().getSubCorridorsList().size();
    }

    private Floor getFirstFloor() {
        return hotel.getFloorList().get(0);
    }

    private int totalFloors() {
        return hotel.getFloorList().size();
    }


}