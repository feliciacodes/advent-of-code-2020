package Day_11;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day11 {
    public static void main(String[] args) throws FileNotFoundException {
        File input = new File("./src/Day_11/day11.txt");
        Scanner in = new Scanner(input);
        ArrayList<ArrayList<String>> seatMap = new ArrayList<>();

        while (in.hasNextLine()) {
            String line = in.nextLine();
            ArrayList<String> row = new ArrayList<>();
            for (int i = 0; i < line.length(); i++) {
                row.add(String.valueOf(line.charAt(i)));
            }
            seatMap.add(row);
        }
        in.close();

        System.out.println(countOccupied(getFinalSeatingMap(seatMap, true)));
        System.out.println(countOccupied(getFinalSeatingMap(seatMap, false)));
    }

    public static  ArrayList<ArrayList<String>> getFinalSeatingMap (ArrayList<ArrayList<String>> seatMap, boolean findDirectlyAdjacent) {
        ArrayList<ArrayList<String>> newMap = new ArrayList<>();
        boolean done = false;
        while (!done) {
            newMap = new ArrayList<>();
            int changedCount = 0;
            for (int i = 0; i < seatMap.size(); i++) {
                ArrayList<String> newRow = new ArrayList<>();
                for (int j = 0; j < seatMap.get(i).size(); j++) {
                    String currentSeat = seatMap.get(i).get(j);
                    String status = findDirectlyAdjacent ? checkVicinityDirectlyAdjacent(seatMap, i, j) : checkVicinityInSight(seatMap, i, j);
                    if (currentSeat.equals("L") && status.equals("empty")) {
                        newRow.add("#");
                        changedCount++;
                    } else if (currentSeat.equals("#") && status.equals("occupied")) {
                        newRow.add("L");
                        changedCount++;
                    } else {
                        newRow.add(currentSeat);
                    }
                }
                newMap.add(newRow);
            }
            seatMap = newMap;
            if (changedCount == 0) {
                done = true;
            }
        }

        return newMap;
    }

    public static String checkVicinityDirectlyAdjacent(ArrayList<ArrayList<String>> seatMap, int x, int y) {
        int count = 0;
        if (x - 1 >= 0 && y - 1 >= 0) {
            String adjacentSeat = seatMap.get(x - 1).get(y-1);
            if (adjacentSeat.equals("#")) {
                count++;
            }
        }
        if (x - 1 >= 0 && y  >= 0) {
            String adjacentSeat = seatMap.get(x - 1).get(y);
            if (adjacentSeat.equals("#")) {
                count++;
            }
        }
        if (x - 1 >= 0 && y + 1 < seatMap.get(0).size()) {
            String adjacentSeat = seatMap.get(x - 1).get(y+1);
            if (adjacentSeat.equals("#")) {
                count++;
            }
        }
        if (x >= 0 && y - 1 >= 0) {
            String adjacentSeat = seatMap.get(x).get(y-1);
            if (adjacentSeat.equals("#")) {
                count++;
            }
        }
        if (x >= 0 && y + 1 < seatMap.get(0).size()) {
            String adjacentSeat = seatMap.get(x).get(y+1);
            if (adjacentSeat.equals("#")) {
                count++;
            }
        }
        if (x + 1 < seatMap.size() && y - 1 >= 0) {
            String adjacentSeat = seatMap.get(x + 1).get(y-1);
            if (adjacentSeat.equals("#")) {
                count++;
            }
        }
        if (x + 1 < seatMap.size() && y >= 0) {
            String adjacentSeat = seatMap.get(x + 1).get(y);
            if (adjacentSeat.equals("#")) {
                count++;
            }
        }
        if (x + 1 < seatMap.size() && y + 1 < seatMap.get(0).size()) {
            String adjacentSeat = seatMap.get(x + 1).get(y +1);
            if (adjacentSeat.equals("#")) {
                count++;
            }
        }
        if (count == 0) {
            return "empty";
        }

        if (count >= 4) {
            return "occupied";
        }
        return "unchanged";
    }

    public static String checkVicinityInSight (ArrayList<ArrayList<String>> seatMap, int x, int y) {
        int count = 0;
        if (x - 1 >= 0 && y - 1 >= 0) {
            if (hasOccupiedInSight(seatMap, x, y, -1, -1)) {
                count++;
            }
        }
        if (x - 1 >= 0 && y  >= 0) {
            if (hasOccupiedInSight(seatMap, x, y, -1, 0)) {
                count++;
            }
        }
        if (x - 1 >= 0 && y + 1 < seatMap.get(0).size()) {
            if (hasOccupiedInSight(seatMap, x, y, -1, 1)) {
                count++;
            }
        }
        if (x >= 0 && y - 1 >= 0) {
            if (hasOccupiedInSight(seatMap, x, y, 0, -1)) {
                count++;
            }
        }
        if (x >= 0 && y + 1 < seatMap.get(0).size()) {
            if (hasOccupiedInSight(seatMap, x, y, 0, 1)) {
                count++;
            }
        }
        if (x + 1 < seatMap.size() && y - 1 >= 0) {
            if (hasOccupiedInSight(seatMap, x, y, 1, -1)) {
                count++;
            }
        }
        if (x + 1 < seatMap.size() && y >= 0) {
            if (hasOccupiedInSight(seatMap, x, y, 1, 0)) {
                count++;
            }
        }
        if (x + 1 < seatMap.size() && y + 1 < seatMap.get(0).size()) {
            if (hasOccupiedInSight(seatMap, x, y, 1, 1)) {
                count++;
            }
        }
        if (count == 0) {
            return "empty";
        }
        if (count >= 5) {
            return "occupied";
        }
        return "unchanged";
    }

    public static boolean hasOccupiedInSight(ArrayList<ArrayList<String>> seatMap, int x, int y, int slopeX, int slopeY) {
        if (x + slopeX < 0 || x + slopeX >= seatMap.size() || y + slopeY < 0 || y + slopeY >= seatMap.get(0).size()) {
            return false;
        }
        String adjacentSeat = seatMap.get(x + slopeX).get(y + slopeY);
        if (adjacentSeat.equals("#")) {
            return true;
        }
        if (adjacentSeat.equals("L")) {
            return false;
        }
        return hasOccupiedInSight(seatMap, x + slopeX, y + slopeY, slopeX, slopeY);
    }

    public static int countOccupied(ArrayList<ArrayList<String>> seatMap) {
        int count = 0;
        for (int i = 0; i < seatMap.size(); i++) {
            for (int j = 0; j < seatMap.get(i).size(); j++) {
                String currentSeat = seatMap.get(i).get(j);
                if (currentSeat.equals("#")) {
                    count++;
                }
            }
        }
        return count;
    }
}
