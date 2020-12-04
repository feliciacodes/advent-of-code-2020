package Day_4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day4 {
    private static final List<String> VALID_EYE_COLORS = List.of("amb", "blu", "brn", "gry", "grn", "hzl", "oth");
    public static void main(String[] args) throws FileNotFoundException {
        File input = new File("./src/Day_4/day4.txt");
        Scanner in = new Scanner(input);
        ArrayList<Map<String, String>> passports = new ArrayList<>();
        Map<String, String> passport = new HashMap<>();
        while (in.hasNextLine()) {
            String line = in.nextLine();
            if (line.equals("")) {
                passports.add(passport);
                passport = new HashMap<>();
            } else {
                String[] fields = line.split(" ");
                for (int i = 0; i < fields.length; i++) {
                    String[] keyValue = fields[i].split(":");
                    passport.put(keyValue[0], keyValue[1]);
                }
            }
        }
        passports.add(passport);
        in.close();
        int numValid = 0;
        for (int i = 0; i < passports.size(); i++) {
            Map<String, String> p = passports.get(i);
            if (p.size() == 8) {
                numValid++;
            } else if (p.size() == 7) {
                if (!p.containsKey("cid")) {
                    numValid++;
                }
            }
        }
        System.out.println(numValid);

        numValid = 0;
        for (int i = 0; i < passports.size(); i++) {
            Map<String, String> p = passports.get(i);
            if (p.size() == 8) {
                if (areAllFieldsValid(p)) {
                    numValid++;
                }
            } else if (p.size() == 7) {
                if (!p.containsKey("cid")) {
                    if (areAllFieldsValid(p)) {
                        numValid++;
                    }
                }
            }
        }

        System.out.println(numValid);
    }

    public static boolean areAllFieldsValid(Map<String, String> passport) {
        for (Map.Entry<String, String> field : passport.entrySet()) {
            String key = field.getKey();
            String value = field.getValue();
            switch(key) {
                case "byr":
                    int year = Integer.parseInt(value);
                    if (year < 1920 || year > 2002) {
                        return false;
                    }
                    break;
                case "iyr":
                    year = Integer.parseInt(value);
                    if (year < 2010 || year > 2020) {
                        return false;
                    }
                    break;
                case "eyr":
                    year = Integer.parseInt(value);
                    if (year < 2020 || year > 2030) {
                        return false;
                    }
                    break;
                case "hgt":
                    String unit = value.substring(value.length() - 2);
                    switch(unit) {
                        case "cm":
                            int height = Integer.parseInt(value.substring(0, value.length() - 2));
                            if (height < 150 || height > 193) {
                                return false;
                            }
                            break;
                        case "in":
                            height = Integer.parseInt(value.substring(0, value.length() - 2));
                            if (height < 59 || height > 76) {
                                return false;
                            }
                            break;
                        default:
                            return false;
                    }
                    break;
                case "hcl":
                    if (value.charAt(0) != '#' || value.length() != 7) {
                        return false;
                    }
                    String colorCode = value.substring(1);
                    if (!isColorCodeValid(colorCode)) {
                        return false;
                    }
                    break;
                case "ecl":
                    if (!VALID_EYE_COLORS.contains(value)) {
                        return false;
                    }
                    break;
                case "pid":
                    if (value.length() != 9) {
                        return false;
                    }
                    break;
            }
        }
        return true;
    }

    private static boolean isColorCodeValid(String colorCode) {
        Pattern p = Pattern.compile("[a-f0-9]");
        Matcher m = p.matcher(colorCode);
        int count = 0;
        while (m.find()) {
            count += 1;
        }
        if (count != colorCode.length()) {
            return false;
        }
        return true;
    }
}
