package com.lambdaschool.projectrestdogs.Services;

import com.lambdaschool.projectrestdogs.ProjectrestdogsApplication;
import com.lambdaschool.projectrestdogs.model.MessageDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class MessageListenerDogCreated {
    private static final Logger logger = LoggerFactory.getLogger(MessageListenerDogCreated.class);

    @RabbitListener(queues = ProjectrestdogsApplication.QUEUE_NAME_DOG_CREATED)
    public void receiveDogCreatedMessage(MessageDetail msg) {
        logger.info("Received message: {}", msg.toString());
    }

    @RabbitListener(queues = ProjectrestdogsApplication.QUEUE_NAME_DOGS_ACCESSED)
    public void receiveDogsAccessedMessage(MessageDetail msg) {
        logger.info("Received message: {}", msg.toString());
    }

    @RabbitListener(queues = ProjectrestdogsApplication.QUEUE_NAME_BREEDS_ACCESSED)
    public void receiveBreedsAccessedMessage(MessageDetail msg) {
        logger.info("Received message: {}", msg.toString());
    }
}

