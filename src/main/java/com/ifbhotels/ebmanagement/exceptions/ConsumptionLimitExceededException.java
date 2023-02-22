package com.ifbhotels.ebmanagement.exceptions;

public class ConsumptionLimitExceededException extends Exception {

    public ConsumptionLimitExceededException (String message) {
        super(message);
    }

    public ConsumptionLimitExceededException () {}

}
