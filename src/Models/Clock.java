package Models;

import java.util.Date;

public class Clock {

    private Date lastClock;
    private boolean clockin;
    private float accumHours;

    public Clock(Date lastClock, boolean clockin, float accumHours) {
        this.lastClock = lastClock;
        this.clockin = clockin;
        this.accumHours = accumHours;
    }
    public Clock() {
    }

    public Date getLastClock() {
        return lastClock;
    }

    public void setLastClock(Date lastClock) {
        this.lastClock = lastClock;
    }

    public boolean wasClockin() {
        return clockin;
    }

    public void setLastClockin(boolean clockin) {
        this.clockin = clockin;
    }
    public float getAccumHours() {
        return accumHours;
    }

    public void setAccumHours(float accumHours) {
        this.accumHours = accumHours;
    }
}
