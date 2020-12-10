package Day_10;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day10 {
    private static ArrayList<Long> voltages;
    private static Map<Long, ArrayList<Long>> voltageToPaths;
    private static Map<Long, Long> numPathsFromVoltage;
    private static long highestVoltage;
    public static void main(String[] args) throws FileNotFoundException {
        File input = new File("./src/Day_10/day10.txt");
        Scanner in = new Scanner(input);
        voltages = new ArrayList<>();
        while (in.hasNextLong()) {
            long volt = in.nextLong();
            voltages.add(volt);
        }
        in.close();

        Collections.sort(voltages);
        highestVoltage = voltages.get(voltages.size() - 1);
        System.out.println(findDifferences());

        voltages.add(0, (long) 0);
        voltageToPaths = new HashMap<>();
        for (int i= 0; i < voltages.size(); i++) {
            ArrayList<Long> paths = new ArrayList<>();
            long currentVoltage = voltages.get(i);
            if (i + 1 < voltages.size()) {
                long nextVoltage = voltages.get(i + 1);
                long difference = nextVoltage - currentVoltage;
                if (isInRange(difference)) {
                    paths.add(nextVoltage);
                }
            }
            if (i + 2 < voltages.size()) {
                long nextVoltage = voltages.get(i + 2);
                long difference = nextVoltage - currentVoltage;
                if (isInRange(difference)) {
                    paths.add(nextVoltage);
                }
            }
            if (i + 3 < voltages.size()) {
                long nextVoltage = voltages.get(i + 3);
                long difference = nextVoltage - currentVoltage;
                if (isInRange(difference)) {
                    paths.add(nextVoltage);
                }
            }
            voltageToPaths.put(currentVoltage, paths);
        }
        numPathsFromVoltage = new HashMap<>();

        System.out.println(findArrangements(0));
    }

    public static long findDifferences() {
        long numOnes = 0;
        long numThrees = 0;
        long currentVoltage = 0;
        for (int i = 0; i < voltages.size(); i++) {
            long nextVoltage = voltages.get(i);
            long diff = nextVoltage - currentVoltage;
            if (diff == 1) {
                numOnes++;
            } else if (diff == 3) {
                numThrees++;
            }
            currentVoltage = nextVoltage;
        }
        numThrees++;
        return numOnes * numThrees;
    }

    public static long findArrangements(long currentVoltage) {
        if (currentVoltage == highestVoltage) {
            return 1;
        }

        ArrayList<Long> paths = voltageToPaths.get(currentVoltage);
        long total = 0;
        for (int i = 0; i < paths.size(); i++) {
            long nextVoltage = paths.get(i);
            if (numPathsFromVoltage.containsKey(nextVoltage)) {
                total += numPathsFromVoltage.get(nextVoltage);
            } else {
                long numPaths = findArrangements(nextVoltage);
                numPathsFromVoltage.put(nextVoltage, numPaths);
                total += numPaths;
            }
        }
        return total;
    }
    private static boolean isInRange(long difference) {
        return difference == 1 || difference == 2 || difference == 3;
    }
}
