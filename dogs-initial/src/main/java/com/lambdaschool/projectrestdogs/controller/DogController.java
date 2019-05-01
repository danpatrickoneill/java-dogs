package com.lambdaschool.projectrestdogs.controller;

import com.lambdaschool.projectrestdogs.exceptions.ResourceNotFoundException;
import com.lambdaschool.projectrestdogs.model.Dog;
import com.lambdaschool.projectrestdogs.ProjectrestdogsApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/dogs")
public class DogController
{
    private static final Logger logger = LoggerFactory.getLogger(DogController.class);

    // localhost:8080/dogs/dogs
    @GetMapping(value = "/dogs")
    public ResponseEntity<?> getAllDogs()
    {
        logger.info("/dogs/dogs accessed.");
        return new ResponseEntity<>(ProjectrestdogsApplication.ourDogList.dogList, HttpStatus.OK);
    }

    // localhost:8080/dogs/{id}
    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getDogDetail(@PathVariable long id)
    {
        Dog rtnDog;

        if (ProjectrestdogsApplication.ourDogList.findDog(d -> d.getId() == id) == null) {
            throw new ResourceNotFoundException("Dog with ID of " + id + " not found.");
        }

        rtnDog = ProjectrestdogsApplication.ourDogList.findDog(d -> (d.getId() == id));
        logger.info("/dogs/{} accessed.", id);
        return new ResponseEntity<>(rtnDog, HttpStatus.OK);

    }

    // localhost:8080/dogs/breeds/{breed}
    @GetMapping(value = "/breeds/{breed}")
    public ResponseEntity<?> getDogBreeds (@PathVariable String breed)
    {
        ArrayList<Dog> rtnDogs = ProjectrestdogsApplication.ourDogList.
                findDogs(d -> d.getBreed().toUpperCase().contains(breed.toUpperCase()));

        if (rtnDogs.size() == 0) {
            throw new ResourceNotFoundException("No dogs of breed " + breed + " found.");
        }

        logger.info("/dogs/breeds/{} accessed.", breed);
        return new ResponseEntity<>(rtnDogs, HttpStatus.OK);
    }
}
