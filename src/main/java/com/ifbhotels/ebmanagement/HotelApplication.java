package com.ifbhotels.ebmanagement;

import com.ifbhotels.ebmanagement.controllers.HotelController;
import com.ifbhotels.ebmanagement.exceptions.InvalidInputException;
import com.ifbhotels.ebmanagement.models.data.Movement;
import com.ifbhotels.ebmanagement.models.structures.Corridor;
import com.ifbhotels.ebmanagement.models.structures.Floor;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class HotelApplication {

    private static final Scanner FILE_SCANNER;

    private static final String PATH = "/Users/nithishkumar/IdeaProjects/IfbHotels/src/InputFile";

    private final HotelController hotelController;

    static {
        try {
            FILE_SCANNER = new Scanner(new File(PATH));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private HotelApplication () {
        hotelController = new HotelController();
    }

    private int floors;

    private int mainCorridorsPerFloor;

    private int subCorridorsPerFloor;

    private void parseSensorInput(String sensorInput)
            throws InvalidInputException, NumberFormatException {
        String[] inputWords = sensorInput.split(" ");
        Movement movement = null;

        switch (inputWords[0]) {
            case "Number" : floors = Integer.parseInt(inputWords[inputWords.length - 1]);
            break;
            case "Main" : mainCorridorsPerFloor = Integer.parseInt(inputWords[inputWords.length - 1]);
            break;
            case "Sub" : subCorridorsPerFloor = Integer.parseInt(inputWords[inputWords.length - 1]);
                hotelController.createHotel(floors, mainCorridorsPerFloor, subCorridorsPerFloor);
                System.out.println(hotelController.getHotel());
            break;
            case "Movement" :
                movement = parseSensorInputToMovement(inputWords);
                turnOnDevicesFor(movement);
            break;
            case "No" :
                movement = parseSensorInputToMovement(inputWords);
                turnOffDevicesFor(movement);
            break;
        }
    }

    private void turnOnDevicesFor(Movement movement) {
        System.out.println(movement);
        try {
            hotelController.turnOnAC(movement);
            hotelController.turnOnLight(movement);
            System.out.println(hotelController.getHotel());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void turnOffDevicesFor(Movement movement) {
        System.out.println(movement);
        hotelController.turnOffAC(movement);
        hotelController.turnOffLight(movement);
        System.out.println(hotelController.getHotel());
    }

    private Movement parseSensorInputToMovement(String[] input) {
        Floor floor = null;
        Corridor corridor = null;
        for (int i = 0; i < input.length; i++) {
            if (input[i].equalsIgnoreCase("floor") )
                floor = hotelController.getFloor(
                        Integer.parseInt(input[i + 1].substring(0, input[i + 1].length() - 1) ) - 1 );
            else if (input[i].equalsIgnoreCase("Sub") )
                corridor = hotelController.getSubCorridor(floor,
                        Integer.parseInt(input[i + 2] ) );
            else if (input[i].equalsIgnoreCase("Main") )
                corridor = hotelController.getMainCorridor(floor,
                        Integer.parseInt(input[i + 2]) );
        }
        return new Movement(floor, corridor);
    }

    public static void main(String[] args) {
        try {
            HotelApplication application = new HotelApplication();
            while (FILE_SCANNER.hasNextLine()) {
                String sensorInput = FILE_SCANNER.nextLine();
                application.parseSensorInput(sensorInput);
            }
        } catch (IllegalArgumentException | NoSuchElementException e) {
            e.printStackTrace();
        }
    }

}
