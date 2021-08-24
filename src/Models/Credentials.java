package Models;

public class Credentials {

    private String employeeId;
    private boolean isAdmin;



    public Credentials(String employeeId, boolean isAdmin) {
        this.employeeId = employeeId;
        this.isAdmin = isAdmin;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

}
