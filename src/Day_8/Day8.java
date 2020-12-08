package Day_8;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day8 {
    public static void main(String[] args) throws FileNotFoundException {
        File input = new File("./src/Day_8/day8.txt");
        Scanner in = new Scanner(input);
        ArrayList<String> instructions = new ArrayList<>();
        while (in.hasNextLine()) {
            String line = in.nextLine();
            instructions.add(line);
        }
        in.close();

        System.out.println(runInstructions(instructions)[0]);

        int accumulator = 0;
        for (int i = 0; i < instructions.size(); i++) {
            String instruction = instructions.get(i);
            String action = instruction.substring(0, 3);
            if (action.equals("nop")) {
                instructions.set(i, instruction.replace(action, "jmp"));
            } else if (action.equals("jmp")) {
                instructions.set(i, instruction.replace(action, "nop"));
            }
            int[] result = runInstructions(instructions);
            if (result[1] == 1) {
                accumulator = result[0];
            }
            instructions.set(i, instruction);
        }

        System.out.println(accumulator);
    }

    public static int[] runInstructions(ArrayList<String> instructions) {
        boolean[] visited = new boolean[instructions.size()];
        int index = 0;
        int accumulator = 0;
        while (index < instructions.size()) {
            if (visited[index]) {
                break;
            }
            String instruction = instructions.get(index);
            String action = instruction.substring(0, 3);
            int value = Integer.parseInt(instruction.substring(instruction.indexOf(" ") + 1));
            if (action.equals("nop")) {
                index++;
            } else if (action.equals("acc")) {
                visited[index] = true;
                accumulator += value;
                index++;
            } else if (action.equals("jmp")) {
                index += value;
            }
        }
        int completedNormal;
        if (index == instructions.size()) {
            completedNormal = 1;
        } else {
            completedNormal = 0;
        }
        int[] result = {accumulator, completedNormal};
        return result;
    }



}
