/* Author: Matt McDonald
Date: Apr 23rd 2021
Instructor: Dr. Helsing
Description: This program  houses the Mound class, filed with a Linked List of Mushroom objects*/

import java.util.LinkedList;

public class Mound
{
    // Class vars
    private int nutrients;
    private int spores;
    private int mushrooms = 0;
    private LinkedList<Mushroom> list = new LinkedList<Mushroom>();

    // Constructor
    public Mound (int nutrients)
    {
        this.setNutrients(nutrients);
    }

    // Getters and setters
    public int getNutrients() {
        return nutrients;
    }

    public void setNutrients(int nutrients) {
        this.nutrients = nutrients;
    }

    public int getSpores() {
        return spores;
    }

    public void setSpores(int spores) {
        this.spores = spores;
    }

    public int getMushrooms () {
        return mushrooms;
    }

    public LinkedList<Mushroom> getLinkedList() {
        return list;
    }

    // Method to add a mushroom to the mound
    public void addSpores(int s)
    {
        spores = spores + s;
    }

    // Method to age the mushroom in a mound and remove them if the days = 0
    public void ageMushrooms()
    {
        for (int i = 0; i < list.size(); i++)
        {
            // The mushroom loses one day of its lifespan
            list.get(i).setDays(list.get(i).getDays() - 1);

            // If a mushroom runs out of days, remove it
            if (list.get(i).getDays() == 0)
            {
                list.remove(list.get(i));
                i--;
                mushrooms = mushrooms - 1;
            }
        }
    }

    // Method to grow Mushrooms from available spores, and for every mushroom grown decrement a nutrient as it is used
    public void growMushroom (int lifespan)
    {
        if (spores > 0)
        {
            for (int i = 0; i <= spores; i++)
            {
                if (nutrients > 0)
                {
                    Mushroom tempMush = new Mushroom(lifespan);
                    list.add(tempMush);
                    mushrooms++;
                    nutrients = nutrients - 1;
                    spores = spores - 1;
                }
            }
        }
    }

}