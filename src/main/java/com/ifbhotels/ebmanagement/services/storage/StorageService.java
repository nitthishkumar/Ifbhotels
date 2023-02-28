package com.ifbhotels.ebmanagement.services.storage;

import com.ifbhotels.ebmanagement.exceptions.ConsumptionLimitExceededException;
import com.ifbhotels.ebmanagement.models.structures.Floor;
import com.ifbhotels.ebmanagement.models.structures.Hotel;
import com.ifbhotels.ebmanagement.models.structures.MainCorridor;
import com.ifbhotels.ebmanagement.models.structures.SubCorridor;

public interface StorageService {

    Hotel getHotel();

    void setHotel(Hotel hotel);

    int getTotalPowerConsumed();

    void setTotalPowerConsumed(int totalPowerConsumed);

    int addConsumptionCost(int consumptionCost) throws ConsumptionLimitExceededException;

    int reduceConsumptionCost (int reducedCost);

    Floor getFloor(int floorId);

    MainCorridor getMainCorridor(Floor floor, int mainCorridorId);

    SubCorridor getSubCorridor(Floor floor, int subCorridorId);

    int getTotalMainCorridors();

    int getTotalSubCorridors();

    boolean isConsumptionWithinLimits();

    int getMaxConsumptionAllowed();

}
