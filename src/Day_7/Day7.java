package Day_7;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Day7 {
    private static Map<String, Map<String, Integer>> bagRules;
    private static Map<String, Boolean> bagContainsShinyGoldMap;

    public static void main(String[] args) throws FileNotFoundException {
        File input = new File("./src/Day_7/day7.txt");
        Scanner in = new Scanner(input);
        bagRules = new HashMap<>();
        while (in.hasNextLine()) {
            String line = in.nextLine();
            String outerBag = line.substring(0, line.indexOf(" bags contain "));
            String[] innerBags = line.substring(line.indexOf("contain ") + 8).split(", ");
            Map<String, Integer> innerBagMap = new HashMap<>();

            for (int i = 0; i < innerBags.length; i++) {
                String bag = innerBags[i];
                if (!bag.equals("no other bags.")) {
                    int quantity = Integer.parseInt(bag.substring(0, bag.indexOf(" ")));
                    String type = bag.substring(bag.indexOf(" ") + 1, bag.indexOf(" bag"));
                    innerBagMap.put(type, quantity);
                }

            }
            bagRules.put(outerBag, innerBagMap);
        }


        in.close();

        int count = 0;
        bagContainsShinyGoldMap = new HashMap<>();
        for (Map.Entry<String, Map<String, Integer>> entry : bagRules.entrySet()) {
            boolean contains;
            if (bagContainsShinyGoldMap.containsKey(entry.getKey())) {
                contains = bagContainsShinyGoldMap.get(entry.getKey());

            } else {
                contains = containsShinyGold(entry.getKey());
            }
            if (contains) {
                count++;
            }
        }
        System.out.println(count);

        int numBags = getNumBagsRecursive("shiny gold");
        System.out.println(numBags);
    }

    public static boolean containsShinyGold(String outerBag) {
        if (bagRules.get(outerBag).size() == 0) {
            bagContainsShinyGoldMap.put(outerBag, false);
            return false;
        }
        if (bagContainsShinyGoldMap.containsKey(outerBag)) {
            return bagContainsShinyGoldMap.get(outerBag);
        }

        Map<String, Integer> outerBagMap = bagRules.get(outerBag);
        if (outerBagMap.containsKey("shiny gold")) {
            bagContainsShinyGoldMap.put(outerBag, true);
            return true;
        }
        boolean contains = false;
        for (Map.Entry<String, Integer> entry : outerBagMap.entrySet()) {
            boolean innerBagContains = containsShinyGold(entry.getKey());
            if (innerBagContains) {
                contains = true;
            }
        }
        bagContainsShinyGoldMap.put(outerBag, contains);
        return contains;
    }

    public static int getNumBagsRecursive(String outerBag) {
        if (bagRules.get(outerBag).size() == 0) {
            return 0;
        }

        int count = 0;
        Map<String, Integer> outerBagMap = bagRules.get(outerBag);
        for (Map.Entry<String, Integer> entry : outerBagMap.entrySet()) {
            count += entry.getValue() * getNumBagsRecursive(entry.getKey()) + entry.getValue();
        }
        return count;
    }
}
