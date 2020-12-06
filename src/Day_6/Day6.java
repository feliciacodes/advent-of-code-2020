package Day_6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Day6 {
    public static void main(String[] args) throws FileNotFoundException {
        File input = new File("./src/Day_6/day6.txt");
        Scanner in = new Scanner(input);
        ArrayList<Integer> allAnswersAnyoneYes = new ArrayList<>();
        ArrayList<Integer> allAnswersEveryoneYes = new ArrayList<>();

        Map<String, Integer> answer = new HashMap<>();
        int numPeople = 0;
        while(in.hasNextLine()) {
            String line = in.nextLine();
            if (line.equals("")) {
                int finalNumPeople = numPeople;
                int numAllYes = answer.entrySet().stream()
                        .filter(map -> map.getValue() == finalNumPeople)
                        .collect(Collectors.toMap(map -> map.getKey(), map -> map.getValue()))
                        .size();
                allAnswersAnyoneYes.add(answer.size());
                allAnswersEveryoneYes.add(numAllYes);
                answer = new HashMap<>();
                numPeople = 0;
            } else {
                numPeople++;
                char[] questions = line.toCharArray();
                for (int i = 0; i < questions.length; i++) {
                    String question = String.valueOf(questions[i]);
                    if (answer.containsKey(question)) {
                        answer.put(question, answer.get(question) + 1);
                    } else {
                        answer.put(question, 1);
                    }
                }
            }
        }
        int finalNumPeople = numPeople;
        int numAllYes = answer.entrySet().stream()
                .filter(map -> map.getValue() == finalNumPeople)
                .collect(Collectors.toMap(map -> map.getKey(), map -> map.getValue()))
                .size();

        // adds last answer
        allAnswersAnyoneYes.add(answer.size());
        allAnswersEveryoneYes.add(numAllYes);
        in.close();


        int countAnyoneYes = 0;
        int countEveryoneYes = 0;

        for (int i = 0; i < allAnswersEveryoneYes.size(); i++) {
            countAnyoneYes += allAnswersAnyoneYes.get(i);
            countEveryoneYes += allAnswersEveryoneYes.get(i);
        }
        System.out.println(countAnyoneYes);
        System.out.println(countEveryoneYes);
    }
}
