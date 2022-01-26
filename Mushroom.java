/* Author: Matt McDonald
Date: Apr 23rd 2021
Instructor: Dr. Helsing
Description: This program houses the Mushroom class, which initiates Mushroom objects for Mounds*/

public class Mushroom
{
    // Class vars
    private int days;

    // Constructor
    public Mushroom(int days)
    {
        this.setDays(days);
    }

    // Getter / setter
    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }
}

