package Day_1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Day1 {
    public static void main(String[] args) throws FileNotFoundException {
        File input = new File("./src/Day_1/day1.txt");
        Scanner in = new Scanner(input);
        Map<Integer, Integer> map = new HashMap<>();
        while(in.hasNext()) {
            int num = Integer.parseInt(in.next());
            map.put(num, 2020 - num);
        }
        in.close();

        int product = -1;
        for(Map.Entry<Integer, Integer> entry: map.entrySet()) {
            if (map.containsKey(entry.getValue())) {
                product = entry.getKey() * entry.getValue();
            }
        }
        System.out.println(product);

        for(Map.Entry<Integer, Integer> entry: map.entrySet()) {
            for(Map.Entry<Integer, Integer> entry2: map.entrySet()) {
                int diff1 = entry.getValue();
                if (map.containsKey(diff1 - entry2.getKey())) {
                    product = entry.getKey() * (2020 - entry.getKey() - entry2.getKey()) * entry2.getKey();
                }
            }
        }
        System.out.println(product);
    }
}
