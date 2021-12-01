package Utilities;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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

    public static void validatePassword(String pass, Label validationLabel) {
        if (Pattern.compile("\\w{5,12}").matcher(pass).matches())
            validationLabel.setText(
                    validationLabel.getText().replaceAll("(\nInvalid Password)|(Invalid Password)", "")
            );
        else
            validationLabel.setText(
                    validationLabel.getText() == ""? "Invalid Password" : validationLabel.getText() + "\nInvalid Password");

    }
    public static void validateSalary(TextField SalaryText, Label validationLabel) {
        boolean validated;
        try {
            Float.parseFloat(SalaryText.getText());
            validated = true;
        } catch (Exception e) {
            validated = false;
            SalaryText.clear();
        }
        if (validated) validationLabel.setText(validationLabel.getText().replaceAll("(\nSalary should be numbers only)|(Salary should be numbers only)", ""));
        else validationLabel.setText(
                validationLabel.getText() == ""? "Salary should be numbers only" : validationLabel.getText() + "\nSalary should be numbers only");
    }
    public static void validatePhone(TextField PhoneText, Label validationLabel) {

        if (Pattern.compile("[\\D]").matcher(PhoneText.getText()).find()) {
            validationLabel.setText(
                    validationLabel.getText() == ""? "Phone number should be numbers only" : validationLabel.getText() + "\nPhone number should be numbers only");
            PhoneText.setText(Pattern.compile("[\\D]").matcher(PhoneText.getText()).replaceAll(""));
        } else validationLabel.setText(validationLabel.getText().replaceAll("(\nPhone number should be numbers only)|(Phone number should be numbers only)", ""));
        if (PhoneText.getText().length() >= 11) {
            PhoneText.setText(PhoneText.getText().substring(1));
        }

    }
    public static void validateEmail(TextField EmailText, Label validationLabel) {
        if (!Pattern.compile("[\\S^@]+@[\\S^@]+.[com|net|org]$").matcher(EmailText.getText()).matches()) {
            if (!validationLabel.getText().contains("Invalid Email")) {
                validationLabel.setText(
                        validationLabel.getText() == ""
                                ? "Invalid Email" : validationLabel.getText() + "\nInvalid Email");
            }
        }

        else validationLabel.setText(validationLabel.getText().replaceAll("\nInvalidEmail|Invalid Email", ""));
        System.out.println(validationLabel.getText());

    }

}
