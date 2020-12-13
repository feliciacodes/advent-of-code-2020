package Day_12;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day12 {
    public static void main(String[] args) throws FileNotFoundException {
        File input = new File("./src/Day_12/day12.txt");
        Scanner in = new Scanner(input);
        ArrayList<String> directions = new ArrayList<>();
        while (in.hasNextLine()) {
            directions.add(in.nextLine());
        }
        in.close();
        getFinalPosition(directions);
        getFinalPositionPart2(directions);
    }

    public static void getFinalPosition(ArrayList<String> directions) {
        int eastWest = 0;
        int northSouth = 0;
        int currentDirection = 0;
        for (int i = 0; i < directions.size(); i++) {
            String direction = directions.get(i);
            char action = direction.charAt(0);
            int value = Integer.parseInt(direction.substring(1));

            if (action == 'N') {
                northSouth += value;
            } else if (action == 'S') {
                northSouth -= value;
            } else if (action == 'E') {
                eastWest += value;
            } else if (action == 'W') {
                eastWest -= value;
            } else if (action == 'L') {
                currentDirection = (currentDirection + value) % 360;
            } else if (action == 'R') {
                currentDirection = (currentDirection + (360 - value)) % 360;
            } else if (action == 'F') {
                if (currentDirection == 0) {
                    eastWest += value;
                } else if (currentDirection == 90) {
                    northSouth += value;
                } else if (currentDirection == 180) {
                    eastWest -= value;
                } else if (currentDirection == 270) {
                    northSouth -= value;
                }
            }
        }
        System.out.println(Math.abs(eastWest) + Math.abs(northSouth));
    }

    public static void getFinalPositionPart2(ArrayList<String> directions) {
        int waypointEastWest = 10;
        int waypointNorthSouth = 1;
        int eastWest = 0;
        int northSouth = 0;
        for (int i = 0; i < directions.size(); i++) {
            String direction = directions.get(i);
            char action = direction.charAt(0);
            int value = Integer.parseInt(direction.substring(1));

            if (action == 'N') {
                waypointNorthSouth += value;
            } else if (action == 'S') {
                waypointNorthSouth -= value;
            } else if (action == 'E') {
                waypointEastWest += value;
            } else if (action == 'W') {
                waypointEastWest -= value;
            } else if (action == 'L' || action == 'R') {
                int[] newWayPoint = rotateWaypoint(action, value, waypointEastWest, waypointNorthSouth);
                waypointEastWest = newWayPoint[0];
                waypointNorthSouth = newWayPoint[1];
            }  else if (action == 'F') {
                eastWest += waypointEastWest * value;
                northSouth += waypointNorthSouth * value;
            }
        }
        System.out.println(Math.abs(eastWest) + Math.abs(northSouth));
    }

    private static int[] rotateWaypoint(char direction, int value, int waypointX, int waypointY) {
        int newWaypointX = waypointX;
        int newWaypointY = waypointY;
        if (direction == 'L') {
            if (value == 90) {
                newWaypointX = waypointY * -1;
                newWaypointY = waypointX;
            } else if (value == 180) {
                newWaypointX = waypointX * -1;
                newWaypointY = waypointY * -1;
            } else if (value == 270) {
                newWaypointX = waypointY;
                newWaypointY = waypointX * -1;
            }
        } else {
            if (value == 90) {
                newWaypointX = waypointY;
                newWaypointY = waypointX * -1;
            } else if (value == 180) {
                newWaypointX = waypointX * -1;
                newWaypointY = waypointY * -1;
            } else if (value == 270) {
                newWaypointX = waypointY * -1;
                newWaypointY = waypointX;
            }
        }
        int[] point = {newWaypointX, newWaypointY};
        return point;
    }
}
