package Models;

import java.util.Date;
import java.util.regex.Pattern;

public class Employee {
    private int EmployeeId;
    private String EmployeeName;
    private String PhoneNumber;
    private String Email;
    private Date EmployedSince;
    private float HourlySalary;
    private int TitleId;


    public Employee(int employeeId, String employeeName, String phoneNumber, String email, Date employedSince, float hourlySalary, int titleId) {
        EmployeeId = employeeId;
        EmployeeName = employeeName;
        PhoneNumber = phoneNumber;
        Email = email;
        EmployedSince = employedSince;
        HourlySalary = hourlySalary;
        TitleId = titleId;
        if (employeeName == null
                || employeeName == ""
                || Pattern.compile("\\d").matcher(employeeName).find())  throw new IllegalArgumentException("Invalid Name");
        if (phoneNumber == null || phoneNumber == "")
            throw new IllegalArgumentException("Invalid Number");
        if (employedSince == null) throw new IllegalArgumentException("Invalid Date");
        if (hourlySalary < 7.25f) throw new IllegalArgumentException("PAY YOUR EMPLOYEES MORE");
    }

    public int getEmployeeId() {
        return EmployeeId;
    }

    public void setEmployeeId(int employeeId) {
        EmployeeId = employeeId;
    }

    public String getEmployeeName() {
        return EmployeeName;
    }

    public void setEmployeeName(String employeeName) {
        EmployeeName = employeeName;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public Date getEmployedSince() {
        return EmployedSince;
    }

    public void setEmployedSince(Date employedSince) {
        EmployedSince = employedSince;
    }

    public float getHourlySalary() {
        return HourlySalary;
    }

    public void setHourlySalary(float hourlySalary) {
        HourlySalary = hourlySalary;
    }

    public int getTitleId() {
        return TitleId;
    }

    public void setTitleId(int titleId) {
        TitleId = titleId;
    }
}
