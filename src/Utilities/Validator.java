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
            validationLabel.setText("");
        else
            validationLabel.setText("Invalid Password");
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
        if (validated) validationLabel.setText("");
        else validationLabel.setText("Salary should be numbers only");
    }

    public static void validatePhone(TextField PhoneText, Label validationLabel) {
        if (PhoneText.getText().length() >= 11) {
            PhoneText.setText(PhoneText.getText().substring(1));
        }
        if (Pattern.compile("[\\D]").matcher(PhoneText.getText()).find() || PhoneText.getText().length() != 10) {
            if (validationLabel.getText() != "Numbers Only, 11 digits")
                validationLabel.setText("Numbers Only, 11 digits");
            PhoneText.setText(PhoneText.getText().replaceAll("[\\D]", ""));
        } else validationLabel.setText("");

    }

    public static void validateEmail(TextField EmailText, Label validationLabel) {
        if (!Pattern.compile("[\\S]+@[\\S]+.[\\S]$").matcher(EmailText.getText()).matches()) {
            if (!validationLabel.getText().contains("Invalid Email")) {
                validationLabel.setText("Invalid Email");
            }
        }
        else validationLabel.setText("");
    }

}
