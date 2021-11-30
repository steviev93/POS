package DAO;

import Models.Clock;

import java.sql.*;
import java.util.Date;

public class ClockDAO {
    private Connection connection;
    private Date PPS;
    private Date PPE;
    java.text.SimpleDateFormat dateFormat =
            new java.text.SimpleDateFormat("yyyy-MM-dd 00:00:00");

    public ClockDAO() throws SQLException {
        DBConnector connector = new DBConnector();
        this.connection = connector.getConnection();
        PPS = getPPS();
        PPE = getPPE();
    }

    public Clock lastClock(String employeeId) {
        String lastClock = "SELECT * FROM TimeSheet WHERE ClockId=" +
                "(SELECT MAX(ClockId) FROM TimeSheet" +
                " WHERE EmployeeId='"+employeeId+"')";
        try {
            Statement lastClockStatement = connection.createStatement();
            ResultSet lastClockResult = lastClockStatement.executeQuery(lastClock);
            if (lastClockResult.next()) {
                if (lastClockResult.getDate("ClockOut") == null)
                    return new Clock(lastClockResult.getDate("ClockIn"),true,lastClockResult.getFloat("WeeklyHours"));
                else
                    return new Clock(lastClockResult.getDate("ClockOut"),false,lastClockResult.getFloat("WeeklyHours"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
            e.getCause();
        }
        return new Clock();

    }

    public boolean clockIn(String employeeId, Clock lastClock) throws SQLException {

        String clockString = "INSERT INTO TimeSheet(ClockIn, WeeklyHours, EmployeeId, PayPeriodStart, PayPeriodEnd) VALUES("
                    + " NOW() " + ", '"
                    + (lastClock.getLastClock() == null || PPE.before(new java.util.Date()) ? 0 : lastClock.getAccumHours()) + "', '"
                    + employeeId + "', '"
                    + PPS + "', '"
                    + PPE + "')";
            Statement insertStatement = connection.createStatement();
            insertStatement.executeUpdate(clockString);
            return true;
    }
    public boolean clockOut(String employeeId, Clock lastClock) throws SQLException {

        String updateString = "UPDATE TimeSheet SET ClockOut = NOW(), " +
                "WeeklyHours = ROUND(WeeklyHours + (TIME_TO_SEC(TIMEDIFF(NOW(), ClockIn))/3600), 3) " +
                "WHERE EmployeeId = '" +
                employeeId + "' ORDER BY ClockId DESC LIMIT 1";
        Statement updateStatement = connection.createStatement();
        updateStatement.executeUpdate(updateString);
        return true;
    }
    public Date getPPS() throws SQLException{

        String prevThurs = "SELECT DATE_ADD(CURDATE(), INTERVAL (12 - IF(DAYOFWEEK(CURDATE())<5, DAYOFWEEK(CURDATE())+7, DAYOFWEEK(CURDATE())))-7 DAY) AS PREVTHURSDAY";
        PreparedStatement prevThursP = connection.prepareStatement(prevThurs);
        ResultSet prevTSet = prevThursP.executeQuery();
        prevTSet.next();
        return prevTSet.getDate("PREVTHURSDAY");
    }
    public Date getPPE() throws SQLException{

        String nextThurs = "SELECT DATE_ADD(CURDATE(), INTERVAL (12 - IF(DAYOFWEEK(CURDATE())<5, DAYOFWEEK(CURDATE())+7, DAYOFWEEK(CURDATE()))) DAY) AS NEXTTHURSDAY";
        PreparedStatement nextThursP = connection.prepareStatement(nextThurs);
        ResultSet nextTSet = nextThursP.executeQuery();
        nextTSet.next();
        return nextTSet.getDate("NEXTTHURSDAY");
    }
}
