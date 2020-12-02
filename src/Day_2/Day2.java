package Day_2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day2 {
    public static void main(String[] args) throws FileNotFoundException {
        File input = new File("./src/Day_2/day2.txt");
        Scanner in = new Scanner(input);
        ArrayList<String> passwords = new ArrayList<>();
        while (in.hasNextLine()) {
            String line = in.nextLine();
            passwords.add(line);
        }

        in.close();
        int numValid = 0;
        for(int i = 0; i < passwords.size(); i++) {
            String[] info = passwords.get(i).split(" ");
            int min = Integer.parseInt(info[0].substring(0, info[0].indexOf("-")));
            int max = Integer.parseInt(info[0].substring(info[0].indexOf("-") + 1));
            String character = info[1].substring(0, info[1].indexOf(":"));
            String password = info[2];

            int numOccurances = password.length() - password.replaceAll(character, "").length();
            if (numOccurances >= min && numOccurances <= max) {
                numValid++;
            }
        }
        System.out.println(numValid);

        numValid = 0;
        for (int i = 0; i < passwords.size(); i++) {
            String[] info = passwords.get(i).split(" ");
            int position1 = Integer.parseInt(info[0].substring(0, info[0].indexOf("-"))) - 1;
            int position2 = Integer.parseInt(info[0].substring(info[0].indexOf("-") + 1)) - 1;
            String character = info[1].substring(0, info[1].indexOf(":"));
            String password = info[2];
            if (String.valueOf(password.charAt(position1)).equals(character) ^ String.valueOf(password.charAt(position2)).equals(character))  {
                numValid++;
            }
        }

        System.out.println(numValid);
    }
}
