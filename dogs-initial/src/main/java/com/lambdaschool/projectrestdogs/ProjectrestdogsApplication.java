package com.lambdaschool.projectrestdogs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjectrestdogsApplication
{

    public static DogList ourDogList;
    public static void main(String[] args)
    {
        ourDogList = new DogList();
        SpringApplication.run(ProjectrestdogsApplication.class, args);
    }

}

