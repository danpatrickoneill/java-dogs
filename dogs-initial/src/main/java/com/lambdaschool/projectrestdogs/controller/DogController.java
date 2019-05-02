package com.lambdaschool.projectrestdogs.controller;

import com.lambdaschool.projectrestdogs.Services.MessageSender;
import com.lambdaschool.projectrestdogs.exceptions.ResourceNotFoundException;
import com.lambdaschool.projectrestdogs.model.Dog;
import com.lambdaschool.projectrestdogs.ProjectrestdogsApplication;
import com.lambdaschool.projectrestdogs.model.MessageDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@RestController
@RequestMapping("/data")
public class DogController
{
    @Autowired
    MessageSender messageSender;

    private static final Logger logger = LoggerFactory.getLogger(DogController.class);
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss 'on' yyyy-MM-dd");

    // localhost:8080/data/dogs
    @GetMapping(value = "/dogs", produces = {"application/json"})
    public ResponseEntity<?> getAllDogs()
    {
        logger.info("/dogs/dogs accessed at " + simpleDateFormat.format(new Date()) + ".");
        // MessageDetail message = new MessageDetail("/dogs/dogs accessed at " + simpleDateFormat.format(new Date()) + ".");
        // messageSender.sendMessage(ProjectrestdogsApplication.QUEUE_NAME_DOGS_ACCESSED, message);
        return new ResponseEntity<>(ProjectrestdogsApplication.ourDogList.dogList, HttpStatus.OK);
    }

    // localhost:8080/data/{id}
    @GetMapping(value = "/{id}", produces = {"application/json"})
    public ResponseEntity<?> getDogDetail(@PathVariable long id)
    {
        Dog rtnDog;

        if (ProjectrestdogsApplication.ourDogList.findDog(d -> d.getId() == id) == null) {
            throw new ResourceNotFoundException("Dog with ID of " + id + " not found.");
        }

        rtnDog = ProjectrestdogsApplication.ourDogList.findDog(d -> (d.getId() == id));
        logger.info("/dogs/{} accessed at " + simpleDateFormat.format(new Date()) + ".", id);
        return new ResponseEntity<>(rtnDog, HttpStatus.OK);

    }

    // localhost:8080/data/breeds/{breed}
    @GetMapping(value = "/breeds/{breed}", produces = {"application/json"})
    public ResponseEntity<?> getDogBreeds (@PathVariable String breed)
    {
        ArrayList<Dog> rtnDogs = ProjectrestdogsApplication.ourDogList.
                findDogs(d -> d.getBreed().toUpperCase().contains(breed.toUpperCase()));

        if (rtnDogs.size() == 0) {
            throw new ResourceNotFoundException("No dogs of breed " + breed + " found.");
        }

        logger.info("/dogs/breeds/{} accessed at " + simpleDateFormat.format(new Date()) + ".", breed);
        // MessageDetail message = new MessageDetail("/dogs/breeds accessed at " + simpleDateFormat.format(new Date()) + ".");
        // messageSender.sendMessage(ProjectrestdogsApplication.QUEUE_NAME_BREEDS_ACCESSED, message);
        return new ResponseEntity<>(rtnDogs, HttpStatus.OK);
    }

    // localhost:8080/data/dogtable
    @GetMapping(value = "/dogtable")
    public ModelAndView displayDogTable() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Dogs");
        mav.addObject("dogList", ProjectrestdogsApplication.ourDogList.dogList);

        return mav;
    }
}
