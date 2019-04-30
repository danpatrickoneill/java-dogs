package com.lambdaschool.projectrestdogs;

import com.lambdaschool.projectrestdogs.model.Dog;

import java.util.ArrayList;

public class DogList
{
    public ArrayList<Dog> dogList = new ArrayList<Dog>();

    public DogList()
    {
        dogList.add(new Dog("Springer", 50, false));
        dogList.add(new Dog("Bulldog", 50, true));
        dogList.add(new Dog("Collie", 50, false));
        dogList.add(new Dog("Boston Terrier", 35, true));
        dogList.add(new Dog("Corgi", 35, true));
    }

    public Dog findDog(CheckDog tester)
    {
        for (Dog d : dogList)
        {
            if (tester.test(d))
            {
                return d;
            }
        }
        return null;
    }

    public ArrayList<Dog> findDogs(CheckDog tester)
    {
        ArrayList<Dog> tempDogList = new ArrayList<>();

        for (Dog d : dogList)
        {
            if (tester.test(d))
            {
                tempDogList.add(d);
            }
        }

        return tempDogList;
    }
}
