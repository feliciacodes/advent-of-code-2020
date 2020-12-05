package Day_5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Day5 {
    public static void main(String[] args) throws FileNotFoundException {
        File input = new File("./src/Day_5/day5.txt");
        Scanner in = new Scanner(input);
        ArrayList<String> seats = new ArrayList<>();
        ArrayList<Integer> seatIds = new ArrayList<>();
        while (in.hasNextLine()) {
            String line = in.nextLine();
            seats.add(line);
        }
        in.close();
        int highestSeatId = 0;
        int lowestSeatId = 1024; // max potential seat id (127 * 8 + 8)
        for (int i = 0; i < seats.size(); i++) {
            String seat = seats.get(i);
            char[] rowMap = seat.substring(0, 7).toCharArray();
            char[] columnMap = seat.substring(7).toCharArray();

            int row = getRow(0, 127, 0, rowMap);
            int column = getColumn(0, 7, 0, columnMap);

            int seatId = row * 8 + column;
            seatIds.add(seatId);
            if (seatId > highestSeatId) {
                highestSeatId = seatId;
            }
            if (seatId < lowestSeatId) {
                lowestSeatId = seatId;
            }
        }
        System.out.println("Highest Seat ID: " + highestSeatId);

        Collections.sort(seatIds);
        for (int i = 0; i < seatIds.size(); i++) {
            if (i + lowestSeatId != seatIds.get(i)) {
                System.out.println("My Seat ID: " + (seatIds.get(i) - 1));
                break;
            }
        }
    }

    private static int getRow(int startRow, int endRow, int index, char[] rowMap) {
        if (index == 6) {
            if (rowMap[index] == 'F') {
                return startRow;
            } else {
                return endRow;
            }
        }
        if (rowMap[index] == 'F') {
            return getRow(startRow, ((startRow + endRow + 1)/2) - 1, index + 1, rowMap);
        } else {
            return getRow(((startRow + endRow + 1)/2), endRow, index + 1, rowMap);
        }
    }

    private static int getColumn(int startColumn, int endColumn, int index, char[] columnMap) {
        if (index == 2) {
            if (columnMap[index] == 'L') {
                return startColumn;
            } else {
                return endColumn;
            }
        }
        if (columnMap[index] == 'L') {
            return getColumn(startColumn, ((startColumn + endColumn + 1)/2) - 1, index + 1, columnMap);
        } else {
            return getColumn(((startColumn + endColumn + 1)/2), endColumn, index + 1, columnMap);
        }
    }
}
