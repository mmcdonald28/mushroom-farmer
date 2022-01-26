/* Author: Matt McDonald
Date: April 23rd 2021
Instructor: Dr. Helsing
Description: This program houses the mushroom farmer, which implements throughout the growth of the mushroom field and
prints it*/


import java.io.FileNotFoundException;

public class MushroomFarmer
{
    public static void main(String[] args) throws FileNotFoundException
    {
        // Variables
        Field myField = new Field();

        // Initialization method
        myField.initialize();

        // Initial call of farm method for day 0
        myField.farm();
        myField.printField();



        while (myField.getTotalMushrooms() > 0)
        {
            myField.farm();
            myField.printField();
        }

        myField.printSummarization();

    }
}

