package Utilities;

import java.util.regex.Pattern;

public class Validator {

    public static boolean validateID(String ID) {
        try {
            Integer.parseInt(ID);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean validatePassword(String pass) {
        if (Pattern.compile("\\w{5,12}").matcher(pass).matches())
            return true;
        else
            return false;

    }
//    public static String formatPhone(String number) {
//
//        return fNumber;
//    }
}
