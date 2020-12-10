package Day_9;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day9 {
    public static void main(String[] args) throws FileNotFoundException {
        File input = new File("./src/Day_9/day9.txt");
        Scanner in = new Scanner(input);
        ArrayList<Integer> numbers = new ArrayList<>();

        while (in.hasNextInt()) {
            numbers.add(in.nextInt());
        }
        in.close();

        boolean keepGoing = true;
        int index = 0;
        while (keepGoing) {
            int num = checkForInvalidNum(numbers, index);
            if (num != -1) {
                System.out.println(num);

                ArrayList<Integer> range = findInvalidNumSumRange(numbers, num);
                System.out.println(range.get(0) + range.get(range.size() - 1));
                keepGoing = false;
            }
            index++;
        }
    }

    public static int checkForInvalidNum(ArrayList<Integer> numbers, int index) {
        ArrayList<Integer> preamble = new ArrayList(numbers.subList(index, index + 25));
        int nextNumber = numbers.get(index + 25);
        boolean isValid = false;
        for (int i = 0; i < preamble.size(); i++) {
            int num = preamble.get(i);
            int diff = nextNumber - num;
            if (preamble.contains(diff) && diff != num) {
                isValid = true;
                break;
            }
        }
        if (!isValid) {
            return nextNumber;
        }
        return -1;
    }

    public static ArrayList<Integer> findInvalidNumSumRange(ArrayList<Integer> numbers, int invalidNum) {
        for (int i = 0; i < numbers.size() - 1; i++) {
            int sum = numbers.get(i);
            int index = i + 1;
            while (sum < invalidNum) {
                sum += numbers.get(index);
                index++;
            }
            if (sum == invalidNum) {
                ArrayList<Integer> range = new ArrayList<>(numbers.subList(i, index));
                Collections.sort(range);
                return range;
            }
        }
        return new ArrayList<>();
    }

}
