/* Author: Matt McDonald
Date: April 23rd 2021
Instructor: Dr. Helsing
Description: This program houses the field of mounds of mushrooms*/

import javax.sound.sampled.Line;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Field
{
    // Class vars
    private Mound [] [] arr;
    private int day;
    private int maxMushrooms = 0;
    private int maxMushroomsDay = 0;
    private int totalMushrooms;
    private int mushroomLifespan;
    private int columns = 0;
    private int rows = 0;

    // Method to get the totalMushrooms currently in the field
    public int getTotalMushrooms()
    {
        return totalMushrooms;
    }

    // Method to get the day
    public int getDay()
    {
        return day;
    }

    // Method to initialize the field
    public void initialize() throws FileNotFoundException
    {
        // Variables
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter the name of the field file");
        String fileName = scan.nextLine();
        File myFile = new File(fileName);
        ArrayList<Mound> tempArr = new ArrayList<Mound>();

        while (!myFile.exists())
        {
            System.out.println(fileName);
            System.out.println("That file doesn't exist, please enter the name of the field file");
            fileName = scan.next();
            myFile = new File(fileName);
        }

        // Scanner obj to read file
        Scanner fileReader = new Scanner(myFile);

        // Code to read the first few lines of lifespan and first spore location
        // Very inefficent, probably can be done much better, but it was hard to naviagte the second line properly
        // First line:
        String line = fileReader.nextLine();
        Scanner lineReader = new Scanner(line);
        lineReader.useDelimiter("=");
        lineReader.next();
        mushroomLifespan = lineReader.nextInt();
        // Second line
        line = fileReader.nextLine();
        lineReader = new Scanner(line);
        lineReader.useDelimiter("=");
        lineReader.next();
        String x = lineReader.next();
        lineReader = new Scanner(x);
        lineReader.useDelimiter(",");
        int sporeX = lineReader.nextInt();
        int sporeY = lineReader.nextInt();

        // While to navigate the overall text and assign values to the temp array list
        // also row and column are incremented to get the dimensions of the field
        while(fileReader.hasNextLine())
        {
            columns = 0;
            rows ++;
            line = fileReader.nextLine();
            lineReader = new Scanner(line);
            lineReader.useDelimiter(",");
            // While to navigate each line
            while(lineReader.hasNextInt())
            {
                int nextInt = lineReader.nextInt();
                Mound m = new Mound(nextInt);
                tempArr.add(m);
                columns++;
            }
        }

        // Code to initialize the proper 2D array at the right specs, and move the mound objects from tempArr to arr
        arr = new Mound [rows][columns];
        int tempArrCounter = 0;

        for (int row = 0; row < arr.length; row++)
        {
            for (int column = 0; column < arr[row].length; column++)
            {
                arr[row][column] = tempArr.get(tempArrCounter);
                tempArrCounter++;
            }
        }


        // A spore is added to the first mushroom location
        arr[sporeX][sporeY].addSpores(1);

        // The day is initialized as -1 so when the farm method increments it by one it becomes day 0
        day = -1;
    }

    public void farm ()
    {
        // Variables
        int count = 0;
        LinkedList<Mushroom> tempLinkedList;

        // Nested loop iterates all through mounds to age mushrooms and see if any die, and spread spores if they do
        for (int row = 0; row < arr.length; row++)
        {
            for (int column = 0; column < arr[row].length; column++)
            {
                    // LL variable to be able to check the ages of all the mushrooms
                    tempLinkedList = arr[row][column].getLinkedList();

                    for (int i = 0; i < tempLinkedList.size(); i++)
                    {
                        // the boolean checks if a mushroom dies in the ageMushrooms method, if it does spores spread
                        // If the age is at one, it is going to die this loop so spores spread
                        if (tempLinkedList.get(i).getDays() == 1)
                        {
                            // Checking if the mound lies on a boundary
                            // I hate that I had to hard code this it's gross but had to be done
                            if (row == 0 & column == 0)
                            {
                                arr[row+1][column].addSpores(1);
                                arr[row][column+1].addSpores(1);
                            }
                            else if (row == 0 && column == columns-1)
                            {
                                arr[row+1][column].addSpores(1);
                                arr[row][column-1].addSpores(1);
                            }
                            else if (row == rows-1 && column == 0)
                            {
                                arr[row-1][column].addSpores(1);
                                arr[row][column+1].addSpores(1);
                            }
                            else if (row == rows-1 && column == columns-1)
                            {
                                arr[row-1][column].addSpores(1);
                                arr[row][column-1].addSpores(1);
                            }
                            else if (row == 0)
                            {
                                arr[row+1][column].addSpores(1);
                                arr[row][column+1].addSpores(1);
                                arr[row][column-1].addSpores(1);
                            }
                            else if (column == columns-1)
                            {
                                arr[row+1][column].addSpores(1);
                                arr[row-1][column].addSpores(1);
                                arr[row][column-1].addSpores(1);
                            }
                            else if (row == rows-1)
                            {
                                arr[row-1][column].addSpores(1);
                                arr[row][column+1].addSpores(1);
                                arr[row][column-1].addSpores(1);
                            }
                            else if (column == 0)
                            {
                                arr[row-1][column].addSpores(1);
                                arr[row+1][column].addSpores(1);
                                arr[row][column+1].addSpores(1);
                            }
                            else
                            {
                                arr[row-1][column].addSpores(1);
                                arr[row+1][column].addSpores(1);
                                arr[row][column-1].addSpores(1);
                                arr[row][column+1].addSpores(1);
                            }
                        }
                    }
                    arr[row][column].ageMushrooms();
            }
        }

        // Separate loop for the spores germinating into mushrooms, and to count all the mushrooms in the day
        for (int row = 0; row < arr.length; row++)
        {
            for (int column = 0; column < arr[row].length; column++)
            {
                if (arr[row][column].getSpores() > 0);
                {
                    for (int i = 0; i <= arr[row][column].getSpores(); i++)
                    {
                        arr[row][column].growMushroom(mushroomLifespan);
                    }
                    count = count + arr[row][column].getMushrooms();
                }
            }
        }
        // Making totalMushrooms equal count so after every day totalMushrooms equals how many mushrooms there currently
        // are, also tells us when totalMushrooms = 0 we need to stop farming
        totalMushrooms = count;

        // The day goes up by one
        day++;

        // Loop to check for max Amt of mushrooms on this day
        if (count > maxMushrooms)
        {
            maxMushroomsDay = day;
            maxMushrooms = count;
        }

    }

    // Method to print the day and current state of the field
    public void printField()
    {
        //System.out.println(arr[2][2].getMushrooms());
        System.out.println("Day: " + day);
        for (int row = 0; row < arr.length; row++)
        {
            System.out.print("|");
            for (int column = 0; column < arr[row].length; column++)
            {
                System.out.print("M" + arr[row][column].getMushrooms() + "|");

            }
            System.out.println("");
        }
    }

    // Method to summarize results
    public void printSummarization()
    {
        System.out.println("The maximum of mushrooms on a single day was " + maxMushrooms
                + " on day " + maxMushroomsDay);
        System.out.println("The remaining nutrients in the field look like this: ");

        // Loop to iterate through 2D array and print nutrients
        for (int row = 0; row < arr.length; row++)
        {
            System.out.print("|");
            for (int column = 0; column < arr[row].length; column++)
            {
                System.out.print(arr[row][column].getNutrients() + "|");

            }
            System.out.println("");
        }
    }

}