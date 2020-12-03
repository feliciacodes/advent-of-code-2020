package Day_3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day3 {
    private static ArrayList<String> treeMap;
    private static int numRows;
    private static int numColumns;

    public static void main(String[] args) throws FileNotFoundException {
        File input = new File("./src/Day_3/day3.txt");
        Scanner in = new Scanner(input);
        treeMap = new ArrayList<>();

        while(in.hasNextLine()) {
            String line = in.nextLine();
            treeMap.add(line);
        }
        in.close();

        numRows = treeMap.size();
        numColumns = treeMap.get(0).length();

        System.out.println(countTrees(3, 1));

        System.out.println(
            countTrees(1, 1) * countTrees(3, 1) *
            countTrees(5, 1) * countTrees(7, 1) *
            countTrees(1, 2)
        );
    }

    public static int countTrees(int slopeX, int slopeY) {
        int treeCount = 0;
        int x = 0;
        int y = 0;

        while (y < numRows) {
            if (x + slopeX < numColumns) {
                x += slopeX;
            } else {
                x = (x + slopeX) % numColumns;
            }
            y += slopeY;

            if (y < numRows && treeMap.get(y).charAt(x) == '#') {
                treeCount++;
            }
        }
        return treeCount;
    }
}
